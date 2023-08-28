package by.academy.project.hotel.services.description;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.feingClients.DescriptionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionClient descriptionClient;

    @Override
    public String getDescription(RoomCategory roomCategory) {
        return descriptionClient.getDescription(roomCategory);
    }
}
