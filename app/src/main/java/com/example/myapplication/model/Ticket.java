package com.example.myapplication.model;

import com.example.myapplication.bdd.MongoInterface;

import org.bson.Document;

import java.io.Serializable;

public class Ticket implements MongoInterface , Serializable {
    long id;

    public static Ticket testTicket() {
        return new Ticket(42069);
    }

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
