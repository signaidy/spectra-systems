package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Flight;
import SpectraSystems.Nexus.models.Reservation;
import SpectraSystems.Nexus.models.User;
import SpectraSystems.Nexus.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservationsByUserId(Long userId) {
        return reservationRepository.findByUserid(userId);
    }

    public Reservation createReservation(Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        // Set the userid in the reservation object
        reservation.setUser(userId);
        reservation.setState("active");
        // Save the reservation
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByHotelId(String hotelId) {
        return reservationRepository.findAllByHotelId(hotelId);
    }

    // Method to cancel reservations by hotelId
    public void cancelReservationsByHotelId(String hotelId) {
        List<Reservation> reservations = getReservationsByHotelId(hotelId);
        for (Reservation reservation : reservations) {
            reservation.setState("cancelled");
            reservationRepository.save(reservation);
        }
    }

    public void cancelReservationsById(Long id) {
        Optional<Reservation> optionalReservation  = getReservationById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setState("cancelled");
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation was not found");
        }
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));

        // Update reservation details
        reservation.setUser(reservationDetails.getUser());
        reservation.setHotel(reservationDetails.getHotel());
        reservation.setDateStart(reservationDetails.getDateStart());
        reservation.setDateEnd(reservationDetails.getDateEnd());
        reservation.setRoomType(reservationDetails.getRoomType());
        reservation.setReservationNumber(reservationDetails.getReservationNumber());
        reservation.setLocation(reservationDetails.getLocation());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

