package mpoo.bsi.ufrpe.helpvegapp.user.persistence;
//mantem o banco funcionando
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DbHelper extends SQLiteOpenHelper{

    protected static final String USER_DB = "DBUser.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, USER_DB,null,VERSION);
    }
    //criar o banco de dados
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE user ("+
                "id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT  NOT NULL, " +
                "email TEXT  NOT NULL, " +
                "password TEXT  NOT NULL " +
                ")";
        db.execSQL(sql);
    }
    //quando att o banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop apaga a tabela
        String sql = "DROP TABLE IF EXISTS user";
        db.execSQL(sql);
        onCreate(db);

    }
}
