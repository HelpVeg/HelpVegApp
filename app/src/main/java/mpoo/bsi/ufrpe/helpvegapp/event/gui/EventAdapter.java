package mpoo.bsi.ufrpe.helpvegapp.event.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.evaluation.gui.ItemClickListener;
import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>  {
    private List<Event> events;
    private Context context;
    private ItemClickListener itemClickListener;

    public EventAdapter(Context context, List<Event> events, ItemClickListener itemClickListener){
        this.events = events;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameEvent;
        private TextView descriptionEvent;


        public ViewHolder(View view) {
            super(view);
            nameEvent = view.findViewById(R.id.eventNameCard);
            descriptionEvent = view.findViewById(R.id.eventDescriptionCard);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);
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
        Event event = events.get(position);
        myHolder.nameEvent.setText(event.getNameEvent());
        myHolder.descriptionEvent.setText(event.getDescriptionEvent());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void insertItem(Event event) {
        events.add(event);
        notifyItemInserted(getItemCount());
    }


}

