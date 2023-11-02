package bdd;

import com.mongodb.DBObject;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.*;

import java.util.ArrayList;

public class MongoController {
    private MongoDatabase db;
    public MongoController( MongoDatabase db){
        this.db = db;
    }
    public static Document toDocument(Event e){

        ArrayList<Long> arrayTickets= new ArrayList<Long>();
        for (Ticket ticket : e.getTickets()) {
            arrayTickets.add(ticket.getId());
        }

        return new Document()
                .append("_id", e.getId())
                .append("name", e.getName())
                .append("description", e.getDescription())
                .append("tickets",arrayTickets);

    }
    public static Document toDocument(Ticket t){
        return new Document()
                .append("_id", t.getId())
                .append("used", t.isUsed())
                .append("event", t.getEvent().getId())
                .append("user", t.getUser().getId());
    }
    public static Document toDocument(User u){
        return new Document();
    }
}
