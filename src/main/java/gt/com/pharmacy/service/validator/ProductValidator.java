package gt.com.pharmacy.service.validator;

import gt.com.pharmacy.persistence.dto.ProductDTO;
import gt.com.pharmacy.persistence.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator extends BaseValidator {

    private final IProductRepository iProductRepository;

    public void validateOnCreate(ProductDTO dto) {
        validateRequiredFields(dto);
        validateFieldLengths(dto);
        validateCodeFormat(dto.getCode());
        validateUniqueFieldsOnCreate(dto);
    }

    public void validateOnUpdate(ProductDTO dto, Long idToExclude) {
        validateRequiredFields(dto);
        validateFieldLengths(dto);
        validateCodeFormat(dto.getCode());
        validateUniqueFields(dto, idToExclude);
    }

    private void validateCodeFormat(String code) {
        if (code == null) return;
        if (!code.matches("^[A-Z0-9-]+$")) {
            throw new IllegalArgumentException("Code must contain only uppercase letters, numbers, and hyphens");
        }
    }

    private void validateUniqueFields(ProductDTO dto, Long idToExclude) {
        if (dto.getCode() != null && iProductRepository.existsByCodeAndIdNot(dto.getCode(), idToExclude)) {
            throw new IllegalArgumentException("Product code already exists");
        }
    }

    private void validateUniqueFieldsOnCreate(ProductDTO dto) {
        validateUniqueFields(dto, null);
    }

    private void validateRequiredFields(ProductDTO dto) {
        if (isBlank(dto.getCode())) {
            throw new IllegalArgumentException("Product code is required");
        }
        if (isBlank(dto.getName())) {
            throw new IllegalArgumentException("Product name is required");
        }
    }

    private void validateFieldLengths(ProductDTO dto) {
        validateLength(dto.getCode(), 3, 20, "Product code");
        validateLength(dto.getName(), 3, 100, "Product name");
    }
}
