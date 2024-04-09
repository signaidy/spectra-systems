package com.apex.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import oracle.security.o3logon.a;

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
    private final String NEXUS_API = "http://localhost:42069/nexus/flights/";

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
    @PostMapping("/create-user/{token}")
    public Object createUser(@RequestBody User user, @PathVariable String token) {
        String secretkey = "6LfqapMpAAAAABzyK_kit2nrY39Hg1_VTg92SBXR";

        Connection conn = new OracleConnector().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        String passwordHash = BCrypt.hashpw(user.password, BCrypt.gensalt());

        try {
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
            requestMap.add("secret", secretkey);
            requestMap.add("response", token);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://www.google.com/recaptcha/api/siteverify", requestMap, Map.class);

            Map<String, Object> responseBody = response.getBody();

            if ((Boolean) responseBody.get("success")) {
                PreparedStatement query = conn
                        .prepareStatement(String.format(
                                "INSERT INTO users (email, password, first_name, last_name, origin_country, passport_number, role, age, percentage, entry_date) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', 'user', %s, %s, TO_DATE('%s', 'dd-MM-yyyy'))",
                                user.email, passwordHash, user.firstName, user.lastName, user.originCountry,
                                user.passportNumber, user.age, 0, dtf.format(now)));
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
            } else {
                return new WebError("Failed to verify reCAPTCHA");
            }

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

    // Get One Way Flights - API
    @GetMapping("/get-one-way-flights")
    public Object getOneWayFlights(
            @RequestParam(value = "originCity", defaultValue = "") String originCity,
            @RequestParam(value = "destinationCity", defaultValue = "") String destinationCity,
            @RequestParam(value = "departureDay", defaultValue = "") String departureDay,
            @RequestParam(value = "passengers", defaultValue = "") String passengers) {
        Connection conn = new OracleConnector().getConnection();

        List<FlightRecord> flights = new ArrayList<FlightRecord>();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT DISTINCT flights.flight_id, origin.city_id as origin_city_id, origin.name as origin_name, destination.city_id as destination_city_id, destination.name as destination_name, flights.departure_date, flights.arrival_date, flights.price_normal, flights.price_premium, flights.detail, flights.type, flights.state from flights inner join tickets on flights.flight_id = tickets.flight_id inner join cities origin on flights.origin = origin.city_id inner join cities destination on flights.destination = destination.city_id where tickets.user_id is null and flights.origin = %s and flights.destination = %s and TRUNC(flights.departure_date) = TO_DATE('%s', 'YYYY-MM-DD') and tickets.state != 'canceled'",
                            originCity, destinationCity, departureDay));
            ResultSet result = query.executeQuery();

            while (result.next()) {
                List<CommentaryRecord> commentaries = new ArrayList<CommentaryRecord>();

                PreparedStatement countQuery = conn.prepareStatement(String
                        .format("SELECT (select count(ticket_id) from tickets where flight_id = %s and type = 'economy' and state = 'active' and user_id is null) as economy_quantity, (select count(ticket_id) from tickets where flight_id = %s and type = 'premium' and state = 'active' and user_id is null) as premium_quantity",
                                result.getInt("flight_id"), result.getInt("flight_id")));
                ResultSet countResult = countQuery.executeQuery();

                PreparedStatement commentariesQuery = conn.prepareStatement(String.format(
                        "SELECT comments.comment_id, comments.parent_comment_id, comments.user_id, comments.content, comments.creation_date, comments.path, comments.flight_id, users.first_name from comments inner join users on comments.user_id = users.user_id where comments.flight_id = %s",
                        result.getInt("flight_id")));
                ResultSet commentariesResult = commentariesQuery.executeQuery();

                PreparedStatement ratingsQuery = conn.prepareStatement(String.format(
                        "SELECT ratingAverage(%s) as rating, COUNT(ratings.rating_id) as count from ratings where flight_id = %s",
                        result.getInt("flight_id"), result.getInt("flight_id")));
                ResultSet ratingsResult = ratingsQuery.executeQuery();

                if (!countResult.next() || !ratingsResult.next()) {
                    return new WebError("Failed to retrieve flights");
                }

                while (commentariesResult.next()) {
                    commentaries.add(new CommentaryRecord(commentariesResult.getInt("comment_id"),
                            commentariesResult.getInt("parent_comment_id"),
                            commentariesResult.getInt("user_id"), commentariesResult.getString("content"),
                            commentariesResult.getString("creation_date"),
                            commentariesResult.getString("path"), commentariesResult.getInt("flight_id"),
                            commentariesResult.getString("first_name"), new ArrayList<CommentaryRecord>()));
                }

                flights.add(new FlightRecord(result.getInt("flight_id"), result.getInt("origin_city_id"),
                        result.getString("origin_name"), result.getInt("destination_city_id"),
                        result.getString("destination_name"), result.getString("departure_date").toString(),
                        result.getString("arrival_date"), result.getInt("price_normal"), result.getInt("price_premium"),
                        result.getString("detail"),
                        countResult.getInt("economy_quantity"), countResult.getInt("premium_quantity"), commentaries,
                        new RatingRecord(
                                ratingsResult.getInt("rating"), ratingsResult.getInt("count"))));
            }if (flights.isEmpty()) {
                return new Object[0];
            }

            return flights;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve flights");
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

    // Comments - Insert - API
    @PostMapping("/create-commentary")
    public Object createCommentary(@RequestBody Commentary commentary) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "INSERT INTO comments (parent_comment_id, user_id, content, creation_date, path, flight_id) VALUES (%s, %s, '%s', CURRENT_DATE, '/', %s)",
                            commentary.parentId, commentary.userId, commentary.content, commentary.flightId));
            query.executeQuery();

            return new WebSuccess("Commentary created successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to create commentary");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new WebError("Failed to create commentary");
            }
        }
    }

    // Ratings - Insert - API
    @PostMapping("/create-rating")
    public Object createRating(@RequestBody Rating rating) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "INSERT INTO ratings (user_id, flight_id, value) VALUES (%s, %s, %s)",
                            rating.userId, rating.flightId, rating.value));
            query.executeQuery();

            return new WebSuccess("Rating created successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            if (e.getMessage().contains(
                    "ORA-20001: ")) {
                return new WebError("You already have left a rating.");
            }
            return new WebError("Failed to create rating");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new WebError("Failed to create rating");
            }
        }
    }

    // Get all Tickets - API
    @GetMapping("/get-all-tickets")
    public Object getAllTickets() {
        Connection conn = new OracleConnector().getConnection();

        List<TicketRecord> tickets = new ArrayList<TicketRecord>();

        try {
            PreparedStatement query = conn
                    .prepareStatement(
                            "SELECT tickets.ticket_id, tickets.user_id, tickets.flight_id, tickets.type, tickets.state, tickets.price, users.first_name, users.email from tickets left join users on tickets.user_id = users.user_id");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                tickets.add(
                        new TicketRecord(result.getInt("ticket_id"), result.getInt("price"), result.getInt("flight_id"),
                                result.getString("type"), result.getString("state"), result.getInt("user_id"),
                                result.getString("first_name"), result.getString("email")));
            }

            return tickets;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve tickets");
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

    // Get all Flights - API
    @GetMapping("/get-all-flights")
    public Object getFlights() {
        Connection conn = new OracleConnector().getConnection();

        List<CompleteFlightRecord> flights = new ArrayList<CompleteFlightRecord>();

        try {
            PreparedStatement query = conn
                    .prepareStatement(
                            "SELECT DISTINCT flights.flight_id, origin.city_id as origin_city_id, origin.name as origin_name, destination.city_id as destination_city_id, destination.name as destination_name, flights.departure_date, flights.arrival_date, flights.state, flights.amount_normal, flights.amount_premium, flights.price_normal, flights.price_premium, flights.detail, flights.type, flights.state from flights inner join tickets on flights.flight_id = tickets.flight_id inner join cities origin on flights.origin = origin.city_id inner join cities destination on flights.destination = destination.city_id");
            ResultSet result = query.executeQuery();

            while (result.next()) {
                PreparedStatement countQuery = conn.prepareStatement(String
                        .format("SELECT (select count(ticket_id) from tickets where flight_id = %s) as economy_quantity, (select count(ticket_id) from tickets where flight_id = %s) as premium_quantity",
                                result.getInt("flight_id"), result.getInt("flight_id")));
                ResultSet countResult = countQuery.executeQuery();

                if (!countResult.next()) {
                    return new WebError("Failed to retrieve flights");
                }

                flights.add(new CompleteFlightRecord(result.getInt("flight_id"), result.getInt("origin_city_id"),
                        result.getString("origin_name"), result.getInt("destination_city_id"),
                        result.getString("destination_name"), result.getString("departure_date").toString(),
                        result.getString("arrival_date"), result.getInt("price_normal"), result.getInt("price_premium"),
                        result.getString("detail"),
                        countResult.getInt("economy_quantity"), countResult.getInt("premium_quantity"),
                        result.getInt("amount_normal"), result.getInt("amount_premium"),
                        result.getInt("state")));
            }

            return flights;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve flights");
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

            record User_purchases(String purchase_number, String ticket, String type, String origin, String destination,
                    String purchase_date, String price, String paymenth_method,
                    String departure_date, String arrival_date, String user_name, int discount, int user_id) {
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
                        result.getString("user_name"),
                        result.getInt("discount"),
                        result.getInt("user_id")));
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
                            "SELECT o.name as origin, d.name as destination, f.departure_date, f.arrival_date, f.amount_normal, f.amount_premium, f.price_normal, f.price_premium, f.detail, f.type, f.state, f.flight_id \n"
                                    + //
                                    "FROM flights f \n" + //
                                    "JOIN cities o ON f.origin = o.city_id JOIN cities d ON f.destination = d.city_id"));
            ResultSet result = query.executeQuery();

            record FLIGHTS(String origin, String destination, String departure_date, String arrival_date,
                    int amount_normal, int amount_premium, int price_normal, int price_premium,
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
                        result.getInt("flight_id")));
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

            record aboutus(String slogan, String gif, String yt, int cards_amoun, String title_one, String text_one,
                    String img_one,
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
                            "UPDATE About_us SET slogan = '%s', gif = '%s', yt = '%s', cards_amount = %d, title_one = '%s', text_one = '%s', img_one = '%s', \n"
                                    + //
                                    "TITLE_TWO = '%s', text_two = '%s', img_two = '%s', \n" + //
                                    "title_three = '%s', text_three = '%s', img_three = '%s', \n" + //
                                    "title_four = '%s', text_four = '%s', img_four = '%s'",
                            au.slogan, au.gif, au.yt, au.cards_amoun, au.title_one, au.text_one, au.img_one,
                            au.title_two, au.text_two, au.img_two, au.title_three, au.text_three,
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

    // Purchase - API
    @PostMapping("/purchase/{amount}/{method}/{discount}")
    public Object purchase(@RequestBody Ticket_purchase ticket, @PathVariable int amount, @PathVariable String method,
            @PathVariable int discount) {
        Connection conn = new OracleConnector().getConnection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE TICKETS\n" + //
                                    "SET user_id = '%d'\n" + //
                                    "WHERE flight_id = '%d'\n" + //
                                    "  AND state = '%s'\n" + //
                                    "  AND type = '%s'\n" + //
                                    "  AND ROWNUM <= '%d' AND user_id IS NULL",
                            ticket.user_id, ticket.flight_id, ticket.state, ticket.type, amount));
            query.executeQuery();

            PreparedStatement tickets_assigned = conn.prepareStatement(String.format(
                    "Select DISTINCT(t.ticket_id), t.user_id\n" + //
                            "FROM tickets t\n" + //
                            "JOIN purchase p ON t.ticket_id != p.ticket_id WHERE t.flight_id = '%d' and t.type = '%s' and t.user_id = '%d'",
                    ticket.flight_id, ticket.type, ticket.user_id));
            ResultSet usertickets = tickets_assigned.executeQuery();

            record userTickets(int ticket_id, int user_id) {
            }

            List<userTickets> usert = new ArrayList<>();
            while (usertickets.next()) {
                usert.add(new userTickets(
                        usertickets.getInt("ticket_id"),
                        usertickets.getInt("user_id")));

                int ticketId = usertickets.getInt("ticket_id");
                int userId = usertickets.getInt("user_id");

                PreparedStatement purchased_ticket = conn.prepareStatement(String.format(
                        "INSERT INTO purchase (ticket_id, user_id, purchase_date, paymenth_method, discount)\n" + //
                                "SELECT %d, %d, TO_DATE('%s', 'dd-MM-yyyy'), '%s', %d \n" + //
                                "WHERE NOT EXISTS (\n" + //
                                "  SELECT 1 FROM purchase\n" + //
                                "  WHERE ticket_id = %d AND user_id = %d)",
                        ticketId, userId, dtf.format(now), method, discount, ticketId, userId));
                purchased_ticket.executeQuery();

            }
            if (usert.isEmpty()) {
                return new WebError("This user doesn't have any tickets");
            }
            return usert;

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

    // Tickets information - Purchase
    @GetMapping("/availabletickets/{flight_id}/{category}")
    public Object getTicketstobuy(@PathVariable int flight_id, @PathVariable String category) {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT t.ticket_id, t.price, o.name As origin, d.name as destination\n" + //
                                    "FROM tickets t\n" + //
                                    "JOIN Flights f ON f.flight_id = t.flight_id JOIN cities o ON f.origin = o.city_id JOIN cities d ON f.destination = d.city_id\n"
                                    + //
                                    "WHERE t.type = '%s' and t.flight_id = '%d' and t.state = 'active' and t.user_id IS null ",
                            category, flight_id));
            ResultSet result = query.executeQuery();

            record Availabletickets(String origin, String destination, int price, int ticket_id) {
            }

            List<Availabletickets> availabletickets = new ArrayList<>();
            while (result.next()) {
                availabletickets.add(new Availabletickets(
                        result.getString("origin"),
                        result.getString("destination"),
                        result.getInt("price"),
                        result.getInt("ticket_id")));
            }
            if (availabletickets.isEmpty()) {
                return new WebError("No tickets available");
            }
            return availabletickets;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve data");
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

    // Tickets available amount - Purchase
    @GetMapping("/ticketsamount/{flight_id}/{category}")
    public Object getAmounttickets(@PathVariable int flight_id, @PathVariable String category) {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT COUNT(ticket_id) as Tickets_amount\n" + //
                                    "FROM tickets \n" + //
                                    "WHERE type = '%s' and flight_id = '%d' and state = 'active' and user_id IS null ",
                            category, flight_id));
            ResultSet result = query.executeQuery();

            record ticketsamount(int tickets_amount) {
            }
            if (result.next()) {
                return new ticketsamount(
                        result.getInt("tickets_amount"));
            } else {
                return new WebError("No tickets available");
            }
            // return result;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve data");
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

    // Purchase logs - API
    @GetMapping("/purchaselogs")
    public Object getpurchaselogs() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM Historical_purchases"));
            ResultSet result = query.executeQuery();

            record purchases(String purchase_number, String ticket, String type, String origin, String destination,
                    String purchase_date, String price, String paymenth_method,
                    String departure_date, String arrival_date, int discount, String user_name) {
            }

            List<purchases> purchaseslogs = new ArrayList<>();
            while (result.next()) {
                purchaseslogs.add(new purchases(
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
                        result.getInt("discount"),
                        result.getString("user_name")));
            }
            if (purchaseslogs.isEmpty()) {
                return new WebError("This user doesn't have any purchases made");
            }
            return purchaseslogs;
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

    // Header - GET INFORMATION
    @GetMapping("/header")
    public Object getHeader() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM Header"));
            ResultSet result = query.executeQuery();

            record Header(String Text_Logo,
                    String Section,
                    String Link_Section,
                    String Link_Profile,
                    String Link_Login,
                    String Logo) {
            }

            if (result.next()) {
                return new Header(
                        result.getString("Text_Logo"),
                        result.getString("Section"),
                        result.getString("Link_Section"),
                        result.getString("Link_Profile"),
                        result.getString("Link_Login"),
                        result.getString("Logo")

                );
            }
            return new WebError("Failed to get header information");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API Incorrect");
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

    // Header - UPDATE
    @PostMapping("/update-header")
    public Object updateHeader(@RequestBody Header head) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE Header SET TEXT_LOGO = '%s', SECTION = '%s', LINK_SECTION = '%s', LINK_PROFILE = '%s', LINK_LOGIN = '%s', LOGO = '%s'",
                            head.Text_Logo, head.Section, head.Link_Section, head.Link_Profile, head.Link_Login,
                            head.Logo));
            query.executeQuery();

            return new WebSuccess("Header information updated");
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

    // FLight and Tickets - Cancelation
    @PostMapping("/cancelation/{flight_id}")
    public Object updateFlightandTickets(@PathVariable int flight_id) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE FLIGHTS SET State = 0 WHERE FLIGHT_ID = %d",
                            flight_id));
            query.executeQuery();

            PreparedStatement ticketquery = conn
                    .prepareStatement(String.format(
                            "UPDATE TICKETS SET State = 'canceled' WHERE FLIGHT_ID = %d",
                            flight_id));
            ticketquery.executeQuery();
            // API call to nexus cancelation ticket
            // RestTemplate restTemplate = new RestTemplate();
            // ResponseEntity<String> response = restTemplate.exchange(NEXUS_API + "deactivate/" + flight_id, HttpMethod.PUT, null, String.class);
            //End of API call

            return new WebSuccess("Flight and tickets canceled");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to execute operation");
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

    // Tickets Individual - Cancelation
    @PostMapping("/ticketcanceled/{ticket_id}")
    public Object updateIndividualTicket(@PathVariable int ticket_id) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE TICKETS SET User_ID = NULL WHERE TICKET_ID = %d",
                            ticket_id));
            query.executeQuery();
            // API call to nexus cancelation ticket
            // RestTemplate restTemplate = new RestTemplate();
            // ResponseEntity<String> response = restTemplate.exchange(NEXUS_API + "deactivateTicket/" + ticket_id, HttpMethod.PUT, null, String.class);
            //End of API call
            return new WebSuccess("User Ticket canceled");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to execute operation");
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

    // User Discount - GET
    @GetMapping("/discount/{id}")
    public Object getDiscount(@PathVariable int id) {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT percentage FROM USERS WHERE user_id = %d",
                            id));
            ResultSet result = query.executeQuery();

            record Discount(int discount) {
            }

            if (result.next()) {
                return new Discount(
                        result.getInt("percentage"));
            }
            return new WebError("Failed to get user Discount");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve information");
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

    // Update flight
    @PostMapping("/update-flight/{flight_id}")
    public Object updateFlight(@RequestBody Flight flight, @PathVariable int flight_id) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE FLIGHTS SET Origin = %d, Destination = %d, Departure_date = TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS'), Arrival_date = TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS'), Detail = '%s' WHERE FLIGHT_ID = %d",
                            flight.originCity, flight.destinationCity, flight.departureDate, flight.arrivalDate,
                            flight.detail, flight_id));
            query.executeQuery();

            return new WebSuccess("Flight updated successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API ERROR");
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

    // Footer - GET INFORMATION
    @GetMapping("/footer")
    public Object getFooter() {
        Connection conn = new OracleConnector().getConnection();
        try {

            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM Footer"));
            ResultSet result = query.executeQuery();

            record Footer(
                    String Title_1,
                    String Section_1,
                    String L1,
                    String Section_2,
                    String L2,
                    String Section_3,
                    String L3,
                    String Section_4,
                    String L4,
                    String Section_5,
                    String L5,
                    String Section_6,
                    String L6,
                    String Title_2,
                    String Q_1,
                    String Link_quick_1,
                    String Q_2,
                    String Linkl_quick_2,
                    String Title_3,
                    String C_1,
                    String C_2,
                    String Copyright) {
            }

            if (result.next()) {
                return new Footer(
                        result.getString("Title_1"),
                        result.getString("Section_1"),
                        result.getString("L1"),
                        result.getString("Section_2"),
                        result.getString("L2"),
                        result.getString("Section_3"),
                        result.getString("L3"),
                        result.getString("Section_4"),
                        result.getString("L4"),
                        result.getString("Section_5"),
                        result.getString("L5"),
                        result.getString("Section_6"),
                        result.getString("L6"),
                        result.getString("Title_2"),
                        result.getString("Q_1"),
                        result.getString("Link_quick_1"),
                        result.getString("Q_2"),
                        result.getString("Link_quick_2"),
                        result.getString("Title_3"),
                        result.getString("C_1"),
                        result.getString("C_2"),
                        result.getString("Copyright"));
            }
            return new WebError("Failed to get footer information");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API Incorrect");
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

    // Footer - UPDATE
    @PostMapping("/update-footer")
    public Object updateFooter(@RequestBody Footer footer) {
        Connection conn = new OracleConnector().getConnection();

        try {
            Statement checkStatement = conn.createStatement();
            ResultSet result = checkStatement.executeQuery("SELECT COUNT(*) FROM Footer");
            result.next();
            int rowCount = result.getInt(1);

            if (rowCount == 0) {
            PreparedStatement insertStatement = conn.prepareStatement(String.format(
                "INSERT INTO Footer (Title_1, SECTION_1, L1, SECTION_2, L2, SECTION_3, L3, SECTION_4, L4, SECTION_5, L5, SECTION_6, L6,\n" + //
                "Title_2, Q_1, Link_quick_1, Q_2, Link_quick_2, Title_3, C_1, C_2, copyright) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s',\n" + //
                "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
                footer.Title_1, footer.Section_1, footer.L1, footer.Section_2, footer.L2, footer.Section_3,
                footer.L3, footer.Section_4, footer.L4,
                footer.Section_5, footer.L5, footer.Section_6, footer.L6, footer.Title_2,
                footer.Quick_Section_1, footer.Link_quick_1,
                footer.Quick_Section_2, footer.Link_quick_2, footer.Title_3, footer.Contact_1,
                footer.Contact_2, footer.copyright)); 
            insertStatement.executeUpdate();
            return new WebSuccess("Footer information inserted");
            }
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE Footer SET Title_1 = '%s', SECTION_1 = '%s', L1 = '%s', SECTION_2 = '%s', L2 = '%s', SECTION_3 = '%s', L3 = '%s', \n"
                                    + //
                                    "SECTION_4 = '%s', L4 = '%s', SECTION_5 = '%s', L5 = '%s', SECTION_6 = '%s', L6 = '%s', Title_2 = '%s', \n"
                                    + //
                                    "Q_1 = '%s', Link_quick_1 = '%s', Q_2 = '%s', Link_quick_2 = '%s', Title_3 = '%s', C_1 = '%s', C_2 = '%s', copyright = '%s'",
                            footer.Title_1, footer.Section_1, footer.L1, footer.Section_2, footer.L2, footer.Section_3,
                            footer.L3, footer.Section_4, footer.L4,
                            footer.Section_5, footer.L5, footer.Section_6, footer.L6, footer.Title_2,
                            footer.Quick_Section_1, footer.Link_quick_1,
                            footer.Quick_Section_2, footer.Link_quick_2, footer.Title_3, footer.Contact_1,
                            footer.Contact_2, footer.copyright));
            query.executeQuery();

            return new WebSuccess("Footer information updated");
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

    // Partners - GET INFORMATION
    @GetMapping("/partners")
    public Object getPartners() {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM partners"));
            ResultSet result = query.executeQuery();

            record Partner(String Title, String Description, String Partner1, String L1, String Partner2, String L2,
                    String Partner3, String L3,
                    String Partner4, String L4, String Partner5, String L5) {
            }

            if (result.next()) {
                return new Partner(
                        result.getString("Title"),
                        result.getString("Description"),
                        result.getString("Partner_1"),
                        result.getString("L1"),
                        result.getString("Partner_2"),
                        result.getString("L2"),
                        result.getString("Partner_3"),
                        result.getString("L3"),
                        result.getString("Partner_4"),
                        result.getString("L4"),
                        result.getString("Partner_5"),
                        result.getString("L5"));
            }
            return new WebError("Failed to get partners");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API Incorrect");
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

    // Partners - UPDATE
    @PostMapping("/update-partners")
    public Object updatePartners(@RequestBody Partners partner) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE Partners SET Title = '%s', Description = '%s', Partner_1 = '%s', L1 = '%s', Partner_2 = '%s', L2 = '%s', Partner_3 = '%s', \n"
                                    + //
                                    "L3 = '%s', Partner_4 = '%s', L4 = '%s', Partner_5 = '%s', L5= '%s'",
                            partner.Title, partner.Description, partner.Partner1, partner.L1, partner.Partner2,
                            partner.L2,
                            partner.Partner3, partner.L3, partner.Partner4, partner.L4, partner.Partner5, partner.L5));
            query.executeQuery();

            return new WebSuccess("Partners information updated");
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

    // Modification Flight - GET INFORMATION OF RESPECTIVE USERS THAT THE FLIGHT
    // HAVE BEEN MODIFIED
    @GetMapping("/modification-notification/{flightid}")
    public Object getEmailParametersFlightModified(@PathVariable int flightid) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT concat(u.first_name,' ', u.last_name) as username, u.email, t.ticket_id, t.flight_id\n"
                                    + //
                                    "FROM tickets t\n" + //
                                    "Join Users u ON t.user_id = u.user_id where t.flight_id = %d",
                            flightid));
            ResultSet result = query.executeQuery();

            record userinformation(String name, String email, int flight_id, int ticket) {
            }

            List<userinformation> userinformations = new ArrayList<>();
            while (result.next()) {
                userinformations.add(new userinformation(
                        result.getString("username"),
                        result.getString("email"),
                        result.getInt("flight_id"),
                        result.getInt("ticket_id")));
            }
            if (userinformations.isEmpty()) {
                return new WebError("No information retrieve available");
            }
            return userinformations;
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to retrieve information");
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

    // City - REGISTRATION
    @PostMapping("/create-city/{city}")
    public Object createCity(@PathVariable String city) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "INSERT INTO CITIES (name) values ('%s')",
                            city));
            query.executeQuery();
            return new WebSuccess("City added successfully");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to add city");
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

    // Cities - UPDATE
    @PostMapping("/update-city/{city}/{id}")
    public Object updateCities(@PathVariable String city, @PathVariable int id) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE Cities SET Name = '%s' WHERE City_ID = %d",
                            city, id));
            query.executeQuery();
            return new WebSuccess("City name updated");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("Failed to update city");
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

    // Home - GET INFORMATION
    @GetMapping("/home")
    public Object getHome() {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT * FROM Home"));
            ResultSet result = query.executeQuery();

            record Home(String Background, String FeatureImage1, String Title1, String Content1, String FeatureImage2,
                    String Title2, String Content2,
                    String FeatureImage3, String Title3, String Content3) {
            }

            if (result.next()) {
                return new Home(
                        result.getString("Background_Image"),
                        result.getString("FeatureImage_1"),
                        result.getString("Title_1"),
                        result.getString("Content_1"),
                        result.getString("FeatureImage_2"),
                        result.getString("Title_2"),
                        result.getString("Content_2"),
                        result.getString("FeatureImage_3"),
                        result.getString("Title_3"),
                        result.getString("Description_3"));
            }
            return new WebError("Failed to get home information");
        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("API Incorrect");
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

    // Home - UPDATE
    @PostMapping("/update-home")
    public Object updateHome(@RequestBody Home Home) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "UPDATE Home SET BACKGROUND_IMAGE = '%s', FEATUREIMAGE_1 = '%s', TITLE_1 = '%s', CONTENT_1 = '%s', FEATUREIMAGE_2 = '%s', TITLE_2 = '%s', CONTENT_2 = '%s', FEATUREIMAGE_3 = '%s', TITLE_3 = '%s', DESCRIPTION_3 = '%s'",
                            Home.Background, Home.FlightImage1, Home.Title1, Home.Content1, Home.FlightImage2,
                            Home.Title2, Home.Content2, Home.FlightImage3, Home.Title3, Home.Content3));
            query.executeQuery();

            return new WebSuccess("Home information updated");
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

    // Scale Flights Simple - API
    @GetMapping("/scale-flights")
    public Object getScaleFLights(
        @RequestParam(value = "originCity", defaultValue = "") int origin,
        @RequestParam(value = "destinationCity", defaultValue = "") int destination
        ) {
        Connection conn = new OracleConnector().getConnection();

        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format(
                            "SELECT f.flight_id, origin, c1.name AS origin_city, destination, c2.name AS destination_city, f.departure_date, f.arrival_date, f.amount_normal, f.amount_premium,\n"
                            +//
                            "f.price_normal, f.price_premium, f.detail, f.type, f.state FROM Flights f INNER JOIN Cities c1 ON f.origin = c1.city_id INNER JOIN Cities c2 ON f.destination = c2.city_id\n"
                            +//
                            "WHERE flight_id IN (SELECT f1.FLIGHT_ID AS first_flight_id FROM Flights f1 INNER JOIN Flights f2 ON f1.destination = f2.ORIGIN WHERE f1.ORIGIN = %d AND f2.destination = %d\n"
                            +//
                            ") AND f.state = 1 ORDER BY flight_id",
                            origin, destination, origin, destination));
            ResultSet result = query.executeQuery();

            record FlightScale(int flightId, int originCityId, String originCityName, int destinationCityId, String destinationCityName,
                    String departureDate, String arrivalDate, int touristQuantity, int businessQuantity, int touristPrice, int businessPrice,
                    String detail, int type, int state, List<CommentaryRecord> commentaries, RatingRecord rating) {
            }

            List<FlightScale> flightscale = new ArrayList<>();
            while (result.next()) {
                List<CommentaryRecord> commentaries = new ArrayList<CommentaryRecord>();

                PreparedStatement countQuery = conn.prepareStatement(String
                        .format("SELECT (select count(ticket_id) from tickets where flight_id = %s and type = 'economy' and state = 'active' and user_id is null) as economy_quantity, (select count(ticket_id) from tickets where flight_id = %s and type = 'premium' and state = 'active' and user_id is null) as premium_quantity",
                                result.getInt("flight_id"), result.getInt("flight_id")));
                ResultSet countResult = countQuery.executeQuery();

                PreparedStatement commentariesQuery = conn.prepareStatement(String.format(
                        "SELECT comments.comment_id, comments.parent_comment_id, comments.user_id, comments.content, comments.creation_date, comments.path, comments.flight_id, users.first_name from comments inner join users on comments.user_id = users.user_id where comments.flight_id = %s",
                        result.getInt("flight_id")));
                ResultSet commentariesResult = commentariesQuery.executeQuery();

                PreparedStatement ratingsQuery = conn.prepareStatement(String.format(
                        "SELECT ratingAverage(%s) as rating, COUNT(ratings.rating_id) as count from ratings where flight_id = %s",
                        result.getInt("flight_id"), result.getInt("flight_id")));
                ResultSet ratingsResult = ratingsQuery.executeQuery();

                if (!countResult.next() || !ratingsResult.next()) {
                    return new WebError("Failed to retrieve flights");
                }

                while (commentariesResult.next()) {
                    commentaries.add(new CommentaryRecord(commentariesResult.getInt("comment_id"),
                            commentariesResult.getInt("parent_comment_id"),
                            commentariesResult.getInt("user_id"), commentariesResult.getString("content"),
                            commentariesResult.getString("creation_date"),
                            commentariesResult.getString("path"), commentariesResult.getInt("flight_id"),
                            commentariesResult.getString("first_name"), new ArrayList<CommentaryRecord>()));
                }

                flightscale.add(new FlightScale(
                        result.getInt("flight_id"),
                        result.getInt("origin"),
                        result.getString("origin_city"),
                        result.getInt("destination"),
                        result.getString("destination_city"),
                        result.getString("departure_date"),
                        result.getString("arrival_date"),
                        result.getInt("amount_normal"),
                        result.getInt("amount_premium"),
                        result.getInt("price_normal"),
                        result.getInt("price_premium"),
                        result.getString("detail"),
                        result.getInt("type"),
                        result.getInt("state"), 
                        commentaries, 
                        new RatingRecord(
                                ratingsResult.getInt("rating"), ratingsResult.getInt("count"))));
            }
            if (flightscale.isEmpty()) {
                return new Object[0];
            }
            return flightscale;
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

    // Get a Cities - API
    @GetMapping("/get-city/{cityid}")
    public Object getOneCity(@PathVariable int cityid) {
        Connection conn = new OracleConnector().getConnection();
        try {
            PreparedStatement query = conn
                    .prepareStatement(String.format("SELECT name FROM cities WHERE city_id = %d", cityid));
            ResultSet result = query.executeQuery();

            record UniqueCity(String name) {
            }

            if (result.next()) {
                return new UniqueCity(result.getString("name"));
            }
            return new WebError("City not found");

        } catch (Throwable e) {
            e.printStackTrace();
            return new WebError("User not found");
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
