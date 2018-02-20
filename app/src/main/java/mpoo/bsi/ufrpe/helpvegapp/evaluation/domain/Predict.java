package mpoo.bsi.ufrpe.helpvegapp.evaluation.domain;

import android.support.annotation.NonNull;

import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;



public class Predict implements Comparable<Predict>  {
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

    @Override
    public int compareTo(@NonNull Predict p) {
        int result = 0;
        if (p.predict < this.predict) {
            result = 1;
        } else if (p.predict > this.predict) {
            result = -1;
        }
        return result;
    }
}
