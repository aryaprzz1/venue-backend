package com.venue.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventDto {

    private Long eventId;
    private Integer organizerId;
    private Integer venueId;
    private LocalDate bookingDate;
    private LocalDateTime eventDateTime;

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
    // Getter and Setter for venueId
    public Integer getVenueId() {
        return venueId;
    }
    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
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
    }}
