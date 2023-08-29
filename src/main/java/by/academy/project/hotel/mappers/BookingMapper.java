package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final UserMapper userMapper;
    private final RoomMapper roomMapper;

    public Booking mapToBooking(BookingRequest bookingRequest) {
        return Booking.builder()
                .rate(bookingRequest.getRate())
                .arrival(bookingRequest.getArrival())
                .departure(bookingRequest.getDeparture())
                .build();
    }

    public Booking updateBooking(Booking booking, BookingRequest bookingRequest) {
        return booking.setRate(bookingRequest.getRate())
                .setArrival(bookingRequest.getArrival())
                .setDeparture(bookingRequest.getDeparture());
    }

    public BookingResponse mapToBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userResponse(userMapper.mapToUserDtoWithoutBookings(booking.getUser()))
                .rooms(roomMapper.mapToRoomsResponseForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    public List<BookingResponse> mapToBookingsResponse(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingResponse)
                .toList();
    }

    public List<BookingResponse> mapToBookingsResponseForUser(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingResponseForUser)
                .toList();
    }

    public List<BookingResponse> mapToBookingsResponseForRoom(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingResponseForRoom)
                .toList();
    }

    private BookingResponse mapToBookingResponseForUser(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .rooms(roomMapper.mapToRoomsResponseForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    private BookingResponse mapToBookingResponseForRoom(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userResponse(userMapper.mapToUserDtoWithoutBookings(booking.getUser()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }
}
