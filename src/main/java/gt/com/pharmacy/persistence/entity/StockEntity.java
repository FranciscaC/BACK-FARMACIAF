package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    @SequenceGenerator(name = "stock_seq", sequenceName = "stock_sequence", allocationSize = 1)
    private Long id;

    @Transient
    public Integer getQuantity() {
        return presentation != null ? presentation.getCurrentStock() : 0;
    }

    @NotNull(message = "Presentation must be associated.")
    @OneToOne
    @JoinColumn(name = "presentation_id", nullable = false, unique = true)
    private PresentationEntity presentation;
}
