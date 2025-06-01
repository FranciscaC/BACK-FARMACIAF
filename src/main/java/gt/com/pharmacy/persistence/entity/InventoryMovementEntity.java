package gt.com.pharmacy.persistence.entity;

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
@Table(name = "inventory_movements")
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
    private PresentationEntity presentation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}
