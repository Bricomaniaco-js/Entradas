package model;

import org.bson.Document;

public class Ticket implements bdd.MongoInterface{
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket(long id) {
        this.id = id;
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("id", this.id);
    }

    @Override
    public Ticket toObject(Document d) {
        return null;
    }


}
