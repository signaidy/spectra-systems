package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.externalFlight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.models.City;
import SpectraSystems.Nexus.models.Comment;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;
    @Autowired
    private CommentService commentService; 
    private List<Comment> commentaries;

    @Autowired
    public FlightService(FlightRepository flightRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }
    
    public List<Flight> getAllFlightsByUserId(Long userId) {
        return flightRepository.findByUserid(userId);
    }

    public Flight createFlight(Flight flight) {
        if (flight != null) {
            return flightRepository.save(flight);
        } else {
            return null;
        }
    }

    public List<externalFlight> getAllFlightsFromOtherBackend() {
        ResponseEntity<externalFlight[]> responseEntity = restTemplate.exchange(
            "http://localhost:8080/get-all-flights",
            HttpMethod.GET,
            null,
            externalFlight[].class
        );
        externalFlight[] externalFlights = responseEntity.getBody();
        return Arrays.asList(externalFlights);
    }

    public List<externalFlight> getOneWayFlightsFromOtherBackend(
        Long originCity,
        Long destinationCity,
        String departureDay,
        int passengers
    ) {
        // Call the other backend to get flights
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080/get-one-way-flights")
                .queryParam("originCity", originCity)
                .queryParam("destinationCity", destinationCity)
                .queryParam("departureDay", departureDay)
                .queryParam("passengers", passengers);

        ResponseEntity<externalFlight[]> responseEntity = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            null,
            externalFlight[].class
        );
        
        // Get flights from the other backend
        externalFlight[] externalFlights = responseEntity.getBody();
        
        // Embed comments from your database
        for (externalFlight flight : externalFlights) {
            List<Comment> comment = commentService.getCommentsByFlightId(flight.getFlightId());
            if (comment.isEmpty()) {
                // Create an empty comments array if there are no comments
                flight.setCommentaries(new ArrayList<>());
            } else {
                flight.setCommentaries(comment);
            }
        }

        return Arrays.asList(externalFlights);
    }

    public List<City> getAllCitiesFromOtherBackend() {
        // Make a request to the other backend to get cities
        ResponseEntity<List<City>> responseEntity = restTemplate.exchange(
        "http://localhost:8080/get-cities",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<City>>() {}
    );
    return responseEntity.getBody();
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        // Update flight details
        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureLocation(flightDetails.getDepartureLocation());
        flight.setArrivalLocation(flightDetails.getArrivalLocation());
        flight.setReturnDate(flightDetails.getReturnDate());

        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

