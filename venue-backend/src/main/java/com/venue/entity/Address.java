package com.venue.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Address_ID")
    private Long addressId;

    @Column(name = "Building_Name", length = 100)
    private String buildingName;

    @Column(name = "Room_Area", length = 100)
    private String roomArea;

    @Column(name = "Campus_Name", length = 100)
    private String campusName;

    @Column(name = "Landmark", length = 100)
    private String landmark;

    // Many Addresses can be associated with one Venue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Venue_ID", nullable = false)  // Venue_ID column in the Address table is a foreign key that links to the Venue table.
                                                      // The nullable = false ensures that an address must always be associated with a venue.
    private Venue venue;

    // Manual Getters and Setters
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
