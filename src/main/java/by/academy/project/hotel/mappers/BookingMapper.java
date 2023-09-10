package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(uses = {UserMapper.class, RoomMapper.class}, componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface BookingMapper {

    /**
     * Mapping bookingRequest to entity.
     *
     * @param bookingRequest booking details
     * @return entity
     */
    @Mapping(source = "userId", target = "user", ignore = true)
    @Mapping(source = "idsRooms", target = "rooms", ignore = true)
    Booking mapToBooking(BookingRequest bookingRequest);

    /**
     * Mapping entity to BookingResponse
     *
     * @param booking booking details from the service
     * @return BookingResponse
     */
    @Mapping(source = "user", target = "userResponse", qualifiedByName = "mapToUserResponseForBooking")
    @Mapping(source = "rooms", target = "rooms", qualifiedByName = "mapToRoomResponseForBooking")
    BookingResponse mapToBookingResponse(Booking booking);

    /**
     * Mapping entity to BookingResponse for UserMapper
     *
     * @param booking booking details from the service
     * @return BookingResponse
     */
    @Named("mapToBookingResponseForUser")
    @Mapping(target = "userResponse", ignore = true)
    @Mapping(source = "rooms", target = "rooms", qualifiedByName = "mapToRoomResponseForBooking")
    BookingResponse mapToBookingResponseForUser(Booking booking);

    /**
     * Mapping entity to BookingResponse for RoomMapper
     *
     * @param booking booking details from the service
     * @return BookingResponse
     */
    @Named("mapToBookingResponseForRoom")
    @Mapping(target = "rooms", ignore = true)
    @Mapping(source = "user", target = "userResponse", qualifiedByName = "mapToUserResponseForBooking")
    BookingResponse mapToBookingResponseForRoom(Booking booking);

    /**
     * Updating a booking based on data received from bookingRequest
     *
     * @param bookingRequest data for updating the booking
     * @param booking booking to update
     * @return booking
     */
    Booking updateBooking(BookingRequest bookingRequest, @MappingTarget Booking booking);
}
