package SpectraSystems.Nexus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.Reservation;
import SpectraSystems.Nexus.services.ReservationService;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nexus/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
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
    public ResponseEntity<List<Map<String, Object>>> getHotels(@RequestParam(value = "city", required = false) String city,
                                                                @RequestParam(value = "check-in", required = false) String checkIn,
                                                                @RequestParam(value = "check-out", required = false) String checkOut,
                                                                @RequestParam(value = "guests", required = false) Integer guests) {
        try {
            // Read hotel data from hotel.json file
            String hotelData = new String(Files.readAllBytes(new ClassPathResource("Hotel.json").getFile().toPath()));

            // Convert JSON string to a List<Map<String, Object>>
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> hotels = objectMapper.readValue(hotelData, new TypeReference<List<Map<String, Object>>>() {});

            // Filter hotels based on city and maxOccupancy
            List<Map<String, Object>> filteredHotels = hotels.stream()
                    .filter(hotel -> {
                        String hotelCity = (String) ((Map<String, Object>) hotel.get("location")).get("city");
                        if (city != null && !city.equalsIgnoreCase(hotelCity)) {
                            return false; // Filter out hotels not in the specified city
                        }
                        Map<String, Map<String, Object>> rooms = (Map<String, Map<String, Object>>) hotel.get("rooms");
                        rooms.values().removeIf(room -> {
                            int maxOccupancy = (int) room.get("maxOccupancy");
                            return guests != null && guests > maxOccupancy;
                        });
                        // Filter out hotels with no rooms meeting the minimum number of guests
                        return !rooms.isEmpty();
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(filteredHotels);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/roomsearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getHotelRoomById(@RequestParam(value = "id", required = false) String id, 
                                                                @RequestParam(value = "city", required = false) String city) {
        try {
            // Read hotel data from hotel.json file
            String hotelData = new String(Files.readAllBytes(new ClassPathResource("Hotel.json").getFile().toPath()));

            // Convert JSON string to a List<Map<String, Object>>
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> hotels = objectMapper.readValue(hotelData, new TypeReference<List<Map<String, Object>>>() {});

            // Filter hotels based on id and city
            List<Map<String, Object>> filteredHotels = hotels.stream()
                    .filter(hotel -> id.equals(hotel.get("_id")) && (city == null || city.equalsIgnoreCase((String) ((Map<String, Object>) hotel.get("location")).get("city"))))
                    .collect(Collectors.toList());

            // Check if the hotel with the given id and city exists
            if (filteredHotels.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Construct the JSON response with hotel id and rooms
            Map<String, Object> hotel = filteredHotels.get(0); // Assuming there's only one hotel with the given id
            Map<String, Object> response = constructResponse(hotel);

            return ResponseEntity.ok().body(response);
        } catch (IOException e) {
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
    public ResponseEntity<List<Map<String, String>>> getCities() throws IOException {
        try {
            // Read hotel data from hotel.json file
            byte[] jsonData = Files.readAllBytes(new ClassPathResource("Hotel.json").getFile().toPath());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> hotels = objectMapper.readValue(jsonData, List.class);
            List<Map<String, String>> cities = new ArrayList<>();

            for (Map<String, Object> hotel : hotels) {
                String cityId = String.valueOf(cities.size() + 1);
                String cityName =(String) ((Map<String, Object>) hotel.get("location")).get("city");
                Map<String, String> cityMap = Map.of("cityId", cityId, "name", cityName);
                cities.add(cityMap);
            }
            System.out.println(cities);
            return ResponseEntity.ok().body(cities);
        } catch (IOException e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }}
    
}

