package bdd;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.*;

import java.util.ArrayList;

public class MongoController {
    private MongoDatabase db;

    public MongoController(MongoDatabase db) {
        this.db = db;
    }

    public static Document toDocument(Event e) {
        ArrayList<Long> arrayTickets = new ArrayList<Long>();
        e.getTickets().forEach(
                ticket -> arrayTickets.add(ticket.getId())
        );

        return new Document()
                .append("_id", e.getId())
                .append("name", e.getName())
                .append("description", e.getDescription())
                .append("tickets", arrayTickets);

    }

    public static Document toDocument(Ticket t) {
        return new Document()
                .append("_id", t.getId())
                .append("used", t.isUsed())
                .append("event", t.getEvent().getId())
                .append("user", t.getUser().getId());
    }

    public static Document toDocument(User u) {
        ArrayList<Long> arrayTickets = new ArrayList<Long>();
        u.getTickets().forEach(
                ticket -> arrayTickets.add(ticket.getId())
        );

        ArrayList<Long> arrayEvents = new ArrayList<Long>();
        u.getEvents().forEach(
                event -> arrayTickets.add(event.getId())
        );

        return new Document()
                .append("_id", u.getId())
                .append("username", u.getUsername())
                .append("password", u.getPassword())
                .append("tickets", arrayTickets)
                .append("events", arrayEvents);

    }
}
