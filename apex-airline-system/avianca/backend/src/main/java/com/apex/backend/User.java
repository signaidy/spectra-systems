package com.apex.backend;

public class User {
    String email;
    String password;
    String first_name;
    String last_name;
    String origin_country;
    String passport_number;

    public User(String user, String password, String first_name, String last_name, String origin_country, String passport_number) {
        this.email = user;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.origin_country = origin_country;
        this.passport_number = passport_number;
    }
}
