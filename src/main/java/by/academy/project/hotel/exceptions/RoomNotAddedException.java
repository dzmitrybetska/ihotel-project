package by.academy.project.hotel.exceptions;

public class RoomNotAddedException extends Exception{
    private final String message;

    public RoomNotAddedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
