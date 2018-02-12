package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.MapsActivity;
import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    Md5 md5 = new Md5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkSession();

        this.mViewHolder.editEmail = findViewById(R.id.loginEmail);
        this.mViewHolder.editPassword = findViewById(R.id.loginPassword);
        this.mViewHolder.btnEnter = findViewById(R.id.loginBtnEnter);
        this.mViewHolder.navToRegister = findViewById(R.id.loginNavRegister);

        this.mViewHolder.btnEnter.setOnClickListener(this);
        this.mViewHolder.navToRegister.setOnClickListener(this);
    }

    public void checkSession(){
        UserBusiness userBusiness = new UserBusiness();
        if (userBusiness.recoverSession()){
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginBtnEnter){
            if (login()){
                Intent intent = new Intent(this,MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (id == R.id.loginNavRegister){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean login(){
        boolean loginFiedls = false;
        if (validateFields()){
            String email = mViewHolder.editEmail.getText().toString().trim();
            String pass = md5.encrypt(mViewHolder.editPassword.getText().toString().trim());
            User user = new UserBusiness().validateLogin(email,pass);
            if(user != null){
                Toast.makeText(this, R.string.toastLoginSuccessful, Toast.LENGTH_SHORT).show();
                loginFiedls = true;
            }
            else{
                Toast.makeText(this, R.string.toastLoginFailed, Toast.LENGTH_SHORT).show();
            }
        }else{
            loginFiedls = false ;
        }
        return loginFiedls;
    }

    public boolean validateFields(){
        String email = mViewHolder.editEmail.getText().toString();
        String password = mViewHolder.editPassword.getText().toString();

        boolean blankValidate = true;

        if (TextUtils.isEmpty(email)){
            mViewHolder.editEmail.setError("campo email invalido");
            blankValidate = false;
        }

        else if (TextUtils.isEmpty(password)){
            mViewHolder.editPassword.setError("campo senha inválido");
            blankValidate = false;
        }
        return blankValidate;
    }

    private static class ViewHolder{
        EditText editEmail;
        EditText editPassword;
        Button btnEnter;
        TextView navToRegister;
    }


}