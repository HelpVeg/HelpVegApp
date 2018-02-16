package mpoo.bsi.ufrpe.helpvegapp.user.domain;

import android.graphics.Bitmap;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

/**
 * <h1>User</h1>
 * Classe responsavel pela criacao dos objetos User com seus gets e sets.
 */
public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Bitmap userPhoto;

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Bitmap getUserPhoto(){
        return userPhoto;
    }
    public void setUserPhoto(Bitmap userPhoto){
        this.userPhoto = userPhoto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        final User other = (User) obj;
        return this.getUserId() == other.getUserId();
    }

    @Override
    public int hashCode() {
        return this.userId;
    }

}
