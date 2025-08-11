package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import gt.com.pharmacy.persistence.entity.*;
import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import gt.com.pharmacy.persistence.mapper.ISaleMapper;
import gt.com.pharmacy.persistence.repository.IPresentationRepository;
import gt.com.pharmacy.persistence.repository.ISaleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImplementation extends AbstractCrudDtoServiceImplementation<SaleDTO, SaleEntity, Long> {

    private final ISaleMapper saleMapper;
    private final ISaleRepository saleRepository;
    private final IPresentationRepository presentationRepository;
    private final EntityManager entityManager;

    public SaleServiceImplementation(
            ISaleRepository saleRepository,
            ISaleMapper saleMapper,
            IPresentationRepository presentationRepository,
            EntityManager entityManager
    ) {
        super(saleRepository);
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.presentationRepository = presentationRepository;
        this.entityManager = entityManager;
    }

    @Override
    public SaleDTO toDTO(SaleEntity entity) {
        return saleMapper.toDto(entity);
    }

    @Override
    public SaleEntity toEntity(SaleDTO dto) {
        return saleMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public SaleDTO save(SaleDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least 1 item");
        }
        SaleEntity sale = SaleEntity.builder()
                .customer(dto.getCustomer())
                .date(dto.getDate() != null ? dto.getDate() : LocalDateTime.now())
                .total(calculateTotal(dto.getItems()))
                .isAvailable(true)
                .build();
        List<SaleItemEntity> items = dto.getItems().stream()
                .map(item -> {
                    PresentationEntity presentation = presentationRepository.findById(item.getPresentationId())
                            .orElseThrow(() -> new EntityNotFoundException("Presentation not found"));
                    if (presentation.getCurrentStock() < item.getQuantity()) {
                        throw new IllegalArgumentException("Insufficient stock for presentation ID: " + item.getPresentationId());
                    }
                    presentation.setCurrentStock(presentation.getCurrentStock() - item.getQuantity());
                    InventoryMovementEntity movement = InventoryMovementEntity.builder()
                            .type(MovementTypeEnum.OUT)
                            .movementDate(LocalDateTime.now().toLocalDate())
                            .quantity(item.getQuantity())
                            .presentation(presentation)
                            .note("Sale registered")
                            .build();
                    entityManager.persist(movement);

                    return SaleItemEntity.builder()
                            .sale(sale)
                            .presentation(presentation)
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .build();
                })
                .collect(Collectors.toList());
        sale.setItems(items);
        SaleEntity savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);
    }

    private BigDecimal calculateTotal(List<SaleItemDTO> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
