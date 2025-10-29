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
import java.time.LocalDate;
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
                .date(dto.getDate() != null ? dto.getDate() : LocalDate.now())
                .total(calculateTotal(dto.getItems()))
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

    @Transactional
    public void delete(Long id) {
        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + id));

        @SuppressWarnings("unchecked")
        List<Object[]> items = entityManager.createNativeQuery("SELECT presentation_id, quantity FROM sale_items WHERE sale_id = ?1")
                .setParameter(1, id)
                .getResultList();

        for (Object[] item : items) {
            Long presentationId = ((Number) item[0]).longValue();
            Integer quantity = ((Number) item[1]).intValue();

            PresentationEntity presentation = presentationRepository.findById(presentationId)
                    .orElseThrow(() -> new EntityNotFoundException("Presentation not found with id: " + presentationId));

            presentation.setCurrentStock(presentation.getCurrentStock() + quantity);

            InventoryMovementEntity movement = InventoryMovementEntity.builder()
                    .type(MovementTypeEnum.IN)
                    .movementDate(LocalDateTime.now().toLocalDate())
                    .quantity(quantity)
                    .presentation(presentation)
                    .note("Sale cancellation - ID: " + id)
                    .build();
            entityManager.persist(movement);
        }

        entityManager.createNativeQuery("DELETE FROM sale_items WHERE sale_id = ?1")
                .setParameter(1, id)
                .executeUpdate();

        saleRepository.delete(sale);
    }

    public List<SaleDTO> findSalesByDate(LocalDate date) {
        List<SaleEntity> sales = saleRepository.findByDate(date);
        return sales.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        return saleRepository.sumTotalByDateRange(startDate, endDate);
    }

    private BigDecimal calculateTotal(List<SaleItemDTO> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
