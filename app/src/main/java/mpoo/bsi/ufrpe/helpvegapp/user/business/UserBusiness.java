package mpoo.bsi.ufrpe.helpvegapp.user.business;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;

public class UserBusiness {
    private UserDAO userDAO = new UserDAO();

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean registerUser(User user){
        return getUserDAO().createUser(user);
    }

    public boolean validateLogin(String email, String pass){
        return getUserDAO().getLogin(email, pass);
    }

    public void viewUsers() {


        ArrayList<User> users = getUserDAO().getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            System.out.println("#" + i + " ID: " + us.getUserId() + " Nome: " + us.getUserName() + ", Email: " + us.getUserEmail() + ", Senha: " + us.getUserPassword());
        }

        if (users.size() == 0) System.out.println("# Não existem registros.");
    }
}
