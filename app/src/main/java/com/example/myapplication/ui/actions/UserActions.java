package com.example.myapplication.ui.actions;

import android.os.Handler;
import android.os.Looper;

import com.example.myapplication.App;
import com.example.myapplication.db.ApiController;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.User;

public class UserActions {
    public static void buyTicket(Event event, UserCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        User user = App.getInstance().getCurrentUser();
        App.getInstance().getController().buyTicket(user, event, new ApiController.UserCallback() {
            @Override
            public void onSuccess(User user) {
                handler.post(() -> {
                    App.getInstance().setCurrentUser(user);
                    System.out.println("Ticket Bought" + user.toString());
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
    public interface UserCallback {
        void onSuccess(User user);
        void onFailure();
    }

}
