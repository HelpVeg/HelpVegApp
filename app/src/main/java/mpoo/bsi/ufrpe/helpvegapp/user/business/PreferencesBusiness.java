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

    //-------------------- Registrando as preferências do usuário logado -----------------
    public Boolean registerPreferences(Preferences preferences){
        return getPreferencesDAO().createPreferences(preferences);
    }

    //--------------------- Atualizando as preferências do usuário logado ----------------
    public Boolean updatePreferences(Preferences preferences){
        getPreferencesDAO().updatePreferences(preferences);
        return true;
    }
}
