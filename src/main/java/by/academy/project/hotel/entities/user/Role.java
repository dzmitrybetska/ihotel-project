package by.academy.project.hotel.entities.user;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.services.room.RoomServiceImpl;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.academy.project.hotel.util.configuration.Constants.*;

public enum Role {
    GUEST {
        @Override
        public UserDto createUserDto(HttpServletRequest req) {
            return UserMapper.getInstance().buildUserDtoForGuest(req);
        }

        @Override
        public UserDto createUserDto(Long id) throws NotFoundUserException {
            return UserMapper.getInstance().buildUserDtoForGuest(UserServiceImpl.getInstance().getByID(id));
        }

        @Override
        public UserDto updateUserDto(UserDto userDto, HttpServletRequest req) {
            return userDto
                    .setName(req.getParameter(NAME))
                    .setSurname(req.getParameter(SURNAME))
                    .setPassword(req.getParameter(PASSWORD))
                    .setEmail(req.getParameter(EMAIL))
                    .setPhone(req.getParameter(PHONE));
        }

        @Override
        public List<RoomDto> readRoomsDto() {
            return RoomMapper.getInstance().filterRoomsDto(RoomServiceImpl.getInstance().read());
        }
    }, MANAGER {
        @Override
        public UserDto createUserDto(HttpServletRequest req) {
            return UserMapper.getInstance().buildUserDtoForManager(req);
        }

        @Override
        public UserDto createUserDto(Long id) throws NotFoundUserException {
            return UserMapper.getInstance().buildUserDtoForManager(UserServiceImpl.getInstance().getByID(id));
        }

        @Override
        public UserDto updateUserDto(UserDto userDto, HttpServletRequest req) {
            return userDto
                    .setName(req.getParameter(NAME))
                    .setSurname(req.getParameter(SURNAME))
                    .setPassport(userDto.getPassport()
                            .setPassportSeries(req.getParameter(PASSPORT_SERIES))
                            .setPassportID(req.getParameter(PASSPORT_ID))
                            .setCountry(Country.valueOf(req.getParameter(COUNTRY).toUpperCase())))
                    .setEmail(req.getParameter(EMAIL))
                    .setPhone(req.getParameter(PHONE));
        }

        @Override
        public List<RoomDto> readRoomsDto() {
            return RoomServiceImpl.getInstance().read();
        }
    }, ADMIN {
        @Override
        public UserDto createUserDto(HttpServletRequest req) {
            return UserMapper.getInstance().buildUserDto(req);
        }

        @Override
        public UserDto createUserDto(Long id) throws NotFoundUserException {
            return UserServiceImpl.getInstance().getByID(id);
        }

        @Override
        public UserDto updateUserDto(UserDto userDto, HttpServletRequest req) {
            return userDto
                    .setName(req.getParameter(NAME))
                    .setSurname(req.getParameter(SURNAME))
                    .setPassword(req.getParameter(PASSWORD))
                    .setPassport(userDto.getPassport()
                            .setPassportSeries(req.getParameter(PASSPORT_SERIES))
                            .setPassportID(req.getParameter(PASSPORT_ID))
                            .setCountry(Country.valueOf(req.getParameter(COUNTRY).toUpperCase())))
                    .setEmail(req.getParameter(EMAIL))
                    .setPhone(req.getParameter(PHONE))
                    .setRole(Role.valueOf(req.getParameter(ROLE).toUpperCase()));
        }

        @Override
        public List<RoomDto> readRoomsDto() {
            return RoomServiceImpl.getInstance().read();
        }
    };

    public abstract UserDto createUserDto(HttpServletRequest req);

    public abstract UserDto createUserDto(Long id) throws NotFoundUserException;

    public abstract UserDto updateUserDto(UserDto userDto, HttpServletRequest req);

    public abstract List<RoomDto> readRoomsDto();

}

