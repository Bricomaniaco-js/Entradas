package com.example.myapplication.dtos;

public record UserTicketRequest(
        UserDTO userDTO,
        TicketDTO ticketDTO
) {

        public UserDTO getUserDTO() {
            return userDTO;
        }

        public TicketDTO getTicketDTO() {
            return ticketDTO;
        }
}
