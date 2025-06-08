package gt.com.pharmacy.persistence.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;

    private String fullName;

    private String nit;

    private String phone;

    private String email;

    private List<SaleDTO> sales;
}
