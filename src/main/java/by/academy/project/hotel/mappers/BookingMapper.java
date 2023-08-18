package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.BookingDto;
import by.academy.project.hotel.entities.booking.Booking;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingMapper {

    private static BookingMapper instance;

    private BookingMapper() {
    }

    public static BookingMapper getInstance() {
        if (instance == null) {
            instance = new BookingMapper();
        }
        return instance;
    }

    public Booking buildBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .rate(bookingDto.getRate())
                .arrival(bookingDto.getArrival())
                .departure(bookingDto.getDeparture())
                .build();
    }

    public void updateBooking(Booking booking, BookingDto bookingDto) {
        booking.setRate(bookingDto.getRate())
                .setArrival(bookingDto.getArrival())
                .setDeparture(bookingDto.getDeparture());
    }

    public BookingDto buildBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userDto(UserMapper.getInstance().buildUserDtoWithoutBookings(booking.getUser()))
                .rooms(RoomMapper.getInstance().buildRoomsDtoForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    public List<BookingDto> buildBookingsDto(List<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingDto)
                .collect(Collectors.toList());
    }

    public Set<BookingDto> buildBookingsDtoForUser(Set<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingDtoForUser)
                .collect(Collectors.toSet());
    }

    public Set<BookingDto> buildBookingsDtoForRoom(Set<Booking> bookings) {
        return bookings.stream()
                .map(this::buildBookingDtoForRoom)
                .collect(Collectors.toSet());
    }

    private BookingDto buildBookingDtoForUser(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .rooms(RoomMapper.getInstance().buildRoomsDtoForBooking(booking.getRooms()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }

    private BookingDto buildBookingDtoForRoom(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userDto(UserMapper.getInstance().buildUserDtoWithoutBookings(booking.getUser()))
                .rate(booking.getRate())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .build();
    }
}
