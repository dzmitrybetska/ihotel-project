package by.academy.project.hotel.services.user;


import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Service processes users data.
 * Contains CRUD methods
 */
public interface UserService {

    /**
     * Add new user to database
     *
     * @param userRequest user's data from controller
     * @return information about saved user
     */
    UserResponse create(UserRequest userRequest);

    /**
     * Get information about all users in database
     *
     * @return List with information about all users
     */
    List<UserResponse> read();

    /**
     * Update user info in database
     *
     * @param id user's ID to update
     * @param userRequest data to update
     * @return info about updated user
     */
    UserResponse update(Long id, UserRequest userRequest);

    /**
     * Delete user from database
     *
     * @param id  id user to delete
     */
    void delete(Long id);

    /**
     * Find user by ID
     *
     * @param id user identifier
     * @return user data from the database
     */
    UserResponse findUserById(Long id);

    /**
     * Find a user by ID for the BookingService
     *
     * @param id user identifier
     * @return user data from database
     */
    Optional<User> findUserByIdForBooking(Long id);

    /**
     * Find user by login
     *
     * @param login user login
     * @return user data from database
     */
    UserResponse findUserByLogin(String login);

    /**
     * Find users by first and last name
     *
     * @param name user name
     * @param surname user surname
     * @return users data from the database with the same first and last name
     */
    List<UserResponse> findUsersByNameAndSurname(String name, String surname);
}
