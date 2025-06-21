package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.entity.SaleEntity;
import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import gt.com.pharmacy.persistence.mapper.ISaleMapper;
import gt.com.pharmacy.persistence.repository.ISaleRepository;
import gt.com.pharmacy.service.exception.BusinessException;
import gt.com.pharmacy.service.validator.SaleValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SaleServiceImplementation extends AbstractCrudDtoServiceImplementation<SaleDTO, SaleEntity, Long> {

    private final ISaleMapper mapper;
    private final SaleValidator validator;
    private final InventoryMovementServiceImplementation inventoryService;
    private final ApplicationEventPublisher publisher;

    public SaleServiceImplementation(
            ISaleRepository repository,
            ISaleMapper mapper,
            SaleValidator validator,
            InventoryMovementServiceImplementation inventoryService,
            ApplicationEventPublisher publisher
    ) {
        super(repository);
        this.mapper = mapper;
        this.validator = validator;
        this.inventoryService = inventoryService;
        this.publisher = publisher;
    }

    @Override
    public SaleDTO toDTO(SaleEntity entity) {
        return mapper.toDto(entity);
    }

    @Override
    public SaleEntity toEntity(SaleDTO dto) {
        return mapper.toEntity(dto);
    }

    @Override
    @Transactional
    public SaleDTO save(SaleDTO dto) {
        validator.validateOnCreate(dto);
        SaleEntity sale = toEntity(dto);
        if (sale.getDate() == null) {
            sale.setDate(LocalDateTime.now());
        }
        processSaleItems(sale);
        calculateSaleTotal(sale);
        SaleEntity saved = super.jpaRepository.save(sale);
        publisher.publishEvent(new SaleCompletedEvent(saved));
        return toDTO(sale);
    }

    private void processSaleItems(SaleEntity sale) {
        for (SaleItemEntity item : sale.getItems()) {
            item.setSale(sale);
            validateInventoryForItem(item);
        }
    }

    private void validateInventoryForItem(SaleItemEntity item) {
        int availableQuantity = inventoryService.getCurrentStock(item.getPresentation().getId());
        if (availableQuantity < item.getQuantity()) {
            throw new BusinessException("Insufficient stock for product: " + item.getPresentation().getProduct().getName());
        }
    }

    private void calculateSaleTotal(SaleEntity sale) {
        BigDecimal total = sale.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotal(total);
        if (total.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Sale total must be greater than zero");
        }
    }

    public record SaleCompletedEvent(SaleEntity sale) {
    }
}
