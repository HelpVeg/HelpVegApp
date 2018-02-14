package mpoo.bsi.ufrpe.helpvegapp.user.domain;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.EnumRestaurantType;

public class Preferences {
    private int id;
    private User user;
    private EnumRestaurantType type;
    private double food;
    private double price;
    private double service;
    private double ambiance;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public EnumRestaurantType getType() {
        return type;
    }
    public void setType(EnumRestaurantType  type) {
        this.type = type;
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
}
