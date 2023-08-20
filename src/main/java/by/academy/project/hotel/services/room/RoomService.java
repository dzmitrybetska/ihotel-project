package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.RoomRequest;
import by.academy.project.hotel.dto.RoomResponse;
import by.academy.project.hotel.entities.room.RoomCategory;

import java.util.List;

public interface RoomService {

    RoomResponse add(RoomRequest roomRequest);

    List<RoomResponse> read();

    RoomResponse update(Long id, RoomRequest roomRequest);

    boolean delete(Long id);

    RoomResponse findRoomByID(Long id);

    RoomResponse findRoomByNumber(String number);

    List<RoomResponse> findRoomsByRoomCategory(RoomCategory category);
}
