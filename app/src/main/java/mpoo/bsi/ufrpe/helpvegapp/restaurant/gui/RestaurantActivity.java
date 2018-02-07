package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.business.RatingBusiness;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.CommentActivity;
import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    ViewHolder mViewHolder = new ViewHolder();
    Restaurant restaurant = new Restaurant();
    Rating currentRating;
    RestaurantBusiness restaurantBusiness = new RestaurantBusiness();
    RatingBusiness ratingBusiness = new RatingBusiness();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        restaurant = Session.getCurrentRestaurant();

        mViewHolder.imageRestaurant = findViewById(R.id.imageRestaurant);
        mViewHolder.restaurantName = findViewById(R.id.nameProfileRestaurant);
        mViewHolder.restaurantType = findViewById(R.id.typeProfileRestaurant);

        mViewHolder.btnComments = findViewById(R.id.btnComments);
        mViewHolder.btnRating = findViewById(R.id.btnRating);

        mViewHolder.ratingBarGeneral = findViewById(R.id.ratingBarGeneral);
        mViewHolder.ratingBarFood = findViewById(R.id.ratingBarComida);
        mViewHolder.ratingBarService = findViewById(R.id.ratingBarServiço);
        mViewHolder.ratingBarAmbiance = findViewById(R.id.ratingBarAmbiente);
        mViewHolder.ratingBarPrice = findViewById(R.id.ratingBarPreço);

        mViewHolder.btnComments.setOnClickListener(this);
        mViewHolder.btnRating.setOnClickListener(this);
        mViewHolder.imageRestaurant.setOnClickListener(this);
        showRestaurantInformation();
        updateRating();
    }

    public void showRestaurantInformation(){
        mViewHolder.restaurantName.setText(restaurant.getRestaurantName());
        mViewHolder.restaurantType.setText(restaurant.getRestaurantType());
        if (restaurant.getRestaurantImages().size() != 0){
            mViewHolder.imageRestaurant.setImageBitmap(restaurant.getRestaurantImages().get(0));
        }
    }

    public void updateRating(){
        int restaurantId = restaurantBusiness.getRestaurantFromSession().getRestaurantId();
        Rating rating = ratingBusiness.getRatingFromRestaurant(restaurantId);
        if (rating != null){
            mViewHolder.ratingBarFood.setRating(rating.getFood());
            mViewHolder.ratingBarPrice.setRating(rating.getPrice());
            mViewHolder.ratingBarAmbiance.setRating(rating.getAmbiance());
            mViewHolder.ratingBarService.setRating(rating.getService());
            mViewHolder.ratingBarGeneral.setRating(rating.getGeneral());
        }
    }


    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.btnComments){
            Intent intent = new Intent(this,CommentActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.imageRestaurant){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.btnRating){
            currentRating = new Rating();
            openRatingDialog();
        }
    }

    public void openRatingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.insert_rating, null);
        createDialogView(view);
        builder.setTitle("O que você acha do " + restaurant.getRestaurantName() +"?");
        builder.setPositiveButton("Avaliar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(ratingBusiness.registerRating(currentRating)){
                    Toast.makeText(MyApp.getContext(),"Avaliação registrada", Toast.LENGTH_LONG).show();
                    updateRating();
                }
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

    public void createDialogView(View view){
        RatingBar ratingFood = view.findViewById(R.id.ratingBarFood);
        RatingBar ratingService = view.findViewById(R.id.ratingBarService);
        RatingBar ratingPrice = view.findViewById(R.id.ratingBarPrice);
        RatingBar ratingAmbience = view.findViewById(R.id.ratingBarAmbiance);

        ratingFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating.setFood(rating);
            }
        });
        ratingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating.setService(rating);
            }
        });
        ratingPrice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating.setPrice(rating);
            }
        });
        ratingAmbience.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating.setAmbiance(rating);
            }
        });
    }

    private static class ViewHolder{
        private CircleImageView imageRestaurant;
        private TextView restaurantName;
        private TextView restaurantType;
        private Button btnComments;
        private Button btnRating;

        private RatingBar ratingBarGeneral;
        private RatingBar ratingBarFood;
        private RatingBar ratingBarAmbiance;
        private RatingBar ratingBarService;
        private RatingBar ratingBarPrice;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
