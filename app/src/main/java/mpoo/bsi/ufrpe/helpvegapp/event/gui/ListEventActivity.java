package mpoo.bsi.ufrpe.helpvegapp.event.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.evaluation.gui.ItemClickListener;
import mpoo.bsi.ufrpe.helpvegapp.event.business.EventBusiness;
import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.gui.MapsActivity;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class ListEventActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private List<Event> listEvents;
    private EventBusiness eventBusiness = new EventBusiness();
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        this.mViewHolder.recyclerView =  findViewById(R.id.eventList);
        this.mViewHolder.floatingActionMenu = findViewById(R.id.floatingMenu);
        this.mViewHolder.floatingActionButtonAdd = findViewById(R.id.btnAddEvent);


        this.mViewHolder.floatingActionButtonAdd.setOnClickListener(this);
        createAdapter();
        createEventList();

    }

    public void createAdapter(){
        listEvents = eventBusiness.getAllEvents();
        eventAdapter = new EventAdapter(MyApp.getContext(), listEvents, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                new EventBusiness().selectEvent(listEvents.get(position));
                moveToEventActivity();
            }
        });
    }

    public void createEventList(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mViewHolder.recyclerView.setLayoutManager(mLayoutManager);
        this.mViewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mViewHolder.recyclerView.addItemDecoration(new MyDivider(this,LinearLayoutManager.VERTICAL,0));
        this.mViewHolder.recyclerView.setAdapter(eventAdapter);
    }

    public void moveToEventActivity(){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnAddEvent){
            openEventDialog();
        }
    }

    public void openEventDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.insert_event, null);

        final EditText eventName = view.findViewById(R.id.editEventName);
        final EditText eventDescription = view.findViewById(R.id.editEventDescription);

        builder.setTitle("Adicione um evento");
        builder.setPositiveButton("Adicionar evento", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Event event = new Event();
                event.setUserEvent(new UserBusiness().getUserFromSession());
                event.setNameEvent(eventName.getText().toString());
                event.setDescriptionEvent(eventDescription.getText().toString());
                eventBusiness.insertEvent(event);
                eventAdapter.insertItem(event);
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




    private static class ViewHolder {
        private RecyclerView recyclerView;
        private FloatingActionMenu floatingActionMenu;
        private com.github.clans.fab.FloatingActionButton floatingActionButtonAdd;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
