package by.academy.project.hotel.repositories.booking;

import by.academy.project.hotel.entities.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
