package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findRoomByNumber(String number);

    List<Room> findRoomsByRoomCategory(RoomCategory roomCategory);
}
