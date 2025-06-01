package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_seq")
    @SequenceGenerator(name = "price_seq", sequenceName = "price_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "Pharmacy price cannot be null.")
    @DecimalMin(value = "0.01", message = "Pharmacy price must be greater than 0.")
    @Digits(integer = 6, fraction = 2, message = "Invalid price format. Expected: 999999.99")
    @Column(name = "pharmacy_price", nullable = false)
    private BigDecimal pharmacyPrice;

    @NotNull(message = "Public price cannot be null.")
    @DecimalMin(value = "0.01", message = "Public price must be greater than 0.")
    @Digits(integer = 6, fraction = 2, message = "Invalid price format. Expected: 999999.99")
    @Column(name = "public_price", nullable = false)
    private BigDecimal publicPrice;

    @Column(name = "effective_from", nullable = false)
    private LocalDateTime effectiveFrom;

    @Column(name = "effective_to")
    private LocalDateTime effectiveTo;

    @NotNull(message = "Presentation must be associated.")
    @OneToOne
    @JoinColumn(name = "presentation_id", nullable = false)
    private PresentationEntity presentation;
}
