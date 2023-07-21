package by.academy.project.hotel.entities.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

import static by.academy.project.hotel.util.configuration.DatabaseColumns.*;

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
    @Column(name = NUMBER)
    private String number;
    @Column(name = PRICE)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = ROOM_CATEGORY)
    private RoomCategory roomCategory;
    @Column(name = IS_BOOKED)
    private Boolean isBooked;
    @Enumerated(EnumType.STRING)
    @Column(name = ROOM_STATUS)
    private RoomStatus roomStatus;
}