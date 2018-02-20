package mpoo.bsi.ufrpe.helpvegapp.event.domain;

import mpoo.bsi.ufrpe.helpvegapp.user.domain.User;

public class Event {
    private int idEvent;
    private User userEvent;
    private String nameEvent;
    private String descriptionEvent;

    public int getIdEvent() {
        return idEvent;
    }
    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public User getUserEvent() {
        return userEvent;
    }
    public void setUserEvent(User userEvent) {
        this.userEvent = userEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }
    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }
    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }
}
