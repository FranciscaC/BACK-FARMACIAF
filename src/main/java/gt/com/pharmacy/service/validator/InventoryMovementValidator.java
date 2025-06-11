package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InventoryMovementValidator extends BaseValidator {

    public void validateOnCreate(InventoryMovementDTO dto) {
        validateRequiredFields(dto);
        validateFieldValues(dto);
        validateNoteLength(dto.getNote());
    }

    public void validateOnUpdate(InventoryMovementDTO dto) {
        validateRequiredFields(dto);
        validateFieldValues(dto);
        validateNoteLength(dto.getNote());
    }

    private void validateRequiredFields(InventoryMovementDTO dto) {
        if (dto.getType() == null) {
            throw new IllegalArgumentException("Movement type is required");
        }
        if (dto.getMovementDate() == null) {
            throw new IllegalArgumentException("Movement date is required");
        }
        if (dto.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity is required");
        }
        if (dto.getPresentation() == null) {
            throw new IllegalArgumentException("Presentation is required");
        }
        if (dto.getSupplier() == null) {
            throw new IllegalArgumentException("Supplier is required");
        }
    }

    private void validateFieldValues(InventoryMovementDTO dto) {
        if (dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (dto.getMovementDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Movement date cannot be in the future");
        }
    }

    private void validateNoteLength(String note) {
        if (note != null) {
            validateLength(note, 0, 255, "Note");
        }
    }
}