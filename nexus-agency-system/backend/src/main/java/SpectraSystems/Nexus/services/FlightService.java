package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.Rating;
import SpectraSystems.Nexus.models.TicketPurchase;
import SpectraSystems.Nexus.models.externalFlight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.repositories.TicketPurchaseRepository;
import io.jsonwebtoken.io.IOException;
import SpectraSystems.Nexus.models.City;
import SpectraSystems.Nexus.models.Comment;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;
    private final TicketPurchaseRepository ticketPurchaseRepository;

    @Autowired
    private CommentService commentService; 
    private List<Comment> commentaries;

    @Autowired
    public FlightService(FlightRepository flightRepository, RestTemplate restTemplate, TicketPurchaseRepository ticketPurchaseRepository) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
        this.commentaries = commentaries;
        this.ticketPurchaseRepository = ticketPurchaseRepository;
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
        
        // Embed comments from database
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

    public void purchaseFlight(int amount, String method, Long flightId, Long userId) throws JsonMappingException, JsonProcessingException, HttpServerErrorException  {
        // Set discount and user_id
        int discount = 20;
        long userIdPurchase = 145;

        // Create request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the body as JSON
        String requestBody = "{"
                + "\"user_id\": \"" + userIdPurchase + "\","
                + "\"flight_id\": \"" + flightId + "\","
                + "\"state\": \"\","
                + "\"type\": \"\""
                + "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        String url = String.format("http://localhost:8080/purchase/%d/%s/%d", amount, method, discount);
         ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        // Check if the purchase was successful
        if (response.getStatusCode() == HttpStatus.OK) {
           // Parse the response to extract ticket_ids for purchased tickets
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());

            // Iterate over the last 'amount' elements of the response
            int startIndex = root.size() - amount;
            for (int i = startIndex; i < root.size(); i++) {
                int ticketId = root.get(i).get("ticket_id").asInt();

                // Make a GET request to retrieve flight information
                String flightUrl = String.format("http://localhost:8080/historical_purchases/%d", userIdPurchase);
                ResponseEntity<String> flightResponse = restTemplate.getForEntity(flightUrl, String.class);
                if (flightResponse.getStatusCode() == HttpStatus.OK) {
                    // Parse the flight response to find the relevant flight information
                    JsonNode flights = objectMapper.readTree(flightResponse.getBody());
                    for (JsonNode flight : flights) {
                        if (flight.get("ticket").asInt() == ticketId) {
                            // Found the relevant flight, create Flight and TicketPurchase objects
                            Flight newFlight = new Flight(
                                    flight.get("user_id").asLong(),
                                    flight.get("flightNumber").asText(),
                                    Date.valueOf(flight.get("departure_date").asText().substring(0, 10)), // Assuming format is "YYYY-MM-DD HH:mm:ss"
                                    flight.get("origin").asText(),
                                    flight.get("destination").asText(),
                                    Date.valueOf(flight.get("arrival_date").asText().substring(0, 10))
                            );

                            TicketPurchase newTicket = new TicketPurchase(
                                    flight.get("ticket").asInt(),
                                    flight.get("user_id").asInt(),
                                    flightId.intValue(),
                                    flight.get("type").asText(),
                                    ""
                            );

                            // Save the newFlight and newTicket into your backend
                            flightRepository.save(newFlight);
                            ticketPurchaseRepository.save(newTicket);
                            break; // No need to continue searching
                        }
                    }
                } else {
                    throw new RuntimeException("Failed to retrieve flight information");
                }
            }
        } else {
            throw new RuntimeException("Purchase was unsuccessful");
        }
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

