package mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.business.RatingBusiness;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.RestaurantActivity;

public class IndicationsAdapter extends RecyclerView.Adapter<IndicationsAdapter.ViewHolder>  {
    private List<Restaurant> restaurants;
    private Context context;

    public IndicationsAdapter(Context context, List<Restaurant> restaurants){
        this.restaurants = restaurants;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RatingBar ratingGeneral;
        private TextView restaurantName;
        private CircleImageView restaurantPhoto;


        public ViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.nameRestaurantCard);
            ratingGeneral = view.findViewById(R.id.ratingBarGeneralCard);
            restaurantPhoto = view.findViewById(R.id.imageRestaurantCard);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indications_row, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position) {
        Restaurant restaurant = restaurants.get(position);
        myHolder.restaurantName.setText(restaurant.getRestaurantName());
        float rate = new RatingBusiness().getRatingGeneral(restaurant.getRestaurantId());
        myHolder.ratingGeneral.setRating(rate);
        if (restaurant.getRestaurantImages().size() != 0){
            myHolder.restaurantPhoto.setImageBitmap(restaurant.getRestaurantImages().get(0));
        }


    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

}
