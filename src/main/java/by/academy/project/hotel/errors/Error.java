package by.academy.project.hotel.errors;


import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public record Error(@JsonInclude(NON_EMPTY) String fieldName, String message) {

    public Error(String message) {
        this("", message);
    }
}

