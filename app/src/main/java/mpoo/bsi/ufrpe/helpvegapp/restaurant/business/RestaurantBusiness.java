package mpoo.bsi.ufrpe.helpvegapp.restaurant.business;


import android.graphics.Bitmap;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.evaluation.domain.Predict;
import mpoo.bsi.ufrpe.helpvegapp.evaluation.domain.SlopeOne;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence.RestaurantDAO;

public class RestaurantBusiness {

    private RestaurantDAO restaurantDAO = new RestaurantDAO();

    private RestaurantDAO getRestaurantDAO(){
        return restaurantDAO;
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        return restaurantDAO.getAllRestaurants();
    }

    public void selectRestaurant(Restaurant restaurant){
        restaurant.setRestaurantImages(getAllImagesFromRestaurant(restaurant.getRestaurantId()));
        Session.setCurrentRestaurant(restaurant);
    }

    public ArrayList<Bitmap> getAllImagesFromRestaurant(int restaurantId){
        return restaurantDAO.getAllImagesFromRestaurant(restaurantId);
    }

    public void saveRestaurantImage(Bitmap bitmap){
        restaurantDAO.insertRestaurantImage(bitmap,Session.getCurrentRestaurant().getRestaurantId());
    }

    public Restaurant getRestaurantFromId(int restaurantId){
        return getRestaurantDAO().getRestaurantById(restaurantId);
    }

    public Restaurant getRestaurantFromSession(){
        return Session.getCurrentRestaurant();
    }

    public ArrayList<Predict> getAllRestaurantsIndications(){
        SlopeOne slopeOne = new SlopeOne();
        return slopeOne.getIndicationList();
    }

}
