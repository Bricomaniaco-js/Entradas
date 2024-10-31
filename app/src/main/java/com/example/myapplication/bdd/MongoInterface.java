package com.example.myapplication.bdd;

import org.bson.Document;

public interface MongoInterface {
    public Document toDocument();
    public Object toObject(Document d);
}
