package mpoo.bsi.ufrpe.helpvegapp.event.business;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.event.persistence.EventDAO;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;

public class EventBusiness {

    public Event getEventFromSession(){
        return Session.getCurrentEvent();
    }

    public void insertEvent(Event event){
        new EventDAO().createEvent(event);
    }

    public void updateCurrentEvent(Event event){
        Session.setCurrentEvent(event);
        new EventDAO().updateEvent(getEventFromSession());
    }

    public void deleteCurrentEvent(){
        new EventDAO().deleteEvent();
        Session.setCurrentEvent(null);
    }

    public ArrayList<Event> getAllEventsFromUser(){
        return new EventDAO().getAllEventsFromUser();
    }

    public ArrayList<Event> getAllEvents(){
        return new EventDAO().getAllEvents();
    }

    public void selectEvent(Event event){
        Session.setCurrentEvent(event);
    }
}
