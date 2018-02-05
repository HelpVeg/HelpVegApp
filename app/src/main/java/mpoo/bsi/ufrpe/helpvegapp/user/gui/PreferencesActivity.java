package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;


import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;


public class PreferencesActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
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

        getPreferenceFood();
        getPreferencePlace();
        getPreferencePrice();
        getPreferenceService();
    }
    public void insertPreference(){
       preferencesBusiness.registerPreferences(getPreferenceFood(),getPreferenceService(), getPreferencePlace(),getPreferencePrice());
    }

    public float getPreferenceService() {
        this.mViewHolder.ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numService = rating;
                System.out.println(numService);
            }
        });
        return numService;
    }
    public float getPreferenceFood(){
        this.mViewHolder.ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numFood = rating;
                System.out.println(numFood);
            }
        });
        return numFood;
    }
    public float getPreferencePlace(){
        this.mViewHolder.ratingPlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPlace = rating;
                System.out.println(numPlace);
            }
        });
        return numPlace;
    }
    public float getPreferencePrice(){
        this.mViewHolder.ratingPrice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numPrice = rating;
                System.out.println(numPrice);

            }
        });
        return numPrice;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.preferenceBtnEdit){
            insertPreference();
        }
    }

    private static class ViewHolder{
        private RatingBar ratingFood;
        private RatingBar ratingPrice;
        private RatingBar ratingService;
        private RatingBar ratingPlace;
    }

}