package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.ItemClickListener;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>  {
    private List<Bitmap> images;
    private Context context;
    private ItemClickListener listener;

    public ImageAdapter(Context context, List<Bitmap> listComments, ItemClickListener listener){
        this.images = listComments;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageProfileCard);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), getPosition(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        final ImageAdapter.ViewHolder viewHolder = new ImageAdapter.ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v , viewHolder.getPosition());
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder myHolder, int position) {


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void insertItem(Bitmap bitmap) {
        images.add(bitmap);
        notifyItemInserted(getItemCount());
    }
}

