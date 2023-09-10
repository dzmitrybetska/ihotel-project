package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(uses = BookingMapper.class, componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface UserMapper {

    User mapToUser(UserRequest userRequest);

    @Mapping(source = "bookings", target = "bookings", qualifiedByName = "mapToBookingResponseForUser")
    UserResponse mapToUserResponse(User user);

    @Named("mapToUserResponseForBooking")
    @Mapping(target = "bookings", ignore = true)
    UserResponse mapToUserResponseForBooking(User user);

    User updateUser(@MappingTarget User user, UserRequest userRequest);
}
