package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemDTO {

    private Long id;

    private PresentationDTO presentation;

    private Integer quantity;

    private BigDecimal price;

    private SaleDTO sale;
}
