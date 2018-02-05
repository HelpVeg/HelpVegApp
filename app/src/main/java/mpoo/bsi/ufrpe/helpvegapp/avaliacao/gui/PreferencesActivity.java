package mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;


import mpoo.bsi.ufrpe.helpvegapp.R;

public class PreferencesActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        mViewHolder.ratingFood = findViewById(R.id.ratingBarFood);
        mViewHolder.ratingPrice = findViewById(R.id.ratingBarPrice);
        mViewHolder.ratingPlace = findViewById(R.id.ratingBarPlace);
        mViewHolder.ratingService = findViewById(R.id.ratingBarService);


        mViewHolder.ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //AQUI COLOCA PRA SALVAR USANDO O "rating" que recebe o valor
            }
        });
        mViewHolder.ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //AQUI COLOCA PRA SALVAR USANDO O "rating" que recebe o valor

            }
        });
        mViewHolder.ratingPlace.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //AQUI COLOCA PRA SALVAR USANDO O "rating" que recebe o valor

            }
        });
        mViewHolder.ratingPrice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //AQUI COLOCA PRA SALVAR USANDO O "rating" que recebe o valor

            }
        });

    }

    private static class ViewHolder{
        private RatingBar ratingFood;
        private RatingBar ratingPrice;
        private RatingBar ratingService;
        private RatingBar ratingPlace;
    }
}
