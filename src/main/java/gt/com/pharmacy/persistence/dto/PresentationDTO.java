package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresentationDTO {

    private Long id;

    private String description;

    private ProductDTO product;

    private PriceDTO price;

    private List<InventoryMovementDTO> movements;

    public Integer getCurrentStock() {
        if (movements == null || movements.isEmpty()) return 0;
        return movements.stream()
                .mapToInt(m -> switch (m.getType()) {
                    case INPUT, RETURN -> m.getQuantity();
                    case OUTPUT, ADJUSTMENT -> -m.getQuantity();
                }).sum();
    }
}
