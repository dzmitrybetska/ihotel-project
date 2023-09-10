package by.academy.project.hotel.services.description;

import by.academy.project.hotel.entities.room.RoomCategory;

/**
 * The service receives descriptions of hotel rooms received from a feign client
 */
public interface DescriptionService {

    /**
     * Get room descriptions based on category from a feign client
     *
     * @param roomCategory room category
     * @return room description
     */
    String getDescription(RoomCategory roomCategory);
}
