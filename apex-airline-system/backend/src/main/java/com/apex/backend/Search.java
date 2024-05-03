package com.apex.backend;

public class Search {
    int origin;
    int destination;
    String departure_date;
    String return_date;
    int passengers;
    String flight_type;
    String email;
    String type_user;
    String type_search;
    String date_made;

    public Search(int origin,
    int destination,
    String departure_date,
    String return_date,
    int passengers,
    String flight_type,
    String email,
    String type_user,
    String type_search,
    String date_made) {
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.passengers = passengers;
        this.flight_type = flight_type;
        this.email = email;
        this.type_user = type_user;
        this.type_search = type_search;
        this.date_made = date_made;
    }

}