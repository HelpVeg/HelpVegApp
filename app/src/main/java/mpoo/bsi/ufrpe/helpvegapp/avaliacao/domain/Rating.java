package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class Rating {
    private int ratingId;
    private User userRating;
    private Restaurant restaurantRating;
    private double food;
    private double price;
    private double service;
    private double ambiance;

    public int getRatingId() {
        return ratingId;
    }
    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public User getUserRating() {
        return userRating;
    }
    public void setUserRating(User userRating) {
        this.userRating = userRating;
    }

    public Restaurant getRestaurantRating() {
        return restaurantRating;
    }
    public void setRestaurantRating(Restaurant restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    public double getFood() {
        return food;
    }
    public void setFood(double food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getService() {
        return service;
    }
    public void setService(double service) {
        this.service = service;
    }

    public double getAmbiance() {
        return ambiance;
    }
    public void setAmbiance(double ambiance) {
        this.ambiance = ambiance;
    }

    public double getGeneral(){
        return (getAmbiance()+getFood()+getPrice()+getService()) / 4;
    }

    public double getWeightedAverage(Preferences preferences){

        System.out.println(String.valueOf(preferences.getFood()) + " "
                + String.valueOf(preferences.getPrice()) + " "
                + String.valueOf(preferences.getService()) + " "
                + String.valueOf(preferences.getAmbiance()) + " ");
        System.out.print(String.valueOf(getFood()) + " "
                + String.valueOf(getPrice()) + " "
                + String.valueOf(getService()) + " "
                + String.valueOf(getAmbiance()) + " ");
        double food = preferences.getFood() * this.getFood();
        double service = preferences.getService() * this.getService();
        double ambience = preferences.getAmbiance() * this.getAmbiance();
        double price = preferences.getPrice() * this.getPrice();
        double weights = preferences.getAmbiance() + preferences.getFood()
                + preferences.getService() + preferences.getPrice();
        System.out.println((food+service+ambience+price)/weights);
        return (food+service+ambience+price)/weights;
    }
}
