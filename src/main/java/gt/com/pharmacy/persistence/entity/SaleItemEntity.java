package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "sale_items"
)
public class SaleItemEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_item_seq"
    )
    @SequenceGenerator(
            name = "sale_item_seq",
            sequenceName = "sale_item_sequence",
            allocationSize = 1
    )
    private Long id;

    @NotNull(
            message = "Presentation cannot be null."
    )
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "presentation_id",
            nullable = false
    )
    private PresentationEntity presentation;

    @NotNull(
            message = "Quantity cannot be null."
    )
    @Positive(
            message = "Quantity must be positive"
    )
    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;

    @NotNull(
            message = "Price cannot be null."
    )
    @DecimalMin(
            value = "0.01",
            message = "Price must be greater than 0."
    )
    @Digits(
            integer = 8,
            fraction = 2,
            message = "Invalid price format"
    )
    @Column(
            name = "price",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal price;

    @NotNull(
            message = "Sale reference cannot be null."
    )
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "sale_id",
            nullable = false
    )
    private SaleEntity sale;
}
