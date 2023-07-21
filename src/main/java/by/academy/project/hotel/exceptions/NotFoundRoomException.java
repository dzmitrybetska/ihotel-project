package by.academy.project.hotel.exceptions;

public class NotFoundRoomException extends Exception{
    private final String message;
    public NotFoundRoomException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
