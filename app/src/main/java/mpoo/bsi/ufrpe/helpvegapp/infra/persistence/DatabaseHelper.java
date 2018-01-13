package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "helpveg.db";
    private static final int DATABASE_VERSION = 1;

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

    //---------------------------------------------------------------------------------------

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(QueriesSQL.sqlCreateTableUser());
        database.execSQL(QueriesSQL.sqlUserLogged());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(QueriesSQL.sqlDropTableUser());
        database.execSQL(QueriesSQL.sqlDropTableUserLogged());
        this.onCreate(database);
    }
}
