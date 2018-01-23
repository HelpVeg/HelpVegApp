package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class UserDAO{

    public boolean createUser(User user) {

        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnUserName(), user.getUserName());
        values.put(DatabaseHelper.getColumnUserEmail(), user.getUserEmail());
        values.put(DatabaseHelper.getColumnUserPass(), user.getUserPassword());

        Boolean response = db.insert(DatabaseHelper.getTableUser(), null, values) != -1;
        db.close();
        return response;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnUserId(), user.getUserId());
        values.put(DatabaseHelper.getColumnUserName(), user.getUserName());
        values.put(DatabaseHelper.getColumnUserEmail(), user.getUserEmail());
        values.put(DatabaseHelper.getColumnUserPass(), user.getUserPassword());

        Boolean response = db.update(DatabaseHelper.getTableUser(), values, QueriesSQL.sqlUserFromId(), null) > 0;
        db.close();
        return response;
    }

    public ArrayList<User> getAllUsers() {

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllUsers(), null);

        if (cursor.moveToFirst()) {

            do {
                User user = new User();
                user.setUserId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setUserEmail(cursor.getString(2));
                user.setUserPassword(cursor.getString(3));
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return users;
    }

    public User getSingleUser(int user_id) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromId(), new String[] {Integer.toString(user_id)});

        if (cursor.moveToFirst()) {
            user = new User();
            user.setUserId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setUserEmail(cursor.getString(2));
            user.setUserPassword(cursor.getString(3));
        }
        cursor.close();
        db.close();
        return user;
    }

    public User getLoginUser(String email, String pass){

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();

        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmailAndPass(), new String[] {email, pass});

        if(cursor.moveToFirst()){
            user = new User();
            user.setUserId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setUserEmail(cursor.getString(2));
            user.setUserPassword(cursor.getString(3));
        }
        cursor.close();
        db.close();
        return user;
    }

    public void insertLoggedUser(User user){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        ContentValues values = new ContentValues();

        String columnUserLoggedId = DatabaseHelper.getColumnUserLoggedId();
        values.put(columnUserLoggedId, user.getUserId());

        db.insert(DatabaseHelper.getTableUserLogged(), null, values);
    }

    public User getLoggedUser(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlSearchFromLoggedUser(), null);

        if(cursor.moveToNext()){
            int idUser = cursor.getInt(0);
            user = getSingleUser(idUser);
        }
        cursor.close();
        db.close();
        return user;
    }

    public void removeLoggedUser(){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        db.delete(DatabaseHelper.getTableUserLogged(), null, null);
        db.close();

    }

    public Boolean getLogin(String email, String pass){

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();

        Boolean validate = false;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmailAndPass(), new String[] {email, pass});

        if(cursor.moveToFirst()){
            validate = true;
        }
        cursor.close();
        db.close();
        return validate;
    }

}
