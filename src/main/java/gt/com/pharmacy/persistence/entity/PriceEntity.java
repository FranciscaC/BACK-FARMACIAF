package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "price_seq"
    )
    @SequenceGenerator(
            name = "price_seq",
            sequenceName = "price_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "pharmacy_price", nullable = false)
    private BigDecimal pharmacyPrice;

    @Column(name = "public_price", nullable = false)
    private BigDecimal publicPrice;

    @OneToOne
    @JoinColumn(name = "presentation_id", nullable = false)
    private PresentationEntity presentation;
}
