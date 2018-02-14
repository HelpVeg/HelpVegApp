package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.EnumRestaurantType;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

/**
 * <h1>PreferencesActivity</h1>
 * Activity responsavel por implementar as funcionalidades de preferencias.
 */
public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private UserBusiness userBusiness = new UserBusiness();
    private double numService;
    private double numFood;
    private double numPrice;
    private double numPlace;
    private EnumRestaurantType spinnerType;
    ArrayList<String> types = new ArrayList<>();

    /**
     * O metodo onCreate() tem a funcionalidade de setar o layout: activity_preferences e setar os
     * EditTexts e Rating, do layout para cada atributo da classe e chamar os metodos createSpinner(),
     * showLoggedUserPreference(), getPreferenceFood(), getPreferencePlace(), PreferenceService() e
     * getPreferencePrice() da mesma classe.
     * @see PreferencesActivity#createSpinner()
     * @see PreferencesActivity#showLoggedUserPreference()
     * @see PreferencesActivity#getPreferenceFood()
     * @see PreferencesActivity#getPreferencePlace() ()
     * @see PreferencesActivity#getPreferenceService()
     * @see PreferencesActivity#getPreferencePrice()
     *
     * @param savedInstanceState Um objeto da classe Bundle que contem o estado anterior da activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        this.mViewHolder.ratingFood = findViewById(R.id.ratingBarFood);
        this.mViewHolder.ratingPrice = findViewById(R.id.ratingBarPrice);
        this.mViewHolder.ratingPlace = findViewById(R.id.ratingBarPlace);
        this.mViewHolder.ratingService = findViewById(R.id.ratingBarService);
        this.mViewHolder.spinnerType = findViewById(R.id.spinnerType);

        this.mViewHolder.btnSave = findViewById(R.id.preferenceBtnSave);
        this.mViewHolder.btnSave.setOnClickListener(this);
        this.mViewHolder.spinnerType.setOnItemSelectedListener(this);

        createSpinner();
        showLoggedUserPreference();

        getPreferenceFood();
        getPreferencePlace();
        getPreferencePrice();
        getPreferenceService();
    }

    /**
     * O metodo createSpinner() cria um select na tela de preferencias para o usuario informar o
     * seu tipo de restaurante. É criada uma lista com os dados recebidos da classe EnumRestaurantType e
     * passado para o adapter .
     */
    public void createSpinner(){
        List<EnumRestaurantType> enumList = Arrays.asList(EnumRestaurantType.values());
        for (EnumRestaurantType enumType: enumList){
            types.add(enumType.getDescription());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,R.layout.layout_spinner,types);
        this.mViewHolder.spinnerType.setAdapter(spinnerAdapter);
    }

    /**
     * O metodo showLoggedUserPreference() mostra na tela as preferencias do usuario recuperando o usuario pela
     * sessao e setando no Rating.
     */
    public void showLoggedUserPreference(){
        User user = userBusiness.getUserFromSession();
        Preferences preferences = preferencesBusiness.getPreferencesFromUser(user);
        if (preferences != null){
            numFood = preferences.getFood();
            this.mViewHolder.ratingFood.setRating((float) preferences.getFood());
            numPrice = preferences.getPrice();
            this.mViewHolder.ratingPrice.setRating((float) preferences.getPrice());
            numPlace = preferences.getAmbiance();
            this.mViewHolder.ratingPlace.setRating((float)(preferences.getAmbiance()));
            numService = preferences.getService();
            this.mViewHolder.ratingService.setRating((float)(preferences.getService()));
            this.mViewHolder.spinnerType.setSelection(getCurrentType(preferences));
        }
    }

    /**
     * O metodo getCurrentType() recebe o tipo de preferencia do usuario
     * @param preference Recebe o tipo de preferencia do usuario
     * @return Retorna o tipo de prefencia do usuario
     */

    public int getCurrentType(Preferences preference){
        return types.indexOf(preference.getType().getDescription());
    }

    /**
     * O metodo getPreferenceService() recebe a preferencia do usuario pelo metodo setOnRatingBarChange()
     * @return Retorna um float com o valor da preferencia do usuario
     */
    public double getPreferenceService() {
        this.mViewHolder.ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numService = rating;
            }
        });
        return numService;
    }
    /**
     * O metodo getPreferenceFood() recebe a preferencia do usuario pelo metodo setOnRatingBarChange()
     * @return Retorna um float com o valor da preferencia do usuario
     */
    public double getPreferenceFood(){
        this.mViewHolder.ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numFood = rating;
            }
        });
        return numFood;
    }
    /**
     * O metodo getPreferencePlace() recebe a preferencia do usuario pelo metodo setOnRatingBarChange()
     * @return Retorna um float com o valor da preferencia do usuario
     */
    public double getPreferencePlace(){
        this.mViewHolder.ratingPlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPlace = rating;
            }
        });
        return numPlace;
    }
    /**
     * O metodo getPreferenceFood() recebe a preferencia do usuario pelo metodo setOnRatingBarChange()
     * @return Retorna um float com o valor da preferencia do usuario
     */
    public double getPreferencePrice(){
        this.mViewHolder.ratingPrice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPrice = rating;

            }
        });
        return numPrice;
    }

    /**
     * No metodo onClick() e recebido o id e verificado se este e igual ao botao preferenceBtnSave, se for
     * insere a preferencia
     * @param view Recebe o que foi observado na view
     */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.preferenceBtnSave){
            insertPreference();
        }
    }

    /**
     * O metodo insertPreference() seta as preferencias do usuario e o tipo de restaurante
     */
    public void insertPreference(){
        Preferences preference = new Preferences();
        preference.setService(getPreferenceService());
        preference.setFood(getPreferenceFood());
        preference.setPrice(getPreferencePrice());
        preference.setAmbiance(getPreferencePlace());
        preference.setType(spinnerType);

        preferencesBusiness.registerPreferences(preference);
        Toast.makeText(this, R.string.toastUpdatePreferences, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerType = EnumRestaurantType.values()[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private static class ViewHolder{
        private RatingBar ratingFood;
        private RatingBar ratingPrice;
        private RatingBar ratingService;
        private RatingBar ratingPlace;

        private Spinner spinnerType;

        private Button btnSave;
    }

}