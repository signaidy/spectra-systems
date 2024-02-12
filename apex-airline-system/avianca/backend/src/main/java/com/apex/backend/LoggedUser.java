package com.apex.backend;

public record LoggedUser(String userId, String email, String firstName, String lastName, String originCountry,
        String passportNumber, String role, String age) {
}
