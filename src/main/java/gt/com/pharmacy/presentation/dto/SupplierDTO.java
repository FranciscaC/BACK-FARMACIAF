package gt.com.pharmacy.presentation.dto;

import gt.com.pharmacy.persistence.entity.InventoryMovementEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Boolean isActive;

    private List<InventoryMovementEntity> inputs = new ArrayList<>();
}
