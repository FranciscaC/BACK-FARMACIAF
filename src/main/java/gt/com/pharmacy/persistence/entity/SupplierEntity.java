package gt.com.pharmacy.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suppliers")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
    @SequenceGenerator(name = "supplier_seq", sequenceName = "supplier_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 5, max = 75, message = "Name must between 5 and 75 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\\s.,-]+$", message = "Supplier name can only contain letters, numbers, spaces, and basic punctuation")
    @Column(name = "name", length = 75, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 8, max = 8, message = "Phone number must be exactly 8 characters long")
    @Pattern(regexp = "^\\d{8}$", message = "Phone number must contain exactly 8 digits (0-9)")
    @Column(name = "phone", length = 8, nullable = false, unique = true)
    private String phone;

    @Email(message = "Invalid email format.")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank(message = "Address cannot be blank.")
    @Size(min = 5, max = 100, message = "Address must between 5 and 100 characters.")
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @NotNull(message = "Active status cannot be null.")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("supplier-movement")
    private List<InventoryMovementEntity> movements = new ArrayList<>();
}
