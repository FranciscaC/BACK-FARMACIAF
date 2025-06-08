package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {

    private Long id;

    private BigDecimal pharmacyPrice;

    private BigDecimal publicPrice;

    private LocalDateTime effectiveFrom;

    private LocalDateTime effectiveTo;

    private PresentationDTO presentation;
}
