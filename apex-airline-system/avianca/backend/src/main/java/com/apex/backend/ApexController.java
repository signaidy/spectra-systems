package com.apex.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import java.util.List;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
public class ApexController {

    public record Greeting(long id, String content) {
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // USER - LOGIN
    @PostMapping("/login")
    public Object signIn(@RequestBody User user) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format("SELECT * FROM users WHERE email = '%s'", user.email));
            ResultSet result = query.executeQuery();

            if (!result.next()) {
                return new WebError("User not found");
            }

            if (BCrypt.checkpw(user.password, result.getString("password"))) {
                return new LoggedUser(
                        result.getString("user_id"),
                        result.getString("email"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("origin_country"),
                        result.getString("passport_number"),
                        result.getString("role"),
                        result.getString("age"));
            }
            return new WebError("Invalid password");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to login");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // USER - SIGN UP
    @PostMapping("/create-user")
    public Object createUser(@RequestBody User user) {

        Connection conn = new OracleConnector().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        String passwordHash = BCrypt.hashpw(user.password, BCrypt.gensalt());

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "INSERT INTO users (email, password, first_name, last_name, origin_country, passport_number, role, age, percentage, entry_date) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', 'user', %s, %s, TO_DATE('%s', 'dd-MM-yyyy'))",
                            user.email, passwordHash, user.firstName, user.lastName, user.originCountry,
                            user.passportNumber, user.age, null, dtf.format(now)));
            query.executeQuery();

            PreparedStatement retrievalQueery = conn
                    .prepareStatement(String.format("SELECT * FROM users WHERE email = '%s'", user.email));
            ResultSet result = retrievalQueery.executeQuery();

            if (!result.next()) {
                return new WebError("Failed to create user");
            }

            return new LoggedUser(
                    result.getString("user_id"),
                    result.getString("email"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("origin_country"),
                    result.getString("passport_number"),
                    result.getString("role"),
                    result.getString("age"));
        } catch (Throwable e) {
            e.printStackTrace();
            if (e.getMessage().contains(
                    "ORA-00001: unique constraint (SYSTEM.EMAIL_UK) violated on table SYSTEM.USERS columns (EMAIL)")) {
                return new WebError("Email already in use");
            }
            return new WebError("Failed to create user");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/get-user/{id}")
    public Object getUser(@PathVariable Long id) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format("SELECT * FROM users WHERE user_id = %d", id));
            ResultSet result = query.executeQuery();

            record User(String name, String email) {
            }

            if (result.next()) {
                return new User(result.getString("first_name"), result.getString("email"));
            }
            return new WebError("User not found");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve user");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // FLIGHT - REGISTRATION
    @PostMapping("/create-flight")
    public Object createFlight(@RequestBody Flight flight) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement flightQuery = conn
                    .prepareStatement(String.format(
                            "INSERT INTO flights (origin, destination, departure_date, arrival_date, amount_normal, amount_premium, price_normal, price_premium, detail, type, state) VALUES ('%s', '%s', TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS'), %s, %s, %s, %s, '%s', %s, 1)",
                            flight.originCity, flight.destinationCity, flight.departureDate, flight.arrivalDate,
                            flight.touristCapacity, flight.businessCapacity,
                            flight.touristPrice, flight.businessPrice, flight.detail, flight.type));
            flightQuery.executeQuery();

            PreparedStatement flightIdQuery = conn.prepareStatement("SELECT MAX(flight_id) as flight_id FROM flights");
            ResultSet flightIdResult = flightIdQuery.executeQuery();

            PreparedStatement ticketIdQuery = conn.prepareStatement("SELECT MAX(ticket_id) as ticket_id FROM tickets");
            ResultSet ticketIdResult = ticketIdQuery.executeQuery();

            if (!flightIdResult.next() || !ticketIdResult.next()) {
                return new WebError("Failed to create flight");
            }

            int flightId = flightIdResult.getInt("flight_id");

            int ticketId = ticketIdResult.getInt("ticket_id");

            String tickets = "";
            int counter = 0;
            for (int i = 0; i < flight.touristQuantity; i++) {
                counter++;
                tickets = tickets + String.format(
                        "INTO tickets (ticket_id, price, flight_id, type, state) VALUES (%s, %s, %s, '%s', '%s') ",
                        ticketId + counter,
                        flight.touristPrice, flightId, "economy", "active");
            }
            for (int i = 0; i < flight.businessQuantity; i++) {
                counter++;
                tickets = tickets + String.format(
                        "INTO tickets (ticket_id, price, flight_id, type, state) VALUES (%s, %s, %s, '%s', '%s') ",
                        ticketId + counter,
                        flight.businessPrice, flightId, "premium", "active");
            }

            PreparedStatement ticketQuery = conn
                    .prepareStatement(String.format("INSERT ALL %s SELECT * FROM DUAL", tickets));
            ticketQuery.executeQuery();

            return new WebSuccess("Flight created successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to create flight");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Get all Cities - API
    @GetMapping("/get-cities")
    public Object getCities() {
        Connection conn = new OracleConnector().getConnection();

        List<City> cities = new ArrayList<City>();

        try {
            PreparedStatement query = conn
                    .prepareStatement("SELECT * FROM cities");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                cities.add(new City(result.getString("city_id"), result.getString("name")));
            }
            return cities;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve cities");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Purchase - API
    @PostMapping("/purchase")
    public Object purchase(@RequestBody Purchase purchase) {
        Connection conn = new OracleConnector().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "INSERT INTO PURCHASE (user_id, ticket_id, purchase_date, paymenth_method) VALUES (%s, %s, TO_DATE('%s', 'dd-MM-yyyy'), '%s')",
                            purchase.user_id, purchase.ticket_id, dtf.format(now), purchase.paymenth_method));
            query.executeQuery();
            return "Purchase made";
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to buy ticket");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Ticket information - API
    @GetMapping("/ticket-information")
    public Object getAllticketinfo() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format("Select * FROM Tickets"));
            ResultSet result = query.executeQuery();

            record Ticket(String ticket_id, String price, String flight_id, String type, String state) {
            }

            if (result.next()) {
                return new Ticket(result.getString("ticket_id"), result.getString("price"),
                        result.getString("flight_id"),
                        result.getString("type"), result.getString("state"));
            }
            return new WebError("Tickets information not accesed");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to get tickets information");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // User tickets - API
    @GetMapping("/user_tickets/{id}")
    public Object getUsertickets(@PathVariable int id) {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT t.ticket_id, t.price, t.type, t.state, t.flight_id, o.name as origin, d.name as destination, f.departure_date, f.arrival_date\n" + //
                            "FROM tickets t \n" + //
                            "JOIN flights f ON t.flight_id = f.flight_id JOIN cities o ON f.origin = o.city_id JOIN cities d ON f.destination = d.city_id WHERE t.user_id = %d",
                            id));
            ResultSet result = query.executeQuery();

            record UserTicket(String ticket_id, String price, String type, String state, String flight_id, String origin, String destination, String departure_date, String arrival_date) {
            }

            List<UserTicket> usertickets = new ArrayList<>();
            while (result.next()) {
                usertickets.add(new UserTicket(
                        result.getString("ticket_id"),
                        result.getString("price"),
                        result.getString("type"),
                        result.getString("state"),
                        result.getString("flight_id"),
                        result.getString("origin"),
                        result.getString("destination"), 
                        result.getString("departure_date"),
                        result.getString("arrival_date")
                        ));
            }
            if (usertickets.isEmpty()) {
                return new WebError("This user doesn't have any tickets");
            }
            return usertickets;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to get user tickets");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
