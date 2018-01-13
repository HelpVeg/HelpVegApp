package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class UserDAO{

   private SQLiteDatabase db;
   private DatabaseHelper database = Session.getActuallyDb();

   public void registerUser(User user){
      db = database.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(DatabaseHelper.getColumnUserName(), user.getUserName());
      values.put(DatabaseHelper.getColumnUserEmail(), user.getUserEmail());
      values.put(DatabaseHelper.getColumnUserPass(), user.getUserPassword());
      db.insert(DatabaseHelper.getTableUser(), null, values);
      db.close();
   }

}
