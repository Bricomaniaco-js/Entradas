package com.example.myapplication.db;

import com.example.myapplication.dtos.EventDTO;
import com.example.myapplication.dtos.TicketDTO;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;
import com.example.myapplication.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.*;

/**
 * Controller class to handle API interactions.
 */
public class ApiController {

    private ApiServiceManager apiServiceManager;

    /**
     * Constructs a new ApiController and initializes the ApiServiceManager.
     */
    public ApiController() {
        apiServiceManager = new ApiServiceManager();
    }

    /**
     * Retrieves a user by username and password.
     *
     * @param username the username
     * @param password the password
     * @param callback the callback to handle the response
     */
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

    /**
     * Retrieves a list of events.
     *
     * @param callback the callback to handle the response
     */
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

    /**
     * Buys a ticket for a user for a specific event.
     *
     * @param user the user
     * @param event the event
     * @param callback the callback to handle the response
     */
    public void buyTicket(User user, Event event, final UserCallback callback) {
        apiServiceManager.buyTicket(user, event, new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    UserDTO userDTO = response.body();
                    if (userDTO == null) {
                        callback.onFailure(new Exception("Failed to buy ticket"));
                        return;
                    } else {
                        System.out.println("User with new ticket: " + response.body());
                        callback.onSuccess(userDTO.toUser());
                    }
                } else {
                    callback.onFailure(new Exception("Failed to buy ticket"));
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * Validates a ticket by an admin.
     *
     * @param user the user
     * @param qrContent the QR code content
     * @param ticketCallback the callback to handle the response
     */
    public void validateTicket(User user, String qrContent, ApiController.TicketCallback ticketCallback) {
        apiServiceManager.adminValidateTicket(user, qrContent, new Callback<TicketDTO>() {
            @Override
            public void onResponse(Call<TicketDTO> call, Response<TicketDTO> response) {
                if (response.isSuccessful()) {
                    Ticket ticket = response.body().toTicket();
                    ticketCallback.onSuccess(ticket);
                } else {
                    ticketCallback.onFailure(new Exception("Failed to validate ticket"));
                }
            }

            @Override
            public void onFailure(Call<TicketDTO> call, Throwable t) {
                ticketCallback.onFailure(t);
            }
        });
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param eventId the event ID
     * @param callback the callback to handle the response
     */
    public void getEvent(String eventId, final EventCallback callback) {
        apiServiceManager.getEvent(eventId, new Callback<EventDTO>() {
            @Override
            public void onResponse(Call<EventDTO> call, Response<EventDTO> response) {
                if (response.isSuccessful()) {
                    EventDTO eventDTO = response.body();
                    Event event = eventDTO.toEvent();
                    callback.onSuccess(event);
                } else {
                    callback.onFailure(new Exception("Failed to retrieve event"));
                }
            }

            @Override
            public void onFailure(Call<EventDTO> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * Callback interface for event responses.
     */
    public interface EventCallback {
        void onSuccess(Event event);
        void onFailure(Throwable t);
    }

    /**
     * Callback interface for ticket responses.
     */
    public interface TicketCallback {
        void onSuccess(Ticket ticket);
        void onFailure(Throwable t);
    }

    /**
     * Callback interface for user responses.
     */
    public interface UserCallback {
        void onSuccess(User user);
        void onFailure(Throwable t);
    }

    /**
     * Callback interface for events list responses.
     */
    public interface EventsCallback {
        void onSuccess(List<Event> events);
        void onFailure(Throwable t);
    }
}