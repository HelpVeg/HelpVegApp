package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;


public class QueriesSQL {

    public static String sqlCreateTableUser(){
        String sqlCreateTableUser = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUser() + "("
                + DatabaseHelper.getColumnUserId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnUserName() + " text not null, "
                + DatabaseHelper.getColumnUserEmail() + " text unique not null, "
                + DatabaseHelper.getColumnUserPass() + " text not null, "
                + DatabaseHelper.getColumnUserPhoto() + " blob"
                + ");";
        return (sqlCreateTableUser);
    }

    public static String sqlUserLogged(){
        String sqlUserLogged = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUserLogged() + "("
                + DatabaseHelper.getColumnUserLoggedId() + " integer primary key autoincrement unique not null, "
                + "foreign key ( " + DatabaseHelper.getColumnUserLoggedId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ")";
        return (sqlUserLogged);
    }

    public static String sqlCreateTableRestaurant(){
        String sqlCreateTableRestaurant = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRestaurants() + "("
                + DatabaseHelper.getColumnRestaurantId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnRestaurantName() + " text not null, "
                + DatabaseHelper.getColumnRestaurantLat() + " integer not null, "
                + DatabaseHelper.getColumnRestaurantLong() + " integer not null, "
                + DatabaseHelper.getColumnRestaurantType() + " text not null"
                + ");";
        return sqlCreateTableRestaurant;
    }

    public static String sqlCreateTableComments(){
        String sqlCreateTableComment = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableComments() + "("
                + DatabaseHelper.getColumnCommentId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnCommentUserId() + " integer not null, "
                + DatabaseHelper.getColumnCommentRestaurantsId() + " integer not null, "
                + DatabaseHelper.getColumnCommentText() + " text not null, "
                + "foreign key ( " + DatabaseHelper.getColumnCommentUserId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ");";
        return sqlCreateTableComment;
    }

    public static String sqlCreateTablePhotosRestaurants(){
        String sqlCreateTablePhotosRestaurants = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRestaurantPhotos() + "("
                + DatabaseHelper.getColumnRestaurantPhotosId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnPhoto() + " blob, "
                + DatabaseHelper.getColumnRestaurantId() + " integer not null, "
                + "foreign key ( " + DatabaseHelper.getColumnRestaurantId() + " ) references " + DatabaseHelper.getTableRestaurants() + " ( " + DatabaseHelper.getColumnRestaurantId() + " )"
                +");";
        return sqlCreateTablePhotosRestaurants;
    }


    public static String sqlCreateTablePreferences(){
        String sqlCreateTablePreferences = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTablePreferences() + "("
                + DatabaseHelper.getColumnPreferencesId() + " integer primary key autoincrement unique not null, "
                + DatabaseHelper.getColumnPreferencesUserId() + " integer unique not null, "
                + DatabaseHelper.getColumnPreferencesType() + " text not null, "
                + DatabaseHelper.getColumnPreferencesFood() + " real not null, "
                + DatabaseHelper.getColumnPreferencesPrice() + " real not null, "
                + DatabaseHelper.getColumnPreferencesService() + " real not null, "
                + DatabaseHelper.getColumnPreferencesAmbiance() + " real not null, "
                + "foreign key ( " + DatabaseHelper.getColumnPreferencesUserId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ");";
        return sqlCreateTablePreferences;
    }

    public static String sqlCreateTableRating(){
        String sqlCreateTableRating = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableRating() + "("
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
        return sqlCreateTableRating;
    }

    public static String sqlDropTablePreferences(){
        String sqlDropTablePreferences = "DROP TABLE IF EXISTS " + DatabaseHelper.getTablePreferences();
        return sqlDropTablePreferences;
    }

    public static String sqlDropTableRating(){
        String sqlDropTableRating = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRating();
        return sqlDropTableRating;
    }


    public static String sqlDropTableUser(){
        String sqlDropTableUser = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUser();
        return (sqlDropTableUser);
    }

    public static String sqlDropTableUserLogged(){
        String sqlDropTableUserLogged = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLogged();
        return (sqlDropTableUserLogged);
    }

    public static String sqlDropTableRestaurants(){
        String sqlDropTableRestaurants = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRestaurants();
        return sqlDropTableRestaurants;
    }

    public static String sqlDropTableComments(){
        String sqlDropTableComments = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableComments();
        return sqlDropTableComments;
    }

    public static String sqlDropTableRestaurantPhotos(){
        String sqlDropTableRestaurantPhotos = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableRestaurantPhotos();
        return sqlDropTableRestaurantPhotos;
    }


    public static String sqlUserFromEmail(){
        String sqlUserFromEmail = "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserEmail() + " =?;";
        return sqlUserFromEmail;
    }

    public static String sqlUserFromId(){
        String sqlUserFromId =  "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserId() + " =?;";
        return sqlUserFromId;
    }

    public static String sqlUserFromEmailAndPass() {
        String sqlUserFromEmailAndPass =
                "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE "
                        + DatabaseHelper.getColumnUserEmail() + " =? AND "
                        + DatabaseHelper.getColumnUserPass() + " =?;";
        return sqlUserFromEmailAndPass;
    }

    /* -------------------------------------- Comments --------------------------------------------- */

    public static String sqlCommentFromUser(){
        String sqlCommentFromUser =
                "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
                    + DatabaseHelper.getColumnCommentUserId() + " =?";
        return sqlCommentFromUser;
    }

    public static String sqlCommentFromRestaurant(){
        String sqlCommentFromRestaurant =
                "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
                    + DatabaseHelper.getColumnCommentRestaurantsId() + " =?";
        return sqlCommentFromRestaurant;
    }

    public static String sqlCommentFromUserAndRestaurant(){
        String sqlCommentFromUserAndRestaurant =
                "SELECT * FROM " + DatabaseHelper.getTableComments() + " WHERE "
                    + DatabaseHelper.getColumnCommentUserId() + " =? AND "
                    + DatabaseHelper.getColumnCommentRestaurantsId() + " =?";
        return sqlCommentFromUserAndRestaurant;
    }

    /* ----------------------------------------------------------------------------------------------------- */

    public static String sqlSearchFromLoggedUser() {
        String sqlSearchFromLoggedUser =
                "SELECT " + DatabaseHelper.getColumnUserLoggedId() + " FROM " + DatabaseHelper.getTableUserLogged() + ";";
        return sqlSearchFromLoggedUser;
    }

    public static String sqlGetAllUsers(){
        String sqlGetAllUsers = "SELECT * FROM " + DatabaseHelper.getTableUser();
        return sqlGetAllUsers;
    }

    public static String sqlGetAllRestaurants(){
        String sqlGetAllRestaurants = "SELECT * FROM " + DatabaseHelper.getTableRestaurants();
        return sqlGetAllRestaurants;
    }

    public static String sqlGetAllPreferences(){
        String sqlGetAllPreferences = "SELECT * FROM " + DatabaseHelper.getTablePreferences();
        return sqlGetAllPreferences;
    }

    public static String sqlGetAllRating(){
        String sqlGetAllPreferences = "SELECT * FROM " + DatabaseHelper.getTableRating();
        return sqlGetAllPreferences;
    }

    public static String sqlGetAllImagesFromRestaurants(){
        String sqlImagesFromRestaurant = "SELECT * FROM " + DatabaseHelper.getTableRestaurantPhotos() +
                " WHERE " + DatabaseHelper.getColumnRestaurantId() + " =?";
        return sqlImagesFromRestaurant;
    }

    public static String sqlGetRestaurantFromId(){
        String sqlGetRestaurantById = "SELECT * FROM " + DatabaseHelper.getTableRestaurants() +
                " WHERE " + DatabaseHelper.getColumnRestaurantId() + " =?";
        return sqlGetRestaurantById;
    }

    public static String getPreferencesFromUser(){
        String sqlGetPreferencesFromUser = "SELECT * FROM " + DatabaseHelper.getTablePreferences() +
                " WHERE " + DatabaseHelper.getColumnPreferencesUserId() + " =?";
        return sqlGetPreferencesFromUser;
    }

    public static String sqlGetRatingFromRestaurantAndUser(){
        String getRatingFromRestaurantAndUser = "SELECT * FROM " + DatabaseHelper.getTableRating() +
                " WHERE " + DatabaseHelper.getColumnRatingRestaurantId() +
                "=? AND " + DatabaseHelper.getColumnRatingUserId() + "=?;";
        return getRatingFromRestaurantAndUser;
    }


    public static String sqlGetRatingFromUser(){
        String getRatingFromUser = "SELECT * FROM " + DatabaseHelper.getTableRating() +
                " WHERE " + DatabaseHelper.getColumnRatingUserId() + "=?";
        return getRatingFromUser;
    }



    public static String getMediaRating(String columnRating){
        String getMediaRating = "SELECT AVG("+ columnRating +") FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
        return getMediaRating;
    }

    // Media for Food
    public static String getMediaRatingFood(){
        String getMediaRatingFood = "SELECT AVG("+ DatabaseHelper.getColumnRatingFood() +") FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
        return getMediaRatingFood;
    }

    // Media for Price
    public static String getMediaRatingPrice(){
        String getMediaRatingPrice = "SELECT AVG("+ DatabaseHelper.getColumnRatingPrice() +") FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
        return getMediaRatingPrice;
    }

    // Media for Service
    public static String getMediaRatingService(){
        String getMediaRatingService = "SELECT AVG("+ DatabaseHelper.getColumnRatingService() +") FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
        return getMediaRatingService;
    }

    // Media for Ambiance
    public static String getMediaRatingAmbiance(){
        String getMediaRatingAmbiance = "SELECT AVG("+ DatabaseHelper.getColumnRatingAmbiance() +") FROM " + DatabaseHelper.getTableRating() + " WHERE "
                + DatabaseHelper.getColumnRatingRestaurantId() + "=?";
        return getMediaRatingAmbiance;
    }
}
