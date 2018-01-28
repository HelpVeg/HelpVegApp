package mpoo.bsi.ufrpe.helpvegapp.user.gui;
import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import mpoo.bsi.ufrpe.helpvegapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    Md5 md5 = new Md5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mViewHolder.editName = findViewById(R.id.registerName);
        this.mViewHolder.editEmail = findViewById(R.id.registerEmail);
        this.mViewHolder.editPass = findViewById(R.id.registerPass);
        this.mViewHolder.editConfirmPass = findViewById(R.id.registerConfirmPass);

        this.mViewHolder.btnRegister = findViewById(R.id.registerBtnRegister);
        this.mViewHolder.navToLogin = findViewById(R.id.registerNavLogin);

        this.mViewHolder.btnRegister.setOnClickListener(this);
        this.mViewHolder.navToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.registerBtnRegister){
            if (registerAccount()){
                Toast.makeText(this, R.string.toastRegisterSuccessful, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            new UserBusiness().viewUsers();
        }
        if (id == R.id.registerNavLogin){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean registerAccount(){
        User user = new User();
        if (validateFields()) {
            user.setUserName(mViewHolder.editName.getText().toString().trim());
            user.setUserEmail(mViewHolder.editEmail.getText().toString().trim());

            String password = md5.encrypt(mViewHolder.editPass.getText().toString().trim());
            user.setUserPassword(password);

            if (new UserBusiness().registerUser(user)) {
                return true;
            } else {
                Toast.makeText(this, R.string.toastRegisterFailed, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }
    public boolean validateRegexEmail(String email){
        if (new UserBusiness().regexEmail(email)){
            return true;
        }
        return false;
    }

    public boolean validateFields(){
        String name = mViewHolder.editName.getText().toString().trim();
        String email = mViewHolder.editEmail.getText().toString().trim();
        String password = mViewHolder.editPass.getText().toString().trim();
        String confirmPassword = mViewHolder.editConfirmPass.getText().toString().trim();

        boolean blankValidate = true;

        if (TextUtils.isEmpty(name) || name.length() < 4){
            mViewHolder.editName.setError("O nome deve conter 4 ou mais caracteres");
            blankValidate = false;
        }
        if (TextUtils.isEmpty(email) || !validateRegexEmail(email)){
            mViewHolder.editEmail.setError("Email inválido");
            blankValidate = false;
        }
        if (!password.equals(confirmPassword)){
            mViewHolder.editPass.setError("Senha e confirmação não coincidem");
            mViewHolder.editConfirmPass.setError("Senha e confirmação não coincidem");
            blankValidate = false;
        } else if (TextUtils.isEmpty(password) || password.length() < 5){
            mViewHolder.editPass.setError("A senha deve conter 5 ou mais caracteres");
            blankValidate = false;
        }
        return blankValidate;
    }


    private static class ViewHolder{
        private EditText editName;
        private EditText editEmail;
        private EditText editPass;
        private EditText editConfirmPass;
        private Button btnRegister;
        private TextView navToLogin;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
