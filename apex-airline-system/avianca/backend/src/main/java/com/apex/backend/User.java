package com.apex.backend;

public class User {
    String email;
    String password;
    String firstName;
    String lastName;
    String originCountry;
    String passportNumber;

    public User(String email, String password, String firstName, String lastName, String originCountry, String passportNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.originCountry = originCountry;
        this.passportNumber = passportNumber;
    }
}
