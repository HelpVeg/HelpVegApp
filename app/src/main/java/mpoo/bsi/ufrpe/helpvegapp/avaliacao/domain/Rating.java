package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
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
}
