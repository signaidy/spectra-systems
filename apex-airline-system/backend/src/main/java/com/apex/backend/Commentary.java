package com.apex.backend;

public class Commentary {
    int parentId;
    int userId;
    String content;
    int flightId;

    public Commentary(
            int parentId,
            int userId,
            String content,
            int flightId) {
        this.parentId = parentId;
        this.userId = userId;
        this.content = content;
        this.flightId = flightId;

    }
}

