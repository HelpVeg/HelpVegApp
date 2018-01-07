package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mpoo.bsi.ufrpe.helpvegapp.R;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.mViewHolder.btnCancel = findViewById(R.id.EditProfileBtnCancel);
        this.mViewHolder.btnSave = findViewById(R.id.EditProfileBtnSave);

        this.mViewHolder.btnCancel.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);


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
    }
}
