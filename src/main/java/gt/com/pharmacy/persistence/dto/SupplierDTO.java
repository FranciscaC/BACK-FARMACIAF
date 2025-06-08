package gt.com.pharmacy.persistence.dto;

import lombok.*;

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

    private List<InventoryMovementDTO> inputs;
}
