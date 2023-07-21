package by.academy.project.hotel.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static by.academy.project.hotel.util.configuration.DatabaseColumns.*;

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Passport {
    @Column(name = PASSPORT_SERIES)
    private String passportSeries;
    @Column(name = PASSPORT_ID)
    private String passportID;
    @Enumerated(EnumType.STRING)
    @Column(name = COUNTRY)
    private Country country;
}