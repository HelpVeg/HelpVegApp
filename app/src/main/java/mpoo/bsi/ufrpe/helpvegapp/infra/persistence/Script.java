package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class Script {
    public Script(){}

    private static String insertRestaurant = "INSERT INTO " + DatabaseHelper.getTableRestaurants() +"("
            +DatabaseHelper.getColumnRestaurantName() + " , "
            +DatabaseHelper.getColumnRestaurantImage() + " , "
            +DatabaseHelper.getColumnRestaurantLat() + " , "
            +DatabaseHelper.getColumnRestaurantLong() + " , "
            +DatabaseHelper.getColumnRestaurantType() + " , "
            +DatabaseHelper.getColumnRestaurantRateGeneral() + " , "
            +DatabaseHelper.getColumnRestaurantRateService() + " , "
            +DatabaseHelper.getColumnRestaurantRateFood() + " , "
            +DatabaseHelper.getColumnRestaurantRatePrice() + " , "
            +DatabaseHelper.getColumnRestaurantRateEnvironment() +
            ") VALUES ";

    public static void populateRestaurantTable(SQLiteDatabase db){
        db.execSQL(insertRestaurant +"('Dona Maria', , 'lat', 'long', 'vegano', '0', '0', '0', '0', '0');");
    }
}
