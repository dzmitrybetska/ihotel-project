package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(uses = BookingMapper.class, componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface RoomMapper {

    Room mapToRoom(RoomRequest roomRequest);

    @Mapping(source = "bookings", target = "bookings", qualifiedByName = "mapToBookingResponseForRoom")
    RoomResponse mapToRoomResponse(Room room);

    @Named("mapToRoomResponseForBooking")
    @Mapping(target = "bookings", ignore = true)
    RoomResponse mapToRoomResponseForBooking(Room room);

    Room updateRoom(@MappingTarget Room room, RoomRequest roomRequest);
}
