package mpoo.bsi.ufrpe.helpvegapp.user.business;

import android.content.Context;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;


public class UserBusiness {

    private UserController userController = new UserController(Context);

    public UserController getUserController(){

        return userController;
    }
    public void validateRegister(User user){
        userController.create(user);
    }

}