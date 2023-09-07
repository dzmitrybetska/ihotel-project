package by.academy.project.hotel.feingClients;

import by.academy.project.hotel.entities.room.RoomCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "description-server")
public interface DescriptionClient {

    @GetMapping
    String getDescription(@RequestParam RoomCategory roomCategory);
}
