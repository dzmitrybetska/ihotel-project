package by.academy.project.hotel.controllers;

import by.academy.project.hotel.aspects.SkipLogging;
import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.services.room.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Operations on rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/room")
    @Operation(description = "Add a new room")
    public RoomResponse add(@Valid @RequestBody RoomRequest roomRequest) {
        return roomService.add(roomRequest);
    }

    @SkipLogging
    @GetMapping(value = "/rooms")
    @Operation(description = "Get all rooms")
    public List<RoomResponse> read() {
        return roomService.read();
    }

    @PutMapping(value = "/room/{id}")
    @Operation(description = "Update room data by ID")
    public RoomResponse update(@PathVariable @NotNull Long id, @Valid @RequestBody RoomRequest roomRequest) {
        return roomService.update(id, roomRequest);
    }

    @DeleteMapping(value = "/room/{id}")
    @Operation(description = "Delete a room by ID")
    public void delete(@PathVariable @NotNull Long id) {
        roomService.delete(id);
    }

    @GetMapping(value = "/room/{id}")
    @Operation(description = "Find a room by ID")
    public RoomResponse findRoomByID(@PathVariable @NotNull Long id) {
        return roomService.findRoomById(id);
    }

    @GetMapping(value = "/roomByNumber/{number}")
    @Operation(description = "Find a room by number")
    public RoomResponse findRoomByNumber(@PathVariable @NotBlank String number) {
        return roomService.findRoomByNumber(number);
    }

    @GetMapping(value = "/roomByCategory/{category}")
    @Operation(description = "Find rooms by category")
    public List<RoomResponse> findRoomsByRoomCategory(@PathVariable @NotNull RoomCategory category) {
        return roomService.findRoomsByRoomCategory(category);
    }

    @GetMapping(value = "/rooms/{arrival}/{departure}")
    public List<RoomResponse> findAvailableRooms(@PathVariable LocalDate arrival, @PathVariable LocalDate departure) {
        return roomService.findAvailableRooms(arrival, departure);
    }
}
