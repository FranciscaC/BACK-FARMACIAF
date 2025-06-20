package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaleValidator extends BaseValidator {

    public void validateOnCreate(SaleDTO dto) {
        validateRequiredFields(dto);
        validateItems(dto);
        validateDate(dto.getDate());
    }

    private void validateRequiredFields(SaleDTO dto) {
        if (dto.getDate() == null) {
            throw new IllegalArgumentException("Sale date is required");
        }
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least 1 item");
        }
    }

    private void validateItems(SaleDTO dto) {
        if (dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least 1 item");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Sale date cannot be in the future");
        }
    }
}