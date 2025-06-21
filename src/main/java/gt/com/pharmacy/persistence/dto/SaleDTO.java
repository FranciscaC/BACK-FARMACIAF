package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SaleDTO {
    private Long id;
    private LocalDateTime date;
    private BigDecimal total;
    private List<SaleItemDTO> items;
}
