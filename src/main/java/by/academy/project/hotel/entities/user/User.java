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
    @Column(name = LOGIN)
    private String login;
    @Column(name = PASSWORD)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ID_PASSPORT, unique = true)
    private Passport passport;
    @Column(name = EMAIL)
    private String email;
    @Column(name = PHONE)
    private String phone;
    @Enumerated(STRING)
    @Column(name = ROLE, nullable = false)
    private Role role;
}