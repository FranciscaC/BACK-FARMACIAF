package gt.com.pharmacy.persistence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.view.Views;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDTO {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String address;

    @JsonView(Views.Public.class)
    private String phone;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Public.class)
    private Boolean isActive;

    @JsonView(Views.Detailed.class)
    private List<InventoryMovementDTO> movements;
}
