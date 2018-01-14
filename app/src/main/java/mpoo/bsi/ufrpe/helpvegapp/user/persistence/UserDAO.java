package mpoo.bsi.ufrpe.helpvegapp.user.persistence;
//mantem o banco funcionando
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class UserDAO{
   private SQLiteDatabase db;
   private DatabaseHelper database = Session.getActuallyDb();

   /**
   *<h1>UserDAO</h1>
   *
   *
    */

   //Criar usuário
   public User createUser(Cursor cursor){
       User user = new User();
       user.setUserId(cursor.getInt(0));
       user.setUserName(cursor.getString(1));
       user.setUserEmail(cursor.getString(2));
       user.setUserPassword(cursor.getString(3));
       return user;
   }

   //Registra usuário
   public void registerUser(User user){
      db = database.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(DatabaseHelper.getColumnUserName(), user.getUserName());
      values.put(DatabaseHelper.getColumnUserEmail(), user.getUserEmail());
      values.put(DatabaseHelper.getColumnUserPass(), user.getUserPassword());
      db.insert(DatabaseHelper.getTableUser(), null, values);
      db.close();
   }

   // Busca usuário por e-mail
   public User searchUserForEmail(String email){
       db = database.getReadableDatabase();

       User user = null;
       Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmail(), new String[] {email});

       if(cursor.moveToFirst()){
           user = createUser(cursor);
       }
       db.close();
       cursor.close();
       return user;
   }

   //Busca usuário por id
   public User searchUserForId(int userId){

       db = database.getReadableDatabase();

       User user = null;
       Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromId(), new String[]{Integer.toString(userId)});

       if(cursor.moveToFirst()) {
           user = createUser(cursor);
       }
       cursor.close();
       db.close();
       return user;
   }

   //Validar se usuário está cadastrado
    public boolean userValidateLogin(String email, String password){
       db = database.getReadableDatabase();
       boolean validate = false;
       Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmailAndPass() , new String[] {email, password});
       if (cursor.moveToFirst()){
           validate = true;
       }
       return validate;
    }
    //meu

    public void create (User user){
        ContentValues values = new ContentValues();
        values.put("name",user.getUserName());
        values.put("email",user.getUserEmail());
        values.put("password",user.getUserPassword());

        database.getWritableDatabase().insert("user",null,values);
    }
    //0 de registro ja criados
    public int totalContacts(){
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM contato";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }
    //lista de contatos
    public List<User> ContactList(){
        List<User> users = new ArrayList<>();
        String sql  = "SELECT * FROM user ORDER by id DESC";

        SQLiteDatabase db = database.getReadableDatabase();

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
    public User SearchUserForId(int userId){
        User user = new User();

        SQLiteDatabase db = database.getReadableDatabase();

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
    public boolean update(User user) {
        ContentValues values = new ContentValues();

        values.put("name", user.getUserName());
        values.put("email", user.getUserEmail());
        values.put("password", user.getUserPassword());

        String where = "id = ?";

        String[] whereArgs = {Integer.toString(user.getUserId())};

        SQLiteDatabase db = database.getWritableDatabase();

        boolean isUpdate = db.update("user", values,
                where, whereArgs) > 0;

        db.close();

        return isUpdate;
    }
}
