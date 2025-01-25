package com.example.myapplication.UserManager;

import android.os.Handler;
import android.os.Looper;

import com.example.myapplication.App;
import com.example.myapplication.Main;
import com.example.myapplication.bdd.ApiController;
import com.example.myapplication.bdd.MongoController;
import com.example.myapplication.model.User;
import com.example.myapplication.wrappers.*;

public class UserManager {
    User user;
    ApiController controller;
    UserWrapper userWrapper;
    public UserManager(User user, ApiController controller){
        this.user =user;
        this.controller = controller;
        userWrapper = new UserWrapper();
    }
    public void login(String username, String password, LoginCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        controller.getUser(username, password, new ApiController.UserCallback() {
            @Override
            public void onSuccess(User user) {
                handler.post(() -> {
                    App.getInstance().setCurrentUser(user);
                    System.out.println("User logged in: " + user.toString());
                    callback.onSuccess(user);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                handler.post(() -> {
                    System.out.println("Error: " + t.getMessage());
                    callback.onFailure();
                });
            }
        });
    }

    public interface LoginCallback {
        void onSuccess(User user);
        void onFailure();
    }

}

