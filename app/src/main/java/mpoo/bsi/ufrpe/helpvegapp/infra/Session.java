package mpoo.bsi.ufrpe.helpvegapp.infra;

import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;


public class Session {
    private static User userIn;

    private static Restaurant currentRestaurant;

    private static Event currentEvent;

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

    public static Event getCurrentEvent() {
        return currentEvent;
    }

    public static void setCurrentEvent(Event currentEvent) {
        Session.currentEvent = currentEvent;
    }
}
