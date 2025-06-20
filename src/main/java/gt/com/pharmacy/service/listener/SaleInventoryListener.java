package gt.com.pharmacy.service.listener;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import gt.com.pharmacy.persistence.entity.SaleEntity;
import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import gt.com.pharmacy.persistence.mapper.IPresentationMapper;
import gt.com.pharmacy.service.implementation.InventoryMovementServiceImplementation;
import gt.com.pharmacy.service.implementation.SaleServiceImplementation;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class SaleInventoryListener {

    private final InventoryMovementServiceImplementation inventoryMovementServiceImplementation;
    private final IPresentationMapper iPresentationMapper;

    public SaleInventoryListener(InventoryMovementServiceImplementation inventoryMovementServiceImplementation,
                                 IPresentationMapper iPresentationMapper) {
        this.inventoryMovementServiceImplementation = inventoryMovementServiceImplementation;
        this.iPresentationMapper = iPresentationMapper;
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleSaleCompletedEvent(SaleServiceImplementation.SaleCompletedEvent event) {
        SaleEntity sale = event.sale();
        for (SaleItemEntity item : sale.getItems()) {
            InventoryMovementDTO movement = InventoryMovementDTO.builder()
                    .type(MovementTypeEnum.OUT)
                    .presentation(iPresentationMapper.toDto(item.getPresentation()))
                    .quantity(item.getQuantity())
                    .movementDate(LocalDate.now())
                    .note("Venta #" + sale.getId())
                    .build();
            inventoryMovementServiceImplementation.save(movement);
        }
    }
}
