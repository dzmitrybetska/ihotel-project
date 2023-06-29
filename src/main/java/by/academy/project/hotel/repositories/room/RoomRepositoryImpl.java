package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategories;
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
    public Room updateRoom(String id, Room room) {
        Optional<Room> optionalRoom = getRoomByRoomID(id);
        if (optionalRoom.isPresent()){
            Room foundRoom = optionalRoom.get();
           return foundRoom.setNumber(room.getNumber())
                    .setPrice(room.getPrice())
                    .setRoomCategories(room.getRoomCategories())
                    .setRoomStatus(room.getRoomStatus());
        }
        return null;
    }

    @Override
    public Room updateRoomStatus(String id, RoomStatus status){
        Optional<Room> foundRoom = getRoomByRoomID(id);
        if (foundRoom.isPresent()){
            Room room = foundRoom.get();
            return room.setRoomStatus(status);
        }
        return null;
    }

    @Override
    public Room deleteRoom(String id) {
        Optional<Room> foundRoom = getRoomByRoomID(id);
        if (foundRoom.isPresent()){
            int index = rooms.indexOf(foundRoom.get());
            return rooms.remove(index);
        }
        return null;
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
    public List<Room> roomSearchByCategory(RoomCategories category) {
        return rooms.stream()
                .filter(room -> room.getRoomCategories() == category)
                .collect(Collectors.toList());
    }

}
