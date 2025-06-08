package gt.com.pharmacy.persistence.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {

    private Long id;

    private PresentationDTO presentation;

    public Integer getQuantity() {
        return presentation != null ? presentation.getCurrentStock() : 0;
    }
}
