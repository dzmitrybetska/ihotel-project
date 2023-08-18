package by.academy.project.hotel.exceptions;

public class UserNotCreatedException extends RuntimeException {

    private final String message;

    public UserNotCreatedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
