package mpoo.bsi.ufrpe.helpvegapp.evaluation.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.evaluation.domain.Predict;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class IndicationsAdapter extends RecyclerView.Adapter<IndicationsAdapter.ViewHolder>  {
    private List<Predict> predicts;
    private Context context;
    private ItemClickListener itemClickListener;

    public IndicationsAdapter(Context context, List<Predict> predicts, ItemClickListener itemClickListener){
        this.predicts = predicts;
        this.context = context;
        this.itemClickListener = itemClickListener;
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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, viewHolder.getPosition());
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position) {
        Restaurant restaurant = predicts.get(position).getRestaurant();
        myHolder.restaurantName.setText(restaurant.getRestaurantName());
        Double rate = predicts.get(position).getPredict();
        myHolder.ratingGeneral.setRating(new Float(rate));
        if (restaurant.getRestaurantImages().size() != 0){
            myHolder.restaurantPhoto.setImageBitmap(restaurant.getRestaurantImages().get(0));
        }


    }

    @Override
    public int getItemCount() {
        return predicts.size();
    }

}
