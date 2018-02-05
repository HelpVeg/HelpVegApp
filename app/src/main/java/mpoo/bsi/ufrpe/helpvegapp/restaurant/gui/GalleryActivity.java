package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;

import static android.view.View.GONE;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private static final int REQUEST_GALERY = 0;
    private static final int REQUEST_CROP = 1;
    private RestaurantBusiness restaurantBusiness = new RestaurantBusiness();
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mViewHolder.fabAddImage = findViewById(R.id.fabAddImage);
        mViewHolder.fabAddImage.setOnClickListener(this);
        mViewHolder.gridView = findViewById(R.id.gridView);
        mViewHolder.openImage = findViewById(R.id.openImage);
        createAdapter();
        createGrid();
    }

    public void createAdapter(){
        ArrayList<Bitmap> restaurantImages = Session.getCurrentRestaurant().getRestaurantImages();
        imageAdapter = new ImageAdapter(this, R.layout.activity_gallery, restaurantImages);
    }

    public void createGrid(){
        mViewHolder.gridView.setAdapter(imageAdapter);
        mViewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Bitmap bitmap = (Bitmap) parent.getItemAtPosition(position);
                mViewHolder.openImage.setImageBitmap(bitmap);
                mViewHolder.openImage.setVisibility(View.VISIBLE);
                mViewHolder.gridView.setVisibility(GONE);
                mViewHolder.fabAddImage.setVisibility(GONE);
            }
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fabAddImage){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_GALERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            final Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(targetUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 128);
            intent.putExtra("outputY", 128);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CROP);
        } else if (requestCode == REQUEST_CROP && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bitmap = extras.getParcelable("data");
            restaurantBusiness.saveRestaurantImage(bitmap);
            imageAdapter.insertItem(bitmap);
        }
    }

    private static class ViewHolder{
        public FloatingActionButton fabAddImage;
        public GridView gridView;
        public ImageView openImage;
    }

    @Override
    public void onBackPressed(){
        if(mViewHolder.openImage.getVisibility() == View.VISIBLE){
            mViewHolder.openImage.setVisibility(View.INVISIBLE);
            mViewHolder.fabAddImage.setVisibility(View.VISIBLE);
            mViewHolder.gridView.setVisibility(View.VISIBLE);
            return;
        }
        Intent intent = new Intent(this,RestaurantActivity.class);
        startActivity(intent);
        finish();
    }
}



