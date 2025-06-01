package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sales")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
    @SequenceGenerator(name = "sale_seq", sequenceName = "sale_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "Sale date cannot be null.")
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime date;

    @NotNull(message = "Customer cannot be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @NotNull(message = "Total cannot be null.")
    @DecimalMin(value = "0.01", message = "Total must be greater than 0.")
    @Digits(integer = 8, fraction = 2, message = "Invalid total format")
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull(message = "Items cannot be null.")
    @Size(min = 1, message = "Sale must have at least 1 item.")
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemEntity> items = new ArrayList<>();
}
