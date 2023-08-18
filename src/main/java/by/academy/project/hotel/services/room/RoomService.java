package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.RoomCategory;

import java.util.List;

public interface RoomService {

    RoomDto add(RoomDto roomDto);

    List<RoomDto> read();

    RoomDto update(RoomDto roomDto);

    boolean delete(Long id);

    RoomDto getByID(Long id);

    RoomDto getRoomByNumber(String number);

    List<RoomDto> searchRoomsByCategory(RoomCategory category);
}
