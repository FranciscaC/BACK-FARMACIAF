package gt.com.pharmacy.persistence.dto;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.model.Customer;
import gt.com.pharmacy.persistence.view.Views;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Public.class)
    private Customer customer;

    @JsonView(Views.Public.class)
    private LocalDate date;

    @JsonView(Views.Public.class)
    private BigDecimal total;

    @JsonView(Views.Public.class)
    private List<SaleItemDTO> items;
}
