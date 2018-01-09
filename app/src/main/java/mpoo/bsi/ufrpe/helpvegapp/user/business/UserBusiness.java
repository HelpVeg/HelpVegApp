package mpoo.bsi.ufrpe.helpvegapp.user.business;

import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;*package mpoo.bsi.ufrpe.helpvegapp.user.business;


public class UserBusiness {
    private UserDAO userDAO = new UserDAO();

    public UserDAO getUserDAO(){
        return userDAO;
    }

}
