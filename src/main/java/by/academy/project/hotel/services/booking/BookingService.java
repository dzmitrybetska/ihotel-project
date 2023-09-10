package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;

import java.util.List;

/**
 * Service processes bookings data.
 * Contains CRUD methods
 */
public interface BookingService {

    /**
     * Add a new booking to the database
     *
     * @param bookingRequest booking data from the controller
     * @return information about the saved booking
     */
    BookingResponse book(BookingRequest bookingRequest);

    /**
     * Get information about all bookings in the database
     *
     * @return List with information about all bookings
     */
    List<BookingResponse> read();

    /**
     *Updating boking in the database
     *
     * @param id booking id to update
     * @param bookingRequest data to update
     * @return information about the updated booking
     */
    BookingResponse update(Long id, BookingRequest bookingRequest);

    /**
     * Delete booking from database
     *
     * @param id  id booking to delete
     */
    void delete(Long id);

    /**
     * Find booking by ID
     *
     * @param id booking ID
     * @return information about booking from database
     */
    BookingResponse findBookingById(Long id);
}
