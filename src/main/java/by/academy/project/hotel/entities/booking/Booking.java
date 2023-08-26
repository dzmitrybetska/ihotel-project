package by.academy.project.hotel.entities.booking;

import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

import static by.academy.project.hotel.util.DatabaseColumns.*;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = BOOKINGS)
public class Booking {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = BOOKING_ID)
    private Long id;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = USER_ID_FOR_JOIN_COLUMN, nullable = false)
    private User user;

    @ManyToMany(cascade = {MERGE, PERSIST})
    @JoinTable(name = BOOKINGS_ROOMS,
            joinColumns = {@JoinColumn(name = BOOKING_ID_FOR_MANY)},
            inverseJoinColumns = {@JoinColumn(name = ROOM_ID_FOR_MANY)})
    private List<Room> rooms;

    @Enumerated(STRING)
    @Column(name = RATE, nullable = false)
    private Rate rate;

    @Column(name = ARRIVAL, nullable = false)
    private LocalDate arrival;

    @Column(name = DEPARTURE, nullable = false)
    private LocalDate departure;
}
