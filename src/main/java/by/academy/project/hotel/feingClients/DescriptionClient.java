package by.academy.project.hotel.feingClients;

import by.academy.project.hotel.entities.room.RoomCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Receives descriptions of rooms of the specified category from a third-party API
 */
@FeignClient(name = "description-server")
public interface DescriptionClient {

    /**
     * Get room descriptions based on category
     *
     * @param roomCategory room category
     * @return room description
     */
    @GetMapping
    String getDescription(@RequestParam RoomCategory roomCategory);
}
