package mpoo.bsi.ufrpe.helpvegapp.user.business;

import android.graphics.Bitmap;

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
        User userIn = userDAO.getLoginUser(email,pass);
        if (userIn !=null){
            getUserDAO().insertLoggedUser(userIn);
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

    public User getUserFromSession(){
        User user = getUserById(Session.getUserIn().getUserId());
        return user;
    }

    public boolean regexEmail(String email){
        String regex ="^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher = Pattern.compile(regex).matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public ArrayList<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void viewUsers() {
        ArrayList<User> users = getUserDAO().getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            System.out.println("#" + i + " ID: " + us.getUserId() + " Nome: " + us.getUserName() + ", Email: " + us.getUserEmail() + ", Senha: " + us.getUserPassword());
        }
        if (users.size() == 0) System.out.println("# Não existem registros.");
    }

    public boolean updateUserPassword(String password){
        Session.getUserIn().setUserPassword(password);
        getUserDAO().updateUser(Session.getUserIn());
        return true;
    }

    public boolean updateUserName(String name){
        Session.getUserIn().setUserName(name);
        getUserDAO().updateUser(Session.getUserIn());
        return true;
    }

    public void updateUserPhoto(Bitmap bitmap){
        Session.getUserIn().setUserPhoto(bitmap);
        getUserDAO().updateUser(Session.getUserIn());
    }

    public boolean updateUserEmail(String email){
        if (Session.getUserIn().getUserEmail().equals(email)){
            return true;
        }
        if(userDAO.getSingleUser(email)!=null){
            return false;
        }
        Session.getUserIn().setUserEmail(email);
        getUserDAO().updateUser(Session.getUserIn());
        return true;
    }

    public User getUserById(int id){
        return getUserDAO().getSingleUser(id);
    }

}
