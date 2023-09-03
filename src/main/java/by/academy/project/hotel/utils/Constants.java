package by.academy.project.hotel.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

    /**
     * error message
     */
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    public static final String USER_NOT_FOUND_BY_LOGIN = "User not found by login: %s";
    public static final String ROOM_NOT_FOUND_BY_ID = "Room is not found by id: %s";
    public static final String ROOM_NOT_FOUND_BY_NUMBER = "Room is not found by number: %s";
    public static final String ERROR_MESSAGE_CREATING_BOOKING = "Booking not created! Invalid user or room id!";
    public static final String BOOKING_NOT_FOUND_BY_ID = "Booking is not found by id: %s";
    public static final String EXCEPTION = "Exception: {}";

    /**
     * patterns for logging
     */
    public static final String REQUEST_LOG_PATTERN = "--> {}:{}: \nRequestBody: {} -> {}";
    public static final String RESPONSE_LOG_PATTERN = "<-- {}:{} -> {}, \nResponse: {}";
    public static final String EMPTY = "";
}
