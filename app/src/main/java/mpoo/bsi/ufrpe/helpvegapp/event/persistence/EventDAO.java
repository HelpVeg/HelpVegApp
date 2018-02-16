package mpoo.bsi.ufrpe.helpvegapp.event.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.event.business.EventBusiness;
import mpoo.bsi.ufrpe.helpvegapp.event.domain.Event;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class EventDAO {

    private Event generateEvent(Cursor cursor){
        Event event = new Event();
        event.setIdEvent(cursor.getInt(0));
        event.setUserEvent(new UserBusiness().getUserById(cursor.getInt(1)));
        event.setNameEvent(cursor.getString(2));
        event.setDescriptionEvent(cursor.getString(3));

        return event;
    }

    public boolean createEvent(Event event){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnEventsUserId(), event.getUserEvent().getUserId());
        values.put(DatabaseHelper.getColumnEventsName(), event.getNameEvent());
        values.put(DatabaseHelper.getColumnEventsDescription(), event.getDescriptionEvent());

        Boolean response = db.insert(DatabaseHelper.getTableEvents(),null, values) != -1;
        db.close();
        return response;
    }

    public void updateEvent(Event event){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        String eventId = Integer.toString(new EventBusiness().getEventFromSession().getIdEvent());
        String where = DatabaseHelper.getColumnEventsId() + " = " + eventId +";";

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnEventsName(), event.getNameEvent());
        values.put(DatabaseHelper.getColumnEventsDescription(), event.getDescriptionEvent());

        db.update(DatabaseHelper.getTableEvents(), values, where, null);
        db.close();
    }

    public void deleteEvent(){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        String eventId = Integer.toString(new EventBusiness().getEventFromSession().getIdEvent());
        String where = DatabaseHelper.getColumnEventsId() + " = " + eventId + ";";

        db.delete(DatabaseHelper.getTableEvents(), where, null);
        db.close();
    }

    public ArrayList<Event> getAllEventsFromUser(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();

        ArrayList<Event> events = new ArrayList<>();
        String userId = Integer.toString(new UserBusiness().getUserFromSession().getUserId());

        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllEventsFromUser(), new String[] {userId});

        while(cursor.moveToNext()){
            Event event = generateEvent(cursor);
            events.add(event);
        }
        cursor.close();
        db.close();
        return events;
    }

    public ArrayList<Event> getAllEvents(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlGetAllEvents(), null);

        while(cursor.moveToNext()){
            Event event = generateEvent(cursor);
            events.add(event);
        }
        cursor.close();
        db.close();
        return events;
    }
}
