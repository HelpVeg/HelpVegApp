package mpoo.bsi.ufrpe.helpvegapp.infra;


import android.content.Context;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class Session {

    private static DatabaseHelper actuallyDb;
    private static User currentUser;
    private static Context context;

    public static DatabaseHelper getActuallyDb() {
        return actuallyDb;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        Session.currentUser = user;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Session.context = context;
    }

    public Session(){
        DatabaseHelper database = new DatabaseHelper(Session.getContext());
        Session.actuallyDb = database;
    }
}
