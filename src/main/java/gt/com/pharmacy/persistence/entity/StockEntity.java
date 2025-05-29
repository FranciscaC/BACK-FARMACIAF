package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_seq"
    )
    @SequenceGenerator(
            name = "stock_seq",
            sequenceName = "stock_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "presentation_id", nullable = false)
    private PresentationEntity presentation;
}
