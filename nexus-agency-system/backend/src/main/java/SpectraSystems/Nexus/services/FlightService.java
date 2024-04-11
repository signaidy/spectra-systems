package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.FlightPurchaseRequest;
import SpectraSystems.Nexus.models.TicketPurchase;
import SpectraSystems.Nexus.models.externalFlight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.repositories.TicketPurchaseRepository;
import SpectraSystems.Nexus.models.City;
import SpectraSystems.Nexus.models.Comment;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;
    private final TicketPurchaseRepository ticketPurchaseRepository;
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private CommentService commentService; 

    @Autowired
    public FlightService(FlightRepository flightRepository, RestTemplate restTemplate, TicketPurchaseRepository ticketPurchaseRepository) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
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

    public List<Flight> getFlightsByFlightNumber(String flightNumber) {
        return flightRepository.findAllByFlightNumber(flightNumber);
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
        
        

        builder = UriComponentsBuilder.fromUriString("http://localhost:8080/scale-flights")
                .queryParam("originCity", originCity)
                .queryParam("destinationCity", destinationCity)
                .queryParam("departureDay", departureDay);

        responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                externalFlight[].class
        );

        // Get scale flights from the other backend
        externalFlight[] scaleFlights = responseEntity.getBody();
        // For each scale flight, fetch corresponding flights to final destination
        for (externalFlight scaleFlight : scaleFlights) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String formattedDepartureDay = dateFormat.format(scaleFlight.getArrivalDate());
            builder = UriComponentsBuilder.fromUriString("http://localhost:8080/get-one-way-flights")
                    .queryParam("originCity", scaleFlight.getDestinationCityId())
                    .queryParam("destinationCity", destinationCity)
                    .queryParam("departureDay", formattedDepartureDay)
                    .queryParam("passengers", passengers);

            ResponseEntity<externalFlight[]> secondaryResponseEntity = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    externalFlight[].class
            );

            externalFlight[] secondaryFlights = secondaryResponseEntity.getBody();
            if (secondaryFlights != null && secondaryFlights.length > 0) {
                // Add the first flight from the secondaryFlights array to the scaleFlight
                scaleFlight.setScale(secondaryFlights[0]);
            }
        }

        // Merge regular and scale flights
        List<externalFlight> allFlights = new ArrayList<>();
        allFlights.addAll(Arrays.asList(externalFlights));
        allFlights.addAll(Arrays.asList(scaleFlights));

        // Embed comments from database
        for (externalFlight flight : allFlights) {
            List<Comment> comment = commentService.getCommentsByFlightId(flight.getFlightId());
            if (comment.isEmpty()) {
                flight.setCommentaries(new ArrayList<>());
            } else {
                flight.setCommentaries(comment);
            }
        }

        return allFlights;
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

    public void purchaseFlight(int amount, String method, FlightPurchaseRequest purchaseRequest) throws HttpServerErrorException, JsonProcessingException  {
        // Set discount and user_id
        int discount = 20;
        long userIdPurchase = 145;

        // Create request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the body as JSON
        String requestBody = "{"
                + "\"user_id\": \"" + userIdPurchase + "\","
                + "\"flight_id\": \"" + purchaseRequest.getFlightId() + "\","
                + "\"state\": \"\","
                + "\"type\": \"\""
                + "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        String url = String.format("http://localhost:8080/purchase/%d/%s/%d", amount, method, discount);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        
        // Check if the purchase was successful
        if (response.getStatusCode() == HttpStatus.OK) {
            // Create and save Flight object
            for (int i = 0; i < amount; i++) {
                Flight flight = new Flight(purchaseRequest.getUserId(), purchaseRequest.getFlightId().toString(), purchaseRequest.getDepartureDate(), purchaseRequest.getDepartureLocation(), purchaseRequest.getArrivalLocation(), purchaseRequest.getReturnDate(), purchaseRequest.getType(), purchaseRequest.getPrice());
                flightRepository.save(flight);
            }

            // Convert the response body to a JSON array
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            
            int size = responseBody.size();
            int startIndex = size - amount; // Index to start extracting tickets
            
            // Extract the last 'amount' tickets
            for (int i = startIndex; i < size - 1; i++) {
                JsonNode ticketNode = responseBody.get(i);
                // Check if ticketNode is null before accessing its properties
                if (ticketNode != null) {
                    Long ticketId = ticketNode.get("ticket_id").asLong();
                    Long ticketUserId = ticketNode.get("user_id").asLong();
                    // Create TicketPurchase object
                    TicketPurchase ticketPurchase = new TicketPurchase();
                    ticketPurchase.setTicketId(ticketId.intValue());
                    ticketPurchase.setUserId(ticketUserId.intValue());
                    ticketPurchase.setFlightId(purchaseRequest.getFlightId().intValue()); // Assuming flightId is available here
                    // Set type and state if needed
                    
                    // Save TicketPurchase object to database
                    ticketPurchaseRepository.save(ticketPurchase);
                } else {
                    break;
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

