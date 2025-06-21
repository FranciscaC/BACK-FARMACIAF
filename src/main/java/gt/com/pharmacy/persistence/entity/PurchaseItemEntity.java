package gt.com.pharmacy.persistence.entity;

import gt.com.pharmacy.persistence.model.Price;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_items")
public class PurchaseItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_item_seq")
    @SequenceGenerator(name = "purchase_item_seq", sequenceName = "purchase_item_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private PurchaseEntity purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", nullable = false)
    private PresentationEntity presentation;

    @NotNull
    @Positive
    @Column(name = "quantity")
    private Integer quantity;

    @Embedded
    private Price unitPrice;
}
