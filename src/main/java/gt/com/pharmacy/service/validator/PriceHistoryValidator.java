package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.PriceHistoryDTO;
import gt.com.pharmacy.persistence.model.Price;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceHistoryValidator extends BaseValidator {

    public void validateOnCreate(PriceHistoryDTO dto) {
        validateRequiredFields(dto);
        validatePrice(dto.getPrice());
        validateEffectiveDates(dto);
    }

    private void validateRequiredFields(PriceHistoryDTO dto) {
        if (dto.getPrice() == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (dto.getEffectiveFrom() == null) {
            throw new IllegalArgumentException("Effective start date is required");
        }
    }

    private void validatePrice(Price price) {
        if (price.getPharmacyPrice().compareTo(BigDecimal.ZERO) <= 0 ||
                price.getPublicPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Prices must be greater than 0");
        }
    }

    private void validateEffectiveDates(PriceHistoryDTO dto) {
        if (dto.getEffectiveTo() != null &&
                dto.getEffectiveTo().isBefore(dto.getEffectiveFrom())) {
            throw new IllegalArgumentException("Effective end date must be after start date");
        }
    }
}
