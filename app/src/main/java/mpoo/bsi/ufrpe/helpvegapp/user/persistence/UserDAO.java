package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO extends SQLiteOpenHelper{

    private static final String NAME_D = "DBUser.db";
    private static final int VERSION = 1;

    public UserDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE [DBUser] (\n"+
                "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "[name] VARCHAR(60)  NOT NULL, \n" +
                "[email] VARCHAR(60)  NOT NULL, \n" +
                "[password] VARCHAR(60)  NOT NULL \n" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
