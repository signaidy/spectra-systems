package com.apex.backend;

public class Rating {
    int userId;
    int flightId;
    int value;

    public Rating(int userId, int flightId, int value) {
        this.userId = userId;
        this.flightId = flightId;
        this.value = value;
    }
}
