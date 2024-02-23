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
                        result.getString("age"),
                        result.getString("percentage"),
                        result.getString("entry_date"));
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
                    result.getString("age"),
                    result.getString("percentage"),
                    result.getString("entry_date"));
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

    // Get All Users - API
    @GetMapping("/get-users")
    public Object getUsers() {
        Connection conn = new OracleConnector().getConnection();

        List<LoggedUser> users = new ArrayList<LoggedUser>();

        try {
            PreparedStatement query = conn
                    .prepareStatement("SELECT * FROM users");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                users.add(new LoggedUser(
                        result.getString("user_id"),
                        result.getString("email"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("origin_country"),
                        result.getString("passport_number"),
                        result.getString("role"),
                        result.getString("age"),
                        result.getString("percentage"),
                        result.getString("entry_date")));
            }
            return users;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve users");
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

    // Update User
    @PostMapping("/update-user")
    public Object updateUser(@RequestBody User user) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE users SET first_name = '%s', last_name = '%s', origin_country = '%s', passport_number = '%s', role = '%s', age = '%s', percentage = %s WHERE user_id = %s",
                            user.firstName, user.lastName, user.originCountry, user.passportNumber, user.role,
                            user.age, user.percentage, user.userId));
            query.executeQuery();

            return new WebSuccess("User updated successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to update user");
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
                            "SELECT t.ticket_id, t.price, t.type, t.state, t.flight_id, o.name as origin, d.name as destination, f.departure_date, f.arrival_date\n"
                                    + //
                                    "FROM tickets t \n" + //
                                    "JOIN flights f ON t.flight_id = f.flight_id JOIN cities o ON f.origin = o.city_id JOIN cities d ON f.destination = d.city_id WHERE t.user_id = %d",
                            id));
            ResultSet result = query.executeQuery();

            record UserTicket(String ticket_id, String price, String type, String state, String flight_id,
                    String origin, String destination, String departure_date, String arrival_date) {
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
                        result.getString("arrival_date")));
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

    // Historical purchases - API
    @GetMapping("/historical_purchases/{id}")
    public Object getHistoricpurchases(@PathVariable int id) {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM Historical_purchases WHERE user_id = %d",
                            id));
            ResultSet result = query.executeQuery();

            record User_purchases(String purchase_number, String ticket, String type, String origin, String destination, String purchase_date, String price, String paymenth_method, 
            String departure_date, String arrival_date, String user_name) {
            }

            List<User_purchases> historicalPurchases = new ArrayList<>();
            while (result.next()) {
                historicalPurchases.add(new User_purchases(
                        result.getString("purchase_number"),
                        result.getString("ticket"),
                        result.getString("type"),
                        result.getString("origin"),
                        result.getString("destination"),
                        result.getString("purchase_date"),
                        result.getString("price"),
                        result.getString("paymenth_method"), 
                        result.getString("departure_date"),
                        result.getString("arrival_date"),
                        result.getString("user_name")
                        ));
            }
            if (historicalPurchases.isEmpty()) {
                return new WebError("This user doesn't have any purchases made");
            }
            return historicalPurchases;
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

    // FLIGHTS - ALL
    @GetMapping("/inventory")
    public Object getInvetory() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT o.name as origin, d.name as destination, f.departure_date, f.arrival_date, f.amount_normal, f.amount_premium, f.price_normal, f.price_premium, f.detail, f.type, f.state, f.flight_id \n" + //
                                                                "FROM flights f \n" + //
                                                                "JOIN cities o ON f.origin = o.city_id JOIN cities d ON f.destination = d.city_id"));
            ResultSet result = query.executeQuery();

            record FLIGHTS(String origin, String destination, String departure_date, String arrival_date, int amount_normal, int amount_premium, int price_normal, int price_premium, 
            String detail, boolean type, boolean state, int flight_id) {
            }

            List<FLIGHTS> flight = new ArrayList<>();
            while (result.next()) {
                flight.add(new FLIGHTS(
                        result.getString("origin"),
                        result.getString("destination"),
                        result.getString("departure_date"),
                        result.getString("arrival_date"),
                        result.getInt("amount_normal"),
                        result.getInt("amount_premium"),
                        result.getInt("price_normal"),
                        result.getInt("price_premium"), 
                        result.getString("detail"),
                        result.getBoolean("type"),
                        result.getBoolean("state"), 
                        result.getInt("flight_id") 
                        ));
            }
            if (flight.isEmpty()) {
                return new WebError("Flights haven't been registered");
            }
            return flight;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to get flights");
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

    // AboutUs - GET INFORMATION
    @GetMapping("/aboutus")
    public Object getAbout() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM About_us"));
            ResultSet result = query.executeQuery();

            record aboutus(String slogan, String gif, String yt, int cards_amoun, String title_one, String text_one, String img_one,
            String title_two, String text_two, String img_two,
            String title_three, String text_three, String img_three,
            String title_four, String text_four, String img_four) {
            }

            if (result.next()) {
                return new aboutus(
                    result.getString("slogan"), 
                    result.getString("gif"),
                    result.getString("yt"),
                    result.getInt("cards_amount"), 
                    result.getString("title_one"),
                    result.getString("text_one"),
                    result.getString("img_one"),
                    result.getString("title_two"),
                    result.getString("text_two"),
                    result.getString("img_two"),
                    result.getString("title_three"),
                    result.getString("text_three"),
                    result.getString("img_three"),
                    result.getString("title_four"),
                    result.getString("text_four"),
                    result.getString("img_four"));
            }
                return new WebError("Failed to get about us information");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API DENIED ACCESS");
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

     // About Us - UPDATE
     @PostMapping("/update-aboutus")
     public Object updateAboutUs(@RequestBody Aboutus au) {
         Connection conn = new OracleConnector().getConnection();
 
         try {
             PreparedStatement query = conn
                     .prepareStatement(String.format(
                             "UPDATE About_us SET slogan = '%s', gif = '%s', yt = '%s', cards_amount = %d, title_one = '%s', text_one = '%s', img_one = '%s', \n" + //
                             "TITLE_TWO = '%s', text_two = '%s', img_two = '%s', \n" + //
                             "title_three = '%s', text_three = '%s', img_three = '%s', \n" + //
                             "title_four = '%s', text_four = '%s', img_four = '%s' \n" + //
                             "WHERE ID = 5",
                             au.slogan, au.gif, au.yt, au.cards_amoun, au.title_one, au.text_one, au.img_one, au.title_two, au.text_two, au.img_two, au.title_three, au.text_three,
                             au.img_three, au.title_four, au.text_four, au.img_four));
             query.executeQuery();
 
             return new WebSuccess("Aboutus information updated");
         } catch (Throwable e) {
             e.printStackTrace();
             return new WebError("Failed to update information");
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
