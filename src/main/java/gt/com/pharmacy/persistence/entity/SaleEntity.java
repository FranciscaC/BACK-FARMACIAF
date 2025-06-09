package gt.com.pharmacy.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "sales",
        indexes = {
                @Index(name = "idx_sale_customer", columnList = "customer_id")
        }
)
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
    @JsonBackReference
    private CustomerEntity customer;

    @NotNull(message = "Items cannot be null.")
    @Size(min = 1, message = "Sale must have at least 1 item.")
    @OneToMany(mappedBy = "sale", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<SaleItemEntity> items = new ArrayList<>();
}
