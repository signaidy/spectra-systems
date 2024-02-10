package SpectraSystems.Nexus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        if (flight != null) {
            return flightRepository.save(flight);
        } else {
            // Handle null flight case, such as throwing an exception or logging a warning
            return null;
        }
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        // Update flight details
        flight.setUser(flightDetails.getUser());
        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDate(flightDetails.getDate());
        flight.setDepartureLocation(flightDetails.getDepartureLocation());
        flight.setArrivalLocation(flightDetails.getArrivalLocation());
        flight.setReturnDate(flightDetails.getReturnDate());

        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

