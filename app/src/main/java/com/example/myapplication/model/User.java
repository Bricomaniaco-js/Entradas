package com.example.myapplication.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.bdd.MongoInterface;

public class User implements MongoInterface {


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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tickets=" + tickets +
                ", isAdmin=" + isAdmin +
                ", events=" + events +
                '}';
    }

    public User() {
            this.id = Math.round(Math.random());
            this.username = "username";
            this.password = "password";
            this.events = new ArrayList<Event>();
            this.tickets = new ArrayList<Ticket>();
            this. isAdmin = false;


    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(long id, String username, String password, List<Ticket> tickets, List<Event> events, boolean isAdmin){
        this.id = id;
        this.username = username;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
        this.isAdmin = isAdmin;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Document toDocument() {
        ArrayList <Document> docEvents = new ArrayList<>();
        for(Event e : this.getEvents()){
            docEvents.add(e.toDocument());
        }
        ArrayList <Document> docTickets = new ArrayList<>();
        for(Ticket t : this.getTickets()){
            docTickets.add(t.toDocument());
        }

        Document d = new Document()
                .append("id", this.getId())
                .append("isAdmin", this.isAdmin)
                .append("username", this.getUsername())
                .append("password", this.getPassword())
                .append("Events", docEvents)
                .append("Tickets", docTickets);
        return d;

    }

    @Override
    public Object toObject(Document d) {
        User u = new User(
                d.getLong("id"),
                d.getString("username"),
                d.getString("password"),
                tickets,
                events,
                d.getBoolean("isAdmin")
        );
        return u;
    }
}
