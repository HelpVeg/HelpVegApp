package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.gui.MapsActivity;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    ViewHolder mViewHolder = new ViewHolder();
    Restaurant restaurant = new Restaurant();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        loadRestaurant();

        mViewHolder.imageRestaurant = findViewById(R.id.imageRestaurant);
        mViewHolder.restaurantName = findViewById(R.id.nameProfileRestaurant);
        mViewHolder.restaurantType = findViewById(R.id.typeProfileRestaurant);
        mViewHolder.ratingBarGeneral = findViewById(R.id.ratingBarGeral);
        mViewHolder.ratingBarFood = findViewById(R.id.ratingBarComida);
        mViewHolder.ratingBarPlace = findViewById(R.id.ratingBarAmbiente);
        mViewHolder.ratingBarService = findViewById(R.id.ratingBarServiço);
        mViewHolder.ratingBarPrice = findViewById(R.id.ratingBarPreço);
        mViewHolder.btnComments = findViewById(R.id.btnComments);
        mViewHolder.btnComments.setOnClickListener(this);

        mViewHolder.restaurantName.setText(restaurant.getRestaurantName());
        mViewHolder.restaurantType.setText(restaurant.getRestaurantType());
    }


    public void loadRestaurant(){
        Intent intent = getIntent();
        restaurant.setRestaurantId(intent.getIntExtra("id",0));
        restaurant.setRestaurantName(intent.getStringExtra("name"));
        restaurant.setRestaurantType(intent.getStringExtra("type"));
        double latitude = intent.getDoubleExtra("lat",0);
        double longitude = intent.getDoubleExtra("long",0);
        restaurant.setLatLgn(new LatLng(latitude,longitude));
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.btnComments){
            //aqui chamar a função pra mostrar os coments
        }
    }

    private static class ViewHolder{
        private CircleImageView imageRestaurant;
        private TextView restaurantName;
        private TextView restaurantType;
        private RatingBar ratingBarGeneral;
        private RatingBar ratingBarFood;
        private RatingBar ratingBarPlace;
        private RatingBar ratingBarService;
        private RatingBar ratingBarPrice;
        private Button btnComments;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
