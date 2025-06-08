package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementDTO {

    private Long id;

    private MovementTypeEnum type;

    private LocalDate movementDate;

    private Integer quantity;

    private String note;

    private PresentationDTO presentation;

    private SupplierDTO supplier;
}
