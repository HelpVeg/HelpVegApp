package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.event.business.EventBusiness;
import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.MapsActivity;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

/**
 * <h1>ProfileActivity</h1>
 * Activity responsavel pela tela de login.
 */
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private EventBusiness eventBusiness = new EventBusiness();
    /**
     * O metodo onCreate() seta o layout: activity_profile e setar os
     * EditTexts, Buttons e CircleImageView do layout para cada atributo da classe e
     * chamar o metodo checkSession() e showUserLoggedData() da mesma
     * classe.
     * @see ProfileActivity#checkSession()
     * @see ProfileActivity#showUserLoggedData()
     * @param savedInstanceState Um objeto da classe Bundle que contem o estado anterior da activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mViewHolder.textNameProfile = findViewById(R.id.textNameProfile);
        this.mViewHolder.textEmailProfile = findViewById(R.id.textEmailProfile);
        this.mViewHolder.imgProfile = findViewById(R.id.imgProfilePhoto);

        this.mViewHolder.btnEditProfile = findViewById(R.id.profileBtnEdit);
        this.mViewHolder.btnEditPassword = findViewById(R.id.profileBtnEditPassword);
        this.mViewHolder.btnPreference = findViewById(R.id.profileBtnPreference);
        this.mViewHolder.btnViewEvents = findViewById(R.id.profileBtnEvent);


        this.mViewHolder.btnEditPassword.setOnClickListener(this);
        this.mViewHolder.btnEditProfile.setOnClickListener(this);
        this.mViewHolder.btnPreference.setOnClickListener(this);
        this.mViewHolder.btnViewEvents.setOnClickListener(this);

        checkSession();
        showUserLoggedData();
    }

    /**
     *O metodo checkSession() recupera o usuario da sessao
     */
    public void checkSession(){
        if (Session.getUserIn() == null){
            new UserBusiness().recoverSession();
        }
    }

    /**
     * O metodo showUserLoggedData() mostra na tela o email,nome e foto do usuario, recuperado pela sessao
     */
    public void showUserLoggedData(){
        this.mViewHolder.textEmailProfile.setText(Session.getUserIn().getUserEmail());
        this.mViewHolder.textNameProfile.setText(Session.getUserIn().getUserName());
        if (Session.getUserIn().getUserPhoto()!=null){
            this.mViewHolder.imgProfile.setImageBitmap(Session.getUserIn().getUserPhoto());
        }
    }

    /**
     * O metodo onClick() recebe o id da view e compara, se o id for igual ao do btnEdit ele direciona pra a
     * editProfileActivity, se for igual ao profileBtnEditPassword, direciona a EditPasswordActivity e se
     * for igual a profileBtnPreference direciona a PreferencesActivity.
     * @param view Recebe o que foi observado na view
     */
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
        }else if (id == R.id.profileBtnPreference){
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.profileBtnEvent){
            openEventDialog();
        }
    }

    public void openEventDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.insert_event, null);

        final EditText eventName = view.findViewById(R.id.editEventName);
        final EditText eventDescription = view.findViewById(R.id.editEventDescription);

        builder.setTitle("Adicione um evento");
        builder.setPositiveButton("Adicionar evento", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Event event = new Event();
                event.setUserEvent(new UserBusiness().getUserFromSession());
                event.setNameEvent(eventName.getText().toString());
                event.setDescriptionEvent(eventDescription.getText().toString());
                new Session().setCurrentEvent(event);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void confirmEvent(Event event){
        eventBusiness.insertEvent(event);
    }

    private static class ViewHolder{
        private TextView textNameProfile;
        private TextView textEmailProfile;
        private CircleImageView imgProfile;
        private Button btnViewEvents;
        private Button btnEditProfile;
        private Button btnEditPassword;
        private Button btnPreference;
    }
    /**
     * Metodo utiliza o intent para ir para a MapsActivity
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }

}
