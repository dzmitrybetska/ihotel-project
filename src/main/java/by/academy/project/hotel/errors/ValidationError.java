package by.academy.project.hotel.errors;

import lombok.Getter;

@Getter
public class ValidationError extends Error {

    private final String fieldName;

    public ValidationError(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
