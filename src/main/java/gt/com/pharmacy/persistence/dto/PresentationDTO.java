package gt.com.pharmacy.persistence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.model.Price;
import gt.com.pharmacy.persistence.model.Supplier;
import gt.com.pharmacy.persistence.view.Views;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresentationDTO {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private Supplier supplier;

    @JsonView(Views.Public.class)
    private ProductBasicDTO product;

    @JsonView(Views.Public.class)
    private Price currentPrice;

    @JsonView(Views.Public.class)
    private Integer currentStock;

    @JsonView(Views.Public.class)
    private String description;
}
