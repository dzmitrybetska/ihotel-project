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

    public Booking buildBooking(BookingRequest bookingRequest) {
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

    public BookingResponse buildBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userResponse(userMapper.buildUserDtoWithoutBookings(booking.getUser()))
                .rooms(roomMapper.buildRoomsResponseForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    public List<BookingResponse> buildBookingsResponse(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingResponse)
                .toList();
    }

    public List<BookingResponse> buildBookingsResponseForUser(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingResponseForUser)
                .toList();
    }

    public List<BookingResponse> buildBookingsResponseForRoom(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingResponseForRoom)
                .toList();
    }

    private BookingResponse buildBookingResponseForUser(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .rooms(roomMapper.buildRoomsResponseForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    private BookingResponse buildBookingResponseForRoom(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userResponse(userMapper.buildUserDtoWithoutBookings(booking.getUser()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }
}
