package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class RoomMapper {

    public Room mapToRoom(RoomRequest roomRequest) {
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

    public RoomResponse mapToRoomResponse(Room room, BookingMapper bookingMapper) {
        return room.getBookings() != null ? mapToRoomResponseWithBookings(room, bookingMapper) : mapToRoomResponseWithoutBookings(room);
    }

    public List<RoomResponse> mapToRoomsResponse(List<Room> rooms, BookingMapper bookingMapper) {
        return rooms.stream()
                .map(room -> mapToRoomResponse(room, bookingMapper))
                .toList();
    }

    private RoomResponse mapToRoomResponseWithBookings(Room room, BookingMapper bookingMapper) {
        return RoomResponse.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .bookings(bookingMapper.mapToBookingsResponseForRoom(room.getBookings()))
                .description(room.getDescription())
                .build();
    }

    private RoomResponse mapToRoomResponseWithoutBookings(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .description(room.getDescription())
                .build();
    }

    public List<RoomResponse> mapToRoomsResponseForBooking(List<Room> rooms) {
        return rooms.stream()
                .map(this::mapToRoomResponseWithoutBookings)
                .toList();
    }
}

