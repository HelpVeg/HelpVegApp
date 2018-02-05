package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

import mpoo.bsi.ufrpe.helpvegapp.R;

public class EditPreferencesActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preferences);

        mViewHolder.ratingEditFood = findViewById(R.id.ratingBarFood);
        mViewHolder.ratingEditPrice = findViewById(R.id.ratingBarPrice);
        mViewHolder.ratingEditPlace = findViewById(R.id.ratingBarPlace);
        mViewHolder.ratingEditService = findViewById(R.id.ratingBarService);

    }

    private static class ViewHolder{
        private RatingBar ratingEditFood;
        private RatingBar ratingEditPrice;
        private RatingBar ratingEditService;
        private RatingBar ratingEditPlace;
    }

}
