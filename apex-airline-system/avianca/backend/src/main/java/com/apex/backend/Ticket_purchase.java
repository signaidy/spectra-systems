package com.apex.backend;
public class Ticket_purchase {
    int ticket_id;
    int user_id;
    int flight_id; 
    String type;
    String state;

    public Ticket_purchase(int ticket_id, int user_id, int flight_id, String type, String state) {
        this.ticket_id = ticket_id; 
        this.user_id = user_id; 
        this.type = type; 
        this.state = state;
        this.flight_id = flight_id;  
    }
}
