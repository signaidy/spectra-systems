package SpectraSystems.Nexus.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import SpectraSystems.Nexus.models.City;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.FlightPurchaseRequest;
import SpectraSystems.Nexus.models.externalFlight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.services.FlightService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/nexus/flights")
public class FlightController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public FlightController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
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
            return outboundFlight.getScale().getDestinationCityId().equals(returnFlight.getDestinationCityId()) &&
                    outboundFlight.getScale().getOriginCityId().equals(returnFlight.getOriginCityId());
        } else {
            return outboundFlight.getDestinationCityId().equals(returnFlight.getDestinationCityId()) &&
                    outboundFlight.getOriginCityId().equals(returnFlight.getOriginCityId());
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
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deactivateTicket/{id}")
    public ResponseEntity<List<Flight>> deactivateFlightsById(@PathVariable Long id) {
        Optional<Flight> optionalFlights = flightService.getFlightById(id);
        if(optionalFlights.isPresent()){
            Flight flight = optionalFlights.get();
            flight.setState("cancelled");
            flightRepository.save(flight);
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
}
