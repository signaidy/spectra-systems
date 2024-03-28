package com.apex.backend;


public class Ticket {
    Integer ticket_id;
    Integer price;
    Integer flight_id; 
    Integer type;
    Integer state;

    public Ticket(Integer ticket_id, Integer price, Integer flight_id, Integer type, Integer state) {
        this.ticket_id = ticket_id; 
        this.price = price; 
        this.type = type; 
        this.state = state;
        this.flight_id = flight_id;  
    }
}
