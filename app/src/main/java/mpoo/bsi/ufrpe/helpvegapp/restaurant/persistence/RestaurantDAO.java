package mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class RestaurantDAO {

    public Restaurant generateRestaurant(Cursor cursor){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestauranteId(cursor.getInt(0));
        restaurant.setRestaurantName(cursor.getString(1));
        double lat = cursor.getDouble(2);
        double lng = cursor.getDouble(3);
        restaurant.setLatLgn(new LatLng(lat, lng));
        restaurant.setRestaurantType(cursor.getString(4));

        return restaurant;
    }

    public ArrayList<Restaurant> getAllRestaurants() {

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllRestaurants(), null);

        if (cursor.moveToFirst()) {

            do {
                Restaurant restaurant = generateRestaurant(cursor);
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return restaurants;
    }

}
