package model;

import org.bson.Document;

public class Ticket{
    long id;

    boolean used;

    public Ticket(long id, boolean used, User user, Event event) {
        this.id = id;
        this.used = used;
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    User user;

    Event event;



    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket(long id) {
        this.id = id;
        this.used = false;
    }




}
