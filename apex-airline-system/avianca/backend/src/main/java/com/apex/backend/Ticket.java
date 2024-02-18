package com.apex.backend;


public class Ticket {
    String ticket_id;
    Integer price;
    Integer type;
    Integer state;

    public Ticket(String ticket_id, Integer price, Integer type, Integer state) {
        this.ticket_id = ticket_id; 
        this.price = price; 
        this.type = type; 
        this.state = state; 
    }
}
