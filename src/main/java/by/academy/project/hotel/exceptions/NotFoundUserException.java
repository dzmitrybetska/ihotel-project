package by.academy.project.hotel.exceptions;

public class NotFoundUserException extends Exception {
    private final String message;

    public NotFoundUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
