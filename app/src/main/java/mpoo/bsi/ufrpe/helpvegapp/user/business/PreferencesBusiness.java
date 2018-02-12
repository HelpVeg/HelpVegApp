package mpoo.bsi.ufrpe.helpvegapp.user.business;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.PreferencesDAO;

public class PreferencesBusiness {
    private PreferencesDAO preferencesDAO = new PreferencesDAO();

    private PreferencesDAO getPreferencesDAO() {
        return preferencesDAO;
    }


    //-------------------- Registrando as preferências do usuário logado -----------------
    public void registerPreferences(Preferences preferences){
        preferences.setUser(Session.getUserIn());
        if (getPreferencesFromUser(Session.getUserIn()) != null){
            getPreferencesDAO().updatePreferences(preferences);
        }else{
            getPreferencesDAO().createPreferences(preferences);
        }
    }


    public Preferences getPreferencesFromUser(User user){
        return preferencesDAO.getPreferencesFromUser(user.getUserId());
    }
/*
    public void viewPreferences() {
        ArrayList<Preferences> preferences = preferencesDAO.getAllPreferences();
        for (int i = 0; i < preferences.size(); i++) {
            Preferences pres = preferences.get(i);
            System.out.println("#" + i + " ID: " + pres.getId() + " user: " + pres.getUser().getUserEmail() + " "
                    + String.valueOf(pres.getAmbiance()) + " "
                    + String.valueOf(pres.getFood()) + " "
                    + String.valueOf(pres.getPrice()) + " "
                    + String.valueOf(pres.getService()) + " "
                    );
        }
        if (preferences.size() == 0) System.out.println("# Não existem registros.");
    }
*/
}
