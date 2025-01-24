package com.example.myapplication.dtos;

public record UserEventRequest(
        UserDTO userDTO,
        EventDTO eventDTO

) {


    public UserDTO getUserDTO() {
        return userDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

}