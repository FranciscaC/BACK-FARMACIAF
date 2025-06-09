package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.SupplierDTO;
import gt.com.pharmacy.persistence.repository.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierValidator {

    private final ISupplierRepository iSupplierRepository;

    public void validateUniqueFields(SupplierDTO dto, Long idToExclude) {
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

    public void validateUniqueFieldsOnCreate(SupplierDTO dto) {
        validateUniqueFields(dto, null);
    }
}
