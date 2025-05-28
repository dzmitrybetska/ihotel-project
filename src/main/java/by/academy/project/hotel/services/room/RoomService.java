package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;

import java.time.LocalDate;
import java.util.List;

/**
 * The service processes room data.
 * Contains CRUD methods.
 */
public interface RoomService {

    /**
     * Add a new room to the database.
     *
     * @param roomRequest room data from the controller
     * @return information about the saved room
     */
    RoomResponse add(RoomRequest roomRequest);

    /**
     * Get information about all rooms in the database
     *
     * @return List with information about all rooms
     */
    List<RoomResponse> read();

    /**
     * Update room info in database
     *
     * @param id room's ID to update
     * @param roomRequest data to update
     * @return info about updated room
     */
    RoomResponse update(Long id, RoomRequest roomRequest);

    /**
     * Delete room from database
     *
     * @param id  id room to delete
     */
    void delete(Long id);

    /**
     * Find room by ID
     *
     * @param id room identifier
     * @return room data from database
     */
    RoomResponse findRoomById(Long id);

    /**
     * Find for rooms by ID for BookingService.
     *
     * @param list of room IDs
     * @return a list of the specified rooms from the database
     */
    List<Room> findRoomsByIdForBooking(List<Long> list);

    /**
     * Find a room by number
     *
     * @param number room number
     * @return room data from database
     */
    RoomResponse findRoomByNumber(String number);

    /**
     * Find rooms of a certain category
     *
     * @param category room category
     * @return a list of rooms of the specified category from the database
     */
    List<RoomResponse> findRoomsByRoomCategory(RoomCategory category);

    List<RoomResponse> findAvailableRooms(LocalDate arrival, LocalDate departure);
}
