package bdd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoController {
    private static MongoDatabase db;
    public MongoController( MongoDatabase db){
        this.db = db;
    }
    public static Document toDocument(Event e){
        return new Document()
                .append("id", e.getId())
                .append("name", e.getName())
                .append("description", e.getDescription())
                .append("tickets", e.getTickets());

    }
    public static Document toDocument(User u){
        if(u.isAdmin) {
            Document d = new Document()
                    .append("id", u.getId())
                    .append("isAdmin", "true")
                    .append("username", u.getUsername())
                    .append("password", u.getPassword())
                    .append("Events", u.getEvents());
            return d;

        }else{
            return new Document()
                    .append("id", u.getId())
                    .append("isAdmin", "false")
                    .append("username", u.getUsername())
                    .append("password", u.getPassword())
                    .append("tickets", u.getTickets());
        }
    }
    public static Document toDocument(Ticket t){
        return new Document()
                .append("id", t.getId());

    }
    public static ArrayList<Ticket> retrieveUserTickets(String username, String password){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
                .append("password", password)
        ).first();
        ArrayList<Ticket> tickets = new ArrayList<>();
        if(!listIsEmpty(foundUser.getList("tickets", Document.class))) {
            for (Document t : foundUser.getList("tickets", Document.class)) {
                tickets.add(toTicket(t));
            }
        }
        return tickets;
    }
    
    public static Ticket toTicket(Document d){
        return new Ticket(d.getInteger("id"));
    }

    public static ArrayList<Event> retrieveUserEvents(String username, String password){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
                .append("password", password)
        ).first();
        ArrayList<Event> events = new ArrayList<>();
        if(!listIsEmpty(foundUser.getList("events", Document.class))) {
            for(Document e : foundUser.getList("events", Document.class)){
                events.add(toEvent(e));
            }
        }
        return events;
    }

    public static Event toEvent(Document d){
        ArrayList<Ticket> tickets = retrieveEventTickets(d.getInteger("id"));
        return new Event(
                d.getInteger("id"),
                d.getString("name"), 
                d.getString("description"), 
                tickets);
    }

    private static ArrayList<Ticket> retrieveEventTickets(Integer id) {
        MongoCollection<Document> Events = db.getCollection("Events");
        Document foundEvent = null;
        foundEvent = Events.find(new Document()
                .append("id", id)
        ).first();
        ArrayList<Ticket> tickets = new ArrayList<>();
        if(!listIsEmpty(foundEvent.getList("tickets", Document.class))) {
            for(Document t : foundEvent.getList("tickets", Document.class)){
                tickets.add(toTicket(t));
            }
        }
        return tickets;
    }

    public static User getUser(String username, String password){
        if(userExists(username)) {
            MongoCollection<Document> users = db.getCollection("Users");
            Document foundUser = null;
            foundUser = users.find(new Document()
                    .append("username", username)
                    .append("password", password)
            ).first();

            ArrayList<Ticket> tickets = retrieveUserTickets(username, password);
            ArrayList<Event> events = retrieveUserEvents(username, password);


            return new User(
                    foundUser.getLong("id"),
                    foundUser.getString("username"),
                    foundUser.getString("password"),
                    tickets,
                    events,
                    foundUser.getBoolean("isAdmin"));
        }
        return null;
    }
    public static boolean listIsEmpty(List l){
        if(l == null){
            return true;
        }
        return l.isEmpty();
    }
    public static boolean userExists (String username){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
        ).first();
        return foundUser != null;
    }
}
