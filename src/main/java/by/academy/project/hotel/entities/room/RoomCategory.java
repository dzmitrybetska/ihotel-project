package by.academy.project.hotel.entities.room;

public enum RoomCategory {
    STANDART("standart"), SUPERIOR("superior"), DELUX("delux"), SUITE("suite");

    private final String title;

    RoomCategory(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
