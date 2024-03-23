package SpectraSystems.Nexus.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double percentageAdditionFlights;
    private double percentageAdditionHotel;
    private double percentageDiscount;

    public Provider() {
    }

    public Provider(double percentageAdditionFlights, double percentageAdditionHotel, double percentageDiscount) {
        this.percentageAdditionFlights = percentageAdditionFlights;
        this.percentageAdditionHotel = percentageAdditionHotel; 
        this.percentageDiscount = percentageDiscount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercentageAdditionFlights() {
        return percentageAdditionFlights;
    }

    public void setPercentageAdditionFlights(double percentageAdditionFlights) {
        this.percentageAdditionFlights = percentageAdditionFlights;
    }

    public double getPercentageAdditionHotel() {
        return percentageAdditionHotel;
    }

    public void setPercentageAdditionHotel(double percentageAdditionHotel) {
        this.percentageAdditionHotel = percentageAdditionHotel;
    }

    public double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}

