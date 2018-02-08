package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.user.gui.LoginActivity;
import mpoo.bsi.ufrpe.helpvegapp.user.gui.ProfileActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private static final int REQUEST_FINE_LOCATION = 1;
    static final String TYPE = "Tipo do restaurante: ";
    private RestaurantBusiness restaurantBusiness = new RestaurantBusiness();
    private UserBusiness userBusiness = new UserBusiness();
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mViewHolder.drawer = findViewById(R.id.drawer_layout);
        mViewHolder.toolbar = findViewById(R.id.toolbar);
        mViewHolder.navigationView = findViewById(R.id.nav_view);

        checkSession();
        createMenu();

    }


    public void checkSession(){
        if (Session.getUserIn() == null){
            userBusiness.recoverSession();
        }
    }

    private void checkPermission() {
        boolean permissionFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;
        boolean permissionCoarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;

        if (permissionFineLocation && permissionCoarseLocation) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                updateLocation();
            }
        }
    }

    public void createMenu(){
        mViewHolder.toolbar.setTitle("");
        setSupportActionBar(mViewHolder.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mViewHolder.drawer, mViewHolder.toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        mViewHolder.drawer.setDrawerListener(toggle);
        toggle.syncState();
        mViewHolder.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        updateLocation();
        infoWindowPerso();
        createMarkers();
    }

    public void infoWindowPerso(){
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Restaurant restaurant = (Restaurant) marker.getTag();
                View popup = getLayoutInflater().inflate(R.layout.activity_info_window,null);

                TextView textView = popup.findViewById(R.id.titleInfoWindow);
                ImageView imageView = popup.findViewById(R.id.iconRestaurant);
                TextView snippet = popup.findViewById(R.id.snippet);
                if (restaurant.getRestaurantImages().size()!=0){
                    imageView.setImageBitmap(restaurant.getRestaurantImages().get(0));
                }
                textView.setText(marker.getTitle());
                snippet.setText(TYPE + marker.getSnippet());

                return popup;
            }
        });
    }

    public void createMarkers(){
        ArrayList<Restaurant> restaurants = restaurantBusiness.getAllRestaurants();
        for (int i = 0; i < restaurants.size(); i++){
            Restaurant restaurant = restaurants.get(i);
            restaurant.setRestaurantImages(restaurantBusiness.getAllImagesFromRestaurant(restaurant.getRestaurantId()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(restaurant.getLatLgn())
                    .title(restaurant.getRestaurantName())
                    .snippet(restaurant.getRestaurantType()));
            separationMarkers(marker,restaurant);
            marker.setTag(restaurant);
        }
    }

    public void separationMarkers(Marker marker, Restaurant restaurant){
        if (restaurant.getRestaurantType().equals("vegano")){
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(120.0f));
        } else if (restaurant.getRestaurantType().equals("vegetariano e vegano")){
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(210.0f));
        } else {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(350.0f));
        }
    }


    public void updateLocation() {
        checkPermission();
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED /*&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED*/) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            goToCurrentLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mMap.setMyLocationEnabled(true);
            goToCurrentLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
    }

    public void goToCurrentLocation(Location location){
        if (location!=null){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer,menu);
        ImageView photo = findViewById(R.id.navPhoto);
        TextView email = findViewById(R.id.navEmail);
        TextView name = findViewById(R.id.navName);
        if (Session.getUserIn().getUserPhoto()!=null){
            photo.setImageBitmap(Session.getUserIn().getUserPhoto());
        }
        name.setText(Session.getUserIn().getUserName());
        email.setText(Session.getUserIn().getUserEmail());
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mViewHolder.drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.navLogout: {
                navigationLogout();
                break;
            }
            case R.id.navProfile: {
                navigationProfile();
                break;
            }
        }
        return true;
    }

    public void navigationLogout(){
        userBusiness.endSession();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigationProfile(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Restaurant restaurant = (Restaurant) marker.getTag();
        restaurantBusiness.selectRestaurant(restaurant);
        Intent intent = new Intent(this,RestaurantActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mViewHolder.drawer.isDrawerOpen(GravityCompat.START)) {
            mViewHolder.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private static class ViewHolder{
        private DrawerLayout drawer;
        private Toolbar toolbar;
        private NavigationView navigationView;
    }
}