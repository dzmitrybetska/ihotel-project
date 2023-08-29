package by.academy.project.hotel.utils.validators;

import by.academy.project.hotel.utils.annotations.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\+?[0-9]{10,12}$");
    }
}
