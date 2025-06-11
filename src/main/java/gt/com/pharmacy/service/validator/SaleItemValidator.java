package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaleItemValidator extends BaseValidator {

    public void validateOnCreate(SaleItemDTO dto) {
        validateRequiredFields(dto);
        validateQuantity(dto.getQuantity());
        validatePrice(dto.getPrice());
    }

    private void validateRequiredFields(SaleItemDTO dto) {
        if (dto.getPresentation() == null) {
            throw new IllegalArgumentException("Presentation is required");
        }
        if (dto.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity is required");
        }
        if (dto.getPrice() == null) {
            throw new IllegalArgumentException("Price is required");
        }
    }

    private void validateQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}
