package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

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
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginBtnEnter){

            String email = mViewHolder.editEmail.getText().toString();
            String pass = mViewHolder.editPassword.getText().toString();

            if(new UserBusiness().validateLogin(email, pass)){
                Toast.makeText(this, R.string.toastLoginSuccessful, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, R.string.toastLoginFailed, Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.loginBtnRegister){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }


    private static class ViewHolder{
        EditText editEmail;
        EditText editPassword;
        Button btnEnter;
        Button btnRegister;
    }

}
