package by.academy.project.hotel.util.configuration;

public final class Constants {
    private Constants(){}

    /**User controllers*/
    public static final String GUEST_ACCOUNT = "/pages/user/account user/guest account.jsp";
    public static final String MANAGER_ACCOUNT = "/pages/user/account user/manager account.jsp";
    public static final String ADMIN_ACCOUNT = "/pages/user/account user/admin account.jsp";
    public static final String ACCOUNT_CONTROLLER = "/user/account";
    public final static String SUCCESSFUL_USER_CREATION_GUEST_PAGE = "/pages/user/create user/successful guest registration.jsp";
    public final static String SUCCESSFUL_USER_CREATION = "/pages/user/create user/successful user creation.jsp";
    public static final String ACCESS_IS_DENIED = "/pages/message/access is denied.jsp";
    public static final String SUCCESSFUL_REMOVAL = "/pages/user/delete user/successful removal.jsp";
    public static final String UNSUCCESSFUL_REMOVAL = "/pages/user/delete user/unsuccessful removal.jsp";
    public static final String FOUND_USERS_FOR_MANAGER = "/pages/user/search user/users for manager.jsp";
    public static final String FOUND_USERS_FOR_ADMIN = "/pages/user/search user/users for admin.jsp";
    public static final String USER_NOT_FOUND = "/pages/user/search user/user not found.jsp";
    public static final String GUEST_DETAILS_PAGE = "/pages/user/read user/guest.jsp";
    public static final String MANAGER_DETAILS_PAGE = "/pages/user/read user/manager.jsp";
    public static final String ADMIN_DETAILS_PAGE = "/pages/user/read user/admin.jsp";
    public static final String USERS_PAGE_FOR_MANAGER = "/pages/user/read users/users for manager.jsp";
    public static final String USERS_PAGE_FOR_ADMIN = "/pages/user/read users/users for admin.jsp";
    public static final String SUCCESSFUL_UPDATE = "/pages/user/update user/successful update.jsp";
    public static final String UNSUCCESSFUL_UPDATE = "/pages/user/update user/unsuccessful update.jsp";

    /**Room controllers*/
    public static final String SUCCESSFUL_ROOM_CREATION = "/pages/room/create room/successful room creation.jsp";
    public static final String SUCCESSFUL_REMOVAL_ROOM = "/pages/room/delete room/successful removal.jsp";
    public static final String UNSUCCESSFUL_REMOVAL_ROOM = "/pages/room/delete room/unsuccessful removal.jsp";
    public static final String ROOMS_PAGE_FOR_GUEST = "/pages/room/read rooms/rooms for guest.jsp";
    public static final String ROOMS_PAGE_FOR_MANAGER = "/pages/room/read rooms/rooms for manager.jsp";
    public static final String ROOMS_PAGE_FOR_ADMIN = "/pages/room/read rooms/rooms for admin.jsp";
    public final static String SUCCESSFUL_UPDATE_ROOM = "/pages/room/update room/successful update.jsp";
    public final static String UNSUCCESSFUL_UPDATE_ROOM = "/pages/room/update room/unsuccessful update.jsp";

    /**filters*/
    public static final String INVALID_LOGIN_ON_AUTHORIZATION = "/pages/user/authorization user/invalid login.jsp";
    public static final String INVALID_PASSWORD_ON_AUTHORIZATION = "/pages/user/authorization user/invalid password.jsp";
    public static final String INVALID_LOGIN_ON_CREATE = "/pages/user/create user/invalid login.jsp";
    public static final String INVALID_PASSWORD_ON_CREATE = "/pages/user/create user/invalid password.jsp";
    public static final String INVALID_PASSWORD_ON_UPDATE = "/pages/user/update user/invalid password.jsp";
    public static final String INVALID_EMAIL_ON_UPDATE = "/pages/user/update user/invalid email.jsp";
    public final static String LOGIN_EXISTS = "/pages/user/create user/login exists.jsp";
    public final static String INVALID_EMAIL = "/pages/user/create user/invalid email.jsp";
    public final static String FAILED_CREATION_DUE_TO_NUMBER= "/pages/room/create room/failed creation due to number.jsp";
    public final static String FAILED_CREATION_DUE_TO_ROOM_TYPE = "/pages/room/create room/failed creation due to room type.jsp";
    public final static String FAILED_CREATION_DUE_TO_STATUS = "/pages/room/create room/failed creation due to status.jsp";

    /**fields user*/
    public final static String USER = "user";
    public final static String USERS = "users";
    public final static String FOUND_USERS = "foundUsers";
    public final static String USER_DTO= "userDto";
    public final static String USER_ID = "id";
    public final static String NAME = "name";
    public final static String SURNAME = "surname";
    public final static String LOGIN = "login";
    public final static String PASSWORD = "password";
    public final static String PASSPORT = "passport";
    public final static String PASSPORT_SERIES = "passportSeries";
    public final static String PASSPORT_ID = "passportID";
    public final static String COUNTRY = "country";
    public final static String EMAIL = "email";
    public final static String PHONE = "phone";
    public final static String ROLE = "userRole";
    public final static String ERROR = "error";

    /**fields room*/
    public final static String ROOMS = "rooms";
    public final static String ROOM_ID = "id";
    public final static String NUMBER = "number";
    public final static String PRICE = "price";
    public final static String ROOM_CATEGORY = "roomCategory";
    public final static String IS_BOOKED = "isBooked";
    public final static String ROOM_STATUS = "roomStatus";
    public final static String STANDART = "standart";
    public final static String SUPERIOR = "superior";
    public final static String DELUX = "delux";
    public final static String SUITE = "suite";

    /**regex*/
    public final static String REGEX_LOGIN = "\\w{5,20}";
    public final static String REGEX_PASSWORD = "\\w{8,}";
    public final static String REGEX_EMAIL = "(\\w+[\\.-]*\\w+)+@(\\w+[\\.-]*\\w+)+[\\.]{1}[a-z]{2,6}";

    /**error message*/
    public static final String ERROR_MESSAGE_BY_USER = "User is not found!";
    public static final String ERROR_MESSAGE_BY_ROOM = "Room is not found!";
}