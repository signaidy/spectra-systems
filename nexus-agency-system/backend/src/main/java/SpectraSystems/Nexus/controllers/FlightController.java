package SpectraSystems.Nexus.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import SpectraSystems.Nexus.models.City;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.FlightPurchaseRequest;
import SpectraSystems.Nexus.models.Reservation;
import SpectraSystems.Nexus.models.User;
import SpectraSystems.Nexus.models.externalFlight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.repositories.ReservationRepository;
import SpectraSystems.Nexus.repositories.UserRepository;
import SpectraSystems.Nexus.services.FlightService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/nexus/flights")
public class FlightController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;


    // private static final Logger logger = LoggerFactory.getLogger(FlightController.class); Use for loggin errors lmao

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    public FlightController(FlightRepository flightRepository, FlightService flightService, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    // Endpoint to retrieve all flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Endpoint to retrieve a flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") Long id) {
        return flightService.getFlightById(id)
                .map(flight -> new ResponseEntity<>(flight, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to retrieve all flights by userID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Flight>> getAllFlightsByUserId(@PathVariable Long userId) {
        List<Flight> flights = flightService.getAllFlightsByUserId(userId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/avianca/flights")
    public ResponseEntity<List<externalFlight>> getAllFlightsFromOtherBackend() {
        List<externalFlight> flights = flightService.getAllFlightsFromOtherBackend();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/avianca/one-way-flights")
    public ResponseEntity<List<externalFlight>> getOneWayFlightsFromOtherBackend(
        @RequestParam(value = "originCity") Long originCityId,
        @RequestParam(value = "destinationCity") Long destinationCityId,
        @RequestParam(value = "departureDay") String departureDay,
        @RequestParam(value = "passengers") int passengers
    ) {
        List<externalFlight> flights = flightService.getOneWayFlightsFromOtherBackend(originCityId, destinationCityId, departureDay, passengers);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/avianca/round-trip-flights")
    public ResponseEntity<List<externalFlight>> getRoundTripFlights(
            @RequestParam(value = "originCity") Long originCityId,
            @RequestParam(value = "destinationCity") Long destinationCityId,
            @RequestParam(value = "departureDay") String departureDay,
            @RequestParam(value = "returnDay") String returnDay,
            @RequestParam(value = "passengers") int passengers
    ) {
        // Get outbound flights
        List<externalFlight> outboundFlights = flightService.getOneWayFlightsFromOtherBackend(
                originCityId, destinationCityId, departureDay, passengers);

        // Get return flights (reverse origin and destination)
        List<externalFlight> returnFlights = flightService.getOneWayFlightsFromOtherBackend(
                destinationCityId, originCityId, returnDay, passengers);

        // Add return flights to each outbound flight
        for (externalFlight outboundFlight : outboundFlights) {
            for (externalFlight returnFlight : returnFlights) {
                if (matchFlights(outboundFlight, returnFlight)) {
                    outboundFlight.setReturnFlight(returnFlight);
                    break;
                }
            }
        }

        return new ResponseEntity<>(outboundFlights, HttpStatus.OK);
    }

    // Method to check if flights match based on origin, destination cities, and scales
    private boolean matchFlights(externalFlight outboundFlight, externalFlight returnFlight) {
        if (outboundFlight.getScale() != null && returnFlight.getScale() != null) {
            return outboundFlight.getScale().getDestinationCityId().equals(returnFlight.getOriginCityId()) &&
                    outboundFlight.getScale().getOriginCityId().equals(returnFlight.getDestinationCityId());
        } else if (outboundFlight.getScale() != null) {
            return outboundFlight.getScale().getDestinationCityId().equals(returnFlight.getOriginCityId()) &&
                    outboundFlight.getOriginCityId().equals(returnFlight.getDestinationCityId());
        } else {
            return outboundFlight.getDestinationCityId().equals(returnFlight.getOriginCityId()) &&
                    outboundFlight.getOriginCityId().equals(returnFlight.getDestinationCityId());
        }
    }

    @GetMapping("/avianca/cities")
    public ResponseEntity<List<City>> getAllCitiesFromOtherBackend() {
        List<City> cities = flightService.getAllCitiesFromOtherBackend();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Endpoint to create a new flight
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    // Endpoint for purchasing a flight
    @PostMapping("/purchase/{amount}/{method}")
    public ResponseEntity<Map<String, String>> purchaseFlight(
            @PathVariable int amount,
            @PathVariable String method,
            @RequestBody FlightPurchaseRequest purchaseRequest
    ) throws JsonProcessingException {
        flightService.purchaseFlight( amount,  method, purchaseRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Flight purchased successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // Endpoint to update an existing flight
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") Long id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @PutMapping("/deactivate/{flightNumber}")
    public ResponseEntity<List<Flight>> deactivateFlightsByFlightNumber(@PathVariable String flightNumber) {
        List<Flight> flights = flightService.getFlightsByFlightNumber(flightNumber);
        if (!flights.isEmpty()) {
            for (Flight flight : flights) {
                flight.setState("cancelled");
                flightService.updateFlight(flight.getId(), flight);
                String bundle = flight.getBundle();
            
                List<Flight> flightsWithSameBundle = flightRepository.findByBundle(bundle);
                
                for (Flight flightBundle : flightsWithSameBundle) {
                    flightBundle.setState("cancelled");
                    flightRepository.save(flightBundle);
                    sendCancellationEmail(flightBundle.getUser());
                }
                List<Reservation> reservationsWithSameBundle = reservationRepository.findByBundle(bundle);
                for (Reservation reservation : reservationsWithSameBundle) {
                    reservation.setState("cancelled");
                    reservationRepository.save(reservation);
                    sendCancellationEmail(reservation.getUser());
                }
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deactivateTicket/{id}")
    public ResponseEntity<List<Flight>> deactivateFlightsById(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightService.getFlightById(id);
        if(optionalFlight.isPresent()){
            Flight flight = optionalFlight.get();
            flight.setState("cancelled");
            flightRepository.save(flight);
            String bundle = flight.getBundle();
            
            List<Flight> flightsWithSameBundle = flightRepository.findByBundle(bundle);
            
            for (Flight flights : flightsWithSameBundle) {
                flights.setState("cancelled");
                flightRepository.save(flights);
            }
            List<Reservation> reservationsWithSameBundle = reservationRepository.findByBundle(bundle);
            for (Reservation reservation : reservationsWithSameBundle) {
                reservation.setState("cancelled");
                reservationRepository.save(reservation);
            }
            
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a flight by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void sendCancellationEmail(Long userId) {
        try {
            // Retrieve user by userId
            User user = userRepository.getReferenceById(userId);
            // Get the email of the user
            String userEmail = user.getEmail();
    
            // Create MimeMessage
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject("Flight Reservation Cancellation");
            helper.setText("Your flight reservation has been cancelled.");
    
            // Send email
            emailSender.send(message);
        } catch (MessagingException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }
}
