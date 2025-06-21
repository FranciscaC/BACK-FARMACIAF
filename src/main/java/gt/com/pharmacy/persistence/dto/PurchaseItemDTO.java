package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.entity.PresentationEntity;
import gt.com.pharmacy.persistence.model.Price;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemDTO {

    private Long id;

    private PurchaseDTO purchase;

    private PresentationEntity presentation;

    private Integer quantity;

    private Price unitPrice;
}
