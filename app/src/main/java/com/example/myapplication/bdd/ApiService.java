package com.example.myapplication.bdd;

import com.example.myapplication.dtos.EventDTO;
import com.example.myapplication.dtos.UserDTO;
import com.example.myapplication.dtos.UserEventRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/api/users/user/findByLogin")
    Call<UserDTO> getUser(@Query("username") String username, @Query("password") String password);
    @GET("/api/events/getEvents")
    Call<List<EventDTO>> getEvents();
    @POST("/api/users/user/buyTicket")
    Call<UserDTO> buyTicket(@Body UserEventRequest userEventRequest);
}