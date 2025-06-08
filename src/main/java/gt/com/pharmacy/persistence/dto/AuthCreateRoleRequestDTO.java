package gt.com.pharmacy.persistence.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequestDTO(@Size(max = 3) List<String> roleListName) {
}
