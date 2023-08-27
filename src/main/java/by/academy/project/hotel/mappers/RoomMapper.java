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

    public RoomResponse buildRoomResponse(Room room, BookingMapper bookingMapper) {
        return room.getBookings() != null ? buildRoomResponseWithBookings(room, bookingMapper) : buildRoomResponseWithoutBookings(room);
    }

    public List<RoomResponse> buildRoomsResponse(List<Room> rooms, BookingMapper bookingMapper) {
        return rooms.stream()
                .map(room -> buildRoomResponse(room, bookingMapper))
                .toList();
    }

    public RoomResponse buildRoomResponseWithoutBookings(Room room) {
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

    public List<RoomResponse> buildRoomsResponseForBooking(List<Room> rooms) {
        return rooms.stream()
                .map(this::buildRoomResponseWithoutBookings)
                .toList();
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
                .description(room.getDescription())
                .build();
    }
}

