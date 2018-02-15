package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.business.RatingBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;


public class SlopeOne {



    private UserBusiness userBusiness = new UserBusiness();
    private RatingBusiness ratingBusiness = new RatingBusiness();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private RestaurantBusiness restaurantBusiness = new RestaurantBusiness();

    private Map<Integer, Map<Integer, Double>> variationMatrix = new HashMap<>();
    private Map<Integer, Map<Integer,Integer>> frequencyMatrix = new HashMap<>();
    private Map<Integer, HashMap<Integer, Double>> allRatings = new HashMap<>();
    private Map<Integer, HashMap<Integer, Double>> allPredictions = new HashMap<>();


    private ArrayList<User> allUsers;
    private ArrayList<Restaurant> allRestaurants;

    public SlopeOne(){
        readData();
        createVariationMatrix();
        predict();
    }

    public void readData(){
        allUsers = userBusiness.getAllUsers();
        allRestaurants = restaurantBusiness.getAllRestaurants();

        for (User user: allUsers){
            HashMap<Integer, Double> ratingsUser = new HashMap<>();

            ArrayList<Rating> restaurantsRated = ratingBusiness.getAllRatingsFromUser(user);
            for (Rating rating : restaurantsRated){
                Preferences preference = preferencesBusiness.getPreferencesFromUser(user);

                if (preference == null){
                    preference = new Preferences();
                }
                double weightedAverage = rating.getWeightedAverage(preference);
                ratingsUser.put(rating.getRestaurantRating().getRestaurantId(), weightedAverage);
            }
            allRatings.put(user.getUserId(), ratingsUser);
        }
    }

    public Set<Integer> getIndicationList(int userId){
        HashMap<Integer, Double> userPredictions = allPredictions.get(userId);
        return userPredictions.keySet();
    }

    private void createVariationMatrix() {
        for (HashMap<Integer, Double> user : allRatings.values()) {
            for (Map.Entry<Integer, Double> entry : user.entrySet()) {
                if (!variationMatrix.containsKey(entry.getKey())) {
                    variationMatrix.put(entry.getKey(), new HashMap<Integer, Double>());
                    frequencyMatrix.put(entry.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> entry2 : user.entrySet()) {
                    int oldFrequency = 0;
                    if (frequencyMatrix.get(entry.getKey()).containsKey(entry2.getKey())) {
                        oldFrequency = frequencyMatrix.get(entry.getKey()).get(entry2.getKey()).intValue();
                    }
                    double oldVariation = 0.0;
                    if (variationMatrix.get(entry.getKey()).containsKey(entry2.getKey())) {
                        oldVariation = variationMatrix.get(entry.getKey()).get(entry2.getKey()).doubleValue();
                    }
                    double newVariation = entry.getValue() - entry2.getValue();
                    frequencyMatrix.get(entry.getKey()).put(entry2.getKey(), oldFrequency + 1);
                    variationMatrix.get(entry.getKey()).put(entry2.getKey(), oldVariation + newVariation);
                }
            }
        }
        for (Integer j : variationMatrix.keySet()) {
            for (Integer i : variationMatrix.get(j).keySet()) {
                double oldValue = variationMatrix.get(j).get(i).doubleValue();
                int count = frequencyMatrix.get(j).get(i).intValue();
                variationMatrix.get(j).put(i, oldValue / count);
            }
        }
    }

    private void predict() {
        HashMap<Integer, Double> predictions = new HashMap<>();
        HashMap<Integer, Integer> frequencys = new HashMap<>();
        for (Integer restaurant : variationMatrix.keySet()) {
            frequencys.put(restaurant, 0);
            predictions.put(restaurant, 0.0);
        }
        for (Map.Entry<Integer, HashMap<Integer, Double>> entry : allRatings.entrySet()) {
            for (Integer j : entry.getValue().keySet()) {
                for (Integer k : variationMatrix.keySet()) {
                    try {
                        double predictedValue = variationMatrix.get(k).get(j).doubleValue() + entry.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * frequencyMatrix.get(k).get(j).intValue();
                        predictions.put(k, predictions.get(k) + finalValue);
                        frequencys.put(k, frequencys.get(k) + frequencyMatrix.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }
            HashMap<Integer, Double> cleanPredictions = new HashMap<>();
            for (Integer j : predictions.keySet()) {
                if (frequencys.get(j) > 0) {
                    cleanPredictions.put(j, predictions.get(j).doubleValue() / frequencys.get(j).intValue());
                }
            }
            for (Restaurant j : allRestaurants) {
                cleanPredictions.put(j.getRestaurantId(), entry.getValue().get(j));

            }
            allPredictions.put(entry.getKey(), cleanPredictions);
        }
    }
}
