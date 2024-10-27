package org.example;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import UserManager.UserManager;
import model.*;
import bdd.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static MongoController controller;
    private static User currentUser;

    public static void main(String[] args) {


        String connectionString = "mongodb://localhost:27017"; // Cambia esto con tu URL de conexión
        String databaseName = "AppEntradas"; // Cambia esto con el nombre de tu base de datos

        MongoDBConnection mongoDBConnection = new MongoDBConnection(connectionString, databaseName);

        // Ahora puedes usar mongoDBConnection para interactuar con la base de datos
        // Por ejemplo:
        MongoDatabase db = mongoDBConnection.getDatabase();
        System.out.println("Conexión a la base de datos establecida: " + db.getName());
        controller = new MongoController(db);

        currentUser = null;
        User u = controller.getUser("username", "password");
        System.out.println(u.toString());
        UserManager userManager = new UserManager(currentUser, controller);
/*
        Ticket t = new Ticket(123);

        List<Ticket> tiquets = new ArrayList<>();
        tiquets.add(t);
        MongoController controller = new MongoController(db);


        //User u = new User(123, "a", "b", tiquets);
         User u = new User();
        MongoCollection<Document> tickets = db.getCollection("Tickets");
        MongoCollection<Document> usuarios = db.getCollection("Users");
        usuarios.insertOne(controller.toDocument(u));

*/
        mongoDBConnection.close();

    }


}