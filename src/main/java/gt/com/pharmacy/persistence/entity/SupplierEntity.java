package gt.com.pharmacy.persistence.entity;

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
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 5, max = 75, message = "Name must between 5 and 75 characters.")
    @Column(name = "name", length = 75, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Address cannot be blank.")
    @Size(min = 5, max = 100, message = "Address must between 5 and 100 characters.")
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 8, max = 8, message = "Phone number must be exactly 8 characters long")
    @Pattern(regexp = "^\\d{8}$", message = "Phone number must contain exactly 8 digits (0-9)")
    private String phone;

    @Email(message = "Invalid email format.")
    @Column(name = "email", length = 100)
    private String email;

    @NotNull(message = "Active status cannot be null.")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryMovementEntity> inputs = new ArrayList<>();
}
