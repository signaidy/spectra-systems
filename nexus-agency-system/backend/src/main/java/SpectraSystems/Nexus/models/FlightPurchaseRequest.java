package SpectraSystems.Nexus.models;

public class FlightPurchaseRequest {
    private Long userId;
    private Long flightId;
    private String state;
    private String type;

    public FlightPurchaseRequest() {
        // Default constructor
    }

    public FlightPurchaseRequest(Long userId, Long flightId, String state, String type) {
        this.userId = userId;
        this.flightId = flightId;
        this.state = state;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
