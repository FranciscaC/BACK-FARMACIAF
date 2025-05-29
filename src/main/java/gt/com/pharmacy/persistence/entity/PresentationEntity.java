package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "presentations")
public class PresentationEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "presentation_seq"
    )
    @SequenceGenerator(
            name = "presentation_seq",
            sequenceName = "presentation_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @OneToOne(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private PriceEntity price;

    @OneToOne(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private StockEntity stock;
}
