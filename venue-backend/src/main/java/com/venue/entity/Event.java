package com.venue.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event_ID")
    private Long eventId;

    @Column(name = "Organizer_ID")
    private Integer organizerId;

    @Column(name = "Booking_Date")
    private LocalDate bookingDate;

    @Column(name = "Event_DateTime")
    private LocalDateTime eventDateTime;

    // Many Events can be held at one Venue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Venue_ID", nullable = false)
    private Venue venue;

    // Getter and Setter for eventId
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    // Getter and Setter for organizerId
    public Integer getOrganizerId() {
        return organizerId;
    }
    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }
    // Getter and Setter for bookingDate
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    // Getter and Setter for eventDateTime
    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }
    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
    // Getter and Setter for venue
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public void setVenueId(Integer venueId) {
    }
}
