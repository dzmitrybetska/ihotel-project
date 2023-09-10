package by.academy.project.hotel.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.academy.project.hotel.utils.DatabaseColumns.*;
import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = PASSPORTS)
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = PASSPORT_SERIES, nullable = false)
    private String passportSeries;

    @Column(name = PASSPORT_ID, nullable = false)
    private String passportID;

    @Enumerated(STRING)
    @Column(name = COUNTRY, nullable = false)
    private Country country;
}