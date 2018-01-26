package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;
import mpoo.bsi.ufrpe.helpvegapp.user.persistence.Session;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mViewHolder.textNameProfile = findViewById(R.id.textNameProfile);
        this.mViewHolder.textEmailProfile = findViewById(R.id.textEmailProfile);
        this.mViewHolder.btnEditProfile = findViewById(R.id.profileBtnEdit);

        this.mViewHolder.btnEditProfile.setOnClickListener(this);
        showUserLoggedData();
    }


    //mostra os dados do usuario nos TextView
    public void showUserLoggedData(){
        this.mViewHolder.textEmailProfile.setText(Session.getUserIn().getUserEmail());
        this.mViewHolder.textNameProfile.setText(Session.getUserIn().getUserName());
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.profileBtnEdit){
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder{
        private TextView textNameProfile;
        private TextView textEmailProfile;
        private Button btnEditProfile;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
