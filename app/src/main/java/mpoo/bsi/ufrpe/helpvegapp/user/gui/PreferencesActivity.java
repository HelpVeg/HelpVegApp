package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;


public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private UserBusiness userBusiness = new UserBusiness();
    private float numService;
    private float numFood;
    private float numPrice;
    private float numPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        this.mViewHolder.ratingFood = findViewById(R.id.ratingBarFood);
        this.mViewHolder.ratingPrice = findViewById(R.id.ratingBarPrice);
        this.mViewHolder.ratingPlace = findViewById(R.id.ratingBarPlace);
        this.mViewHolder.ratingService = findViewById(R.id.ratingBarService);

        this.mViewHolder.btnSave = findViewById(R.id.preferenceBtnSave);
        this.mViewHolder.btnSave.setOnClickListener(this);

        showLoggedUserPreference();

        getPreferenceFood();
        getPreferencePlace();
        getPreferencePrice();
        getPreferenceService();
    }

    public void showLoggedUserPreference(){
        User user = userBusiness.getUserFromSession();
        Preferences preferences = preferencesBusiness.getPreferencesFromUser(user);

        this.mViewHolder.ratingFood.setRating(preferences.getFood());
        this.mViewHolder.ratingPrice.setRating(preferences.getPrice());
        this.mViewHolder.ratingPlace.setRating(preferences.getAmbiance());
        this.mViewHolder.ratingService.setRating(preferences.getService());
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
        preferencesBusiness.registerPreferences(getPreferenceFood(),
                getPreferenceService(), getPreferencePlace(),getPreferencePrice());
        Toast.makeText(this, R.string.toastUpdatePreferences, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private static class ViewHolder{
        private RatingBar ratingFood;
        private RatingBar ratingPrice;
        private RatingBar ratingService;
        private RatingBar ratingPlace;

        private Button btnSave;
    }

}