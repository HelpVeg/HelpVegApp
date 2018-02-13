package mpoo.bsi.ufrpe.helpvegapp.user.business;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.PreferencesDAO;
/**
 * <h1>Preferences Business</h1>
 * Classe responsavel pelas regras de negocio relacionadas o objeto Prefereces.
 */
public class PreferencesBusiness {
    private PreferencesDAO preferencesDAO = new PreferencesDAO();

    private PreferencesDAO getPreferencesDAO() {
        return preferencesDAO;
    }

    /**
     * O metodo registerPreferences() seta uma preferecia para o usuario que esta logado na sessao
     * com o metodo createPreferences() e altera a preferencia com o metodo updatePreferences()
     *
     * @param preferences recebe as preferencias do usuario
     */
    public void registerPreferences(Preferences preferences){
        preferences.setUser(Session.getUserIn());
        if (getPreferencesFromUser(Session.getUserIn()) != null){
            getPreferencesDAO().updatePreferences(preferences);
        }else{
            getPreferencesDAO().createPreferences(preferences);
        }
    }

    /**
     * O metodo getPreferencesFromUser() recebe um usuario e pega suas preferencias.
     * @param user recebe um usuario
     * @return Retorna as preferencias do usuarios
     */
    public Preferences getPreferencesFromUser(User user){
        return preferencesDAO.getPreferencesFromUser(user.getUserId());
    }
/*
       REMOVER ESTE METODO
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
