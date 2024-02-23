package SpectraSystems.Nexus.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpectraSystems.Nexus.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    // You can add custom query methods here if needed
}

