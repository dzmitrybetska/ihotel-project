package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;

import java.util.List;

public interface RoomService {

    RoomResponse add(RoomRequest roomRequest);

    List<RoomResponse> read();

    RoomResponse update(Long id, RoomRequest roomRequest);

    void delete(Long id);

    RoomResponse findRoomById(Long id);

    List<Room> findRoomsByIdForBooking(List<Long> list);

    RoomResponse findRoomByNumber(String number);

    List<RoomResponse> findRoomsByRoomCategory(RoomCategory category);
}
