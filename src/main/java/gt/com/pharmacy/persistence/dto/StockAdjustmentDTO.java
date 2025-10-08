package gt.com.pharmacy.persistence.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentDTO {

    @NotNull(message = "Presentation ID cannot be null")
    private Long presentationId;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;
}
