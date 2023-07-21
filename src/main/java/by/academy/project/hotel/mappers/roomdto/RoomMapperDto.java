package by.academy.project.hotel.mappers.roomdto;

import by.academy.project.hotel.dto.dataroom.DataRoomForAdmin;
import by.academy.project.hotel.dto.dataroom.DataRoomForGuest;
import by.academy.project.hotel.dto.dataroom.DataRoomForManager;
import by.academy.project.hotel.entities.room.Room;

public abstract class RoomMapperDto {
    public abstract DataRoomForGuest buildDataRoomForGuest(Room room);
    public abstract DataRoomForManager buildDataRoomForManager(Room room);
    public abstract DataRoomForAdmin buildDataRoomForAdmin(Room room);
}
