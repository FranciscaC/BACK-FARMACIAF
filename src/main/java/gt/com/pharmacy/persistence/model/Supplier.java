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
public class Supplier {

    @NotBlank(message = "Laboratory cannot be blank.")
    @Size(min = 1, max = 255, message = "Laboratory must between 1 and 255 characters.")
    @Column(name = "laboratory")
    @JsonView(Views.Public.class)
    private String laboratory;

    @NotBlank(message = "Phone cannot be blank.")
    @Size(min = 1, max = 15, message = "Phone must between 1 and 15 characters.")
    @Column(name = "phone")
    @JsonView(Views.Public.class)
    private String phone;
}
