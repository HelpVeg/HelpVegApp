package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

import mpoo.bsi.ufrpe.helpvegapp.R;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private User user = new User();
    private Resources resources;
    private Md5 criptoMd5;
    private UserBusiness userBusiness;
    private ViewHolder mViewHolder = new ViewHolder();


    private static class ViewHolder{
        private EditText editName;
        private EditText editEmail;
        private EditText editPass;
        private Button btnRegister;
        private TextView navToLogin;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mViewHolder.editName = findViewById(R.id.registerName);
        this.mViewHolder.editEmail = findViewById(R.id.registerEmail);
        this.mViewHolder.editPass = findViewById(R.id.registerPass);
        this.mViewHolder.btnRegister = findViewById(R.id.registerBtnRegister);
        this.mViewHolder.navToLogin = findViewById(R.id.registerNavLogin);

        this.mViewHolder.btnRegister.setOnClickListener(this);

        initViews();
    }

    @Override
    public void onClick(View v) {
        register();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void initViews(){
        resources = getResources();

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        this.mViewHolder.editName.addTextChangedListener(textWatcher);
        this.mViewHolder.editEmail.addTextChangedListener(textWatcher);
        this.mViewHolder.editPass.addTextChangedListener(textWatcher);
    }

    //verifica a validação de todos os campos
    private boolean validateFieldsRegister(){
        String name = this.mViewHolder.editName.getText().toString().trim().toLowerCase();
        String email = this.mViewHolder.editEmail.getText().toString().trim().toLowerCase();
        String password = this.mViewHolder.editPass.getText().toString().trim().toLowerCase();

        return (isValidateFields(name,email,password));

    }
    //verifica se o campo está vazio
    private boolean isValidateFields(String name,String email,String password){
        boolean validate = true;
        if (TextUtils.isEmpty(name)){
            this.mViewHolder.editName.requestFocus();
            this.mViewHolder.editName.setError(resources.getString(R.string.errorFieldEmail));
        }
        if (TextUtils.isEmpty(email)){
            this.mViewHolder.editEmail.requestFocus();
            this.mViewHolder.editEmail.setError(resources.getString(R.string.errorFieldEmail));
        }
        if (TextUtils.isEmpty(password)){
            this.mViewHolder.editPass.requestFocus();
            this.mViewHolder.editPass.setError(resources.getString(R.string.errorFieldPassword));
        }else{
            validate = false;
        }
        return validate;
    }
    private void register(){
        if (validateFieldsRegister()){
            criptoMd5 = new Md5();

            User user = new User();

            user.setUserName(this.mViewHolder.editName.getText().toString());
            user.setUserEmail(this.mViewHolder.editEmail.getText().toString());
            String newPassword = criptoMd5.encrypt(this.mViewHolder.editPass.getText().toString());
            user.setUserPassword(newPassword);

            userBusiness.validateRegister(user);
        }
    }
    public void navLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

}
