package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
/**
 * <h1>EditPasswordActivity</h1>
 * Acitvity responsavel por implementar a funcionalidade editar senha.
 */
public class EditPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Md5 md5 = new Md5();

    /**
     * O metodo onCreate() seta o layout: activity_edit_password e setar os
     * EditTexts e Buttons do layout para cada atributo da classe e chamar o metodo checkSession() da mesma
     * classe.
     * @see EditPasswordActivity#checkSession()
     * @param savedInstanceState Um objeto da classe Bundle que contem o estado anterior da activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        this.mViewHolder.edtOldPassword = findViewById(R.id.EditProfileOldPassword);
        this.mViewHolder.edtNewPassword = findViewById(R.id.EditProfileNewPassword);
        this.mViewHolder.edtConfirmPassword = findViewById(R.id.EditProfileConfirmPassword);
        this.mViewHolder.btnSave = findViewById(R.id.EditPasswordBtnSave);
        this.mViewHolder.btnSave.setOnClickListener(this);
        checkSession();
    }

    /**
     *O metodo checkSession() recupera o usuario da sessao
     */
    public void checkSession(){
        if (Session.getUserIn() == null){
            new UserBusiness().recoverSession();
        }
    }

    /**
     * O metodo onClick() verifica se o id do botao clicado foi igual ao do botao salvar e leva a outra
     * activity
     * @param view Recebe o que foi observado na view
     */
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.EditPasswordBtnSave){
            if (verifyEdition()){
                Toast.makeText(this,R.string.toastUpdatePasswordSuccessful,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    /**
     * O metodo verifyEdition() verifica se o campo está preenchido e criptografa a senha com o metodo encrypt()
     * da classe Md5
     * @return Retorna a nova senha criptografada caso a operacao seja concluida com sucesso e falso, caso esta
     * nao seja concluida.
     */
    public boolean verifyEdition(){
        if (validateFields()){
            String newPass = md5.encrypt(this.mViewHolder.edtNewPassword.getText().toString().trim());
            UserBusiness userBusiness = new UserBusiness();
            return userBusiness.updateUserPassword(newPass);
        }
        return false;
    }

    /**
     * O metodo validateFields() verifica se a senha esta igual a senha anterior, confirma se as duas senhas
     * digitadas nos campos coincidem e se o campo nao esta vazio
     * @return Retorna se os campos se atendem as exigencias do campo
     */

    public boolean validateFields(){
        String oldPass = md5.encrypt(this.mViewHolder.edtOldPassword.getText().toString().trim());
        String newPass = this.mViewHolder.edtNewPassword.getText().toString().trim();
        String confirmPass = this.mViewHolder.edtConfirmPassword.getText().toString().trim();
        boolean validate = true;

        if (!oldPass.equals(Session.getUserIn().getUserPassword())){
            this.mViewHolder.edtOldPassword.setError("Senha incorreta");
            validate = false;
        }
        if (!newPass.equals(confirmPass)){
            this.mViewHolder.edtNewPassword.setError("Senha e confirmação não coincidem");
            this.mViewHolder.edtNewPassword.setError("Senha e confirmação não coincidem");
            validate = false;
        } else {
            if (TextUtils.isEmpty(newPass) || newPass.length() < 5){
                this.mViewHolder.edtNewPassword.setError("A nova senha deve conter 5 ou mais caracteres");
                validate = false;
            }
        }
        return validate;
    }



    private static class ViewHolder{
        private Button btnSave;

        private EditText edtOldPassword;
        private EditText edtNewPassword;
        private EditText edtConfirmPassword;
    }

    /**
     * Metodo utiliza o intent para ir para a ProfileActivity
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

}
