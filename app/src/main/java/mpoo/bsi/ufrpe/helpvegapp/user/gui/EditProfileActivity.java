package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private UserBusiness userBusiness = new UserBusiness();
    private static final int REQUEST_GALERY = 0;
    private static final int REQUEST_CROP = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.mViewHolder.btnSave = findViewById(R.id.EditProfileBtnSave);
        this.mViewHolder.imgProfile = (CircleImageView) findViewById(R.id.ImgProfile);
        this.mViewHolder.edtEmail = findViewById(R.id.EditProfileEmail);
        this.mViewHolder.edtName = findViewById(R.id.EditProfileName);
        this.mViewHolder.imgProfile.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);
        showUserLoggedData();
    }

    public void showUserLoggedData(){
        this.mViewHolder.edtName.setText(Session.getUserIn().getUserName());
        this.mViewHolder.edtEmail.setText(Session.getUserIn().getUserEmail());
        if (Session.getUserIn().getUserPhoto()!=null){
            this.mViewHolder.imgProfile.setImageBitmap(Session.getUserIn().getUserPhoto());
        }
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.EditProfileBtnSave){
            if (verifyEdition()){
                Toast.makeText(this,R.string.toastUpdateSuccessful,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (id==R.id.ImgProfile){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_GALERY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            final Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(targetUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 128);
            intent.putExtra("outputY", 128);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CROP);
        } else if (requestCode == REQUEST_CROP && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bitmap = extras.getParcelable("data");
            saveProfileImage(bitmap);
            mViewHolder.imgProfile.setImageBitmap(bitmap);
        }
    }

    public void saveProfileImage(Bitmap bitmap){
        userBusiness.updateUserPhoto(bitmap);
    }

    public boolean verifyEdition(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();
        if (validateFields()){
            if (!userBusiness.updateUserEmail(email)){
                this.mViewHolder.edtEmail.setError("Email já cadastrado");
                return false;
            } else{
                userBusiness.updateUserName(name);
                return true;
            }
        }
        return false;
    }

    public boolean validateFields(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();

        boolean blankValidate = true;
        if (TextUtils.isEmpty(name) || name.length() < 4){
            this.mViewHolder.edtName.setError("O nome deve conter 4 ou mais caracteres");
            blankValidate = false;
        }
        if (TextUtils.isEmpty(email) || !userBusiness.regexEmail(email)){
            this.mViewHolder.edtEmail.setError("Email invalido");
            blankValidate = false;
        }
        return blankValidate;
    }

    private static class ViewHolder{
        private Button btnSave;
        private CircleImageView imgProfile;
        private EditText edtName;
        private EditText edtEmail;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

}
