package by.academy.project.hotel.entities.user;

import lombok.*;
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
    @ToString.Exclude
    private User user;
    @Column(name = PASSPORT_SERIES)
    private String passportSeries;
    @Column(name = PASSPORT_ID)
    private String passportID;
    @Enumerated(STRING)
    @Column(name = COUNTRY)
    private Country country;
}