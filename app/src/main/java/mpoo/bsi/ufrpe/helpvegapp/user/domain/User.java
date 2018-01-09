package mpoo.bsi.ufrpe.helpvegapp.user.domain;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private byte[] userImage;
    private Context context;
    private boolean delete;


    public User(Context context){
        this.context = context;
    }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte[] getUserImage() {
        return userImage;
    }
    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }



    public boolean delete(){
        UserDAO userDAO = null;
        SQLiteDatabase sqLiteDatabase = null;
        try {
            userDAO = new UserDAO(context, "DBUser.db", null, 1);
            sqLiteDatabase = userDAO.getWritableDatabase();
            String sql = "";

            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.delete("user","userId = ?",new String[]{String.valueOf(userId)});

            delete = true;

            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }
    public boolean save(){
        UserDAO userDAO = null;
        SQLiteDatabase sqLiteDatabase = null;
        try {
            userDAO = new UserDAO(context, "DBUser.db", null, 1);
            sqLiteDatabase = userDAO.getWritableDatabase();
            String sql = "";
            if (userId == -1){
                sql = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
            }else{
                sql = "UPDATE user SET name = ?,email = ?, password = ? WHERE userId = ?";
            }
            sqLiteDatabase.beginTransaction();
            //comando sql no banco
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1,"name");
            sqLiteStatement.bindString(2,"email");
            sqLiteStatement.bindString(3,"password");
            if(userId != -1)
                sqLiteStatement.bindString(4, String.valueOf(userId));

            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
            if (userDAO != null) {
                userDAO.close();
            }
        }
    }

    public ArrayList<User> getUsers(){
        UserDAO userDAO = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<User> users = new ArrayList<>();
        try{
            userDAO = new UserDAO(context,"DBUser.db",null,1);
            sqLiteDatabase = userDAO.getReadableDatabase();
            cursor = sqLiteDatabase.query("user",null,null,null,null,null,null);
            while(cursor.moveToNext()){
                User user = new User(context);
                user.userId=cursor.getInt(cursor.getColumnIndex("userId"));
                user.userName=cursor.getString(cursor.getColumnIndex("userName"));
                user.userPassword=cursor.getString(cursor.getColumnIndex("userPassword"));
                user.setUserEmail(cursor.getString(cursor.getColumnIndex("userEmail")));
                users.add(user);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
            if (userDAO != null){
                userDAO.close();
            }
        }
        return users;
    }
    public void loadUserById(int userId){
        UserDAO userDAO = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try{
            userDAO = new UserDAO(context,"DBUser.db",null,1);
            sqLiteDatabase = userDAO.getReadableDatabase();
            cursor = sqLiteDatabase.query("user",null,"userId = ?",new String[]{String.valueOf(userId)},null,null,null);
            while(cursor.moveToNext()){
                this.userId=cursor.getInt(cursor.getColumnIndex("userId"));
                userName=cursor.getString(cursor.getColumnIndex("userName"));
                userPassword=cursor.getString(cursor.getColumnIndex("userPassword"));
                setUserEmail(cursor.getString(cursor.getColumnIndex("userEmail")));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
            if (userDAO != null){
                userDAO.close();
            }
        }
    }
}
