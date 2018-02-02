package mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class RestaurantDAO {
    public ArrayList<Restaurant> getAllRestaurants() {

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.getAllRestaurants(), null);

        if (cursor.moveToFirst()) {

            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(cursor.getInt(0));
                restaurant.setRestaurantName(cursor.getString(1));
                Bitmap photo = byteToBitmap(cursor.getBlob(2));
                restaurant.setRestaurantImage(photo);
                double lat = cursor.getDouble(3);
                double lng = cursor.getDouble(4);
                restaurant.setLatLgn(new LatLng(lat,lng));
                restaurant.setRestaurantType(cursor.getString(5));

                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return restaurants;
    }

    public Restaurant getRestaurantById(int id){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.getRestaurantFromId(), new String[] {Integer.toString(id)});

        Restaurant restaurant = null;

        if(cursor.moveToFirst()){
            restaurant = new Restaurant();
            restaurant.setRestaurantId(cursor.getInt(0));
            restaurant.setRestaurantName(cursor.getString(1));
            Bitmap photo = byteToBitmap(cursor.getBlob(2));
            restaurant.setRestaurantImage(photo);
            double lat = cursor.getDouble(3);
            double lng = cursor.getDouble(4);
            restaurant.setLatLgn(new LatLng(lat,lng));
            restaurant.setRestaurantType(cursor.getString(5));

        }

        return (restaurant);

    }

    private Bitmap byteToBitmap(byte[] byteArray){
        if(byteArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
            return bitmap;
        }
        return null;
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        if (bitmap!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }
        return null;
    }

}
