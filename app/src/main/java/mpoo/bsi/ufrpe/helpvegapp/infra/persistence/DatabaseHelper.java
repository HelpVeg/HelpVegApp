package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

public class DatabaseHelper{



    // ----------------------------- User table and columns -----------------------------
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASS = "user_pass";
    //private static final String COLUMN_USER_IMG = "user_img";

    // ------------------------- User Logged table and columns -------------------------
    private static final String TABLE_USER_LOGGED = "user_logged";
    private static final String COLUMN_USER_LOGGED_ID = "user_id";
    private static final String COLUMN_USER_IS_LOGGED = "user_is_logged";

    // ----------------------------- User getters and setters -----------------------------
    public static String getTableUser() {
        return TABLE_USER;
    }

    public static String getColumnUserId() {
        return COLUMN_USER_ID;
    }

    public static String getColumnUserName() {
        return COLUMN_USER_NAME;
    }

    public static String getColumnUserEmail() {
        return COLUMN_USER_EMAIL;
    }

    public static String getColumnUserPass() {
        return COLUMN_USER_PASS;
    }

    /*public static String getColumnUserImg() {
        return COLUMN_USER_IMG;
    }*/

    // ------------------------ User Logged getters and setters --------------------------
    public static String getTableUserLogged() {
        return TABLE_USER_LOGGED;
    }

    public static String getColumnUserLoggedId() {
        return COLUMN_USER_LOGGED_ID;
    }

    public static String getColumnUserIsLogged() { return COLUMN_USER_IS_LOGGED; }

    //---------------------------------------------------------------------------------------
}
