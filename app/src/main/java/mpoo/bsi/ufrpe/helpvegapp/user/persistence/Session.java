package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

/**
 * Created by user on 23/01/2018.
 */

public class Session {
    private static User userIn;

    public static User getUserIn() {
        return userIn;
    }

    public static void setUserIn(User userIn) {
        Session.userIn = userIn;
    }
}
