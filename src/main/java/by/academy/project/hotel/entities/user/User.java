package by.academy.project.hotel.entities.user;

import by.academy.project.hotel.entities.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static by.academy.project.hotel.utils.DatabaseColumns.*;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = USERS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID)
    private Long id;

    @Column(name = NAME, nullable = false, length = 32)
    private String name;

    @Column(name = SURNAME, nullable = false, length = 32)
    private String surname;

    @Column(name = LOGIN, nullable = false, unique = true, length = 8)
    private String login;

    @Column(name = PASSWORD, nullable = false, length = 100)
    private String password;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = ID_PASSPORT, nullable = false, unique = true)
    private Passport passport;

    @Column(name = EMAIL, nullable = false, unique = true, length = 20)
    private String email;

    @Column(name = PHONE, nullable = false, length = 15)
    private String phone;

    @Enumerated(STRING)
    @Column(name = ROLE, nullable = false)
    private Role role;

    @Column(name = ADDRESS, nullable = false)
    @ElementCollection()
    @CollectionTable(name = ADDRESSES, joinColumns = @JoinColumn(name = USER_ID_FOR_JOIN_COLUMN))
    private List<Address> addresses;

    @OneToMany(mappedBy = USER, cascade = ALL, orphanRemoval = true)
    private List<Booking> bookings;
}