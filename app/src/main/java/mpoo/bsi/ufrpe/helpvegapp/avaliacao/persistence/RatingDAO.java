package mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class RatingDAO {

    public Rating generateRating(Cursor cursor) {
        Rating rating = new Rating();
        rating.setRatingId(cursor.getInt(0));
        int userId = cursor.getInt(1);
        rating.setUserRating(new UserBusiness().getUserById(userId));
        int restaurantId = cursor.getInt(2);
        rating.setRestaurantRating(new RestaurantBusiness().getRestaurantFromId(restaurantId));
        rating.setFood(cursor.getFloat(3));
        rating.setPrice(cursor.getFloat(4));
        rating.setService(cursor.getFloat(5));
        rating.setAmbiance(cursor.getFloat(6));
        return rating;
    }

    public boolean createRating(Rating rating) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnRatingUserId(), rating.getUserRating().getUserId());
        values.put(DatabaseHelper.getColumnRatingRestaurantId(), rating.getRestaurantRating().getRestaurantId());
        values.put(DatabaseHelper.getColumnRatingFood(), rating.getFood());
        values.put(DatabaseHelper.getColumnRatingPrice(), rating.getPrice());
        values.put(DatabaseHelper.getColumnRatingService(), rating.getService());
        values.put(DatabaseHelper.getColumnRatingAmbiance(), rating.getAmbiance());

        boolean insert = db.insert(DatabaseHelper.getTableRating(), null, values) != -1;
        db.close();
        return insert;
    }

    public void updateRating(Rating rating) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        int restaurantId = rating.getRestaurantRating().getRestaurantId();
        int userId = rating.getUserRating().getUserId();

        String where = DatabaseHelper.getColumnRatingUserId() + " = " + Integer.toString(userId) +
                " AND " + DatabaseHelper.getColumnRatingRestaurantId() +
                " = " + Integer.toString(restaurantId);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.getColumnRatingUserId(), rating.getUserRating().getUserId());
        values.put(DatabaseHelper.getColumnRatingRestaurantId(), rating.getRestaurantRating().getRestaurantId());
        values.put(DatabaseHelper.getColumnRatingFood(), rating.getFood());
        values.put(DatabaseHelper.getColumnRatingPrice(), rating.getPrice());
        values.put(DatabaseHelper.getColumnRatingService(), rating.getService());
        values.put(DatabaseHelper.getColumnRatingAmbiance(), rating.getAmbiance());
        db.update(DatabaseHelper.getTableRating(), values, where, null);
        db.close();
    }

    public ArrayList<Rating> getAllRatingsFromUser(int userId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllRatingsFromUser(), new String[]{Integer.toString(userId)});
        ArrayList<Rating> userRatings = new ArrayList<>();

        while (cursor.moveToNext()) {
            Rating rating = generateRating(cursor);
            userRatings.add(rating);
        }
        cursor.close();
        db.close();
        return userRatings;
    }

    public ArrayList<Rating> getAllRating(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllRating(), null);
        ArrayList<Rating> ratings = new ArrayList<>();

        while (cursor.moveToNext()) {
            Rating rating = generateRating(cursor);
            ratings.add(rating);
        }
        cursor.close();
        db.close();
        return ratings;
    }


    public Rating getRatingFromRestaurantAndUser(int restaurantId, int userId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetRatingFromRestaurantAndUser(),
                new String[]{Integer.toString(restaurantId),Integer.toString(userId)});

        Rating rating = null;
        if (cursor.moveToFirst()) {
            rating = generateRating(cursor);
        }
        return rating;
    }

    //Media For Food
    public float getMediaColumnRatingFood(int restaurantId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingFood(), new String[]{Integer.toString(restaurantId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Price
    public float getMediaColumnRatingPrice(int restaurantId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingPrice(), new String[]{Integer.toString(restaurantId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Service
    public float getMediaColumnRatingService(int restaurantId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingService(), new String[]{Integer.toString(restaurantId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Ambiance
    public float getMediaColumnRatingAmbiance(int restaurantId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingAmbiance(), new String[]{Integer.toString(restaurantId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }
}