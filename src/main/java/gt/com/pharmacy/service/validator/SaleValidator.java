package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import org.springframework.stereotype.Component;

@Component
public class SaleValidator extends BaseValidator {

    public void validateOnCreate(SaleDTO dto) {
        validateRequiredFields(dto);
        validateItems(dto);
    }

    private void validateRequiredFields(SaleDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least 1 item");
        }
    }

    private void validateItems(SaleDTO dto) {
        if (dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least 1 item");
        }
    }
}
