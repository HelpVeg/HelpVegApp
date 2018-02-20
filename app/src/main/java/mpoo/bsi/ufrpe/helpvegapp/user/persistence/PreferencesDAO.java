package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.EnumRestaurantType;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;

/**
 * <h1>PreferencesDAO</h1>
 * Classe responsavel pelas preferencias do usuario
 */
public class PreferencesDAO {

    /**
    * Método genérico para criar preferências, caso precise instaciar mais de uma vez a classe preferência
    * */
    private Preferences generatePreferences(Cursor cursor){
        Preferences preferences = new Preferences();
        preferences.setId(cursor.getInt(0));
        int userId = (cursor.getInt(1));
        preferences.setUser(new UserBusiness().getUserById(userId));
        preferences.setType(EnumRestaurantType.valueOf(cursor.getString(2)));
        preferences.setFood(cursor.getFloat(3));
        preferences.setPrice(cursor.getFloat(4));
        preferences.setService(cursor.getFloat(5));
        preferences.setAmbiance(cursor.getFloat(6));
        return preferences;
    }

    /**
    * Metodo a preferência pelo id usuário
    * @return
    * */
    public Preferences getPreferencesFromUser(int user_id) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Preferences preferences = null;
        Cursor cursor = db.rawQuery(QueriesSQL.getPreferencesFromUser(), new String[] {Integer.toString(user_id)});

        if (cursor.moveToFirst()) {
            preferences = generatePreferences(cursor);
        }
        cursor.close();
        db.close();
        return preferences;
    }

    /**
     * Cria uma lista com todas as preferencias
     * @return lista de preferencias
     */

    public ArrayList<Preferences> getAllPreferences() {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Preferences> preferencesList = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllPreferences(), null);

        while(cursor.moveToNext()) {
            Preferences preferences = generatePreferences(cursor);
            preferencesList.add(preferences);
        }
        cursor.close();
        db.close();
        return preferencesList;
    }

    /**
     * Método createPreferences serve para criar as preferências
     * @param preferences
     */
    public void createPreferences(Preferences preferences){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnPreferencesUserId(), preferences.getUser().getUserId());
        values.put(DatabaseHelper.getColumnPreferencesType(), preferences.getType().name());
        values.put(DatabaseHelper.getColumnPreferencesFood(), preferences.getFood());
        values.put(DatabaseHelper.getColumnPreferencesPrice(), preferences.getPrice());
        values.put(DatabaseHelper.getColumnPreferencesService(), preferences.getService());
        values.put(DatabaseHelper.getColumnPreferencesAmbiance(), preferences.getAmbiance());

        db.insert(DatabaseHelper.getTablePreferences(), null, values);
        db.close();
    }

    /**
    * Método para atualizar preferências
    * */
    public void updatePreferences(Preferences preferences) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        String where = DatabaseHelper.getColumnPreferencesUserId() + " = " + Integer.toString(Session.getUserIn().getUserId()) + ";";
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnPreferencesUserId(), preferences.getUser().getUserId());
        values.put(DatabaseHelper.getColumnPreferencesType(), preferences.getType().name());
        values.put(DatabaseHelper.getColumnPreferencesFood(), preferences.getFood());
        values.put(DatabaseHelper.getColumnPreferencesPrice(), preferences.getPrice());
        values.put(DatabaseHelper.getColumnPreferencesService(), preferences.getService());
        values.put(DatabaseHelper.getColumnPreferencesAmbiance(), preferences.getAmbiance());

        db.update(DatabaseHelper.getTablePreferences(), values, where, null);
        db.close();
    }
}
