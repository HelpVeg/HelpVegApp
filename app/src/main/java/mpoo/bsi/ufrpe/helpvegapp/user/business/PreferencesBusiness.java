package mpoo.bsi.ufrpe.helpvegapp.user.business;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.PreferencesDAO;

public class PreferencesBusiness {
    private PreferencesDAO preferencesDAO;

    public PreferencesDAO getPreferencesDAO() {
        return preferencesDAO;
    }

    //------------------ Obtendo as preferências do usuário logado --------------------------
    public Preferences getUserPreferences(){
        Preferences preferences = getPreferencesDAO().getPreferencesFromUser(Session.getUserIn().getUserId());
        return preferences;
    }

    public Preferences generatePreference(float food,float service,float place, float price){
        Preferences preferences = new Preferences();
        preferences.setUser(Session.getUserIn());
        preferences.setFood(food);
        preferences.setAmbiance(place);
        preferences.setPrice(price);
        preferences.setService(service);

        return preferences;
    }

    //-------------------- Registrando as preferências do usuário logado -----------------
    public Boolean registerPreferences(float food,float service,float place, float price){
        Preferences preferences = generatePreference(food,service,place,price);
        return getPreferencesDAO().createPreferences(preferences);
    }

    //--------------------- Atualizando as preferências do usuário logado ----------------
    public Boolean updatePreferences(Preferences preferences){
        getPreferencesDAO().updatePreferences(preferences);
        return true;
    }
}
