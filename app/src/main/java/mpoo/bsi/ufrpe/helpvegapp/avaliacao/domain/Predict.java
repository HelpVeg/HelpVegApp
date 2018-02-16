package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;



public class Predict {
    private Restaurant restaurant;
    private Double predict;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Double getPredict() {
        return predict;
    }

    public void setPredict(Double predict) {
        this.predict = predict;
    }
}
