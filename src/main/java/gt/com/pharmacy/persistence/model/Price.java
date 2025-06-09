package gt.com.pharmacy.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Price {

    @DecimalMin(value = "0.01", message = "Pharmacy price must be greater than 0.")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "pharmacy_price", precision = 10, scale = 2)
    private BigDecimal pharmacyPrice;

    @DecimalMin(value = "0.01", message = "Public price must be greater than 0.")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "public_price", precision = 10, scale = 2)
    private BigDecimal publicPrice;
}
