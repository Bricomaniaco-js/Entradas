package com.example.myapplication.bdd;


import retrofit2.*;
import com.example.myapplication.dtos.*;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;

import org.bson.types.ObjectId;

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

    public void buyTicket(User user, Event event, Callback<UserDTO> callback) {
        Call<UserDTO> call = apiService.buyTicket(new UserEventRequest(new UserDTO(user), new EventDTO(event)));
        call.enqueue(callback);
    }

    public void adminValidateTicket(User user, String qrContent, Callback<TicketDTO> callback) {
        UserTicketRequest userTicketRequest = new UserTicketRequest(new UserDTO(user), new TicketDTO(
                                                                                            new Ticket(
                                                                                                    new ObjectId(qrContent))));
        Call<TicketDTO> call = apiService.validateTicket(userTicketRequest);
        call.enqueue(callback);
    }

    public void getEvent(String eventId, Callback<EventDTO> callback) {
        Call<EventDTO> call = apiService.getEvent(eventId);
        call.enqueue(callback);
    }
}