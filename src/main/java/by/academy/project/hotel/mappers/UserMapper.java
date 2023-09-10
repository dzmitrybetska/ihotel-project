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

    /**
     * Mapping userRequest to entity.
     *
     * @param userRequest user details
     * @return entity
     */
    User mapToUser(UserRequest userRequest);

    /**
     * Mapping entity to UserResponse
     *
     * @param user user details from the service
     * @return UserResponse
     */
    @Mapping(source = "bookings", target = "bookings", qualifiedByName = "mapToBookingResponseForUser")
    UserResponse mapToUserResponse(User user);

    /**
     * Mapping entity to UserResponse for BookingMapper
     *
     * @param user user details from the service
     * @return UserResponse
     */
    @Named("mapToUserResponseForBooking")
    @Mapping(target = "bookings", ignore = true)
    UserResponse mapToUserResponseForBooking(User user);

    /**
     * Updating a user based on data received from userRequest
     *
     * @param user user to update
     * @param userRequest data for updating the user
     * @return user
     */
    User updateUser(@MappingTarget User user, UserRequest userRequest);
}
