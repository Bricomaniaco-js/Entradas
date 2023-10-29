package model;

import org.bson.Document;

import java.util.List;
import java.util.Optional;

public class User{

    long id;
    String username;
    String password;
    List<Ticket> tickets;

    public boolean isAdmin;


    List<Event> events;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public List<Event> getEvents() {
        return events;
    }




    public User(long id, String username, String password, List<Event> events, boolean isAdmin) {
        if(isAdmin){
            this.id = id;
            this.username = username;
            this.password = password;
            this.events = events;
        }else{
            this.id = id;
            this.username = username;
            this.password = password;
            this.tickets = null;
        }


    }

    public User(long id, String username, String password, List<Ticket> tickets) {
        isAdmin = false;
        this.id = id;
        this.username = username;
        this.password = password;
        this.tickets = tickets;

    }

}
