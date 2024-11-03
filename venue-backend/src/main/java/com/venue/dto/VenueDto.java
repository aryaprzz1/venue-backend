package com.venue.dto;

public class VenueDto {

    private Integer venueId;
    private String venueName;
    private Integer capacity;
    private String status;
    private String phoneNo;

    // Constructor
    public VenueDto(Integer venueId, String venueName, Integer capacity, String status, String phoneNo) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.capacity = capacity;
        this.status = status;
        this.phoneNo = phoneNo;
    }

    // Getters
    public Integer getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
