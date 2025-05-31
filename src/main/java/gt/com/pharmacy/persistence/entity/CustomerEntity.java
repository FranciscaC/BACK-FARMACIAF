package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "customers"
)
public class CustomerEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_seq"
    )
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    private Long id;

    @NotBlank
            (message = "Full name cannot be blank."
            )
    @Size(
            min = 2,
            max = 75,
            message = "Full name must be between 2-50 characters."
    )
    @Column(
            name = "full_name",
            length = 75,
            nullable = false,
            unique = true
    )
    private String fullName;

    @NotBlank(
            message = "NIT cannot be blank."
    )
    @Size(
            min = 7,
            max = 12,
            message = "NIT must be between 7-12 characters."
    )
    @Pattern(
            regexp = "^[0-9A-Za-z-]+$",
            message = "Invalid NIT format"
    )
    @Column(
            name = "nit",
            length = 12,
            unique = true
    )
    private String nit;

    @NotBlank(
            message = "Phone cannot be blank."
    )
    @Size(
            min = 8,
            max = 8,
            message = "Phone must be exactly 8 digits."
    )
    @Pattern(
            regexp = "^\\d{8}$",
            message = "Phone must contain only digits."
    )
    @Column(
            name = "phone",
            length = 8,
            nullable = false,
            unique = true
    )
    private String phone;

    @Email(
            message = "Invalid email format."
    )
    @Column(
            name = "email",
            length = 50,
            nullable = false,
            unique = true
    )
    private String email;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SaleEntity> sales = new ArrayList<>();
}
