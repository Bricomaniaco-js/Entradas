package com.example.myapplication.db;

import retrofit2.*;
import com.example.myapplication.dtos.*;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Manages API service calls.
 */
public class ApiServiceManager {
    private ApiService apiService;

    /**
     * Constructs a new ApiServiceManager and initializes the Retrofit client.
     */
    public ApiServiceManager() {
        Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:8080");
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * Retrieves a user by username and password.
     *
     * @param username the username
     * @param password the password
     * @param callback the callback to handle the response
     */
    public void getUser(String username, String password, Callback<UserDTO> callback) {
        Call<UserDTO> call = apiService.getUser(username, password);
        call.enqueue(callback);
    }

    /**
     * Retrieves a list of events.
     *
     * @param callback the callback to handle the response
     */
    public void getEvents(Callback<List<EventDTO>> callback) {
        Call<List<EventDTO>> call = apiService.getEvents();
        call.enqueue(callback);
    }

    /**
     * Buys a ticket for a user for a specific event.
     *
     * @param user the user
     * @param event the event
     * @param callback the callback to handle the response
     */
    public void buyTicket(User user, Event event, Callback<UserDTO> callback) {
        Call<UserDTO> call = apiService.buyTicket(new UserEventRequest(new UserDTO(user), new EventDTO(event)));
        call.enqueue(callback);
    }

    /**
     * Validates a ticket by an admin.
     *
     * @param user the user
     * @param qrContent the QR code content
     * @param callback the callback to handle the response
     */
    public void adminValidateTicket(User user, String qrContent, Callback<TicketDTO> callback) {
        UserTicketRequest userTicketRequest = new UserTicketRequest(new UserDTO(user), new TicketDTO(
                new Ticket(
                        new ObjectId(qrContent))));
        Call<TicketDTO> call = apiService.validateTicket(userTicketRequest);
        call.enqueue(callback);
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param eventId the event ID
     * @param callback the callback to handle the response
     */
    public void getEvent(String eventId, Callback<EventDTO> callback) {
        Call<EventDTO> call = apiService.getEvent(eventId);
        call.enqueue(callback);
    }
}