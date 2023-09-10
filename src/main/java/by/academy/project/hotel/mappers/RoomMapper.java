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

    /**
     * Mapping roomRequest to entity.
     *
     * @param roomRequest room details
     * @return entity
     */
    Room mapToRoom(RoomRequest roomRequest);

    /**
     * Mapping entity to RoomResponse
     *
     * @param room room details from the service
     * @return RoomResponse
     */
    @Mapping(source = "bookings", target = "bookings", qualifiedByName = "mapToBookingResponseForRoom")
    RoomResponse mapToRoomResponse(Room room);

    /**
     * Mapping entity to RoomResponse for BookingMapper
     *
     * @param room room details from the service
     * @return RoomResponse
     */
    @Named("mapToRoomResponseForBooking")
    @Mapping(target = "bookings", ignore = true)
    RoomResponse mapToRoomResponseForBooking(Room room);

    /**
     * Updating a room based on data received from roomRequest
     *
     * @param room room to update
     * @param roomRequest data for updating the room
     * @return room
     */
    Room updateRoom(@MappingTarget Room room, RoomRequest roomRequest);
}
