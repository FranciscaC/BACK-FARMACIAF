package gt.com.pharmacy.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "inventory_movements",
        indexes = {
                @Index(name = "idx_movement_presentation", columnList = "presentation_id"),
                @Index(name = "idx_movement_supplier", columnList = "supplier_id")
        }
)
public class InventoryMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "Movement type cannot be null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private MovementTypeEnum type;

    @NotNull(message = "Movement date cannot be null.")
    @PastOrPresent(message = "Movement date must be in past or present")
    @Column(name = "movement_date")
    private LocalDate movementDate;

    @NotNull(message = "Quantity cannot be null.")
    @Positive(message = "Quantity must be positive")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Size(max = 255, message = "Note must be less than 255 characters")
    @Column(name = "note")
    private String note;

    @NotNull(message = "Presentation cannot be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", nullable = false)
    @JsonBackReference("presentation-movement")
    private PresentationEntity presentation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonBackReference("supplier-movement")
    private SupplierEntity supplier;
}
