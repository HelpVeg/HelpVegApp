package mpoo.bsi.ufrpe.helpvegapp.restaurant.business;


import android.graphics.Bitmap;

import java.util.ArrayList;

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
/*
    public void viewRestaurants() {
        ArrayList<Restaurant> restaurants = getAllRestaurants();
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant res = restaurants.get(i);
            System.out.println("#" + i + " ID: " + res.getRestaurantId() + " Name: " + res.getRestaurantName() + ", Type: " + res.getRestaurantType());
        }
        if (restaurants.size() == 0) System.out.println("# Não existem registros.");
    }
*/
    public void selectRestaurant(Restaurant restaurant){
        restaurant.setRestaurantImages(getAllImagesFromRestaurant(restaurant.getRestaurantId()));
        Session.setCurrentRestaurant(restaurant);
    }

    public Restaurant getSingleRestaurant(int id){
        return getRestaurantDAO().getRestaurantById(id);
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

}
