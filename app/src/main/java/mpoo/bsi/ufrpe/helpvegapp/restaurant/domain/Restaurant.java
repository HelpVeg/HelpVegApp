package mpoo.bsi.ufrpe.helpvegapp.restaurant.domain;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private List<Bitmap> restaurantImages;
    private LatLng latLgn;
    private String restaurantType;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Bitmap> getRestaurantImages() {
        return restaurantImages;
    }

    public void setRestaurantImages(List<Bitmap> restaurantImages) {
        this.restaurantImages = restaurantImages;
    }

    public LatLng getLatLgn() {
        return latLgn;
    }

    public void setLatLgn(LatLng latLgn) {
        this.latLgn = latLgn;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }
}
