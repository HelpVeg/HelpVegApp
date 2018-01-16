package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import mpoo.bsi.ufrpe.helpvegapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private FirebaseAuth firebaseAuth;

    private static class ViewHolder{
        EditText editEmail;
        EditText editPassword;
        Button btnEnter;
        Button btnRegister;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.editEmail = findViewById(R.id.loginEmail);
        this.mViewHolder.editPassword = findViewById(R.id.loginPassword);
        this.mViewHolder.btnEnter = findViewById(R.id.loginBtnEnter);
        this.mViewHolder.btnRegister = findViewById(R.id.loginBtnRegister);

        this.mViewHolder.btnEnter.setOnClickListener(this);
        this.mViewHolder.btnRegister.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void SignIn(){
        String email = this.mViewHolder.editEmail.getText().toString();
        String password = this.mViewHolder.editPassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                           boolean value = false;
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginBtnEnter){
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
        }
        if (id == R.id.loginBtnRegister){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }



}
