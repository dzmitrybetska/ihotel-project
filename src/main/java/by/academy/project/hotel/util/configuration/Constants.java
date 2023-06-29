package by.academy.project.hotel.util.configuration;

public final class Constants {
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
    public static final String INVALID_LOGIN = "/pages/user/authorization user/invalid login.jsp";
    public static final String INVALID_PASSWORD = "/pages/user/authorization user/invalid password.jsp";
    public final static String LOGIN_EXISTS = "/pages/user/create user/login exists.jsp";
    public final static String INVALID_EMAIL = "/pages/user/create user/invalid email.jsp";
    public final static String FAILED_CREATION_DUE_TO_NUMBER= "/pages/room/create room/failed creation due to number.jsp";
    public final static String FAILED_CREATION_DUE_TO_ROOM_TYPE = "/pages/room/create room/failed creation due to room type.jsp";
    public final static String FAILED_CREATION_DUE_TO_STATUS = "/pages/room/create room/failed creation due to status.jsp";

    /**regex*/
    public final static String REGEX_LOGIN = "\\w{5,20}";
    public final static String REGEX_PASSWORD = "\\w{8,}";
    public final static String REGEX_EMAIL = "(\\w+[\\.-]*\\w+)+@(\\w+[\\.-]*\\w+)+[\\.]{1}[a-z]{2,6}";

}
