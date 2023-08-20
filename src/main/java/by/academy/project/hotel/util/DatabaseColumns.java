package by.academy.project.hotel.util;

public final class DatabaseColumns {

    private DatabaseColumns() {
    }

    /**
     * Room columns and table
     */
    public final static String ROOMS = "ROOMS";
    public final static String ROOM_ID = "ID";
    public final static String NUMBER = "NUMBER";
    public final static String PRICE = "PRICE";
    public final static String ROOM_CATEGORY = "ROOM_CATEGORY";
    public final static String IS_BOOKED = "IS_BOOKED";
    public final static String ROOM_STATUS = "ROOM_STATUS";
    public final static String ROOMS_FOR_MANY = "rooms";

    /**
     * User columns and table
     */
    public final static String USERS = "USERS";
    public final static String USER = "user";
    public final static String USER_ID = "ID";
    public final static String NAME = "NAME";
    public final static String SURNAME = "SURNAME";
    public final static String LOGIN = "LOGIN";
    public final static String PASSWORD = "PASSWORD";
    public final static String EMAIL = "EMAIL";
    public final static String PHONE = "PHONE";
    public final static String ROLE = "ROLE";
    public final static String PASSPORT_SERIES = "PASSPORT_SERIES";
    public final static String ID_PASSPORT = "ID_PASSPORT";
    public final static String PASSPORT = "passport";
    public final static String PASSPORTS = "PASSPORTS";
    public final static String ID = "ID";
    public final static String PASSPORT_ID = "PASSPORT_ID";
    public final static String COUNTRY = "COUNTRY";

    /**
     * Booking columns and table
     */
    public final static String BOOKINGS = "BOOKINGS";
    public final static String BOOKING_ID = "ID";
    public final static String USER_ID_FOR_JOIN_COLUMN = "USER_ID";
    public final static String BOOKINGS_ROOMS = "BOOKINGS_ROOMS";
    public final static String BOOKING_ID_FOR_MANY = "BOOKING_ID";
    public final static String ROOM_ID_FOR_MANY = "ROOM_ID";
    public final static String RATE = "RATE";
    public final static String ARRIVAL = "ARRIVAL";
    public final static String DEPARTURE = "DEPARTURE";

    /**
     * Address columns and table
     */
    public final static String ADDRESS = "ADDRESS";
    public final static String ADDRESSES = "ADDRESSES";
    public final static String ZIPCODE = "ZIPCODE";
    public final static String CITY = "CITY";
    public final static String STREET = "STREET";
    public final static String HOUSE_NUMBER = "HOUSE_NUMBER";
    public final static String FLAT_NUMBER = "FLAT_NUMBER";
}
