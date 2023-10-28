package model;

import org.bson.Document;

import java.util.List;

public class Admin extends Account implements bdd.MongoInterface{
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Admin(long id, String username, String password, List<Event> events) {
        super(id, username, password);
        this.events = events;
    }

    List<Event> events;


    @Override
    public Document toDocument() {
        return new Document()
                .append("id", this.id)
                .append("username", this.username)
                .append("password", this.getPassword())
                .append("tickets", this.getEvents());
    }
}
