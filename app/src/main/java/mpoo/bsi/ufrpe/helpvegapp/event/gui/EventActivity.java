package mpoo.bsi.ufrpe.helpvegapp.event.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.event.business.EventBusiness;
import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private EventBusiness eventBusiness = new EventBusiness();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        this.mViewHolder.nameEvent = findViewById(R.id.eventPageName);
        this.mViewHolder.descriptionEvent = findViewById(R.id.eventPageDescription);
        this.mViewHolder.editEvent = findViewById(R.id.btnEditEvent);
        this.mViewHolder.deleteEvent = findViewById(R.id.btnDeleteEvent);

        this.mViewHolder.editEvent.setOnClickListener(this);
        this.mViewHolder.deleteEvent.setOnClickListener(this);

        returnDataEvent();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnEditEvent){
            openEventDialog();
        }
        if(id == R.id.btnDeleteEvent){
            eventBusiness.deleteEvent(eventBusiness.getEventFromSession());
            Intent intent = new Intent(this, ListEventActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private static class ViewHolder{
        private TextView nameEvent;
        private TextView descriptionEvent;
        private Button editEvent;
        private Button deleteEvent;

    }

    public void returnDataEvent(){
        this.mViewHolder.nameEvent.setText(eventBusiness.getEventFromSession().getNameEvent());
        this.mViewHolder.descriptionEvent.setText(eventBusiness.getEventFromSession().getDescriptionEvent());
    }

    public void openEventDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.insert_event, null);
        final EditText eventName = view.findViewById(R.id.editEventName);
        final EditText eventDescription = view.findViewById(R.id.editEventDescription);
        eventName.setText(eventBusiness.getEventFromSession().getNameEvent());
        eventDescription.setText(eventBusiness.getEventFromSession().getDescriptionEvent());
        builder.setTitle("Editar evento");
        builder.setPositiveButton("Editar evento", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Event event = new Event();
                event.setIdEvent(eventBusiness.getEventFromSession().getIdEvent());
                event.setUserEvent(new UserBusiness().getUserFromSession());
                event.setNameEvent(eventName.getText().toString());
                event.setDescriptionEvent(eventDescription.getText().toString());
                eventBusiness.updateCurrentEvent(event);
                returnDataEvent();
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ListEventActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResume(){
        super.onResume();
        EventBusiness eventBusiness = new EventBusiness();
        eventBusiness.getEventFromSession();
    }
}

