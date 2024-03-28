package com.apex.backend;


public class Flights {
    String origin;
    String destination;
    String departure_date;
    String arrival_date;
    int amount_normal_tickets;
    int amount_premium_tickets;
    int price_normal;
    int price_premium;
    boolean type;
    String detail;
    boolean state;

  public Flights(String origin, String destination, String departure_date, String arrival_date, int amount_normal_tickets, int amount_premium_tickets,
  int price_normal, int price_premium, boolean type, String detail,boolean state) {
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.amount_normal_tickets = amount_normal_tickets;
        this.amount_premium_tickets = amount_premium_tickets;
        this.price_normal = price_normal;
        this.price_premium = price_premium;
        this.price_premium = price_premium;
        this.type = type;
        this.detail = detail;
        this.state = state;
    }

}