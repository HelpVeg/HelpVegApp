package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHelper{

    /*
    * No databaseReference é instanciado o banco referenciando a raíz.
    */
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    /*
    *O método getDatabase retorna a variável databaseReference
     */
    public static DatabaseReference getDatabase(){
        return databaseReference;
    }

    // ----------------------------- User table and columns -----------------------------
    private static final String TABLE_USER = "users";

    // ------------------------- User Logged table and columns -------------------------
    private static final String TABLE_USER_LOGGED = "user_logged";
    private static final String COLUMN_USER_LOGGED_ID = "user_id";
    private static final String COLUMN_USER_IS_LOGGED = "user_is_logged";

    // ----------------------------- User getters and setters -----------------------------
    public static String getTableUser() {
        return TABLE_USER;
    }

    // ------------------------ User Logged getters and setters --------------------------
    public static String getTableUserLogged() {
        return TABLE_USER_LOGGED;
    }

    public static String getColumnUserLoggedId() {
        return COLUMN_USER_LOGGED_ID;
    }

    public static String getColumnUserIsLogged() {
        return COLUMN_USER_IS_LOGGED;
    }

    //---------------------------------------------------------------------------------------


}
