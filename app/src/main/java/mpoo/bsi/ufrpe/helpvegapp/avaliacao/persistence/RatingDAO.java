package mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
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
        cursor.close();
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
        String where = DatabaseHelper.getColumnRatingUserId() + " = " + Integer.toString(Session.getUserIn().getUserId()) +
                " AND " + DatabaseHelper.getColumnRatingRestaurantId() +
                " = " + Integer.toString(Session.getCurrentRestaurant().getRestaurantId());
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

    public Rating getRatingFromUser() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int userLoggedId = new UserBusiness().getUserFromSession().getUserId();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetRatingFromUser(), new String[]{Integer.toString(userLoggedId)});

        Rating rating = null;
        if (cursor.moveToFirst()) {
            rating = generateRating(cursor);
        }
        return rating;
    }

    public Rating getRatingFromRestaurant() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int restaurantCurrentId = new RestaurantBusiness().getRestaurantFromSession().getRestaurantId();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetRatingFromRestaurant(), new String[]{Integer.toString(restaurantCurrentId)});

        Rating rating = null;
        if (cursor.moveToFirst()) {
            rating = generateRating(cursor);
        }
        return rating;
    }

    //Media For Food
    public float getMediaColumnRatingFood() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int restaurantCurrentId = new RestaurantBusiness().getRestaurantFromSession().getRestaurantId();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingFood(), new String[]{Integer.toString(restaurantCurrentId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Price
    public float getMediaColumnRatingPrice() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int restaurantCurrentId = new RestaurantBusiness().getRestaurantFromSession().getRestaurantId();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingPrice(), new String[]{Integer.toString(restaurantCurrentId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Service
    public float getMediaColumnRatingService() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int restaurantCurrentId = new RestaurantBusiness().getRestaurantFromSession().getRestaurantId();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingService(), new String[]{Integer.toString(restaurantCurrentId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }

    //Media For Ambiance
    public float getMediaColumnRatingAmbiance() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        int restaurantCurrentId = new RestaurantBusiness().getRestaurantFromSession().getRestaurantId();
        Cursor cursor = db.rawQuery(QueriesSQL.getMediaRatingAmbiance(), new String[]{Integer.toString(restaurantCurrentId)});
        float rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getFloat(0);
        }
        return rate;
    }
}