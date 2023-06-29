package by.academy.project.hotel.entities.room;

public enum RoomStatus {
    SERVICED("serviced"), REPAIRED("repaired");

    private final String title;

    RoomStatus(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
