package mpoo.bsi.ufrpe.helpvegapp.user.business;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.DbHelper;


public class UserController  extends DbHelper{

    public UserController(Context context){
        super(context);
    }

    public boolean create (User user){
        ContentValues values = new ContentValues();
        values.put("name",user.getUserName());
        values.put("email",user.getUserEmail());
        values.put("password",user.getUserPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("user",null,values) > 0;
        db.close();

        return isCreate;
    }
    //0 de registro ja criados
    public int totalContacts(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }
    //lista de contatos
    public List<User> ContactList(){
        List<User> users = new ArrayList<>();
        String sql  = "SELECT * FROM user ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){

            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String password = cursor.getString(cursor.getColumnIndex("password"));

                User user = new User();

                user.setUserId(id);
                user.setUserName(name);
                user.setUserEmail(email);
                user.setUserPassword(password);

                users.add(user);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return users;
    }
    //busca pelo id
    public User SearchForId(int userId){
        User user = new User();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM user WHERE id = "+userId;

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            user.setUserId(userId);
            user.setUserName(name);
            user.setUserEmail(email);
            user.setUserPassword(password);
        }
        db.close();
        cursor.close();
        return user;
    }
    //altera objeto
    public boolean update(User user){
        ContentValues values = new ContentValues();

        values.put("name", user.getUserName());
        values.put("email", user.getUserEmail());
        values.put("password",user.getUserPassword());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(user.getUserId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("user", values,
                where, whereArgs) > 0;

        db.close();

        return  isUpdate;

    }
    //remove o objeto
    public boolean delete(int userId){
        boolean isDeleted = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("user", "id ='" + userId + "'", null) > 0;
        db.close();

        return isDeleted;

    }
}
