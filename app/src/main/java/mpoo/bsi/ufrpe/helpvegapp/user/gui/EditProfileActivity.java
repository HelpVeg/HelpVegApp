package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.Session;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.mViewHolder.btnCancel = findViewById(R.id.EditProfileBtnCancel);
        this.mViewHolder.btnSave = findViewById(R.id.EditProfileBtnSave);

        this.mViewHolder.edtOldPassword = findViewById(R.id.EditProfileOldPassword);
        this.mViewHolder.edtName = findViewById(R.id.EditProfileName);
        this.mViewHolder.edtNewPassword = findViewById(R.id.EditProfileNewPassword);
        this.mViewHolder.edtConfirmPassword = findViewById(R.id.EditProfileConfirmPassword);

        this.mViewHolder.btnCancel.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);

        showUserLoggedData();
    }

    public void showUserLoggedData(){
        this.mViewHolder.edtName.setHint(Session.getUserIn().getUserName());
        this.mViewHolder.edtName.setHint(Session.getUserIn().getUserEmail());
    }

    public void onClick(View view){
        int id = view.getId();

        if (id == R.id.EditProfileBtnSave){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        if (id==R.id.EditProfileBtnCancel){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
    }

    public static class ViewHolder{
        private Button btnCancel;
        private Button btnSave;
        private EditText edtName;
        private EditText edtOldPassword;
        private EditText edtNewPassword;
        private EditText edtConfirmPassword;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
