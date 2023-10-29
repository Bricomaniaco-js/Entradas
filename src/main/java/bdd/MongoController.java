package bdd;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.*;

public class MongoController {
    private MongoDatabase db;
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
            return new Document()
                    .append("id", u.getId())
                    .append("username", u.getUsername())
                    .append("password", u.getPassword())
                    .append("events", u.getEvents());
        }else{
            return new Document()
                    .append("id", u.getId())
                    .append("username", u.getUsername())
                    .append("password", u.getPassword())
                    .append("tickets", u.getTickets());
        }
    }
}
