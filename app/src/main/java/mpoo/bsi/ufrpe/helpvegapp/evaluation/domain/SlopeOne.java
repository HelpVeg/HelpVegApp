package mpoo.bsi.ufrpe.helpvegapp.evaluation.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mpoo.bsi.ufrpe.helpvegapp.evaluation.business.RatingBusiness;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

/**
 * Codigo adaptado a partir da fonte abaixo:
 * Daniel Lemire: A simple implementation of the weighted slope one
 * www.programcreek.com/java-api-examples/index.php?source_dir=HappyResearch-master/happyresearch/src/main/java/happy/research/cf/SlopeOne.java
 */

public class SlopeOne {
    private UserBusiness userBusiness = new UserBusiness();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private RatingBusiness ratingBusiness = new RatingBusiness();

    private ArrayList<User> allUsers = userBusiness.getAllUsers();

    private Map<User,Map<Restaurant,Double>> allRatings = new HashMap<>();
    private Map<Restaurant, Double> userRatings = new HashMap<>();

    private Map<Restaurant, Map<Restaurant, Double>> variationMatrix = new HashMap<>();
    private Map<Restaurant, Map<Restaurant, Integer>> frequencyMatrix = new HashMap<>();


    public SlopeOne(){
        getAllRatings();
        createVariationMatrix();
    }

    public ArrayList<Predict> getIndicationList(){
        Map<Restaurant, Double> predictMaps = predict();
        ArrayList<Predict> predictList = new ArrayList<>();
        for(Map.Entry<Restaurant,Double> entry : predictMaps.entrySet()){
            if (!userRatings.containsKey(entry.getKey())){
                Predict predict = new Predict();
                predict.setPredict(entry.getValue());
                predict.setRestaurant(entry.getKey());
                predictList.add(predict);
            }
        }
        Collections.sort(predictList);
        Collections.reverse(predictList);
        return predictList;
    }

    private void getAllRatings(){
        for(User user: allUsers){
            HashMap<Restaurant, Double> ratingsUser = new HashMap<>();
            ArrayList<Rating> restaurantsRated = ratingBusiness.getAllRatingsFromUser(user);
            for (Rating rating : restaurantsRated){
                Preferences preference = preferencesBusiness.getPreferencesFromUser(userBusiness.getUserFromSession());
                if (preference == null){
                    ratingsUser.put(rating.getRestaurantRating(), rating.getGeneral());
                }else{
                    double weightedAverage = rating.getWeightedAverage(preference);
                    ratingsUser.put(rating.getRestaurantRating(), weightedAverage);
                }
            }
            allRatings.put(user, ratingsUser);
        }
        userRatings = allRatings.get(Session.getUserIn());
    }


    private void createVariationMatrix() {
        for (Map<Restaurant, Double> user : allRatings.values()) {
            for (Entry<Restaurant, Double> entry : user.entrySet()) {
                Restaurant restaurant = entry.getKey();
                Double rate = entry.getValue();
                if (!variationMatrix.containsKey(restaurant)) {
                    variationMatrix.put(restaurant, new HashMap<Restaurant, Double>());
                    frequencyMatrix.put(restaurant, new HashMap<Restaurant, Integer>());
                }
                for (Entry<Restaurant, Double> entry2 : user.entrySet()) {
                    Restaurant restaurant2 = entry2.getKey();
                    double rate2 = entry2.getValue();

                    int frequency = 0;
                    if (frequencyMatrix.get(restaurant).containsKey(restaurant2)){
                        frequency = frequencyMatrix.get(restaurant).get(restaurant2);
                    }
                    double variation = 0.0;
                    if (variationMatrix.get(restaurant).containsKey(restaurant2)){
                        variation = variationMatrix.get(restaurant).get(restaurant2);
                    }
                    double newVariation = rate - rate2;

                    frequencyMatrix.get(restaurant).put(restaurant2, frequency + 1);
                    variationMatrix.get(restaurant).put(restaurant2, variation + newVariation);
                }
            }
        }
        for (Restaurant j : variationMatrix.keySet()) {
            for (Restaurant i : variationMatrix.get(j).keySet()) {
                Double oldVariation = variationMatrix.get(j).get(i);
                int frequency = frequencyMatrix.get(j).get(i);
                variationMatrix.get(j).put(i, oldVariation / frequency);
            }
        }
    }

    private Map<Restaurant, Double> predict(){
        HashMap<Restaurant, Double> predictions = new HashMap<>();
        HashMap<Restaurant, Integer> frequencies = new HashMap<>();
        for (Restaurant j : variationMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Restaurant j : userRatings.keySet()) {
            for (Restaurant k : variationMatrix.keySet()) {
                try{
                    Double newval = (variationMatrix.get(k).get(j) + userRatings.get(j)) * frequencyMatrix.get(k).get(j);
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + frequencyMatrix.get(k).get(j));
                } catch (NullPointerException e){
                }
            }
        }
        HashMap<Restaurant, Double> cleanpredictions = new HashMap<>();
        for (Restaurant j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j));
            }
        }
        for (Restaurant j : userRatings.keySet()) {
            cleanpredictions.put(j, userRatings.get(j));
        }
        return cleanpredictions;
    }
}

