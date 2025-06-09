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
public class CustomerDTO {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String fullName;

    @JsonView(Views.Public.class)
    private String nit;

    @JsonView(Views.Public.class)
    private String phone;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Detailed.class)
    private List<SaleDTO> sales;
}
