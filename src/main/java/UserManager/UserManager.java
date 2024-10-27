package UserManager;

import org.example.Main;

import bdd.MongoController;
import model.User;

public class UserManager {
    User user;
    MongoController controller;
    UserManager(User user, MongoController controller){
        this.user =user;
        this.controller = controller;
    }

}
