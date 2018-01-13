package mpoo.bsi.ufrpe.helpvegapp.infra.persistence;

public class QueriesSQL {

    public static String sqlCreateTableUser(){
        String sqlCreateTableUser = "CREATE TABLE " + DatabaseHelper.getTableUser() + "(" +
                DatabaseHelper.getColumnUserId() + " integer primary key autoincrement unique not null, " +
                DatabaseHelper.getColumnUserName() + " text not null, " +
                DatabaseHelper.getColumnUserEmail() + " text unique not null, " +
                DatabaseHelper.getColumnUserPass() + " text not null";
                //DatabaseHelper.getColumnUserImg() + " blob";
        return (sqlCreateTableUser);
    }

    public static String sqlUserLogged(){
        String sqlUserLogged = "CREATE TABLE " + DatabaseHelper.getTableUserLogged() + "(" +
                DatabaseHelper.getColumnUserLoggedId() + " integer primary key autoincrement unique not null , "
                + "foreign key ( " + DatabaseHelper.getColumnUserLoggedId() + " ) references " + DatabaseHelper.getTableUser() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                + ")";
        return (sqlUserLogged);
    }

    public static String sqlDropTableUser(){
        String sqlDropTableUser = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUser();
        return sqlDropTableUser();
    }

    public static String sqlDropTableUserLogged(){
        String sqlDropTableUserLogged = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLogged();
        return sqlDropTableUserLogged;
    }

    public static String sqlUserFromEmail(){
        String sqlUserFromEmail = "SELECT * " + DatabaseHelper.getTableUser() + " WHERE " + DatabaseHelper.getColumnUserEmail() + " =?;";
        return (sqlUserFromEmail);
    }
}
