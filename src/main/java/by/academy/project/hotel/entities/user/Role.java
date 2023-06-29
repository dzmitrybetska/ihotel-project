package by.academy.project.hotel.entities.user;

public enum Role {
    GUEST("guest"), MANAGER ("manager"), ADMIN("admin");
    private final String title;
    Role(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
