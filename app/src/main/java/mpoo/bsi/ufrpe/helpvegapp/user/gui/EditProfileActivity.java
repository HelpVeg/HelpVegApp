package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.Md5;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;

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

        this.mViewHolder.imgProfile = findViewById(R.id.ImgProfile);

        this.mViewHolder.edtEmail = findViewById(R.id.EditProfileEmail);
        this.mViewHolder.edtOldPassword = findViewById(R.id.EditProfileOldPassword);
        this.mViewHolder.edtName = findViewById(R.id.EditProfileName);
        this.mViewHolder.edtNewPassword = findViewById(R.id.EditProfileNewPassword);
        this.mViewHolder.edtConfirmPassword = findViewById(R.id.EditProfileConfirmPassword);

        this.mViewHolder.imgProfile.setOnClickListener(this);
        this.mViewHolder.btnCancel.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);

        showUserLoggedData();
    }

    public void showUserLoggedData(){
        this.mViewHolder.edtName.setText(Session.getUserIn().getUserName());
        this.mViewHolder.edtEmail.setText(Session.getUserIn().getUserEmail());
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.EditProfileBtnSave){
            if (verifyEdition()){
                Toast.makeText(this,R.string.toastUpdateSuccessful,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
            }
        }
        if (id==R.id.EditProfileBtnCancel){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        if (id==R.id.ImgProfile){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {

                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                mViewHolder.imgProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean validateRegexEmail(String email){
        if (userBusiness.regexEmail(email)){
            return true;
        }
        return false;
    }


    public boolean verifyEdition(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();
        String newPass = this.mViewHolder.edtNewPassword.getText().toString().trim();
        if (validateFields()){
            User user = new User();
            user.setUserEmail(email);
            user.setUserName(name);
            user.setUserPassword(md5.encrypt(newPass));
            return checkEmailUpdate(user);
        }
        return false;
    }

    public boolean checkEmailUpdate(User user){
        if (userBusiness.updateUser(user)){
            return true;
        }
        else {
            this.mViewHolder.edtEmail.setError("Email já cadastrado");
            return false;
        }
    }


    public boolean validateFields(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();
        String oldPass = md5.encrypt(this.mViewHolder.edtOldPassword.getText().toString().trim());
        String newPass = this.mViewHolder.edtNewPassword.getText().toString().trim();
        String confirmPass = this.mViewHolder.edtConfirmPassword.getText().toString().trim();

        boolean blankValidate = true;

        if (TextUtils.isEmpty(name) || name.length() < 4){
            this.mViewHolder.edtName.setError("Campo nome invalido");
            blankValidate = false;
        }

        if (TextUtils.isEmpty(email) || !validateRegexEmail(email)){
            this.mViewHolder.edtEmail.setError("Campo email invalido");
            blankValidate = false;
        }

        if (!newPass.equals(confirmPass)){
            this.mViewHolder.edtNewPassword.setError("Senha e confirmação não coincidem");
            this.mViewHolder.edtNewPassword.setError("Senha e confirmação não coincidem");
            blankValidate = false;
        } else {
            if (TextUtils.isEmpty(newPass) || oldPass.length() < 5){
                this.mViewHolder.edtOldPassword.setError("A nova senha deve conter 5 ou mais caracteres");
                blankValidate = false;
            }
        }
        if (!oldPass.equals(Session.getUserIn().getUserPassword())){
            this.mViewHolder.edtOldPassword.setError("Senha incorreta");
            blankValidate = false;
        }

        return blankValidate;
    }

    public static class ViewHolder{
        private Button btnCancel;
        private Button btnSave;

        private ImageView imgProfile;

        private EditText edtName;
        private EditText edtEmail;
        private EditText edtOldPassword;
        private EditText edtNewPassword;
        private EditText edtConfirmPassword;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
