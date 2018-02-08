package mpoo.bsi.ufrpe.helpvegapp.avaliacao.business;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence.RatingDAO;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Restaurant;

public class RatingBusiness {
    private RatingDAO ratingDAO = new RatingDAO();

    public RatingDAO getRatingDAO() {
        return ratingDAO;
    }

    public float getRatingFood(int restaurantId){
        return getRatingDAO().getMediaColumnRatingFood(restaurantId);
    }

    public float getRatingPrice(int restaurantId){
        return getRatingDAO().getMediaColumnRatingPrice(restaurantId);
    }

    public float getRatingService(int restaurantId){
        return getRatingDAO().getMediaColumnRatingService(restaurantId);
    }

    public float getRatingAmbiance(int restaurantId){
        return getRatingDAO().getMediaColumnRatingAmbiance(restaurantId);
    }

    public float getRatingGeneral(int restaurantId){
        float food = getRatingFood(restaurantId);
        float price = getRatingPrice(restaurantId);
        float service = getRatingService(restaurantId);
        float ambiance = getRatingAmbiance(restaurantId);
        return (food + price + service + ambiance) / 4;
    }

    public Rating getRatingFromUser(){
        return getRatingDAO().getRatingFromUser();
    }

    public Rating getRatingFromRestaurant(int restaurantId){
        Rating rating = new Rating();
        rating.setAmbiance(getRatingAmbiance(restaurantId));
        rating.setFood(getRatingFood(restaurantId));
        rating.setService(getRatingService(restaurantId));
        rating.setPrice(getRatingPrice(restaurantId));
        return rating;
    }

    public Rating getRating(int userId, int restaurantId){
        return ratingDAO.getRatingFromRestaurantAndUser(restaurantId,userId);
    }

    public void print(){
        ArrayList<Rating> ratings = ratingDAO.getAllRating();
        for (int i = 0; i < ratings.size(); i++) {
            Rating res = ratings.get(i);
            System.out.println("#" + i + " ID: " + res.getRatingId() +
                    " User: " + res.getUserRating().getUserEmail() +
                    ", Restaurant: " + res.getRestaurantRating().getRestaurantName() +
                    " food: " + String.valueOf(res.getFood()) +
                    " serv: " + String.valueOf(res.getService()) +
                    " prec: " + String.valueOf(res.getPrice()) +
                    " ambi: " + String.valueOf(res.getAmbiance()));
        }
        if (ratings.size() == 0) System.out.println("# Não existem registros.");
    }

    public void registerRating(Rating rating){
        rating.setUserRating(Session.getUserIn());
        rating.setRestaurantRating(Session.getCurrentRestaurant());

        Rating verify = getRating(rating.getUserRating().getUserId(),rating.getRestaurantRating().getRestaurantId());

        if (verify!=null){
            rating.setRatingId(verify.getRatingId());
            ratingDAO.updateRating(rating);
            print();
            return;
        }
        ratingDAO.createRating(rating);
        print();
    }
}
