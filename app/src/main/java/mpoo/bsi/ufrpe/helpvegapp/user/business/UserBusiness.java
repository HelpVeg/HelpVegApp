package mpoo.bsi.ufrpe.helpvegapp.user.business;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;

public class UserBusiness {
    private UserDAO userDAO = new UserDAO();

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean registerUser(User user){
        return getUserDAO().createUser(user);
    }



    public User validateLogin(String email, String pass){
        User userIn = getUserDAO().getLoginUser(email,pass);
        if (userIn !=null){
            userDAO.insertLoggedUser(userIn);
            Session.setUserIn(userIn);
        }
        return userIn;
    }

    public boolean recoverSession(){
        User loggedUser = userDAO.getLoggedUser();
        if (loggedUser!=null){
            Session.setUserIn(loggedUser);
            return true;
        }
        return false;
    }

    public void endSession(){
        userDAO.removeLoggedUser();
        Session.setUserIn(null);
    }


    public boolean regexEmail(String email){
        String regex ="^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher = Pattern.compile(regex).matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public void viewUsers() {
        ArrayList<User> users = getUserDAO().getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            System.out.println("#" + i + " ID: " + us.getUserId() + " Nome: " + us.getUserName() + ", Email: " + us.getUserEmail() + ", Senha: " + us.getUserPassword());
        }

        if (users.size() == 0) System.out.println("# Não existem registros.");
    }

    public boolean updateUser(User user){
        if (userDAO.getSingleUser(user.getUserEmail())==null){
            getUserDAO().updateUser(user);
            Session.setUserIn(user);
            return true;
        }
        return false;
    }
}
