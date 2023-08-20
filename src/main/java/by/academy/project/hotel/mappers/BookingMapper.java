package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.BookingRequest;
import by.academy.project.hotel.dto.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        booking.setRate(bookingRequest.getRate())
                .setArrival(bookingRequest.getArrival())
                .setDeparture(bookingRequest.getDeparture());
        return booking;
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
                .collect(Collectors.toList());
    }

    public List<BookingResponse> buildBookingsResponseForUser(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingResponseForUser)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> buildBookingsResponseForRoom(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingResponseForRoom)
                .collect(Collectors.toList());
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
