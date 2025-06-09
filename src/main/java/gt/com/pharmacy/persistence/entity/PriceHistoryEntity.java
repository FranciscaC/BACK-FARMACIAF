package gt.com.pharmacy.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import gt.com.pharmacy.persistence.model.Price;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "prices_history",
        indexes = @Index(name = "idx_price_presentation", columnList = "presentation_id")
)
public class PriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_history_seq")
    @SequenceGenerator(name = "price_history_seq", sequenceName = "price_history_sequence", allocationSize = 1)
    private Long id;

    @Embedded
    private Price price;

    @NotNull(message = "Effective start date cannot be null")
    @Column(name = "effective_from", nullable = false)
    private LocalDateTime effectiveFrom;

    @Column(name = "effective_to")
    private LocalDateTime effectiveTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", nullable = false)
    @JsonBackReference("presentation-price")
    private PresentationEntity presentation;
}
