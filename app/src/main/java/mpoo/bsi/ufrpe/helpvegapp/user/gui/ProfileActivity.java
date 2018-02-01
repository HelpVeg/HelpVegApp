package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mViewHolder.textNameProfile = findViewById(R.id.textNameProfile);
        this.mViewHolder.textEmailProfile = findViewById(R.id.textEmailProfile);
        this.mViewHolder.imgProfile = findViewById(R.id.imgProfilePhoto);

        this.mViewHolder.btnEditProfile = findViewById(R.id.profileBtnEdit);
        this.mViewHolder.btnEditPassword = findViewById(R.id.profileBtnEditPassword);

        this.mViewHolder.btnEditPassword.setOnClickListener(this);
        this.mViewHolder.btnEditProfile.setOnClickListener(this);
        checkSession();
        showUserLoggedData();
    }


    public void checkSession(){
        if (Session.getUserIn() == null){
            new UserBusiness().recoverSession();
        }
    }

    
    public void showUserLoggedData(){
        this.mViewHolder.textEmailProfile.setText(Session.getUserIn().getUserEmail());
        this.mViewHolder.textNameProfile.setText(Session.getUserIn().getUserName());
        if (Session.getUserIn().getUserPhoto()!=null){
            this.mViewHolder.imgProfile.setImageBitmap(Session.getUserIn().getUserPhoto());
        }
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.profileBtnEdit){
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.profileBtnEditPassword){
            Intent intent = new Intent(this, EditPasswordActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private static class ViewHolder{
        private TextView textNameProfile;
        private TextView textEmailProfile;
        private CircleImageView imgProfile;
        private Button btnEditProfile;
        private Button btnEditPassword;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }

}
