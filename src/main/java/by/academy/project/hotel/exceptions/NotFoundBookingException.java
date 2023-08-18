package by.academy.project.hotel.exceptions;

public class NotFoundBookingException extends RuntimeException {

    private final String message;

    public NotFoundBookingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
