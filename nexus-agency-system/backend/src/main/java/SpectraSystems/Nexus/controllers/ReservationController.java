package SpectraSystems.Nexus.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.Reservation;
import SpectraSystems.Nexus.services.ReservationService;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nexus/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RestTemplate restTemplate;
    private static final String HOTEL_USER_ID = "65f9310acfb50244b4e886b0";
    private static final SimpleDateFormat API_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationService reservationService, RestTemplate restTemplate) {
        this.reservationService = reservationService;
        this.restTemplate = restTemplate;
    }

    // Endpoint to retrieve all reservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getAllReservationsByUserId(@PathVariable Long userId) {
        List<Reservation> Reservations = reservationService.getAllReservationsByUserId(userId);
        return new ResponseEntity<>(Reservations, HttpStatus.OK);
    }

    // Endpoint to retrieve a reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        return reservationService.getReservationById(id)
                .map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        String formattedCheckin = API_DATE_FORMAT.format(reservation.getDateStart());
        String formattedCheckout = API_DATE_FORMAT.format(reservation.getDateEnd());
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("hotelId", reservation.getHotelId());
        requestBody.put("userId", HOTEL_USER_ID);
        requestBody.put("checkin", formattedCheckin);
        requestBody.put("checkout", formattedCheckout);
        requestBody.put("roomType", reservation.getRoomType());
        requestBody.put("roomPrice", reservation.getPrice());
        requestBody.put("guests", reservation.getGuests());
        requestBody.put("stayDays", reservation.getTotalDays());
        requestBody.put("totalPrice", reservation.getTotalPrice());

        

        // Make a POST request to the external API to create the reservation
        String apiUrl = "http://localhost:3001/create-reservation";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestBody, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK && responseEntity.getStatusCode() != HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
            String reservationId = rootNode.get("_id").asText();

            reservation.setReservationNumber(reservationId);

            Reservation createdReservation = reservationService.createReservation(reservation);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to update an existing reservation
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservationDetails) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservationDetails);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    // Endpoint to delete a reservation by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/hotelsearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> getHotels(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "check-in", required = false) String checkIn,
            @RequestParam(value = "check-out", required = false) String checkOut,
            @RequestParam(value = "guests", required = false) Integer guests) {

        // Define the URL for the external API
        String apiUrl = "http://localhost:3001/get-filtered-hotels?city=" + city;

        // Create a RestTemplate instance to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Make a GET request to the external API
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );

            // Get the response body
            List<Map<String, Object>> hotels = response.getBody();

            return ResponseEntity.ok().body(hotels);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/roomsearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getHotelRoomById(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "city", required = false) String city) {

        // Define the URL for the external API
        String apiUrl = "http://localhost:3001/get-hotel-by-id/" + id;

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);

            Map<String, Object> hotel = response.getBody();

            // Check if the response contains hotel data
            if (hotel == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> responseBody = constructResponse(hotel);

            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Map<String, Object> constructResponse(Map<String, Object> hotel) {
        // Construct the JSON response with hotel id and rooms
        Map<String, Object> response = Map.of(
                "hotelId", hotel.get("_id"),                
                "name", hotel.get("name"),
                "rooms", hotel.get("rooms")
        );
        return response;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<Map<String, String>>> getCities() {
        // Define the URL for the external API
        String apiUrl = "http://localhost:3001/get-hotels";

        // Create a RestTemplate instance to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Make a GET request to the external API
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );

            // Get the response body
            List<Map<String, Object>> hotels = response.getBody();
            List<Map<String, String>> cities = new ArrayList<>();

            // Extract cities from the hotel data
            for (Map<String, Object> hotel : hotels) {
                String cityId = String.valueOf(cities.size() + 1);
                String cityName = (String) ((Map<String, Object>) hotel.get("location")).get("city");
                Map<String, String> cityMap = Map.of("cityId", cityId, "name", cityName);
                cities.add(cityMap);
            }

            return ResponseEntity.ok().body(cities);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}

