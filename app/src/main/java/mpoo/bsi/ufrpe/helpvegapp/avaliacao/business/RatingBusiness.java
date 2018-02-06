package mpoo.bsi.ufrpe.helpvegapp.avaliacao.business;

import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Rating;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence.RatingDAO;

public class RatingBusiness {
    private RatingDAO ratingDAO;

    public RatingDAO getRatingDAO() {
        return ratingDAO;
    }

    public float getRatingFood(){
        return getRatingDAO().getMediaColumnRatingFood();
    }

    public float getRatingPrice(){
        return getRatingDAO().getMediaColumnRatingPrice();
    }

    public float getRatingService(){
        return getRatingDAO().getMediaColumnRatingService();
    }

    public float getRatingAmbiance(){
        return getRatingDAO().getMediaColumnRatingAmbiance();
    }

    public float getRatingGeneral(){
        return (getRatingFood() + getRatingPrice() + getRatingService() + getRatingAmbiance()) / 4;
    }

    public Rating getRatingFromUser(){
        return getRatingDAO().getRatingFromUser();
    }

    public Rating getRatingFromRestaurant(){
        return getRatingDAO().getRatingFromRestaurant();
    }

    /*
    * EXEMPLO DE USO NA GUI
    * RatingBusiness ratingBusiness = new RatingBusiness();
    * Rating rating = new Rating();
    * rating.setFood(ratingBusiness.getRatingFood());
    *
    *
    * GENERAL
    *
    * ratingBar.setRating(getRatingGeneral());
    **/
}
