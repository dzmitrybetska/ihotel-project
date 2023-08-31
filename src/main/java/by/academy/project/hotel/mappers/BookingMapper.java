package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(uses = {UserMapper.class, RoomMapper.class}, componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface BookingMapper {

    @Mapping(source = "userId", target = "user", ignore = true)
    @Mapping(source = "idsRooms", target = "rooms", ignore = true)
    Booking mapToBooking(BookingRequest bookingRequest);

    @Mapping(source = "booking.user", target = "userResponse")
    BookingResponse mapToBookingResponse(Booking booking);

    Booking updateBooking(BookingRequest bookingRequest, @MappingTarget Booking booking);
}
