package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static DatabaseHelper db;

    public static DatabaseHelper getDb(){
        if(db == null) db = new DatabaseHelper();
        return db;
    }

    private static final String DATABASE_NAME = "helpveg.db";
    private static final int DATABASE_VERSION = 1;

    // ----------------------------- User table and columns -----------------------------
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASS = "user_pass";
    private static final String COLUMN_USER_PHOTO = "user_photo";

    // ------------------------- User Logged table and columns -------------------------
    private static final String TABLE_USER_LOGGED = "user_logged";
    private static final String COLUMN_USER_LOGGED_ID = "user_id";

    // ------------------------ Restaurant table ---------------------------------------
    private static final String TABLE_RESTAURANTS = "restaurants";
    private static final String COLUMN_RESTAURANT_ID = "restaurant_id";
    private static final String COLUMN_RESTAURANT_NAME = "restaurant_name";
    private static final String COLUMN_RESTAURANT_IMAGE = "restaurant_image";
    private static final String COLUMN_RESTAURANT_LAT = "restaurant_lat";
    private static final String COLUMN_RESTAURANT_LONG = "restaurant_long";
    private static final String COLUMN_RESTAURANT_TYPE = "restaurant_type";
    private static final String COLUMN_RESTAURANT_RATE_GENERAL = "restaurant_rate_general";
    private static final String COLUMN_RESTAURANT_RATE_SERVICE = "restaurant_rate_service";
    private static final String COLUMN_RESTAURANT_RATE_FOOD = "restaurant_rate_food";
    private static final String COLUMN_RESTAURANT_RATE_PRICE = "restaurant_rate_price";
    private static final String COLUMN_RESTAURANT_RATE_ENVIRONMENT = "restaurant_rate_environment";

    // ----------------------------- User getters -----------------------------
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

    public static String getColumnUserPhoto() {
        return COLUMN_USER_PHOTO;
    }


    // ------------------------ User Logged getters --------------------------
    public static String getTableUserLogged() {
        return TABLE_USER_LOGGED;
    }

    public static String getColumnUserLoggedId() {
        return COLUMN_USER_LOGGED_ID;
    }


    //--------------------------  Restaurant getters  -------------------------------------
    public static String getTableRestaurants() {
        return TABLE_RESTAURANTS;
    }

    public static String getColumnRestaurantId() {
        return COLUMN_RESTAURANT_ID;
    }

    public static String getColumnRestaurantName() {
        return COLUMN_RESTAURANT_NAME;
    }

    public static String getColumnRestaurantImage() {
        return COLUMN_RESTAURANT_IMAGE;
    }

    public static String getColumnRestaurantLat() {
        return COLUMN_RESTAURANT_LAT;
    }

    public static String getColumnRestaurantLong() {
        return COLUMN_RESTAURANT_LONG;
    }

    public static String getColumnRestaurantType() {
        return COLUMN_RESTAURANT_TYPE;
    }

    public static String getColumnRestaurantRateGeneral() {
        return COLUMN_RESTAURANT_RATE_GENERAL;
    }

    public static String getColumnRestaurantRateService() {
        return COLUMN_RESTAURANT_RATE_SERVICE;
    }

    public static String getColumnRestaurantRateFood() {
        return COLUMN_RESTAURANT_RATE_FOOD;
    }

    public static String getColumnRestaurantRatePrice() {
        return COLUMN_RESTAURANT_RATE_PRICE;
    }

    public static String getColumnRestaurantRateEnvironment() {
        return COLUMN_RESTAURANT_RATE_ENVIRONMENT;
    }

    private DatabaseHelper() {
        super(MyApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QueriesSQL.sqlCreateTableUser());
        db.execSQL(QueriesSQL.sqlUserLogged());
        db.execSQL(QueriesSQL.sqlCreateTableRestaurant());
        //Script.populateRestaurantTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QueriesSQL.sqlDropTableUser());
        db.execSQL(QueriesSQL.sqlDropTableUserLogged());
        db.execSQL(QueriesSQL.sqlDropTableRestaurants());
        this.onCreate(db);
    }

    @Override
    public synchronized void close() {
        db = null;
        super.close();
    }
}
