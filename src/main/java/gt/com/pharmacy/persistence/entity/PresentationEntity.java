package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "presentations")
public class PresentationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentation_seq")
    @SequenceGenerator(name = "presentation_seq", sequenceName = "presentation_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 5, max = 255, message = "Description must between 5 and 255 characters.")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @OneToOne(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private PriceEntity price;

    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryMovementEntity> movements = new ArrayList<>();

    @Transient
    public Integer getCurrentStock() {
        if (movements == null || movements.isEmpty()) return 0;

        return movements.stream()
                .mapToInt(m -> switch (m.getType()) {
                    case ENTRADA, DEVOLUCION -> m.getQuantity();
                    case SALIDA, AJUSTE -> -m.getQuantity();
                    default -> 0;
                })
                .sum();
    }
}
