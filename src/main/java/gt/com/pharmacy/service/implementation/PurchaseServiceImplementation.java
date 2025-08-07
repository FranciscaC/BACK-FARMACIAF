package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.PurchaseDTO;
import gt.com.pharmacy.persistence.entity.*;
import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import gt.com.pharmacy.persistence.mapper.IPurchaseMapper;
import gt.com.pharmacy.persistence.repository.IPresentationRepository;
import gt.com.pharmacy.persistence.repository.IPurchaseRepository;
import gt.com.pharmacy.persistence.repository.ISupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImplementation extends AbstractCrudDtoServiceImplementation<PurchaseDTO, PurchaseEntity, Long> {

    private final IPurchaseMapper purchaseMapper;
    private final IPurchaseRepository purchaseRepository;
    private final IPresentationRepository presentationRepository;
    private final ISupplierRepository supplierRepository;

    public PurchaseServiceImplementation(
            IPurchaseRepository purchaseRepository,
            IPurchaseMapper purchaseMapper,
            IPresentationRepository presentationRepository,
            ISupplierRepository supplierRepository
    ) {
        super(purchaseRepository);
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.presentationRepository = presentationRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public PurchaseDTO toDTO(PurchaseEntity entity) {
        return purchaseMapper.toDto(entity);
    }

    @Override
    public PurchaseEntity toEntity(PurchaseDTO dto) {
        return purchaseMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public PurchaseDTO save(PurchaseDTO dto) {
        SupplierEntity supplier = supplierRepository.findById(dto.getSupplier().getId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        PurchaseEntity purchase = PurchaseEntity.builder()
                .supplier(supplier)
                .purchaseDate(dto.getPurchaseDate() != null ? dto.getPurchaseDate() : LocalDateTime.now())
                .note(dto.getNote())
                .build();
        List<PurchaseItemEntity> items = dto.getItems().stream()
                .map(item -> {
                    PresentationEntity presentation = presentationRepository.findById(item.getPresentation().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Presentation not found"));
                    presentation.setCurrentStock(presentation.getCurrentStock() + item.getQuantity());
                    InventoryMovementEntity movement = InventoryMovementEntity.builder()
                            .type(MovementTypeEnum.IN)
                            .movementDate(LocalDateTime.now().toLocalDate())
                            .quantity(item.getQuantity())
                            .presentation(presentation)
                            .supplier(supplier)
                            .note("Purchase registered")
                            .build();
                    presentation.getMovements().add(movement);
                    return PurchaseItemEntity.builder()
                            .purchase(purchase)
                            .presentation(presentation)
                            .quantity(item.getQuantity())
                            .unitPrice(item.getUnitPrice())
                            .build();
                })
                .collect(Collectors.toList());
        purchase.setItems(items);
        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.toDto(savedPurchase);
    }

    @Transactional(readOnly = true)
    public List<PurchaseDTO> findByPurchaseDate(LocalDate date) {
        List<PurchaseEntity> purchases = purchaseRepository.findByPurchaseDate(date);
        return purchases.stream()
                .map(purchaseMapper::toDto)
                .collect(Collectors.toList());
    }
}
