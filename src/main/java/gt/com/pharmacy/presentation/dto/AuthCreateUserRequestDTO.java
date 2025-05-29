package gt.com.pharmacy.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequestDTO(
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRoleRequestDTO authCreateRoleRequestDTO
) {
}
