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

public class EditPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Md5 md5 = new Md5();

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

    public void checkSession(){
        if (Session.getUserIn() == null){
            new UserBusiness().recoverSession();
        }
    }

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


    public boolean verifyEdition(){
        if (validateFields()){
            String newPass = md5.encrypt(this.mViewHolder.edtNewPassword.getText().toString().trim());
            UserBusiness userBusiness = new UserBusiness();
            return userBusiness.updateUserPassword(newPass);
        }
        return false;
    }

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

}
