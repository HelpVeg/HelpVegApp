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
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.EnumRestaurantType;
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
        cursor.close();
        return images;
    }

    public ArrayList<Restaurant> getRestaurantsFromType(EnumRestaurantType type){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.getTableRestaurants()
                + " WHERE " + DatabaseHelper.getColumnRestaurantType() + " =?;";
        Cursor cursor = db.rawQuery(query,new String[]{type.name()});
        while (cursor.moveToNext()){
            Restaurant restaurant = generateRestaurant(cursor);
            restaurants.add(restaurant);
        }
        cursor.close();
        db.close();
        return restaurants;
    }


    public void insertRestaurantImage(Bitmap bitmap, int restaurantId){
        byte[] byteImage = bitmapToByte(bitmap);

        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnPhoto(), byteImage);
        values.put(DatabaseHelper.getColumnRestaurantId(), restaurantId);

        db.insert(DatabaseHelper.getTableRestaurantPhotos(), null, values);
        db.close();

    }

    private Restaurant generateRestaurant(Cursor cursor){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(cursor.getInt(0));
        restaurant.setRestaurantName(cursor.getString(1));
        double lat = cursor.getDouble(2);
        double lng = cursor.getDouble(3);
        String type = cursor.getString(4);

        restaurant.setLatLgn(new LatLng(lat, lng));
        restaurant.setRestaurantType(EnumRestaurantType.valueOf(type));
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
            return  BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
        }
        return null;
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        if (bitmap!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }
        return null;
    }

}
