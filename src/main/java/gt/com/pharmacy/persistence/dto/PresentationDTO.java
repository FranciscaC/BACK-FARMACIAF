package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.model.Price;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresentationDTO {

    private Long id;

    private ProductDTO product;

    private Price currentPrice;

    private Integer currentStock;

    private String description;

    private List<PriceHistoryDTO> priceHistory;

    private List<InventoryMovementDTO> movements;
}
