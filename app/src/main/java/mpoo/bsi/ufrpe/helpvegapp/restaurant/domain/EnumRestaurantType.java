package mpoo.bsi.ufrpe.helpvegapp.restaurant.domain;


public enum EnumRestaurantType {
    COMUM("Comum"), VEGANO("Vegano"), VEGETARIANO_E_VEGANO("Vegetariano e vegano");

    private String description;

    EnumRestaurantType(String value){
        this.description = value;
    }

    public String getDescription(){
        return this.description;
    }
}
