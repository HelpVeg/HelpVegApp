package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.CommentActivity;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    ViewHolder mViewHolder = new ViewHolder();
    Restaurant restaurant = new Restaurant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        restaurant = Session.getCurrentRestaurant();

        mViewHolder.imageRestaurant = findViewById(R.id.imageRestaurant);
        mViewHolder.restaurantName = findViewById(R.id.nameProfileRestaurant);
        mViewHolder.restaurantType = findViewById(R.id.typeProfileRestaurant);
        mViewHolder.btnComments = findViewById(R.id.btnComments);
        mViewHolder.btnComments.setOnClickListener(this);

        mViewHolder.imageRestaurant.setOnClickListener(this);

        showRestaurantInformation();
    }

    public void showRestaurantInformation(){
        mViewHolder.restaurantName.setText(restaurant.getRestaurantName());
        mViewHolder.restaurantType.setText(restaurant.getRestaurantType());
        if (restaurant.getRestaurantImages().size() != 0){
            mViewHolder.imageRestaurant.setImageBitmap(restaurant.getRestaurantImages().get(0));
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
        }
    }

    private static class ViewHolder{
        private CircleImageView imageRestaurant;
        private TextView restaurantName;
        private TextView restaurantType;
        private Button btnComments;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
