package gt.com.pharmacy.persistence.entity;

import gt.com.pharmacy.persistence.model.Price;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Formula;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Embedded
    private Price currentPrice;

    @Formula("(SELECT COALESCE(SUM(CASE im.type WHEN 'INPUT' THEN im.quantity WHEN 'OUTPUT' THEN -im.quantity END), 0) " +
            "FROM inventory_movements im WHERE im.presentation_id = id)")
    private Integer currentStock;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 5, max = 255, message = "Description must between 5 and 255 characters.")
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistoryEntity> priceHistory = new ArrayList<>();
}
