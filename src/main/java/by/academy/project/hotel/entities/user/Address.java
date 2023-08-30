package by.academy.project.hotel.entities.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static by.academy.project.hotel.utils.DatabaseColumns.*;
import static jakarta.persistence.EnumType.STRING;

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
    @Column(name = COUNTRY, nullable = false)
    private Country country;

    @Column(name = CITY, nullable = false)
    private String city;

    @Column(name = STREET, nullable = false)
    private String street;

    @Column(name = HOUSE_NUMBER, nullable = false)
    private String houseNumber;

    @Column(name = FLAT_NUMBER, nullable = false)
    private String flatNumber;
}
