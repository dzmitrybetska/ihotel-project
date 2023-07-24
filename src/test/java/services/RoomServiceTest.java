package services;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.exceptions.RoomNotAddedException;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.room.RoomServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    private RoomService roomService;
    private static final RoomMapper mapper = RoomMapper.getInstance();
    private static Room testRoom;
    private static RoomDto testRoomDto;

    @BeforeClass
    public static void prepareTestEntity() {
        testRoom = Room.builder()
                .id(12L)
                .number("12")
                .price(BigDecimal.valueOf(456))
                .roomCategory(RoomCategory.DELUX)
                .isBooked(true)
                .roomStatus(RoomStatus.SERVICED)
                .build();
        testRoomDto = mapper.buildRoomDto(testRoom);
    }

    @Before
    public void init() {
        roomService = new RoomServiceImpl(roomRepository);
    }

    @Test
    public void addTest() throws RoomNotAddedException {
        when(roomRepository.add(any(RoomDto.class))).thenReturn(Optional.of(testRoom));
        RoomDto resultRoomDto = roomService.add(testRoomDto);
        assertEquals(resultRoomDto, testRoomDto);
    }

    @Test(expected = RoomNotAddedException.class)
    public void addExpectedException() throws RoomNotAddedException {
        when(roomRepository.add(any(RoomDto.class))).thenReturn(Optional.empty());
        roomService.add(testRoomDto);
    }

    @Test
    public void readTest() {
        when(roomRepository.read()).thenReturn(List.of(testRoom));
        List<RoomDto> result = roomService.read();
        assertEquals(result.get(0), testRoomDto);
    }

    @Test
    public void updateTest() throws NotFoundRoomException {
        Room roomForUpdate = mapper.buildRoom(testRoomDto);
        roomForUpdate.setPrice(BigDecimal.valueOf(224.5))
                .setRoomStatus(RoomStatus.REPAIRED)
                .setRoomCategory(RoomCategory.SUITE);
        RoomDto roomDtoForUpdate = mapper.buildRoomDto(roomForUpdate);
        when(roomRepository.update(any(RoomDto.class))).thenReturn(Optional.of(roomForUpdate));
        RoomDto roomDtoResult = roomService.update(roomDtoForUpdate);
        assertSame(roomDtoResult.getPrice(), roomDtoForUpdate.getPrice());
        assertEquals(roomDtoForUpdate, roomDtoResult);
    }

    @Test(expected = NotFoundRoomException.class)
    public void updateExpectedException() throws NotFoundRoomException {
        when(roomRepository.update(any(RoomDto.class))).thenReturn(Optional.empty());
        roomService.update(testRoomDto);
    }

    @Test
    public void deleteTest() {
        when(roomRepository.delete(testRoom.getId())).thenReturn(Optional.of(testRoom));
        boolean result = roomService.delete(testRoom.getId());
        assertTrue(result);
    }

    @Test
    public void getByIdTest() throws NotFoundRoomException {
        when(roomRepository.getByID(any(Long.class))).thenReturn(Optional.of(testRoom));
        RoomDto result = roomService.getByID(testRoom.getId());
        assertEquals(result, testRoomDto);
    }

    @Test(expected = NotFoundRoomException.class)
    public void getByIdExpectedException() throws NotFoundRoomException {
        when(roomRepository.getByID(any(Long.class))).thenReturn(Optional.empty());
        roomService.getByID(testRoomDto.getId());
    }

    @Test
    public void getRoomByNumberTest() throws NotFoundRoomException {
        when(roomRepository.getRoomByNumber(any(String.class))).thenReturn(Optional.of(testRoom));
        RoomDto result = roomService.getRoomByNumber(testRoom.getNumber());
        assertEquals(result, testRoomDto);
    }

    @Test(expected = NotFoundRoomException.class)
    public void getRoomByNumberExpectedException() throws NotFoundRoomException {
        when(roomRepository.getRoomByNumber(any(String.class))).thenReturn(Optional.empty());
        roomService.getRoomByNumber(testRoomDto.getNumber());
    }

    @Test
    public void searchRoomsByCategoryTest() throws NotFoundRoomException {
        when(roomRepository.searchRoomsByCategory(any(RoomCategory.class))).thenReturn(List.of(testRoom));
        List<RoomDto> result = roomService.searchRoomsByCategory(testRoom.getRoomCategory());
        assertEquals(result.get(0).getRoomCategory(), testRoomDto.getRoomCategory());
    }

    @Test(expected = NotFoundRoomException.class)
    public void searchRoomsByCategoryExpectedException() throws NotFoundRoomException {
        when(roomRepository.searchRoomsByCategory(any(RoomCategory.class))).thenReturn(List.of());
        roomService.searchRoomsByCategory(testRoomDto.getRoomCategory());
    }
}
