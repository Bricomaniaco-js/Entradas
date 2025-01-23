package com.example.myapplication.bdd;


import retrofit2.*;
import com.example.myapplication.dtos.*;

import java.util.List;


public class ApiServiceManager {
    private ApiService apiService;

    public ApiServiceManager() {
        Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:8080");
        apiService = retrofit.create(ApiService.class);
    }

    public void getUser(String username, String password, Callback<UserDTO> callback) {
        Call<UserDTO> call = apiService.getUser(username, password);
        call.enqueue(callback);
    }
    public void getEvents(Callback<List<EventDTO>> callback) {
        Call<List<EventDTO>> call = apiService.getEvents();
        call.enqueue(callback);
    }
}