package mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence;


import android.content.ContentValues;
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


    public ArrayList<Bitmap> getAllImagesFromRestaurant(int id){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Bitmap> images = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllImagesFromRestaurants(),new String[] {Integer.toString(id)});

        while (cursor.moveToNext()) {
            byte[] byteArray = cursor.getBlob(1);
            Bitmap image = byteToBitmap(byteArray);
            images.add(image);
        }
        return images;
    }


    public void insertRestaurantImage(Bitmap bitmap, int restaurantId){
        byte[] byteImage = bitmapToByte(bitmap);

        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnPhoto(), byteImage);
        values.put(DatabaseHelper.getColumnRestaurantId(), restaurantId);

        Boolean response = db.insert(DatabaseHelper.getTableRestaurantPhotos(), null, values) != -1;
        db.close();
        System.out.println(response.toString());

    }

    public Restaurant generateRestaurant(Cursor cursor){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(cursor.getInt(0));
        restaurant.setRestaurantName(cursor.getString(1));
        double lat = cursor.getDouble(2);
        double lng = cursor.getDouble(3);
        restaurant.setLatLgn(new LatLng(lat, lng));
        restaurant.setRestaurantType(cursor.getString(4));
        ArrayList<Bitmap> imageList = getAllImagesFromRestaurant(restaurant.getRestaurantId());
        restaurant.setRestaurantImages(imageList);

        return restaurant;
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllRestaurants(), null);
        while(cursor.moveToNext()){
            Restaurant restaurant = generateRestaurant(cursor);
            restaurants.add(restaurant);
        }
        cursor.close();
        db.close();
        return restaurants;
    }

    public Restaurant getRestaurantById(int id){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetRestaurantFromId(), new String[] {Integer.toString(id)});

        Restaurant restaurant = null;

        if(cursor.moveToFirst()){
            restaurant = generateRestaurant(cursor);
        }
        return restaurant;

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
