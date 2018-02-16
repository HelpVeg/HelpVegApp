package mpoo.bsi.ufrpe.helpvegapp.evaluation.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.evaluation.domain.Predict;
import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.MapsActivity;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.RestaurantActivity;

public class IndicationsActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private List<Predict> listIndications;
    private RestaurantBusiness restaurantBusiness = new RestaurantBusiness();
    private IndicationsAdapter indicationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indications);

        this.mViewHolder.recyclerView =  findViewById(R.id.indicationsList);

        createAdapter();
        createIndicationsList();
    }

    public void createAdapter(){
        listIndications = restaurantBusiness.getAllRestaurantsIndications();
        indicationsAdapter = new IndicationsAdapter(MyApp.getContext(), listIndications, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                restaurantBusiness.selectRestaurant(listIndications.get(position).getRestaurant());
                moveToRestaurantActivity();
            }
        });
    }

    public void createIndicationsList(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mViewHolder.recyclerView.setLayoutManager(mLayoutManager);
        this.mViewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mViewHolder.recyclerView.addItemDecoration(new MyDivider(this,LinearLayoutManager.VERTICAL,0));
        this.mViewHolder.recyclerView.setAdapter(indicationsAdapter);
    }

    private static class ViewHolder{
        private RecyclerView recyclerView;
    }

    public void moveToRestaurantActivity(){
        Intent intent = new Intent(this, RestaurantActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }

}
