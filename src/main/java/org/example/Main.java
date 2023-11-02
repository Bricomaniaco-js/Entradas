package org.example;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.*;
import bdd.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        String connectionString = "mongodb://localhost:27017"; // Cambia esto con tu URL de conexión
        String databaseName = "AppEntradas"; // Cambia esto con el nombre de tu base de datos

        MongoDBConnection mongoDBConnection = new MongoDBConnection(connectionString, databaseName);

        // Ahora puedes usar mongoDBConnection para interactuar con la base de datos
        // Por ejemplo:
        MongoDatabase db = mongoDBConnection.getDatabase();
        System.out.println("Conexión a la base de datos establecida: " + db.getName());

        Ticket t = new Ticket(123);

        List<Ticket> tiquets = new ArrayList<>();
        tiquets.add(t);


        Event e = new Event(
                1,
                "coso",
                "coso",
                tiquets
        );

        MongoCollection eventos = db.getCollection("Events");
        eventos.insertOne(MongoController.toDocument(e));


        mongoDBConnection.close();

    }
}