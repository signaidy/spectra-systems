package SpectraSystems.Nexus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpectraSystems.Nexus.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
