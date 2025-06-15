package gt.com.pharmacy.service.validator;

abstract class BaseValidator {

    protected void validateLength(String value, int min, int max, String fieldName) {
        if (value == null) {
            return;
        }
        int length = value.trim().length();
        if (length < min || length > max) {
            throw new IllegalArgumentException(String.format("%s must be between %d and %d characters", fieldName, min, max));
        }

    }

    protected void validatePhoneFormat(String phone) {
        if (phone == null) {
            return;
        }
        if (!phone.matches("^\\d{8}$")) {
            throw new IllegalArgumentException("Phone must contain exactly 8 digits");
        }
    }

    protected void validateEmailFormat(String email) {
        if (email == null) {
            return;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    protected boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
