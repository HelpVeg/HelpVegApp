package mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.business.RatingBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.PreferencesBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.Preferences;
import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;


public class SlopeOne {

    private RatingBusiness ratingBusiness = new RatingBusiness();
    private UserBusiness userBusiness = new UserBusiness();
    private PreferencesBusiness preferencesBusiness = new PreferencesBusiness();
    private Map<Integer, HashMap<Integer, Double>> data = new HashMap<Integer, HashMap<Integer, Double>>();
    private Map<Integer, Map<Integer, Double>> variationMatrix;
    private Map<Integer, Map<Integer, Integer>> frequencyMatrix;

    private ArrayList<User> allUsers;


    public void readData(){
        allUsers = userBusiness.getAllUsers();
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
            data.put(user.getUserId(), ratingsUser);
        }
    }

     public ArrayList<Integer> indicationList(User user){
         createVariationMatrix();
         Set<Integer> listIdOrdenados = compareSort(predict(data.get(user.getUserId())));
         ArrayList<Integer> restaurantRecomended = new ArrayList<>();
         for (Integer i : listIdOrdenados ) {
             restaurantRecomended.add(i);
         }
         return restaurantRecomended;

    }

    public Map<Integer, Double> predict(Map<Integer, Double> userRating) {
        HashMap<Integer, Double> predictions = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for (Integer j : variationMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Integer j : userRating.keySet()) {
            for (Integer k : variationMatrix.keySet()) {
                try {
                    Double newValue = (variationMatrix.get(k).get(j).doubleValue() + userRating.get(j).doubleValue()) * frequencyMatrix.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + newValue);
                    frequencies.put(k, frequencies.get(k) + frequencyMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                }
            }
        }
        HashMap<Integer, Double> cleanPredictions = new HashMap<Integer, Double>();
        for (Integer j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanPredictions.put(j, predictions.get(j).doubleValue() / frequencies.get(j).intValue());
            }
        }
        for (Integer j : userRating.keySet()) {
            cleanPredictions.put(j, userRating.get(j));
        }
        return cleanPredictions;
    }


    public static Set<Integer> compareSort(Map<Integer, Double> map) {
        Comparator comparator = new Comparator(map);
        Map<Integer, Double> sorted_map = new TreeMap<Integer, Double>(comparator);
        sorted_map.putAll(map);
        return sorted_map.keySet();
    }

    public static class Comparator implements java.util.Comparator {
        private Map m = null;

        public Comparator(Map map) {
            this.m = map;
        }

        public int compare(Object o1, Object o2) {
            Double v1 = (Double) m.get(o1);
            Double v2 = (Double) m.get(o2);
            return v1.compareTo(v2);
        }
    }

    public void createVariationMatrix() {
        variationMatrix = new HashMap<Integer, Map<Integer, Double>>();
        frequencyMatrix = new HashMap<Integer, Map<Integer, Integer>>();

        for (Map<Integer, Double> user : data.values()) {
            for (Map.Entry<Integer, Double> entry : user.entrySet()) {
                if (!variationMatrix.containsKey(entry.getKey())) {
                    variationMatrix.put(entry.getKey(), new HashMap<Integer, Double>());
                    frequencyMatrix.put(entry.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> entry2 : user.entrySet()) {
                    int oldcount = 0;
                    if (frequencyMatrix.get(entry.getKey()).containsKey(entry2.getKey())){
                        oldcount = frequencyMatrix.get(entry.getKey()).get(entry2.getKey()).intValue();
                    double olddiff = 0.0;
                    if (variationMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = variationMatrix.get(entry.getKey()).get(entry2.getKey());
                    double observeddiff = entry.getValue() - entry2.getValue();
                    frequencyMatrix.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
                    variationMatrix.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
                    }
                }
            }
        }

        for (Integer j : variationMatrix.keySet()) {
            for (Integer i : variationMatrix.get(j).keySet()) {
                Double oldvalue = variationMatrix.get(j).get(i).doubleValue();
                int count = variationMatrix.get(j).get(i).intValue();
                variationMatrix.get(j).put(i, oldvalue / count);
            }
        }
    }

}
