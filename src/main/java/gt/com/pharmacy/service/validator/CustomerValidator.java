package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator extends BaseValidator {

    private final ICustomerRepository iCustomerRepository;

    public void validateOnCreate(CustomerDTO dto) {
        validateRequireFields(dto);
        validateFieldLengths(dto);
        validatePhoneFormat(dto.getPhone());
        validateEmailFormat(dto.getEmail());
        validateUniqueFieldsOnCreate(dto);
    }

    public void validateOnUpdate(CustomerDTO dto, Long idToExclude) {
        validateRequireFields(dto);
        validateFieldLengths(dto);
        validatePhoneFormat(dto.getPhone());
        validateEmailFormat(dto.getEmail());
        validateUniqueFields(dto, idToExclude);
    }

    private void validateUniqueFields(CustomerDTO dto, Long idToExclude) {
        if (dto.getFullName() != null && iCustomerRepository.existsByFullNameAndIdNot(dto.getFullName(), idToExclude)) {
            throw new IllegalArgumentException("Full name already exists");
        }
        if (dto.getNit() != null && iCustomerRepository.existsByNitAndIdNot(dto.getNit(), idToExclude)) {
            throw new IllegalArgumentException("NIT already exists");
        }
        if (dto.getPhone() != null && iCustomerRepository.existsByPhoneAndIdNot(dto.getPhone(), idToExclude)) {
            throw new IllegalArgumentException("Phone already exists");
        }
        if (dto.getEmail() != null && iCustomerRepository.existsByEmailAndIdNot(dto.getEmail(), idToExclude)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private void validateUniqueFieldsOnCreate(CustomerDTO dto) {
        validateUniqueFields(dto, null);
    }

    private void validateRequireFields(CustomerDTO dto) {
        if (isBlank(dto.getFullName())) {
            throw new IllegalArgumentException("Full name is required");
        }
        if (isBlank(dto.getNit())) {
            throw new IllegalArgumentException("NIT is required");
        }
        if (isBlank(dto.getPhone())) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (isBlank(dto.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
    }

    private void validateFieldLengths(CustomerDTO dto) {
        validateLength(dto.getFullName(), 5, 75, "Full name");
        validateLength(dto.getNit(), 7, 12, "NIT");
        validateLength(dto.getPhone(), 8, 8, "Phone");
        if (dto.getEmail() != null) {
            validateLength(dto.getEmail(), 1, 50, "Email");
        }
    }
}
