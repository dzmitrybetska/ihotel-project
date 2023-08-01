package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Long, RoomDto, Room> {
    Optional<Room> getByID(Long id);

    Optional<Room> getRoomByNumber(String number);

    List<Room> searchRoomsByCategory(RoomCategory category);
}
