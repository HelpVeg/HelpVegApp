package mpoo.bsi.ufrpe.helpvegapp.user.business;

import android.content.Context;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;


public class UserBusiness {

    private UserDAO userDAO = new UserDAO();

    public UserDAO getUserController() {

        return userDAO;
    }

    public void validateRegister(User user) {
        userDAO.create(user);
    }
}