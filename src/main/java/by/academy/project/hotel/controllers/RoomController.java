package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.services.room.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/room")
    public RoomResponse add(@Valid @RequestBody RoomRequest roomRequest) {
        return roomService.add(roomRequest);
    }

    @GetMapping(value = "/rooms")
    public List<RoomResponse> read() {
        return roomService.read();
    }

    @PutMapping(value = "/room/{id}")
    public RoomResponse update(@PathVariable @Min(1) Long id, @Valid @RequestBody RoomRequest roomRequest) {
        return roomService.update(id, roomRequest);
    }

    @DeleteMapping(value = "/room/{id}")
    public boolean delete(@PathVariable @Min(1) Long id) {
        return roomService.delete(id);
    }

    @GetMapping(value = "/room/{id}")
    public RoomResponse findRoomByID(@PathVariable @Min(1) Long id) {
        return roomService.findRoomByID(id);
    }

    @GetMapping(value = "/roomByNumber/{number}")
    public RoomResponse findRoomByNumber(@PathVariable @NotBlank String number) {
        return roomService.findRoomByNumber(number);
    }

    @GetMapping(value = "/roomByCategory/{category}")
    public List<RoomResponse> findRoomsByRoomCategory(@PathVariable @NotNull RoomCategory category) {
        return roomService.findRoomsByRoomCategory(category);
    }
}
