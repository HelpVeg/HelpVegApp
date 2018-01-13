package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

}
