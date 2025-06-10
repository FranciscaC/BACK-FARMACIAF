package gt.com.pharmacy.persistence.dto;

import gt.com.pharmacy.persistence.model.Price;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceHistoryDTO {

    private Long id;

    private Price price;

    private LocalDateTime effectiveFrom;

    private LocalDateTime effectiveTo;

    private PresentationDTO presentation;
}
