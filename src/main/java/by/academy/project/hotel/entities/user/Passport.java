package by.academy.project.hotel.entities.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import static by.academy.project.hotel.util.DatabaseColumns.*;
import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@Accessors(chain = true)
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