package SpectraSystems.Nexus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
@Table(name = "NEXUSPROVIDERS")
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

    @Column(name = "gains_flights", nullable = true)
    private Double gainsFlights;

    @Column(name = "gains_hotel",nullable = true)
    private Double gainsHotel;

    @Column(name = "discount", nullable = true)
    private Double percentageDiscount;

    public Provider() {
    }

    public Provider(Long id, String providerName, String providerUrl, Type type, Double gainsFlights, Double gainsHotel, Double percentageDiscount) {
        this.id = id;
        this.providerName = providerName;
        this.providerUrl = providerUrl;
        this.type = type;
        this.gainsFlights = gainsFlights;
        this.gainsHotel = gainsHotel; 
        this.percentageDiscount = percentageDiscount;
    }

    public Provider(String providerName, String providerUrl, Double gainsFlights, Type type, Double gainsHotel, Double percentageDiscount) {
        this.providerName = providerName;
        this.providerUrl = providerUrl;
        this.type = type;
        this.gainsFlights = gainsFlights;
        this.gainsHotel = gainsHotel; 
        this.percentageDiscount = percentageDiscount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getGainsFlights() {
        return gainsFlights != null ? gainsFlights : 0.0;
    }

    public void setGainsFlights(double gainsFlights) {
        this.gainsFlights = gainsFlights;
    }

    public Double getGainsHotel() {
        return gainsHotel != null ? gainsHotel : 0.0;
    }

    public void setGainsHotel(double gainsHotel) {
        this.gainsHotel = gainsHotel;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount != null ? percentageDiscount : 0.0;
    }

    public void setPercentageDiscount(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}

