package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class Script {
    public Script(){}

    private static String insertRestaurant = "INSERT INTO "
            +DatabaseHelper.getTableRestaurants() +"("
            +DatabaseHelper.getColumnRestaurantName() + " , "
            //+DatabaseHelper.getColumnRestaurantImage() + " , "
            +DatabaseHelper.getColumnRestaurantLat() + " , "
            +DatabaseHelper.getColumnRestaurantLong() + " , "
            +DatabaseHelper.getColumnRestaurantType()
            + ") VALUES ";

    public static void populateRestaurantTable(SQLiteDatabase db){
        db.execSQL(insertRestaurant +"('Restaurante Da Chita', '-8.048148', '-34.954484', 'vegano');");
        db.execSQL(insertRestaurant +"('Vida Longa', '-8.059153', '-34.882352','vegano');");
        db.execSQL(insertRestaurant +"('Dhuzati Coletiva Vegetariana Artesanal', '8.002701', '-34.964109', 'vegano');");


        db.execSQL(insertRestaurant +"('O Vegetariano', '-8.052149 ', '-34.888927', 'vegg');");
        db.execSQL(insertRestaurant +"('Cantina Vegetariana', '-8.064793', '-34.929533', 'vegg');");
        db.execSQL(insertRestaurant +"('Govinda', '-8.054950', '-34.885912', 'vegg');");


        db.execSQL(insertRestaurant +"('Papaya Verde', '-8.045085', '-34.892816' ,'comum');");
        db.execSQL(insertRestaurant +"('Restaurante Véu e Terra', '-8.057843', '-34.886157', 'comum');");
        db.execSQL(insertRestaurant +"('Udon Cozinha Oriental', '-8.028559', '-34.919384', 'comum');");
        db.execSQL(insertRestaurant +"('Pizzeria Vesuvio', '-8.135034', '-34.907661', 'comum');");
        db.execSQL(insertRestaurant +"('Prima Deli', '-8.108943', '-34.890261', 'comum');");
        db.execSQL(insertRestaurant +"('Delipizza', '-8.127488', '-34.905711', 'comum');");
        db.execSQL(insertRestaurant +"('Moo Hamburgueria Gourmet', '-8.125372', '-34.900817', 'comum');");
        db.execSQL(insertRestaurant +"('Markis Place', '-8.052176', '-34.901044', 'comum');");
        db.execSQL(insertRestaurant +"('Escalantes Tex Mex', '-8.110006', '-34.894706', 'comum');");
        db.execSQL(insertRestaurant +"('Snoubar Rua', '-8.031910', '-34.914225', 'comum');");
        db.execSQL(insertRestaurant +"('Café com Ginga' ,'-8.048507', '-34.958128', 'comum');");
        db.execSQL(insertRestaurant +"('Teichi Temakeria', '-8.041253', '-34.958383', 'comum');");
        db.execSQL(insertRestaurant +"('Habibah Cozinha árabe', '-8.044924', '-34.945714', 'comum');");
        db.execSQL(insertRestaurant +"('Pomodoro Café', '-8.035912', '-34.914498', 'comum');");
        db.execSQL(insertRestaurant +"('Gamerz Burguer', '-8.042992', '-34.890739', 'comum');");
        db.execSQL(insertRestaurant +"('Meio Mundo Café Bistrô', '-8.049948', '-34.905200', 'comum');");
        db.execSQL(insertRestaurant +"('Massa Nobre', '-8.047270', '-34.906481', 'comum');");
        db.execSQL(insertRestaurant +"('Dom Black Gourmet Mix', '-8.164133', '-34.913550', 'comum');");
        db.execSQL(insertRestaurant +"('Rockebab', '-8.032142', '-34.919591', 'comum');");
        db.execSQL(insertRestaurant +"('Castigliani Cafés Especiais', '-8.032267', '-34.913345', 'comum');");
        db.execSQL(insertRestaurant +"('Coco Bambu Recife', '-8.119329', '-34.902807', 'comum');");
        db.execSQL(insertRestaurant +"('Bar Central', '-8.057737', '-34.880198', 'comum');");
        db.execSQL(insertRestaurant +"('Bercy', '-8.032225', '-34.913469', 'comum');");
        db.execSQL(insertRestaurant +"('Chica Pitanga', '-8.130797', '-34.900637', 'comum');");
        db.execSQL(insertRestaurant +"('Anjo Solto', '-8.088746', '-34.885643', 'comum');");






    }
}
