package by.academy.project.hotel.entities.room;

import by.academy.project.hotel.entities.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

import static by.academy.project.hotel.utils.DatabaseColumns.*;
import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ROOMS)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ROOM_ID)
    private Long id;

    @Column(name = NUMBER, nullable = false, unique = true)
    private String number;

    @Column(name = PRICE, nullable = false)
    private BigDecimal price;

    @Enumerated(STRING)
    @Column(name = ROOM_CATEGORY, nullable = false)
    private RoomCategory roomCategory;

    @Column(name = IS_BOOKED, nullable = false)
    private Boolean isBooked;

    @Enumerated(STRING)
    @Column(name = ROOM_STATUS, nullable = false)
    private RoomStatus roomStatus;

    @ManyToMany(mappedBy = ROOMS_FOR_MANY)
    private List<Booking> bookings;
}