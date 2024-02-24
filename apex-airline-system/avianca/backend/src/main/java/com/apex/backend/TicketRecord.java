package com.apex.backend;

public record TicketRecord(int ticketId, int price, int flightId, String type, String state, int userId, String userName) {
    
}
