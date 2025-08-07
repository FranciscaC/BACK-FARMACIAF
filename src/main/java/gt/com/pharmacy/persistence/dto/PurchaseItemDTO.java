package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.model.Price;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PurchaseItemDTO {

    private Long id;
    private PresentationDTO presentation;
    private Integer quantity;
    private Price unitPrice;
}
