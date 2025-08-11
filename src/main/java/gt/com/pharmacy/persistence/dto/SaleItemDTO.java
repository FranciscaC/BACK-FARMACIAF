package gt.com.pharmacy.persistence.dto;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.view.Views;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemDTO {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private Long presentationId;

    @JsonView(Views.Public.class)
    private Integer quantity;

    @JsonView(Views.Public.class)
    private BigDecimal price;
}
