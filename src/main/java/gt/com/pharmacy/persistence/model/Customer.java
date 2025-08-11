package gt.com.pharmacy.persistence.model;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.view.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Customer {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 1, max = 255, message = "Name must between 1 and 255 characters.")
    @Column(name = "name")
    @JsonView(Views.Public.class)
    private String name;

    @NotBlank(message = "Nit cannot be blank.")
    @Size(min = 1, max = 15, message = "Nit must between 1 and 15 characters.")
    @Column(name = "nit")
    @JsonView(Views.Public.class)
    private String nit;
}
