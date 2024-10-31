package com.example.myapplication;
import com.example.myapplication.bdd.MongoController;
import com.example.myapplication.bdd.MongoDBConnection;
import com.example.myapplication.model.User;
import com.mongodb.client.MongoDatabase;

import com.example.myapplication.UserManager.UserManager;
import com.example.myapplication.model.*;
import com.example.myapplication.bdd.*;


public class Main {
    private static MongoController controller;
    private static UserManager UM;
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

        currentUser = new User("NULL", "NULL");

        UM = new UserManager(currentUser, controller);
        UM.login("a","b");
        System.out.println(currentUser.toString());

        /*
        Ticket t = new Ticket(123);


        List<Ticket> tiquets = new ArrayList<>();
        List<Event> eventos = new ArrayList<>();
        tiquets.add(t);

        Event e = new Event(666, "evento1", "esto es el evento1.", tiquets);
        eventos.add(e);

        List<Ticket> tiquets1 = new ArrayList<>();

        User us = new User(999999999, "admin", "admin", tiquets1, eventos, true);
        //User u = new User();
        MongoCollection<Document> usuarios = db.getCollection("Users");
        usuarios.insertOne(us.toDocument());
*/


        mongoDBConnection.close();

    }
    public static void setCurrentUser(User u){
        currentUser = u;
    }


}