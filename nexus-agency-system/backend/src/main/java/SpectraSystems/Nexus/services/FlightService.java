package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.repositories.FlightRepository;
import SpectraSystems.Nexus.models.City;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

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

    public List<Flight> getAllFlightsFromOtherBackend() {
        // Make a request to the other backend to get all flights
        // Replace "localhost:8081/get-all-flights" with the actual port
        Flight[] flights = restTemplate.getForObject("http://localhost:8081/get-all-flights", Flight[].class);
        return Arrays.asList(flights);
    }

    public List<City> getAllCitiesFromOtherBackend() {
        // Make a request to the other backend to get cities
        // Replace "localhost:8081/get-cities" with the actual Port
        City[] cities = restTemplate.getForObject("http://localhost:8081/get-cities", City[].class);
        return Arrays.asList(cities);
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

