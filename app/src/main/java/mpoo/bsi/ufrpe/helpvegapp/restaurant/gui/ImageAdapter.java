package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Comment;

public class ImageAdapter extends ArrayAdapter {
    private List<Bitmap> images;
    private int layoutResourceId;
    private Context context;

    public ImageAdapter(Context context, int layoutResourceId, ArrayList imageList) {
        super(context, layoutResourceId, imageList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.images = imageList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageRestaurant);
        }

        @Override
        public void onClick(View view) {

        }
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bitmap bitmap = images.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_row, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.image);
        image.setImageBitmap(bitmap );

        return convertView;
    }

    public void insertItem(Bitmap bitmap) {
        images.add(bitmap);
        synchronized(this){
            this.notify();
        }
    }
}

