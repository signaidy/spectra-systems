package com.apex.backend;

public class User {
    String email;
    String password;
    String firstName;
    String lastName;
    String originCountry;
    String passportNumber;
    String age;
    String percentage;
    String role;
    String userId;

    public User(String email, String password, String firstName, String lastName, String originCountry,
            String passportNumber, String age, String percentage, String role, String userId) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.originCountry = originCountry;
        this.passportNumber = passportNumber;
        this.age = age;
        this.percentage = percentage;
        this.role = role;
        this.userId = userId;
    }
}
