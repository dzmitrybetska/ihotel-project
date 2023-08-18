package by.academy.project.hotel.exceptions;

public class BookingNotCreatedException extends RuntimeException {

    private final String message;

    public BookingNotCreatedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
