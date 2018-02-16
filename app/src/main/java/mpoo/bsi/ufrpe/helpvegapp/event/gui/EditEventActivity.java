package mpoo.bsi.ufrpe.helpvegapp.event.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import mpoo.bsi.ufrpe.helpvegapp.R;

public class EditEventActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        this.mViewHolder.nameEvent = findViewById(R.id.eventEditName);
        this.mViewHolder.descriptionEvent = findViewById(R.id.eventEditDescription);
        this.mViewHolder.editEvent = findViewById(R.id.btnEditEvent);
        this.mViewHolder.deleteEvent = findViewById(R.id.btnDeleteEvent);

    }
    private static class ViewHolder{
        private TextView nameEvent;
        private TextView descriptionEvent;
        private Button editEvent;
        private Button deleteEvent;

    }
}
