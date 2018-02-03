package mpoo.bsi.ufrpe.helpvegapp.infra;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

/**
 * Created by user on 23/01/2018.
 */

public class Session {
    private static User userIn;

    private static Restaurant currentRestaurant;

    public static User getUserIn() {
        return userIn;
    }

    public static void setUserIn(User userIn) {
        Session.userIn = userIn;
    }

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static void setCurrentRestaurant(Restaurant currentRestaurant) {
        Session.currentRestaurant = currentRestaurant;
    }
}
