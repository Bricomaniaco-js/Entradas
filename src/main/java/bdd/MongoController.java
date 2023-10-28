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
    public static Document toDocument(){

    }
}
