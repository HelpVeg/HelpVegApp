package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

/**
 * <h1>UserDAO</h1>
 * Classe responsavel pelo usuario
 */
public class UserDAO{
    /**
     * Metodo createUser cria o usuario
     * @param user usuario a ser inserido
     */
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

    /**
     * O metodo generateUser seta os dados do usuario no banco
     * @return o objeto usuario
     */
    private User generateUser(Cursor cursor) {
        User user = new User();
        user.setUserId(cursor.getInt(0));
        user.setUserName(cursor.getString(1));
        user.setUserEmail(cursor.getString(2));
        user.setUserPassword(cursor.getString(3));
        byte[] byteArray = cursor.getBlob(4);
        user.setUserPhoto(byteToBitmap(byteArray));

        return user;
    }

    /**
     * Metodo para atualizar os dados do usuario no banco
     * @param user
     */

    public void updateUser(User user) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        String where = DatabaseHelper.getColumnUserId() + " = " + Integer.toString(Session.getUserIn().getUserId()) + ";";
        ContentValues values = new ContentValues();


        values.put(DatabaseHelper.getColumnUserName(), user.getUserName());
        values.put(DatabaseHelper.getColumnUserEmail(), user.getUserEmail());
        values.put(DatabaseHelper.getColumnUserPass(), user.getUserPassword());
        values.put(DatabaseHelper.getColumnUserPhoto(), bitmapToByte(user.getUserPhoto()));

        db.update(DatabaseHelper.getTableUser(), values, where, null);
        db.close();
    }

    /**
     * Metodo para pegar todos os usuarios
     * @return Retorna uma lista com todos os usuarios
     */
    public ArrayList<User> getAllUsers() {

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllUsers(), null);

        if (cursor.moveToFirst()) {

            do {
                User user = new User();
                user.setUserId(cursor.getInt(0));
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

    /**
     * Metodo responsável por pegar um unico usuario utilizando seu id
     * @param user_id id do usuario
     * @return Retorna o usuario que possui o id passado como parametro
     */
    public User getSingleUser(int user_id) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromId(), new String[] {Integer.toString(user_id)});

        if (cursor.moveToFirst()) {
            user = generateUser(cursor);
        }
        cursor.close();
        db.close();
        return user;
    }

    public User getSingleUser(String email){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmail(), new String[] {email});

        if (cursor.moveToFirst()) {
            user = generateUser(cursor);
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * Retorna o usuario que possui o email e senha dos paramentros
     * @param email email do usuario
     * @param pass senha do usuario
     * @return Retorna o objeto usuario
     */
    public User getLoginUser(String email, String pass){

        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();

        User user = null;
        Cursor cursor = db.rawQuery(QueriesSQL.sqlUserFromEmailAndPass(), new String[] {email, pass});

        if(cursor.moveToFirst()){
            user = generateUser(cursor);
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * O metodo insere um usuario na tabela usuario logado
     * @param user usuario
     */
    public void insertLoggedUser(User user){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        ContentValues values = new ContentValues();

        String columnUserLoggedId = DatabaseHelper.getColumnUserLoggedId();
        values.put(columnUserLoggedId, user.getUserId());

        db.insert(DatabaseHelper.getTableUserLogged(), null, values);
    }

    /**
     * O metodo retorna o usuario logado
     * @return retorna o usuario
     */
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

    /**
     * Metodo remove o usuario logado
     */
    public void removeLoggedUser(){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        db.delete(DatabaseHelper.getTableUserLogged(), null, null);
        db.close();
    }

    private Bitmap byteToBitmap(byte[] byteArray){
        if(byteArray != null){
            return BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
        }
        return null;
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        if (bitmap!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }
        return null;
    }
}