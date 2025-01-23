package com.example.myapplication.bdd;

import android.os.Handler;

import com.example.myapplication.UserManager.UserManager;
import com.example.myapplication.dtos.EventDTO;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.User;
import com.example.myapplication.dtos.UserDTO;
import com.example.myapplication.wrappers.UserWrapper;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.*;

public class ApiController{

    private ApiServiceManager apiServiceManager;

    public ApiController() {
        apiServiceManager = new ApiServiceManager();
    }

    public void getUser(String username, String password, final UserCallback callback) {
        apiServiceManager.getUser(username, password, new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    UserDTO userDTO = response.body();
                    System.out.println("User retrieved: " + response.body());
                    callback.onSuccess(userDTO.toUser());
                } else {
                    callback.onFailure(new Exception("Failed to retrieve user"));
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
    public void getEvents(final EventsCallback callback) {
        apiServiceManager.getEvents(new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                if (response.isSuccessful()) {
                    List<EventDTO> eventDTOs = response.body();
                    List<Event> events = eventDTOs.stream()
                            .map(EventDTO::toEvent)
                            .collect(Collectors.toList());
                    callback.onSuccess(events);
                } else {
                    callback.onFailure(new Exception("Failed to retrieve events"));
                }
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
    public interface UserCallback {
        void onSuccess(User user);
        void onFailure(Throwable t);
    }

    public interface EventsCallback {

        void onSuccess(List<Event> events);

        void onFailure(Throwable t);
    }



}
