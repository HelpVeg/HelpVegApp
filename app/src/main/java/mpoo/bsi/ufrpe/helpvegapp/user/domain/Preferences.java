package mpoo.bsi.ufrpe.helpvegapp.user.domain;

public class Preferences {
    private int id;
    private User user;
    private String type;
    private float food;
    private float price;
    private float service;
    private float ambiance;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getFood() {
        return food;
    }
    public void setFood(float food) {
        this.food = food;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public float getService() {
        return service;
    }
    public void setService(float service) {
        this.service = service;
    }

    public float getAmbiance() {
        return ambiance;
    }
    public void setAmbiance(float ambiance) {
        this.ambiance = ambiance;
    }
}
