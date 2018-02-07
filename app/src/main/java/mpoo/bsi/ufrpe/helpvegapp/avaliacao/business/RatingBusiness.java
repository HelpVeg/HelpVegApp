package mpoo.bsi.ufrpe.helpvegapp.avaliacao.business;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence.RatingDAO;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;

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

    public boolean registerRating(Rating rating){
        rating.setUserRating(Session.getUserIn());
        rating.setRestaurantRating(Session.getCurrentRestaurant());
        return ratingDAO.createRating(rating);
    }
}
