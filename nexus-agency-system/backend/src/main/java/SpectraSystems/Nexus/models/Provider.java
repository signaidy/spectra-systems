package SpectraSystems.Nexus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "provider_url", nullable = false)
    private String providerUrl;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(nullable = true)
    private Double percentageAdditionFlights;

    @Column(nullable = true)
    private Double percentageAdditionHotel;

    @Column(nullable = true)
    private Double percentageDiscount;

    public Provider() {
    }

    public Provider(Long id, String providerName, String providerUrl, Type type, Double percentageAdditionFlights, Double percentageAdditionHotel, Double percentageDiscount) {
        this.id = id;
        this.providerName = providerName;
        this.providerUrl = providerUrl;
        this.type = type;
        this.percentageAdditionFlights = percentageAdditionFlights;
        this.percentageAdditionHotel = percentageAdditionHotel; 
        this.percentageDiscount = percentageDiscount;
    }

    public Provider(String providerName, String providerUrl, Double percentageAdditionFlights, Type type, Double percentageAdditionHotel, Double percentageDiscount) {
        this.providerName = providerName;
        this.providerUrl = providerUrl;
        this.type = type;
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

