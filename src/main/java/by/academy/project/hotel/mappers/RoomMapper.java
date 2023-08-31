package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(uses = {BookingMapper.class}, componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface RoomMapper {

    Room mapToRoom(RoomRequest roomRequest);

    RoomResponse mapToRoomResponse(Room room);

    Room updateRoom(@MappingTarget Room room, RoomRequest roomRequest);
}
