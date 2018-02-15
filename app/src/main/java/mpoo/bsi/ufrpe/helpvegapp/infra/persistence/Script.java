package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;

public final class Script {

    private static String insertRestaurant = "INSERT INTO "
            +DatabaseHelper.getTableRestaurants() +"("
            +DatabaseHelper.getColumnRestaurantName() + " , "
            +DatabaseHelper.getColumnRestaurantLat() + " , "
            +DatabaseHelper.getColumnRestaurantLong() + " , "
            +DatabaseHelper.getColumnRestaurantType()
            + ") VALUES ";

    private static String insertUsers = "INSERT INTO "
            +DatabaseHelper.getTableUser() + "("
            +DatabaseHelper.getColumnUserName() + " , "
            +DatabaseHelper.getColumnUserEmail() + " , "
            +DatabaseHelper.getColumnUserPass()
            + ") VALUES ";

    private static String insertPreferences = "INSERT INTO "
            +DatabaseHelper.getTablePreferences() + "("
            +DatabaseHelper.getColumnPreferencesUserId() + " , "
            +DatabaseHelper.getColumnPreferencesType() + " , "
            +DatabaseHelper.getColumnPreferencesFood() + " , "
            +DatabaseHelper.getColumnPreferencesService() + " , "
            +DatabaseHelper.getColumnPreferencesPrice() + " , "
            +DatabaseHelper.getColumnPreferencesAmbiance()
            + ") VALUES ";

    private static String insertRatings = "INSERT INTO "
            +DatabaseHelper.getTableRating() + "("
            +DatabaseHelper.getColumnRatingUserId() + " , "
            +DatabaseHelper.getColumnRatingRestaurantId() + " , "
            +DatabaseHelper.getColumnRatingFood() + " , "
            +DatabaseHelper.getColumnRatingService() + " , "
            +DatabaseHelper.getColumnRatingPrice() + " , "
            +DatabaseHelper.getColumnRatingAmbiance()
            + ") VALUES ";

    public static void populateRestaurantTable(SQLiteDatabase db){

        db.execSQL(insertRestaurant +"('Restaurante Da Chita', '-8.048148', '-34.954484', 'VEGANO');");
        db.execSQL(insertRestaurant +"('Vida Longa', '-8.059153', '-34.882352','VEGANO');");
        db.execSQL(insertRestaurant +"('Dhuzati Vegetariana Artesanal', '-8.002701', '-34.964109', 'VEGANO');");
        db.execSQL(insertRestaurant +"('O Vegetariano', '-8.052149 ', '-34.888927', 'VEGETARIANO_E_VEGANO');");
        db.execSQL(insertRestaurant +"('Cantina Vegetariana', '-8.064793', '-34.929533', 'VEGETARIANO_E_VEGANO');");
        db.execSQL(insertRestaurant +"('Govinda', '-8.054950', '-34.885912', 'VEGETARIANO_E_VEGANO');");
        db.execSQL(insertRestaurant +"('Papaya Verde', '-8.045085', '-34.892816' ,'COMUM');");
        db.execSQL(insertRestaurant +"('Restaurante Véu e Terra', '-8.057843', '-34.886157', 'COMUM');");
        db.execSQL(insertRestaurant +"('Udon Cozinha Oriental', '-8.028559', '-34.919384', 'COMUM');");//9
        db.execSQL(insertRestaurant +"('Pizzeria Vesuvio', '-8.135034', '-34.907661', 'COMUM');");
        db.execSQL(insertRestaurant +"('Prima Deli', '-8.108943', '-34.890261', 'COMUM');");
        db.execSQL(insertRestaurant +"('Delipizza', '-8.127488', '-34.905711', 'COMUM');");
        db.execSQL(insertRestaurant +"('Moo Hamburgueria Gourmet', '-8.125372', '-34.900817', 'COMUM');");
        db.execSQL(insertRestaurant +"('Markis Place', '-8.052176', '-34.901044', 'COMUM');");
        db.execSQL(insertRestaurant +"('Escalantes Tex Mex', '-8.110006', '-34.894706', 'COMUM');");
        db.execSQL(insertRestaurant +"('Snoubar Rua', '-8.031910', '-34.914225', 'COMUM');");
        db.execSQL(insertRestaurant +"('Café com Ginga','-8.048507', '-34.958128', 'COMUM');");
        db.execSQL(insertRestaurant +"('Teichi Temakeria', '-8.041253', '-34.958383', 'COMUM');");
        db.execSQL(insertRestaurant +"('Habibah Cozinha árabe', '-8.044924', '-34.945714', 'COMUM');");
        db.execSQL(insertRestaurant +"('Pomodoro Café', '-8.035912', '-34.914498', 'COMUM');");
        db.execSQL(insertRestaurant +"('Gamerz Burguer', '-8.042992', '-34.890739', 'COMUM');");
        db.execSQL(insertRestaurant +"('Meio Mundo Café Bistrô', '-8.049948', '-34.905200', 'COMUM');");
        db.execSQL(insertRestaurant +"('Massa Nobre', '-8.047270', '-34.906481', 'COMUM');");
        db.execSQL(insertRestaurant +"('Dom Black Gourmet Mix', '-8.164133', '-34.913550', 'COMUM');");
        db.execSQL(insertRestaurant +"('Rockebab', '-8.032142', '-34.919591', 'COMUM');");
        db.execSQL(insertRestaurant +"('Castigliani Cafés Especiais', '-8.032267', '-34.913345', 'COMUM');");
        db.execSQL(insertRestaurant +"('Coco Bambu Recife', '-8.119329', '-34.902807', 'COMUM');");
        db.execSQL(insertRestaurant +"('Bar Central', '-8.057737', '-34.880198', 'COMUM');");
        db.execSQL(insertRestaurant +"('Bercy', '-8.032225', '-34.913469', 'COMUM');");
        db.execSQL(insertRestaurant +"('Chica Pitanga', '-8.130797', '-34.900637', 'COMUM');");
        db.execSQL(insertRestaurant +"('Anjo Solto', '-8.088746', '-34.885643', 'COMUM');");

    }

    public static void populateUserTable(SQLiteDatabase db){
        Md5 md5= new Md5();
        String criptPass = md5.encrypt("123456");
        db.execSQL(insertUsers +"('Antonio', 'toni@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('Junior', 'junior@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('Isabel', 'isabel@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('Gabriel', 'gabriel@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('Cicero', 'cicero@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('Ana', 'ana@gmail.com', '"+ criptPass +  "');");
        db.execSQL(insertUsers +"('tio do RU', 'tiodoru@gmail.com', '"+ criptPass +  "');");
    }

    public static void populatePreferenceTable(SQLiteDatabase db){
        db.execSQL(insertPreferences +"('1', 'COMUM', '4', '4', '5', '4');");
        db.execSQL(insertPreferences +"('2', 'COMUM', '5', '3', '4', '5');");
        db.execSQL(insertPreferences +"('3', 'COMUM', '5', '3', '3', '4');");
        db.execSQL(insertPreferences +"('4', 'COMUM', '5', '5', '1', '5');");
        db.execSQL(insertPreferences +"('5', 'COMUM', '5', '5', '1', '5');");
        db.execSQL(insertPreferences +"('6', 'COMUM', '2', '3', '1', '3');");
        db.execSQL(insertPreferences +"('7', 'COMUM', '5', '4', '1', '3');");

    }

    public static void populateRatingTable(SQLiteDatabase db){
        db.execSQL(insertRatings +"('1', '1', '2', '1', '3', '2');");
        db.execSQL(insertRatings +"('1', '2', '4', '2', '5', '2');");
        db.execSQL(insertRatings +"('1', '3', '2', '5', '1', '5');");
        db.execSQL(insertRatings +"('1', '4', '4', '3', '2', '1');");
        db.execSQL(insertRatings +"('1', '5', '1', '5', '3', '3');");
        db.execSQL(insertRatings +"('1', '6', '3', '4', '3', '3');");
        db.execSQL(insertRatings +"('1', '7', '5', '5', '5', '5');");
        //db.execSQL(insertRatings +"('1', '8', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('1', '9', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('1', '10', '1', '1', '1', '1');");

        db.execSQL(insertRatings +"('2', '1', '2', '1', '2', '3');");
        db.execSQL(insertRatings +"('2', '2', '2', '3', '4', '4');");
        db.execSQL(insertRatings +"('2', '3', '5', '4', '3', '5');");
        db.execSQL(insertRatings +"('2', '4', '4', '4', '4', '5');");
        db.execSQL(insertRatings +"('2', '5', '1', '3', '5', '1');");
        db.execSQL(insertRatings +"('2', '6', '4', '4', '2', '1');");
        db.execSQL(insertRatings +"('2', '7', '4', '5', '5', '4');");
        //db.execSQL(insertRatings +"('2', '8', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('2', '9', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('2', '10', '1', '1', '1', '1');");

        db.execSQL(insertRatings +"('3', '1', '4', '2', '1', '2');");
        db.execSQL(insertRatings +"('3', '2', '5', '3', '4', '4');");
        db.execSQL(insertRatings +"('3', '3', '4', '5', '1', '5');");
        db.execSQL(insertRatings +"('3', '4', '3', '2', '3', '4');");
        db.execSQL(insertRatings +"('3', '5', '5', '1', '3', '5');");
        db.execSQL(insertRatings +"('3', '6', '1', '2', '3', '2');");
        db.execSQL(insertRatings +"('3', '7', '5', '4', '5', '5');");
        //db.execSQL(insertRatings +"('3', '8', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('3', '9', '1', '1', '1', '1');");
        //db.execSQL(insertRatings +"('3', '10', '1', '1', '1', '1');");

        db.execSQL(insertRatings +"('5', '1', '2', '3', '1', '2');");
        db.execSQL(insertRatings +"('5', '2', '2', '4', '5', '2');");
        db.execSQL(insertRatings +"('5', '3', '3', '5', '1', '3');");
        db.execSQL(insertRatings +"('5', '4', '1', '3', '4', '4');");
        db.execSQL(insertRatings +"('5', '5', '4', '3', '2', '3');");
        db.execSQL(insertRatings +"('5', '6', '3', '2', '3', '1');");
        db.execSQL(insertRatings +"('5', '7', '4', '4', '4', '4');");
        /*db.execSQL(insertRatings +"('5', '8', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('5', '9', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('5', '10', '1', '1', '1', '1');");

        db.execSQL(insertRatings +"('7', '1', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('7', '2', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('7', '3', '5', '5', '5', '5');");
        db.execSQL(insertRatings +"('7', '4', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '5', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '6', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '7', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '8', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '9', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('7', '10', '1', '1', '1', '1');");

        db.execSQL(insertRatings +"('6', '1', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('6', '2', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('6', '3', '5', '5', '5', '5');");
        db.execSQL(insertRatings +"('6', '4', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '5', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '6', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '7', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '8', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '9', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('6', '10', '1', '1', '1', '1');");


        db.execSQL(insertRatings +"('4', '7', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('4', '8', '2', '2', '2', '2');");
        db.execSQL(insertRatings +"('4', '10', '1', '1', '1', '1');");
        db.execSQL(insertRatings +"('4', '9', '5', '5', '5', '5');");
        db.execSQL(insertRatings +"('4', '10', '1', '1', '1', '1');");*/
    }

}
