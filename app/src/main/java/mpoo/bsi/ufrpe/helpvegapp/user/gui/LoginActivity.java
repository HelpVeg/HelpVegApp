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
/**
 * <h1>LoginActivity</h1>
 * Acitvity responsavel por implementar a funcionalidade de login.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    Md5 md5 = new Md5();

    /**
     * O metodo onCreate() seta o layout: activity_login e setar os
     * EditTexts e Buttons do layout para cada atributo da classe
     * @see LoginActivity#checkSession()
     * @param savedInstanceState Um objeto da classe Bundle que contem o estado anterior da activity
     */
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

    /**
     * O metodo recupera o usuario da sessao e inicia o maps
     */

    public void checkSession(){
        UserBusiness userBusiness = new UserBusiness();
        if (userBusiness.recoverSession()){
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * O método onClick() recebe o id recebido pela view que é comparado com os ids do botao register e navRegister
     * caso o id seja igual ao do botao de registrar é iniciado a MapsActivity, se for igual ao navRegister
     * é iniciada a RegisterActivity
     * @param view  Recebe o que foi observado na view
     */
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

    /**
     *O metodo login() verifica valida os campos com o metodo validateFields(), criptografa a senha inserida
     * no editText e verifica se o email inserido no editText e a senha ja criptografada estao corretas.
     * @return Se o login foi efetuado com sucesso e retornado true, senao e retornado falso para login invalido
     */

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

    /**
     * O metodo validateFields() recebe o email e password do editText e verifica se os campos estao vazios
     * @return Retorna true se os campos estiverem preenchidos, e falso se algum dos campos estiver vazio
     */
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