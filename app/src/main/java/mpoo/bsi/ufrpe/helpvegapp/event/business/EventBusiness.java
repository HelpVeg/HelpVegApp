package mpoo.bsi.ufrpe.helpvegapp.event.business;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.event.persistence.EventDAO;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;

public class EventBusiness {
    private EventDAO eventDAO = new EventDAO();

    public Event getEventFromSession(){
        return Session.getCurrentEvent();
    }

    public void insertEvent(Event event){
        eventDAO.createEvent(event);
    }

    public void updateCurrentEvent(Event event){
        Session.setCurrentEvent(event);
        eventDAO.updateEvent(getEventFromSession());
    }

    public void deleteEvent(Event event){
        eventDAO.deleteEvent(event);
    }

    public ArrayList<Event> getAllEventsFromUser(){
        return new EventDAO().getAllEventsFromUser();
    }

    public ArrayList<Event> getAllEvents(){

        return eventDAO.getAllEvents();
    }

    public void selectEvent(Event event){
        Session.setCurrentEvent(event);
    }
}
