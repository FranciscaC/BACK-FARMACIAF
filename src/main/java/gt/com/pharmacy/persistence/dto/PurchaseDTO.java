package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {

    private Long id;

    private SupplierEntity supplier;

    private LocalDateTime purchaseDate;

    private String note;

    private List<PurchaseItemDTO> items = new ArrayList<>();
}
