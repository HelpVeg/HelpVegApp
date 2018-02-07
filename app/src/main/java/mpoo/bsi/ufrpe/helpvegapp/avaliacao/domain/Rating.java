package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class Rating {
    private int ratingId;
    private User userRating;
    private Restaurant restaurantRating;
    private float food;
    private float price;
    private float service;
    private float ambiance;

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

    public float getFood() {
        return food;
    }
    public void setFood(float food) {
        this.food = food;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public float getService() {
        return service;
    }
    public void setService(float service) {
        this.service = service;
    }

    public float getAmbiance() {
        return ambiance;
    }
    public void setAmbiance(float ambiance) {
        this.ambiance = ambiance;
    }

    public float getGeneral(){
        return (getAmbiance()+getFood()+getPrice()+getService()) / 4;
    }
}
