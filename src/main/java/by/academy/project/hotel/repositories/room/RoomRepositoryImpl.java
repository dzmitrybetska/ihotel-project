package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomRepositoryImpl implements RoomRepository{
    private final List<Room> rooms = new ArrayList<>();

    @Override
    public Room createRoom(Room room) {
        rooms.add(room);
        return room;
    }

    @Override
    public List<Room> readRooms() {
        return rooms;
    }

    @Override
    public Optional<Room> updateRoom(String id, Room room) {
        Optional<Room> optionalRoom = getRoomByRoomID(id);
        if (optionalRoom.isPresent()){
            Room foundRoom = optionalRoom.get();
            foundRoom.setNumber(room.getNumber())
                    .setPrice(room.getPrice())
                    .setRoomCategory(room.getRoomCategory())
                    .setRoomStatus(room.getRoomStatus());
        }
        return optionalRoom;
    }

    @Override
    public Optional<Room> updateRoomStatus(String id, RoomStatus status){
        Optional<Room> foundRoom = getRoomByRoomID(id);
        if (foundRoom.isPresent()){
            Room room = foundRoom.get();
            room.setRoomStatus(status);
        }
        return foundRoom;
    }

    @Override
    public Optional<Room> deleteRoom(String id) {
        Optional<Room> foundRoom = getRoomByRoomID(id);
        foundRoom.ifPresent(rooms::remove);
        return foundRoom;
    }

    @Override
    public Optional<Room> getRoomByRoomID(String id){
        return rooms.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }

    @Override
    public Optional<Room> findRoomByNumber(String number) {
        return rooms.stream()
                .filter(room -> room.getNumber().equals(number))
                .findFirst();
    }

    @Override
    public List<Room> roomSearchByCategory(RoomCategory category) {
        return rooms.stream()
                .filter(room -> room.getRoomCategory() == category)
                .collect(Collectors.toList());
    }

}
