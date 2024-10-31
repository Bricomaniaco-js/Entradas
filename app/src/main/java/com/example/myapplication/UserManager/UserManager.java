package com.example.myapplication.UserManager;

import com.example.myapplication.Main;
import com.example.myapplication.bdd.MongoController;
import com.example.myapplication.model.User;

public class UserManager {
    User user;
    MongoController controller;
    public UserManager(User user, MongoController controller){
        this.user =user;
        this.controller = controller;
    }
    public boolean login(String username, String password){
        //TODO esto creo que esta mal, llama a un metodo dentro de main para cambiar el ususario. no es seguro pero funciona
        if(verifyUser(username, password)){
            Main.setCurrentUser(controller.getUser(username, password));
            return true;
        }
        return false;
    }
    private boolean verifyUser(String username, String password){
        if(controller.userExists(username) && controller.getUser(username, password) == null){
            return false;
        }
        return true;
    }

}
