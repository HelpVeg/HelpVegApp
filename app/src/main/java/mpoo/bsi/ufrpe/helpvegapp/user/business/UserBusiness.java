package mpoo.bsi.ufrpe.helpvegapp.user.business;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.UserDAO;
/**
 * <h1>User Business</h1>
 * Classe responsavel pelas regras de negocio relacionadas o objeto User.
 */
public class UserBusiness {
    private UserDAO userDAO = new UserDAO();

    private UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean registerUser(User user){
        return getUserDAO().createUser(user);
    }


    /**
     * O metodo validateLogin() tem a funcionalidade de validar o login com o auxilio do metodo
     * getLoginUser() presente na UserDao, inserindo o usuario na tabela user_logged, caso o
     * mesmo nao se encontre nela.
     * @param email  email do usuario
     * @param pass  senha do usuario
     * @return Retorna o objeto Usuario
     */
    public User validateLogin(String email, String pass){
        User userIn = userDAO.getLoginUser(email,pass);
        if (userIn !=null){
            getUserDAO().insertLoggedUser(userIn);
            Session.setUserIn(userIn);
        }
        return userIn;
    }

    /**
     * O metodo recoverSession() e responsavel por recuperar a sessao usando a tabela user_logged,
     * com o metodo getLoggedUser(), caso seja diferente de null ele seta o usuario na sessao.
     * @return Se o usuario esta logado ou nao
     */
    public boolean recoverSession(){
        User loggedUser = userDAO.getLoggedUser();
        if (loggedUser!=null){
            Session.setUserIn(loggedUser);
            return true;
        }
        return false;
    }

    /**
     * O metodo endSession() remove o usuario da tabela de usuario logado com o metodo removeLoggedUser,
     * e utilizado para remover encerrar a sessao do usuario
     */

    public void endSession(){
        userDAO.removeLoggedUser();
        Session.setUserIn(null);
    }

    /**
     * O metodo getUserFromSession() usa o metodo getUserById para pegar o id do usuario logado
     * @return id do usuario logado
     */
    public User getUserFromSession(){
        return getUserById(Session.getUserIn().getUserId());
    }

    /**
     * O metodo regexEmail() utiliza uma expressao regular para fazer uma validacao de email
     * @param email email para verificacao se esta no padrao
     */
    public boolean regexEmail(String email){
        String regex ="^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher = Pattern.compile(regex).matcher(email);
        return matcher.matches();
    }

    /**
     *O metodo ArryList() lista todos os usuario utilizando o metodo getAllUsers() presente na classe UserDao
     * @return Uma lista com todos os usuarios
     */
    public ArrayList<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    /**
     * RETIRAR DO CÓDIGO
     */
    public void viewUsers() {
        ArrayList<User> users = getUserDAO().getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            System.out.println("#" + i + " ID: " + us.getUserId() + " Nome: " + us.getUserName() + ", Email: " + us.getUserEmail() + ", Senha: " + us.getUserPassword());
        }
        if (users.size() == 0) System.out.println("# Não existem registros.");
    }

    /**
     * O metodo updateUserPassword() atualiza a senha do usuario, setando outra senha
     * no usuario recuperado pela sessao utilizando o metodo updateUser()
     * @param password nova senha do usuario
     * @return Retornar true significa que a senha foi alterada.
     */

    public boolean updateUserPassword(String password){
        Session.getUserIn().setUserPassword(password);
        getUserDAO().updateUser(Session.getUserIn());
        return true;
    }
    /**
     * O metodo updateUserName() atualiza o nome do usuario, setando outro nome
     * no usuario recuperado pela sessao utilizando o metodo updateUser()
     * @param name novo nome do usuario
     * @return Retornar true significa que a nome foi alterado.
     */
    public boolean updateUserName(String name){
        Session.getUserIn().setUserName(name);
        getUserDAO().updateUser(Session.getUserIn());
        return true;
    }
    /**
     * O metodo updateUserPhoto() atualiza a foto do usuario, setando outra foto
     * no usuario recuperado pela sessao utilizando o metodo updateUser()
     * @param bitmap nova imagem do usuario
     */
    public void updateUserPhoto(Bitmap bitmap){
        Session.getUserIn().setUserPhoto(bitmap);
        getUserDAO().updateUser(Session.getUserIn());
    }

    /**
     * O metodo updateUserEmail() atualiza o email do do usuario, setando outro email
     * no usuario recuperado pela sessao utilizando o metodo updateUser()
     * @param email novo email do usuario
     */

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

    /**
     * O metodo recebe um id
     * @param id id inserido para ser retornado o usuario
     * @return Retorna o usuario com aquele id
     */
    public User getUserById(int id){
        return getUserDAO().getSingleUser(id);
    }

}
