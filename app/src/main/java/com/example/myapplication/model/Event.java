package com.example.myapplication.model;

import com.example.myapplication.bdd.MongoInterface;

import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements MongoInterface, Serializable {
    int id;
    String name;
    String description;
    List<Ticket> tickets;
    List<String> images;

    public Event(int id, String name, String description, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
    }

    public static Event testEvent(){
        ArrayList<String> images = new ArrayList<String>();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        images.add("https://derpicdn.net/img/view/2022/7/1/2898577.png");
        images.add("https://derpicdn.net/img/view/2021/4/15/2593604.jpg");
        images.add("https://derpicdn.net/img/view/2022/5/9/2861888.jpg");
        tickets.add(Ticket.testTicket());
        Event e = new Event(123, "Evento 1", "Descripcion 1", tickets);
        e.setImages(images);
        return e;

    }
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public Document toDocument() {
        ArrayList<Document> docTickets = new ArrayList<>();
        for(Ticket t : this.getTickets()){
            docTickets.add(t.toDocument());
        }
        return new Document()
                .append("id", this.id)
                .append("name", this.name)
                .append("description", this.description)
                .append("tickets", docTickets);
    }

    @Override
    public Event toObject(Document d) {

        return null;
    }

}
