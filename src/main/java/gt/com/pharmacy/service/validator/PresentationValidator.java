package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PresentationValidator extends BaseValidator {

    public void validate(PresentationDTO dto) {
        validateRequiredFields(dto);
        validateFieldLengths(dto);
        validateStock(dto.getCurrentStock());
        validatePrice(dto.getCurrentPrice());
    }

    private void validatePrice(Price price) {
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (price.getPharmacyPrice().compareTo(BigDecimal.ZERO) <= 0 ||
                price.getPublicPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Prices must be greater than 0");
        }
    }

    private void validateStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock must be a positive number");
        }
    }

    private void validateRequiredFields(PresentationDTO dto) {
        if (isBlank(dto.getDescription())) {
            throw new IllegalArgumentException("Description is required");
        }
        if (dto.getCurrentPrice() == null) {
            throw new IllegalArgumentException("Current price is required");
        }
        if (dto.getCurrentStock() == null) {
            throw new IllegalArgumentException("Current stock is required");
        }
    }

    private void validateFieldLengths(PresentationDTO dto) {
        validateLength(dto.getDescription(), 5, 255, "Description");
    }
}
