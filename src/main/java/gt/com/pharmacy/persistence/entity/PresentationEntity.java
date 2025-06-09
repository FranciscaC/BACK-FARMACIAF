package gt.com.pharmacy.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gt.com.pharmacy.persistence.model.Price;
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
@Table(
        name = "presentations",
        indexes = @Index(name = "idx_presentation_product", columnList = "product_id")
)
public class PresentationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentation_seq")
    @SequenceGenerator(name = "presentation_seq", sequenceName = "presentation_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;

    @Embedded
    private Price currentPrice;

    @Column(name = "current_stock", nullable = false)
    private Integer currentStock;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 5, max = 255, message = "Description must between 5 and 255 characters.")
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "presentation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<PriceHistoryEntity> priceHistory = new ArrayList<>();
}
