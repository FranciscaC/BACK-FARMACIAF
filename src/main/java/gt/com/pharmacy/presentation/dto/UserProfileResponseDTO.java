package gt.com.pharmacy.presentation.dto;

import java.util.List;

public record UserProfileResponseDTO(
        String username,
        List<String> roles
) {
}
