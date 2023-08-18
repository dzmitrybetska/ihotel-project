package by.academy.project.hotel.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import static by.academy.project.hotel.util.configuration.DatabaseColumns.*;
import static javax.persistence.EnumType.STRING;

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(name = ZIPCODE, length = 6)
    private String zipcode;

    @Enumerated(STRING)
    @Column(name = COUNTRY, nullable = false, length = 30)
    private Country country;

    @Column(name = CITY, nullable = false, length = 30)
    private String city;

    @Column(name = STREET, nullable = false, length = 30)
    private String street;

    @Column(name = HOUSE_NUMBER, nullable = false, length = 4)
    private String houseNumber;

    @Column(name = FLAT_NUMBER, nullable = false, length = 4)
    private String flatNumber;
}
