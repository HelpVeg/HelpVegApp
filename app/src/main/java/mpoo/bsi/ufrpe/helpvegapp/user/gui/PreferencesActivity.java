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
        List<EnumRestaurantType> enumList = Arrays.asList(EnumRestaurantType.values());
        for (EnumRestaurantType enumType: enumList){
            types.add(enumType.getDescription());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,R.layout.layout_spinner,types);
        this.mViewHolder.spinnerType.setAdapter(spinnerAdapter);
    }

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

    public int getCurrentType(Preferences preference){
        return types.indexOf(preference.getType().getDescription());
    }

    public double getPreferenceService() {
        this.mViewHolder.ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numService = rating;
            }
        });
        return numService;
    }

    public double getPreferenceFood(){
        this.mViewHolder.ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numFood = rating;
            }
        });
        return numFood;
    }

    public double getPreferencePlace(){
        this.mViewHolder.ratingPlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPlace = rating;
            }
        });
        return numPlace;
    }

    public double getPreferencePrice(){
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