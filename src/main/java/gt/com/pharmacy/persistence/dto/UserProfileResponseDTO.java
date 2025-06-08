package gt.com.pharmacy.persistence.dto;

import java.util.List;

public record UserProfileResponseDTO(String username, List<String> roles) {
}
