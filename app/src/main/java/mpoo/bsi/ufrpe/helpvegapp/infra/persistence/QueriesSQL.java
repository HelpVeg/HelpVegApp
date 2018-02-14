package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;


public class QueriesSQL {

    public static String sqlCreateTableUser(){
        return "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUser() + "("
                + DatabaseHelper.getColumnUserId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnUserName() + " text not null, "
                + DatabaseHelper.getColumnUserEmail() + " text unique not null, "
                + DatabaseHelper.getColumnUserPass() + " text not null, "
                + DatabaseHelper.getColumnUserPhoto() + " blob"
                + ");";
    }

    public static String sqlUserLogged(){
        return "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUserLogged() + "("
                + DatabaseHelper.getColumnUserLoggedId() + " integer primary key autoincrement unique not null, "
                + "foreign key ( " + DatabaseHelper.getColumnUserLoggedId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ")";
    }

    public static String sqlCreateTableRestaurant(){
        return "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRestaurants() + "("
                + DatabaseHelper.getColumnRestaurantId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnRestaurantName() + " text not null, "
                + DatabaseHelper.getColumnRestaurantLat() + " integer not null, "
                + DatabaseHelper.getColumnRestaurantLong() + " integer not null, "
                + DatabaseHelper.getColumnRestaurantType() + " text not null"
                + ");";
    }

    public static String sqlCreateTableComments(){
        return  "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableComments() + "("
                + DatabaseHelper.getColumnCommentId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnCommentUserId() + " integer not null, "
                + DatabaseHelper.getColumnCommentRestaurantsId() + " integer not null, "
                + DatabaseHelper.getColumnCommentText() + " text not null, "
                + "foreign key ( " + DatabaseHelper.getColumnCommentUserId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ");";
    }

    public static String sqlCreateTablePhotosRestaurants(){
        return  "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRestaurantPhotos() + "("
                + DatabaseHelper.getColumnRestaurantPhotosId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnPhoto() + " blob, "
                + DatabaseHelper.getColumnRestaurantId() + " integer not null, "
                + "foreign key ( " + DatabaseHelper.getColumnRestaurantId() + " ) references " + DatabaseHelper.getTableRestaurants() + " ( " + DatabaseHelper.getColumnRestaurantId() + " )"
                +");";
    }


    public static String sqlCreateTablePreferences(){
        return  "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTablePreferences() + "("
                + DatabaseHelper.getColumnPreferencesId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnPreferencesUserId() + " integer unique not null, "
                + DatabaseHelper.getColumnPreferencesType() + " text not null, "
                + DatabaseHelper.getColumnPreferencesFood() + " real not null, "
                + DatabaseHelper.getColumnPreferencesPrice() + " real not null, "
                + DatabaseHelper.getColumnPreferencesService() + " real not null, "
                + DatabaseHelper.getColumnPreferencesAmbiance() + " real not null, "
                + "foreign key ( " + DatabaseHelper.getColumnPreferencesUserId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ");";
    }

    public static String sqlCreateTableRating(){
        return  "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRating() + "("
                + DatabaseHelper.getColumnRatingId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnRatingUserId() + " integer not null, "
                + DatabaseHelper.getColumnRatingRestaurantId() + " integer not null, "
                + DatabaseHelper.getColumnRatingFood() + " real not null, "
                + DatabaseHelper.getColumnRatingPrice() + " real not null, "
                + DatabaseHelper.getColumnRatingService() + " real not null, "
                + DatabaseHelper.getColumnRatingAmbiance() + " real not null, "
                + "foreign key ( " + DatabaseHelper.getColumnRatingUserId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " ), "
                + "foreign key ( " + DatabaseHelper.getColumnRatingRestaurantId() + " ) references " + DatabaseHelper.getTableRestaurants() + " ( " + DatabaseHelper.getColumnRestaurantId() + " )"
                + ");";
    }

    public static String sqlDropTablePreferences(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTablePreferences();
    }

    public static String sqlDropTableRating(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRating();
    }


    public static String sqlDropTableUser(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUser();
    }

    public static String sqlDropTableUserLogged(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLogged();
    }

    public static String sqlDropTableRestaurants(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRestaurants();
    }

    public static String sqlDropTableComments(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableComments();
    }

    public static String sqlDropTableRestaurantPhotos(){
        return "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRestaurantPhotos();
    }


    public static String sqlUserFromEmail(){
        return "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserEmail() + " =?;";
    }

    public static String sqlUserFromId(){
        return "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserId() + " =?;";
    }

    public static String sqlUserFromEmailAndPass() {
        return "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE "
                + DatabaseHelper.getColumnUserEmail() + " =? AND "
                + DatabaseHelper.getColumnUserPass() + " =?;";
    }

    /* -------------------------------------- Comments --------------------------------------------- */

    public static String sqlCommentFromUser(){
        return "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
            + DatabaseHelper.getColumnCommentUserId() + " =?";
    }

    public static String sqlCommentFromRestaurant(){
        return "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
            + DatabaseHelper.getColumnCommentRestaurantsId() + " =?";
    }

    public static String sqlCommentFromUserAndRestaurant(){
        return "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
            + DatabaseHelper.getColumnCommentUserId() + " =? AND "
            + DatabaseHelper.getColumnCommentRestaurantsId() + " =?";
    }

    public static String sqlDeleteComment(String userId, String restaurantId){
        return DatabaseHelper.getColumnCommentUserId() + " = " + userId + " AND "
                + DatabaseHelper.getColumnCommentRestaurantsId() + " = " + restaurantId + ";";
    }

    /* ----------------------------------------------------------------------------------------------------- */

    public static String sqlSearchFromLoggedUser() {
        return "SELECT " + DatabaseHelper.getColumnUserLoggedId() + " FROM " + DatabaseHelper.getTableUserLogged() + ";";
    }

    public static String sqlGetAllUsers(){
        return "SELECT * FROM " + DatabaseHelper.getTableUser();
    }

    public static String sqlGetAllRestaurants(){
        return "SELECT * FROM " + DatabaseHelper.getTableRestaurants();
    }

    public static String sqlGetAllPreferences(){
        return "SELECT * FROM " + DatabaseHelper.getTablePreferences();
    }

    public static String sqlGetAllRating(){
        return "SELECT * FROM " + DatabaseHelper.getTableRating();
    }

    public static String sqlGetAllImagesFromRestaurants(){
        return "SELECT * FROM " + DatabaseHelper.getTableRestaurantPhotos() + " WHERE "
                + DatabaseHelper.getColumnRestaurantId() + " =?";
    }

    public static String sqlGetRestaurantFromId(){
        return "SELECT * FROM " + DatabaseHelper.getTableRestaurants() + " WHERE "
                + DatabaseHelper.getColumnRestaurantId() + " =?";
    }

    public static String getPreferencesFromUser(){
        return "SELECT * FROM " + DatabaseHelper.getTablePreferences() + " WHERE "
                + DatabaseHelper.getColumnPreferencesUserId() + " =?";
    }

    public static String sqlGetRatingFromRestaurantAndUser(){
        return "SELECT * FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=? AND "
                + DatabaseHelper.getColumnRatingUserId() + "=?;";
    }


    public static String sqlGetAllRatingsFromUser(){
        return "SELECT * FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingUserId() + "=?";
    }

    // Media for Food
    public static String getMediaRatingFood(){
        return "SELECT AVG("+ DatabaseHelper.getColumnRatingFood() +") FROM "
                + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
    }

    // Media for Price
    public static String getMediaRatingPrice(){
        return "SELECT AVG("+ DatabaseHelper.getColumnRatingPrice() +") FROM "
                + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
    }

    // Media for Service
    public static String getMediaRatingService(){
        return "SELECT AVG("+ DatabaseHelper.getColumnRatingService() +") FROM "
                + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
    }

    // Media for Ambiance
    public static String getMediaRatingAmbiance(){
        return "SELECT AVG("+ DatabaseHelper.getColumnRatingAmbiance() +") FROM "
                + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
    }
}
