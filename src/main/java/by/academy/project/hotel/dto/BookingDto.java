package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.booking.Rate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private UserDto userDto;
    private Set<RoomDto> rooms;
    private Rate rate;
    private LocalDate arrival;
    private LocalDate departure;
}
