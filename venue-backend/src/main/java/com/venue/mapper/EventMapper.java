package com.venue.mapper;

import com.venue.dto.EventDto;
import com.venue.entity.Event;
import com.venue.entity.Venue;

public class EventMapper {

    // Method to convert Event entity to EventDto
    public static EventDto mapToEventDto(Event event) {
        // Check if the Event entity is null before processing
        if (event == null) {
            return null; // Return null if the input event is null to avoid NullPointerException
        }

        // Create a new instance of EventDto to hold the converted data
        EventDto eventDto = new EventDto();

        // Map the Event entity fields to the EventDto fields
        eventDto.setEventId(event.getEventId()); // Set event ID
        eventDto.setOrganizerId(event.getOrganizerId()); // Set organizer ID
        eventDto.setVenueId(event.getVenue().getVenueId()); // Get and set venue ID from the Venue entity
        eventDto.setBookingDate(event.getBookingDate()); // Set booking date
        eventDto.setEventDateTime(event.getEventDateTime()); // Set event date and time

        // Return the fully populated EventDto
        return eventDto;
    }

    // Method to convert EventDto to Event entity
    public static Event mapToEvent(EventDto eventDto) {
        // Check if the EventDto is null before processing
        if (eventDto == null) {
            return null; // Return null if the input eventDto is null to avoid NullPointerException
        }

        // Create a new instance of Event to hold the converted data
        Event event = new Event();

        // Map the EventDto fields to the Event entity fields
        event.setEventId(eventDto.getEventId()); // Set event ID
        event.setOrganizerId(eventDto.getOrganizerId()); // Set organizer ID
        event.setBookingDate(eventDto.getBookingDate()); // Set booking date
        event.setEventDateTime(eventDto.getEventDateTime()); // Set event date and time

        // Since the venueId is present in the DTO, we only need to set it on a Venue object and link it to the event
        Venue venue = new Venue();
        venue.setVenueId(eventDto.getVenueId()); // Set venue ID on the Venue entity (other fields can be loaded later)
        event.setVenue(venue); // Associate the venue with the event

        // Return the fully populated Event entity
        return event;
    }
}
