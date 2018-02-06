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

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;


public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private UserBusiness userBusiness = new UserBusiness();
    private static final int COMUM = 0;
    private static final int VEGANO_VEGETARIANO = 1;
    private static final int VEGANO = 2;
    private float numService;
    private float numFood;
    private float numPrice;
    private float numPlace;
    private String restaurantType;


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

    public void createSpinner(){
        ArrayList<String> types = new ArrayList<String>();
        types.add("Comum");
        types.add("Vegetariano e vegano");
        types.add("Vegano");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.layout_spinner,types);
        this.mViewHolder.spinnerType.setAdapter(spinnerAdapter);
    }

    public void showLoggedUserPreference(){
        User user = userBusiness.getUserFromSession();
        Preferences preferences = preferencesBusiness.getPreferencesFromUser(user);
        if (preferences != null){
            numFood = preferences.getFood();
            this.mViewHolder.ratingFood.setRating(preferences.getFood());
            numPrice = preferences.getPrice();
            this.mViewHolder.ratingPrice.setRating(preferences.getPrice());
            numPlace = preferences.getAmbiance();
            this.mViewHolder.ratingPlace.setRating(preferences.getAmbiance());
            numService = preferences.getService();
            this.mViewHolder.ratingService.setRating(preferences.getService());
            this.mViewHolder.spinnerType.setSelection(getCurrentTpe(preferences));

        }
    }

    public int getCurrentTpe(Preferences preference){
        if (preference.getType().equals("Comum")){
            return COMUM;
        } if (preference.getType().equals("Vegetariano e vegano")){
            return VEGANO_VEGETARIANO;
        } else {
            return VEGANO;
        }
    }

    public float getPreferenceService() {
        this.mViewHolder.ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numService = rating;
            }
        });
        return numService;
    }

    public float getPreferenceFood(){
        this.mViewHolder.ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numFood = rating;
            }
        });
        return numFood;
    }

    public float getPreferencePlace(){
        this.mViewHolder.ratingPlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPlace = rating;
            }
        });
        return numPlace;
    }

    public float getPreferencePrice(){
        this.mViewHolder.ratingPrice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPrice = rating;

            }
        });
        return numPrice;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.preferenceBtnSave){
            insertPreference();
        }
    }

    public void insertPreference(){
        Preferences preference = new Preferences();
        preference.setService(getPreferenceService());
        preference.setFood(getPreferenceFood());
        preference.setPrice(getPreferencePrice());
        preference.setAmbiance(getPreferencePlace());
        preference.setType(restaurantType);

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
        restaurantType = (String) parent.getItemAtPosition(position);
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