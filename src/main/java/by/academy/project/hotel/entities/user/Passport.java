package by.academy.project.hotel.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static by.academy.project.hotel.util.configuration.DatabaseColumns.*;
import static javax.persistence.EnumType.STRING;

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

    @OneToOne(mappedBy = PASSPORT)
    private User user;

    @Column(name = PASSPORT_SERIES, nullable = false)
    private String passportSeries;

    @Column(name = PASSPORT_ID, nullable = false)
    private String passportID;

    @Enumerated(STRING)
    @Column(name = COUNTRY, nullable = false)
    private Country country;
}