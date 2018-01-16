package mpoo.bsi.ufrpe.helpvegapp.user.persistence;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.gui.RegisterActivity;

public class UserDAO{

    private static DatabaseReference database = DatabaseHelper.getDatabase();
    private static FirebaseAuth firebaseAuth;
    /*
    * O método insertUser referencia o nó users (nó que contém os usuários cadastrados)
     * do banco e faz a inserção de novos usuários
     */

    /*public void insertUser(User user){
        DatabaseReference userReference = database.child(DatabaseHelper.getTableUser());
        userReference.child("5").setValue( user );
    }*/
    public static FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }


}
