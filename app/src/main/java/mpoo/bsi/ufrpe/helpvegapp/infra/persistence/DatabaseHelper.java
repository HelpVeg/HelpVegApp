package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static DatabaseHelper db;

    public synchronized static DatabaseHelper getDb(){
        if(db == null){
            db = new DatabaseHelper();
        }
        return db;
    }

    private static final String DATABASE_NAME = "helpveg.db";
    private static final int DATABASE_VERSION = 8;


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
    private static final String COLUMN_RESTAURANT_LAT = "restaurant_lat";
    private static final String COLUMN_RESTAURANT_LONG = "restaurant_long";
    private static final String COLUMN_RESTAURANT_TYPE = "restaurant_type";

    // ------------------------ Comment table ---------------------------------------
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_COMMENT_ID = "comment_id";
    private static final String COLUMN_COMMENT_USER_ID = "comment_id_user";
    private static final String COLUMN_COMMENT_RESTAURANTS_ID = "comment_id_restaurant";
    private static final String COLUMN_COMMENT_TEXT= "comment_text";

    // ------------------------ Comment table ---------------------------------------
    private static final String TABLE_RESTAURANT_PHOTOS = "photos";
    private static final String COLUMN_RESTAURANT_PHOTOS_ID = "photo_id";
    private static final String COLUMN_PHOTO = "photo";

    // -------------------------- Preferences table -------------------------------
    private static final String TABLE_PREFERENCES = "preferences";
    private static final String COLUMN_PREFERENCES_ID = "preferences_id";
    private static final String COLUMN_PREFERENCES_USER_ID = "preferences_user_id";
    private static final String COLUMN_PREFERENCES_TYPE = "preferences_type";
    private static final String COLUMN_PREFERENCES_FOOD_ = "preferences_food";
    private static final String COLUMN_PREFERENCES_SERVICE = "preferences_service";
    private static final String COLUMN_PREFERENCES_PRICE = "preferences_price";
    private static final String COLUMN_PREFERENCES_AMBIANCE = "preferences_ambiance";

    //---------------------------- Rating table ----------------------------------
    private static final String TABLE_RATING = "rating";
    private static final String COLUMN_RATING_ID = "rating_id";
    private static final String COLUMN_RATING_USER_ID = "rating_user_id";
    private static final String COLUMN_RATING_RESTAURANT_ID = "rating_restaurant_id";
    private static final String COLUMN_RATING_FOOD_ = "rating_food";
    private static final String COLUMN_RATING_SERVICE = "rating_service";
    private static final String COLUMN_RATING_PRICE = "rating_price";
    private static final String COLUMN_RATING_AMBIANCE = "rating_ambiance";

    // ---------------------------- Events Table --------------------------------
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_EVENTS_ID = "events_id";
    private static final String COLUMN_EVENTS_USER_ID = "events_name";
    private static final String COLUMN_EVENTS_NAME = "events_user_id";
    private static final String COLUMN_EVENTS_DESCRIPTION = "events_description";

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

    public static String getColumnRestaurantLat() {
        return COLUMN_RESTAURANT_LAT;
    }

    public static String getColumnRestaurantLong() {
        return COLUMN_RESTAURANT_LONG;
    }

    public static String getColumnRestaurantType() {
        return COLUMN_RESTAURANT_TYPE;
    }

    //--------------------------  Comment getters  -------------------------------------
    public static String getTableComments() {
        return TABLE_COMMENTS;
    }

    public static String getColumnCommentId() {
        return COLUMN_COMMENT_ID;
    }

    public static String getColumnCommentUserId() {
        return COLUMN_COMMENT_USER_ID;
    }

    public static String getColumnCommentRestaurantsId() {
        return COLUMN_COMMENT_RESTAURANTS_ID;
    }

    public static String getColumnCommentText() {
        return COLUMN_COMMENT_TEXT;
    }


    //--------------------------  Restaurant photos getters  -------------------------------------
    public static String getTableRestaurantPhotos() {
        return TABLE_RESTAURANT_PHOTOS;
    }

    public static String getColumnRestaurantPhotosId() {
        return COLUMN_RESTAURANT_PHOTOS_ID;
    }

    public static String getColumnPhoto() {
        return COLUMN_PHOTO;
    }


    //---------------------------- Preferences getters --------------------------------
    public static String getTablePreferences() {
        return TABLE_PREFERENCES;
    }

    public static String getColumnPreferencesId() {
        return COLUMN_PREFERENCES_ID;
    }

    public static String getColumnPreferencesUserId() {
        return COLUMN_PREFERENCES_USER_ID;
    }

    public static String getColumnPreferencesType(){
        return COLUMN_PREFERENCES_TYPE;
    }

    public static String getColumnPreferencesFood() {
        return COLUMN_PREFERENCES_FOOD_;
    }

    public static String getColumnPreferencesService() {
        return COLUMN_PREFERENCES_SERVICE;
    }

    public static String getColumnPreferencesPrice() {
        return COLUMN_PREFERENCES_PRICE;
    }

    public static String getColumnPreferencesAmbiance() {
        return COLUMN_PREFERENCES_AMBIANCE;
    }

    //-------------------------- Rating getters -------------------------------------
    public static String getTableRating() {
        return TABLE_RATING;
    }

    public static String getColumnRatingId() {
        return COLUMN_RATING_ID;
    }

    public static String getColumnRatingUserId() {
        return COLUMN_RATING_USER_ID;
    }

    public static String getColumnRatingRestaurantId() {
        return COLUMN_RATING_RESTAURANT_ID;
    }

    public static String getColumnRatingFood() {
        return COLUMN_RATING_FOOD_;
    }

    public static String getColumnRatingService() {
        return COLUMN_RATING_SERVICE;
    }

    public static String getColumnRatingPrice() {
        return COLUMN_RATING_PRICE;
    }

    public static String getColumnRatingAmbiance() {
        return COLUMN_RATING_AMBIANCE;
    }

    //------------------------------- Events getters -------------------------------------
    public static String getTableEvents() {
        return TABLE_EVENTS;
    }

    public static String getColumnEventsId() {
        return COLUMN_EVENTS_ID;
    }

    public static String getColumnEventsUserId() {
        return COLUMN_EVENTS_USER_ID;
    }

    public static String getColumnEventsName() {
        return COLUMN_EVENTS_NAME;
    }

    public static String getColumnEventsDescription() {
        return COLUMN_EVENTS_DESCRIPTION;
    }

    // ----------------------------------------------------------------------------

    private DatabaseHelper() {
        super(MyApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QueriesSQL.sqlCreateTableUser());
        db.execSQL(QueriesSQL.sqlUserLogged());
        db.execSQL(QueriesSQL.sqlCreateTableRestaurant());
        db.execSQL(QueriesSQL.sqlCreateTableComments());
        db.execSQL(QueriesSQL.sqlCreateTablePhotosRestaurants());
        db.execSQL(QueriesSQL.sqlCreateTablePreferences());
        db.execSQL(QueriesSQL.sqlCreateTableRating());
        db.execSQL(QueriesSQL.sqlCreateTableEvents());
        Script.populateRestaurantTable(db);
        Script.populateUserTable(db);
        Script.populatePreferenceTable(db);
        Script.populateRatingTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QueriesSQL.sqlDropTableUser());
        db.execSQL(QueriesSQL.sqlDropTableUserLogged());
        db.execSQL(QueriesSQL.sqlDropTableRestaurants());
        db.execSQL(QueriesSQL.sqlDropTableComments());
        db.execSQL(QueriesSQL.sqlDropTableRestaurantPhotos());
        db.execSQL(QueriesSQL.sqlDropTablePreferences());
        db.execSQL(QueriesSQL.sqlDropTableRating());
        db.execSQL(QueriesSQL.sqlDropTableEvents());
        this.onCreate(db);
    }

    @Override
    public synchronized void close() {
        db = null;
        super.close();
    }
}
