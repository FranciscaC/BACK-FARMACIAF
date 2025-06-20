package gt.com.pharmacy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Full name cannot be blank.")
    @Size(min = 5, max = 75, message = "Full name must be between 2-75 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\\s.,-]+$", message = "Supplier name can only contain letters, numbers, spaces, and basic punctuation")
    @Column(name = "full_name", length = 75, nullable = false, unique = true)
    private String fullName;

    @NotBlank(message = "NIT cannot be blank.")
    @Size(min = 7, max = 12, message = "NIT must be between 7-12 characters.")
    @Pattern(regexp = "^[0-9A-Za-z-]+$", message = "Invalid NIT format")
    @Column(name = "nit", length = 12, nullable = false, unique = true)
    private String nit;

    @NotBlank(message = "Phone cannot be blank.")
    @Size(min = 8, max = 8, message = "Phone must be exactly 8 digits.")
    @Pattern(regexp = "^\\d{8}$", message = "Phone must contain only digits.")
    @Column(name = "phone", length = 8, nullable = false, unique = true)
    private String phone;

    @Email(message = "Invalid email format.")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;
}
