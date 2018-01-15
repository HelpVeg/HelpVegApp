package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import com.google.firebase.database.DatabaseReference;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class UserDAO{

    private static DatabaseReference database = DatabaseHelper.getDatabase();

    /*
    * O método insertUser referencia o nó users (nó que contém os usuários cadastrados)
     * do banco e faz a inserção de novos usuários
     */
    public void insertUser(User user){
        DatabaseReference userReference = database.child(DatabaseHelper.getTableUser());
        userReference.child("5").setValue( user );
    }

}
