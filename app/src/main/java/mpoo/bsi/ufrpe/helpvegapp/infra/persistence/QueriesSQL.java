package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

public class QueriesSQL {

    public static String sqlCreateTableUser(){
        String sqlCreateTableUser = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUser() + "(" +
                DatabaseHelper.getColumnUserId() + " integer primary key autoincrement unique not null, " +
                DatabaseHelper.getColumnUserName() + " text not null, " +
                DatabaseHelper.getColumnUserEmail() + " text unique not null, " +
                DatabaseHelper.getColumnUserPass() + " text not null"
                + ");";
        return (sqlCreateTableUser);
    }

    public static String sqlUserLogged(){
        String sqlUserLogged = "CREATE TABLE IF NOT EXISTS " + DatabaseHelper.getTableUserLogged() + "(" +
                DatabaseHelper.getColumnUserLoggedId() + " integer primary key autoincrement unique not null, "
                + "foreign key ( " + DatabaseHelper.getColumnUserLoggedId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ")";
        return (sqlUserLogged);
    }

    public static String sqlDropTableUser(){
        String sqlDropTableUser = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUser();
        return (sqlDropTableUser);
    }

    public static String sqlDropTableUserLogged(){
        String sqlDropTableUserLogged = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLogged();
        return (sqlDropTableUserLogged);
    }

    public static String sqlUserFromEmail(){
        String sqlUserFromEmail = "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserEmail() + " =?;";
        return (sqlUserFromEmail);
    }

    public static String sqlUserFromId(){
        String sqlUserFromId =  "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserId() + " =?;";
        return (sqlUserFromId);
    }

    public static String sqlUserFromEmailAndPass() {
        String sqlUserFromEmailAndPass =
                "SELECT * FROM " + DatabaseHelper.getTableUser() + " WHERE "
                        + DatabaseHelper.getColumnUserEmail() + " =? AND "
                        + DatabaseHelper.getColumnUserPass() + " =?;";
        return (sqlUserFromEmailAndPass);
    }

    public static String sqlSearchFromLoggedUser() {
        String sqlSearchFromLoggedUser =
                "SELECT " + DatabaseHelper.getColumnUserLoggedId() + " FROM " + DatabaseHelper.getTableUserLogged() + ";";
        return (sqlSearchFromLoggedUser);
    }

    public static String sqlGetAllUsers(){
        String sqlGetAllUsers = "SELECT * FROM " + DatabaseHelper.getTableUser();
        return sqlGetAllUsers;
    }
}
