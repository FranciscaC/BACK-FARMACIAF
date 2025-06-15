package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.SupplierDTO;
import gt.com.pharmacy.persistence.repository.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierValidator extends BaseValidator {

    private final ISupplierRepository iSupplierRepository;

    public void validateOnCreate(SupplierDTO dto) {
        validateRequireFields(dto);
        validateFieldLengths(dto);
        validateNameFormat(dto.getName());
        validatePhoneFormat(dto.getPhone());
        validateEmailFormat(dto.getEmail());
        validateUniqueFieldsOnCreate(dto);
    }

    public void validateOnUpdate(SupplierDTO dto, Long idToExclude) {
        validateRequireFields(dto);
        validateFieldLengths(dto);
        validateNameFormat(dto.getName());
        validatePhoneFormat(dto.getPhone());
        validateEmailFormat(dto.getEmail());
        validateUniqueFields(dto, idToExclude);
    }

    private void validateUniqueFields(SupplierDTO dto, Long idToExclude) {
        if (dto.getName() != null && iSupplierRepository.existsByNameAndIdNot(dto.getName(), idToExclude)) {
            throw new IllegalArgumentException("Name already exists");
        }
        if (dto.getPhone() != null && iSupplierRepository.existsByPhoneAndIdNot(dto.getPhone(), idToExclude)) {
            throw new IllegalArgumentException("Phone already exists");
        }
        if (dto.getEmail() != null && iSupplierRepository.existsByEmailAndIdNot(dto.getEmail(), idToExclude)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private void validateUniqueFieldsOnCreate(SupplierDTO dto) {
        validateUniqueFields(dto, null);
    }

    private void validateRequireFields(SupplierDTO dto) {
        if (isBlank(dto.getName())) {
            throw new IllegalArgumentException("Name is required");
        }
        if (isBlank(dto.getPhone())) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (isBlank(dto.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (isBlank(dto.getAddress())) {
            throw new IllegalArgumentException("Address is required");
        }
        if (dto.getIsActive() == null) {
            throw new IllegalArgumentException("Active status is required");
        }
    }

    private void validateFieldLengths(SupplierDTO dto) {
        validateLength(dto.getName(), 5, 75, "Name");
        validateLength(dto.getPhone(), 8, 8, "Phone");
        validateLength(dto.getAddress(), 5, 100, "Address");
        if (dto.getEmail() != null) {
            validateLength(dto.getEmail(), 5, 50, "Email");
        }

    }
}
