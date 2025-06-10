package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    private String code;

    private String name;

    private Set<PresentationDTO> presentations;
}
