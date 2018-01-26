package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.Session;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Md5 md5 = new Md5();
    private UserBusiness userBusiness = new UserBusiness();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.mViewHolder.btnCancel = findViewById(R.id.EditProfileBtnCancel);
        this.mViewHolder.btnSave = findViewById(R.id.EditProfileBtnSave);

        this.mViewHolder.edtOldPassword = findViewById(R.id.EditProfileOldPassword);
        this.mViewHolder.edtName = findViewById(R.id.EditProfileName);
        this.mViewHolder.edtEmail = findViewById(R.id.EditProfileEmail);
        this.mViewHolder.edtNewPassword = findViewById(R.id.EditProfileNewPassword);
        this.mViewHolder.edtConfirmPassword = findViewById(R.id.EditProfileConfirmPassword);

        this.mViewHolder.btnCancel.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);
        System.out.println("# senha" + Session.getUserIn().getUserEmail());
        new UserBusiness().viewUsers();
        showUserLoggedData();
    }

    public void showUserLoggedData(){
        this.mViewHolder.edtName.setText(Session.getUserIn().getUserName());
        this.mViewHolder.edtEmail.setText(Session.getUserIn().getUserEmail());
    }


    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.EditProfileBtnSave){

            String name = this.mViewHolder.edtName.getText().toString().trim();
            String email = this.mViewHolder.edtEmail.getText().toString().trim();
            String oldPass = md5.encrypt(this.mViewHolder.edtOldPassword.getText().toString().trim());
            String newPass = this.mViewHolder.edtNewPassword.getText().toString().trim();
            String confirmPass = this.mViewHolder.edtConfirmPassword.getText().toString().trim();

            if (validateFields()){
                if(oldPass == Session.getUserIn().getUserPassword()){

                    Intent intent = new Intent(this, ProfileActivity.class);

                    if(newPass == confirmPass){
                        Session.getUserIn().setUserName(name);
                        Session.getUserIn().setUserEmail(email);
                        Session.getUserIn().setUserPassword(md5.encrypt(confirmPass));

                        userBusiness.updateUser(Session.getUserIn());

                        startActivity(intent);
                        finish();
                    }
                    else{
                        Session.getUserIn().setUserName(name);
                        Session.getUserIn().setUserEmail(email);
                        Session.getUserIn().setUserPassword(oldPass);

                        userBusiness.updateUser(Session.getUserIn());

                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(this,"Senha antiga é inválida!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (id==R.id.EditProfileBtnCancel){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateFields(){

        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();
        String oldPass = md5.encrypt(this.mViewHolder.edtOldPassword.getText().toString().trim());

        boolean blankValidate = true;

        if (TextUtils.isEmpty(name)){
            this.mViewHolder.edtName.setError("Campo nome invalido");
            blankValidate = false;
        }

        if (TextUtils.isEmpty(email)){
            this.mViewHolder.edtEmail.setError("Campo email invalido");
            blankValidate = false;
        }

        else if (TextUtils.isEmpty(oldPass)){
            this.mViewHolder.edtOldPassword.setError("Campo senha inválido");
            blankValidate = false;
        }
        return blankValidate;
    }

    public static class ViewHolder{
        private Button btnCancel;
        private Button btnSave;
        private EditText edtName;
        private EditText edtEmail;
        private EditText edtOldPassword;
        private EditText edtNewPassword;
        private EditText edtConfirmPassword;
    }
}
