package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class RoomMapper {
    private static RoomMapper instance;

    private RoomMapper() {
    }

    public static RoomMapper getInstance() {
        if (instance == null) {
            instance = new RoomMapper();
        }
        return instance;
    }

    public Room buildRoom(RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .number(roomDto.getNumber())
                .price(roomDto.getPrice())
                .roomCategory(roomDto.getRoomCategory())
                .isBooked(roomDto.getIsBooked())
                .roomStatus(roomDto.getRoomStatus())
                .build();
    }

    public void updateRoom(Room room, RoomDto roomDto) {
        room.setNumber(roomDto.getNumber())
                .setPrice(roomDto.getPrice())
                .setRoomCategory(roomDto.getRoomCategory())
                .setIsBooked(roomDto.getIsBooked())
                .setRoomStatus(roomDto.getRoomStatus());
    }

    public RoomDto buildRoomDto(String number, BigDecimal price, RoomCategory roomCategory, Boolean isBooked, RoomStatus status) {
        return RoomDto.builder()
                .number(number)
                .price(price)
                .roomCategory(roomCategory)
                .isBooked(isBooked)
                .roomStatus(status)
                .build();
    }

    public RoomDto buildRoomDtoForGuest(RoomDto roomDto) {
        return RoomDto.builder()
                .id(roomDto.getId())
                .number(roomDto.getNumber())
                .price(roomDto.getPrice())
                .roomCategory(roomDto.getRoomCategory())
                .build();
    }

    public RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .build();
    }

    public List<RoomDto> filterRoomsDto(List<RoomDto> rooms) {
        return rooms.stream()
                .filter(room -> room.getRoomStatus() == RoomStatus.SERVICED)
                .map(this::buildRoomDtoForGuest)
                .collect(Collectors.toList());
    }

    public List<RoomDto> buildRoomsDto(List<Room> rooms) {
        return rooms.stream()
                .map(this::buildRoomDto)
                .collect(Collectors.toList());
    }
}

