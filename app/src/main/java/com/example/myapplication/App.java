package com.example.myapplication;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.myapplication.UserManager.UserManager;
import com.example.myapplication.db.ApiController;
import com.example.myapplication.model.User;
import com.example.myapplication.ui.activities.LoginActivity;

public class App extends Application {
    private static App instance;
    private UserManager userManager;
    private User currentUser;
    private ApiController controller;

    public ApiController getController() {
        return controller;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        controller = new ApiController();
        instance = this;
        currentUser = new User();
        System.out.println(currentUser.toString());
        userManager = new UserManager(currentUser, controller);
        if (currentUser != null) {
            Log.d("MSG", currentUser.toString());
        } else {
            // Handle the case where currentUser is null
            Log.d("MSG", "USER IS NULL");
        }

        launchLoginActivity();


    }
    public void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public static App getInstance() {
        return instance;
    }
    public UserManager getUserManager() {
        return userManager;
    }
}
