package mpoo.bsi.ufrpe.helpvegapp.event.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import mpoo.bsi.ufrpe.helpvegapp.R;
public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mViewHolder.floatingActionButtonAddEvent = findViewById(R.id.btnAddEvent);
        mViewHolder.floatingActionMenuEvent = findViewById(R.id.eventFloatingMenu);

        mViewHolder.floatingActionButtonAddEvent.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnAddEvent){
            Intent intent = new Intent (this, EditEventActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private static class ViewHolder{
        private FloatingActionMenu floatingActionMenuEvent;
        private com.github.clans.fab.FloatingActionButton floatingActionButtonAddEvent;
    }
}
