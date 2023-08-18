package by.academy.project.hotel.entities.booking;

public enum Rate {

    Flexible("Change or cancel up to 5 days before arrival. Free breakfast."),
    ADVANCE_PURCHASE("Free breakfast. No cancellations. Pay now."),
    STAY_LONGER_AND_SAVE("No cancellations. Pay now.");
    private final String description;

    Rate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
