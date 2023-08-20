package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.services.room.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_BY_ROOM;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<RoomResponse> read(){
        return roomService.read();
    }

    @GetMapping(value = "/roomById/{id}")
    public RoomResponse findRoomByID(@PathVariable Long id) {
        return roomService.findRoomByID(id);
    }

    @GetMapping(value = "/roomByNumber/{number}")
    public RoomResponse findRoomByNumber(@PathVariable String number) {
        return roomService.findRoomByNumber(number);
    }

    @GetMapping(value = "/roomByCategory/{category}")
    public List<RoomResponse> findRoomsByRoomCategory(@PathVariable RoomCategory category) {
        return roomService.findRoomsByRoomCategory(category);
    }
}
