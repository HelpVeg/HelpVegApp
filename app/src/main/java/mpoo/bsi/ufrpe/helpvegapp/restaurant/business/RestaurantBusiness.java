package mpoo.bsi.ufrpe.helpvegapp.restaurant.business;


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Set;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.SlopeOne;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence.RestaurantDAO;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

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

    public ArrayList<Restaurant> getAllRestaurantsIndications(){
        //Criar array com inteiros com id dos restaurantes
        SlopeOne slopeOne = new SlopeOne();
        slopeOne.readData();
        Set<Integer> indications = slopeOne.getIndicationList(Session.getUserIn().getUserId());

        ArrayList<Restaurant> listRestaurants = new ArrayList<>();;
        for (Integer i : indications){
            listRestaurants.add(getRestaurantFromId(i));
        }
        return listRestaurants;
    }

}
