package by.academy.project.hotel.services.room;

import by.academy.project.hotel.arguments.room.*;
import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.services.description.DescriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing methods of the RoomService")
public class RoomServiceTest {

    private RoomService roomService;
    @Mock
    private DescriptionService descriptionService;
    @Autowired
    private RoomMapper roomMapper;
    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void init() {
        roomService = new RoomServiceImpl(descriptionService, roomMapper, roomRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomAddArguments.class)
    void addRoomTest(RoomRequest roomRequest, Room room, RoomResponse roomResponse) {
        when(roomRepository.save(any(Room.class)))
                .thenReturn(room);
        RoomResponse actualRoomResponse = roomService.add(roomRequest);
        assertEquals(roomResponse, actualRoomResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomGetArguments.class)
    void readRoomsTest(Room room, RoomResponse roomResponse) {
        when(descriptionService.getDescription(any(RoomCategory.class)))
                .thenReturn(room.getRoomCategory().name());
        when(roomRepository.findAll())
                .thenReturn(List.of(room));
        List<RoomResponse> actualRooms = roomService.read();
        assertEquals(Collections.singletonList(roomResponse), actualRooms);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomUpdateArguments.class)
    void updateRoomTest(RoomRequest roomRequest, Room room, RoomResponse roomResponse) {
        when(roomRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class)))
                .thenReturn(room);
        RoomResponse actualRoomResponse = roomService.update(room.getId(), roomRequest);
        assertEquals(roomResponse, actualRoomResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomUpdateInvalidArguments.class)
    void updateExpectedException(Room room, RoomRequest roomRequest) {
        when(roomRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.update(room.getId(), roomRequest));
    }

    @ParameterizedTest
    @ArgumentsSource(RoomGetArguments.class)
    void findRoomByIdTest(Room room, RoomResponse roomResponse) {
        when(descriptionService.getDescription(any(RoomCategory.class)))
                .thenReturn(room.getRoomCategory().name());
        when(roomRepository.findById(any(Long.class))).thenReturn(Optional.of(room));
        RoomResponse actualRoomResponse = roomService.findRoomByID(room.getId());
        assertEquals(roomResponse, actualRoomResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomInvalidArguments.class)
    void findRoomByIdExpectedException(Room room) {
        when(roomRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.findRoomByID(room.getId()));
    }

    @ParameterizedTest
    @ArgumentsSource(RoomGetArguments.class)
    void findRoomByNumber(Room room, RoomResponse roomResponse) {
        when(descriptionService.getDescription(any(RoomCategory.class)))
                .thenReturn(room.getRoomCategory().name());
        when(roomRepository.findRoomByNumber(any(String.class))).thenReturn(Optional.of(room));
        RoomResponse actualRoomResponse = roomService.findRoomByNumber(room.getNumber());
        assertEquals(roomResponse, actualRoomResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(RoomInvalidArguments.class)
    void findRoomByNumberExpectedException(Room room) {
        when(roomRepository.findRoomByNumber(any(String.class)))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.findRoomByNumber(room.getNumber()));
    }

    @ParameterizedTest
    @ArgumentsSource(RoomGetArguments.class)
    void findRoomsByRoomCategory(Room room, RoomResponse roomResponse) {
        when(descriptionService.getDescription(any(RoomCategory.class)))
                .thenReturn(room.getRoomCategory().name());
        when(roomRepository.findRoomsByRoomCategory(any(RoomCategory.class))).thenReturn(List.of(room));
        List<RoomResponse> actualRooms = roomService.findRoomsByRoomCategory(room.getRoomCategory());
        assertEquals(Collections.singletonList(roomResponse), actualRooms);
    }
}
