package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class Script {

    private static String insertRestaurant = "INSERT INTO "
            +DatabaseHelper.getTableRestaurants() +"("
            +DatabaseHelper.getColumnRestaurantName() + " , "
            +DatabaseHelper.getColumnRestaurantImage() + " , "
            +DatabaseHelper.getColumnRestaurantLat() + " , "
            +DatabaseHelper.getColumnRestaurantLong() + " , "
            +DatabaseHelper.getColumnRestaurantType()
            + ") VALUES ";

    public static void populateRestaurantTable(SQLiteDatabase db){

        db.execSQL(insertRestaurant +"('Restaurante Da Chita', null, '-8.048148', '-34.954484', 'vegano');");
        db.execSQL(insertRestaurant +"('Vida Longa', null, '-8.059153', '-34.882352','vegano');");
        db.execSQL(insertRestaurant +"('Dhuzati Vegetariana Artesanal', null, '-8.002701', '-34.964109', 'vegano');");

        db.execSQL(insertRestaurant +"('O Vegetariano', null, '-8.052149 ', '-34.888927', 'vegetariano e vegano');");
        db.execSQL(insertRestaurant +"('Cantina Vegetariana', null, '-8.064793', '-34.929533', 'vegetariano e vegano');");
        db.execSQL(insertRestaurant +"('Govinda', null, '-8.054950', '-34.885912', 'vegetariano e vegano');");

        db.execSQL(insertRestaurant +"('Papaya Verde', null, '-8.045085', '-34.892816' ,'comum');");
        db.execSQL(insertRestaurant +"('Restaurante Véu e Terra', null, '-8.057843', '-34.886157', 'comum');");
        db.execSQL(insertRestaurant +"('Udon Cozinha Oriental', null, '-8.028559', '-34.919384', 'comum');");
        db.execSQL(insertRestaurant +"('Pizzeria Vesuvio', null, '-8.135034', '-34.907661', 'comum');");
        db.execSQL(insertRestaurant +"('Prima Deli', null, '-8.108943', '-34.890261', 'comum');");
        db.execSQL(insertRestaurant +"('Delipizza', null, '-8.127488', '-34.905711', 'comum');");
        db.execSQL(insertRestaurant +"('Moo Hamburgueria Gourmet', null, '-8.125372', '-34.900817', 'comum');");
        db.execSQL(insertRestaurant +"('Markis Place', null, '-8.052176', '-34.901044', 'comum');");
        db.execSQL(insertRestaurant +"('Escalantes Tex Mex', null, '-8.110006', '-34.894706', 'comum');");
        db.execSQL(insertRestaurant +"('Snoubar Rua', null, '-8.031910', '-34.914225', 'comum');");
        db.execSQL(insertRestaurant +"('Café com Ginga',  null,'-8.048507', '-34.958128', 'comum');");
        db.execSQL(insertRestaurant +"('Teichi Temakeria', null, '-8.041253', '-34.958383', 'comum');");
        db.execSQL(insertRestaurant +"('Habibah Cozinha árabe', null, '-8.044924', '-34.945714', 'comum');");
        db.execSQL(insertRestaurant +"('Pomodoro Café', null, '-8.035912', '-34.914498', 'comum');");
        db.execSQL(insertRestaurant +"('Gamerz Burguer', null, '-8.042992', '-34.890739', 'comum');");
        db.execSQL(insertRestaurant +"('Meio Mundo Café Bistrô', null, '-8.049948', '-34.905200', 'comum');");
        db.execSQL(insertRestaurant +"('Massa Nobre', null, '-8.047270', '-34.906481', 'comum');");
        db.execSQL(insertRestaurant +"('Dom Black Gourmet Mix', null, '-8.164133', '-34.913550', 'comum');");
        db.execSQL(insertRestaurant +"('Rockebab', null, '-8.032142', '-34.919591', 'comum');");
        db.execSQL(insertRestaurant +"('Castigliani Cafés Especiais', null, '-8.032267', '-34.913345', 'comum');");
        db.execSQL(insertRestaurant +"('Coco Bambu Recife', null, '-8.119329', '-34.902807', 'comum');");
        db.execSQL(insertRestaurant +"('Bar Central', null, '-8.057737', '-34.880198', 'comum');");
        db.execSQL(insertRestaurant +"('Bercy', null, '-8.032225', '-34.913469', 'comum');");
        db.execSQL(insertRestaurant +"('Chica Pitanga', null, '-8.130797', '-34.900637', 'comum');");
        db.execSQL(insertRestaurant +"('Anjo Solto', null, '-8.088746', '-34.885643', 'comum');");
    }
}
