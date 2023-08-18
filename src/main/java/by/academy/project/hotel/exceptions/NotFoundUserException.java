package by.academy.project.hotel.exceptions;

public class NotFoundUserException extends RuntimeException {

    private final String message;

    public NotFoundUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
