package by.academy.project.hotel.utils.annotations;

import by.academy.project.hotel.utils.validators.PhoneNumberConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "Invalid number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
