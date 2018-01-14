package mpoo.bsi.ufrpe.helpvegapp.user.gui;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mpoo.bsi.ufrpe.helpvegapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private UserBusiness mUserBusiness;
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mViewHolder.editName = findViewById(R.id.registerName);
        this.mViewHolder.editEmail = findViewById(R.id.registerEmail);
        this.mViewHolder.editPass = findViewById(R.id.registerPass);
        this.mViewHolder.btnRegister = findViewById(R.id.registerBtnRegister);
        this.mViewHolder.navToLogin = findViewById(R.id.registerNavLogin);

        this.mViewHolder.btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.registerBtnRegister){
            registerAccount();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void registerAccount(){
        User user = new User();
        user.setUserName(mViewHolder.editName.getText().toString());
        user.setUserEmail(mViewHolder.editEmail.getText().toString());
        user.setUserEmail(mViewHolder.editPass.getText().toString());
        mUserBusiness.getUserDAO().registerUser(user);
    }

    public void navLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private static class ViewHolder{
        private EditText editName;
        private EditText editEmail;
        private EditText editPass;
        private Button btnRegister;
        private TextView navToLogin;
    }
}
