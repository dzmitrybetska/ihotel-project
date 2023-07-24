package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.exceptions.RoomNotAddedException;

import java.util.List;

public interface RoomService {
    RoomDto add(RoomDto roomDto) throws RoomNotAddedException;

    List<RoomDto> read();

    RoomDto update(RoomDto roomDto) throws NotFoundRoomException;

    boolean delete(Long id);

    RoomDto getByID(Long id) throws NotFoundRoomException;

    RoomDto getRoomByNumber(String number) throws NotFoundRoomException;

    List<RoomDto> searchRoomsByCategory(RoomCategory category) throws NotFoundRoomException;
}
