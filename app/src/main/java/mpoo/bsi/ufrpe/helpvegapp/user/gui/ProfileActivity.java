package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.content.res.Resources;

import mpoo.bsi.ufrpe.helpvegapp.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    private static class ViewHolder{
        private Button btnEditProfile;
        private Button btnLogout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mViewHolder.btnEditProfile = findViewById(R.id.profileBtnEdit);
        this.mViewHolder.btnLogout = findViewById(R.id.profileBtnLogout);

        this.mViewHolder.btnEditProfile.setOnClickListener(this);
        this.mViewHolder.btnLogout.setOnClickListener(this);
    }


    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.profileBtnEdit){
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        }
        if (id == R.id.profileBtnLogout){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
