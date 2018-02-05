package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.business.CommentBusiness;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Comment;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.CommentAdapter;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.ItemClickListener;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui.MyDivider;
import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_GALERY = 0;
    private static final int REQUEST_CROP = 1;
    private ViewHolder mViewHolder = new ViewHolder();
    private List<Bitmap> listImages;
    private RestaurantBusiness restaurantBusiness = new RestaurantBusiness();
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mViewHolder.fabAddImage = findViewById(R.id.fabAddImage);
        mViewHolder.recyclerView = findViewById(R.id.recyclerView);

        mViewHolder.fabAddImage.setOnClickListener(this);

        createAdapter();
        createImageList();
    }

    public void createAdapter(){
        listImages = restaurantBusiness.getAllImagesFromRestaurant(Session.getCurrentRestaurant().getRestaurantId());
        imageAdapter = new ImageAdapter(MyApp.getContext(), listImages, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
    }

    public void createImageList(){
        mViewHolder.recyclerView.setLayoutManager( new GridLayoutManager(this, 2));
        //this.mViewHolder.recyclerView.setLayoutManager(mLayoutManager);
        this.mViewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        //this.mViewHolder.recyclerView.addItemDecoration(new MyDivider(this,LinearLayoutManager.VERTICAL,0));
        this.mViewHolder.recyclerView.setAdapter(imageAdapter);
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

        }
    }

    private static class ViewHolder{
        public FloatingActionButton fabAddImage;
        public RecyclerView recyclerView;
    }
}
