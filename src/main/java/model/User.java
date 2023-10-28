package model;

import org.bson.Document;

import java.util.List;

public class User extends Account implements bdd.MongoInterface{
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User(long id, String username, String password, List<Ticket> tickets) {
        super(id, username, password);
        this.tickets = tickets;
    }

    List<Ticket> tickets;


    @Override
    public Document toDocument() {
        return new Document()
                .append("id", this.id)
                .append("username", this.username)
                .append("password", this.getPassword())
                .append("tickets", this.getTickets());
    }
}
