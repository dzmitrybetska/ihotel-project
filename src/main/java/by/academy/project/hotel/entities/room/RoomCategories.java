package by.academy.project.hotel.entities.room;

public enum RoomCategories {
    STANDART("standart"), SUPERIOR("superior"), DELUX("delux"), SUITE("suite");

    private final String title;

    RoomCategories(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
