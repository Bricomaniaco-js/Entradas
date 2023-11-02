package model;

import org.bson.Document;

import java.util.List;

public class User{
    long id;
    String username;
    String Password;

    boolean isAdmin;
    List<Ticket> tickets;

    List<Event> events;

    public User(long id, String username, String password, boolean isAdmin, List<Ticket> tickets, List<Event> events) {
        this.id = id;
        this.username = username;
        Password = password;
        this.isAdmin = isAdmin;
        this.tickets = tickets;
        this.events = events;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

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
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }





}
