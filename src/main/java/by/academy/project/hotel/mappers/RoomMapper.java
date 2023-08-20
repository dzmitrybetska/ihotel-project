package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.RoomRequest;
import by.academy.project.hotel.dto.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class RoomMapper {

    public Room buildRoom(RoomRequest roomRequest) {
        return Room.builder()
                .number(roomRequest.getNumber())
                .price(roomRequest.getPrice())
                .roomCategory(roomRequest.getRoomCategory())
                .isBooked(roomRequest.getIsBooked())
                .roomStatus(roomRequest.getRoomStatus())
                .build();
    }

    public Room updateRoom(Room room, RoomRequest roomRequest) {
        room.setNumber(roomRequest.getNumber())
                .setPrice(roomRequest.getPrice())
                .setRoomCategory(roomRequest.getRoomCategory())
                .setIsBooked(roomRequest.getIsBooked())
                .setRoomStatus(roomRequest.getRoomStatus());
        return room;
    }

    public RoomResponse buildRoomResponse(String number, BigDecimal price, RoomCategory roomCategory, Boolean isBooked, RoomStatus status) {
        return RoomResponse.builder()
                .number(number)
                .price(price)
                .roomCategory(roomCategory)
                .isBooked(isBooked)
                .roomStatus(status)
                .build();
    }

    public RoomResponse buildRoomResponseForGuest(RoomResponse roomResponse) {
        return RoomResponse.builder()
                .id(roomResponse.getId())
                .number(roomResponse.getNumber())
                .price(roomResponse.getPrice())
                .roomCategory(roomResponse.getRoomCategory())
                .build();
    }

    public RoomResponse buildRoomResponse(Room room, BookingMapper bookingMapper) {
        return room.getBookings() != null ? buildRoomResponseWithBookings(room, bookingMapper) : buildRoomResponseWithoutBookings(room);
    }

    public List<RoomResponse> filterRoomsDto(List<RoomResponse> rooms) {
        return rooms.stream()
                .filter(room -> room.getRoomStatus() == RoomStatus.SERVICED)
                .map(this::buildRoomResponseForGuest)
                .collect(Collectors.toList());
    }

    public List<RoomResponse> buildRoomsResponse(List<Room> rooms, BookingMapper bookingMapper) {
        return rooms.stream()
                .map(room -> buildRoomResponse(room, bookingMapper))
                .collect(Collectors.toList());
    }

    public RoomResponse buildRoomResponseWithoutBookings(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .build();
    }

    public Room buildRoomForBooking(RoomResponse room) {
        return Room.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .build();
    }

    public List<RoomResponse> buildRoomsResponseForBooking(List<Room> rooms) {
        return rooms.stream()
                .map(this::buildRoomResponseWithoutBookings)
                .collect(Collectors.toList());
    }

    public List<Room> buildRoomsForBooking(List<RoomResponse> rooms) {
        return rooms.stream()
                .map(this::buildRoomForBooking)
                .collect(Collectors.toList());
    }

    private RoomResponse buildRoomResponseWithBookings(Room room, BookingMapper bookingMapper) {
        return RoomResponse.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .bookings(bookingMapper.buildBookingsResponseForRoom(room.getBookings()))
                .build();
    }

}

