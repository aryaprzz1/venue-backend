package com.venue.mapper;

import com.venue.dto.VenueDto;
import com.venue.entity.Venue;

public class VenueMapper {

    // Method to map from Venue entity to VenueDto
    public static VenueDto mapToVenueDto(Venue venue) {
        // Check if the Venue entity is null before processing
        if (venue == null) {
            return null; // Return null if the input venue is null to avoid NullPointerException
        }

        // Create and return a new VenueDto object with the mapped fields from the Venue entity
        return new VenueDto(
                venue.getVenueId(),     // Set venue ID
                venue.getVenueName(),   // Set venue name
                venue.getCapacity(),    // Set venue capacity
                venue.getStatus(),      // Set venue status (e.g., open, closed)
                venue.getPhoneNo()      // Set venue phone number
        );
    }

    // Method to map from VenueDto to Venue entity
    public static Venue mapToVenue(VenueDto venueDto) {
        // Check if the VenueDto is null before processing
        if (venueDto == null) {
            return null; // Return null if the input venueDto is null to avoid NullPointerException
        }
        // Create a new Venue entity and map the fields from the VenueDto
        Venue venue = new Venue();
        venue.setVenueId(venueDto.getVenueId());        // Set venue ID
        venue.setVenueName(venueDto.getVenueName());    // Set venue name
        venue.setCapacity(venueDto.getCapacity());      // Set venue capacity
        venue.setStatus(venueDto.getStatus());          // Set venue status
        venue.setPhoneNo(venueDto.getPhoneNo());        // Set venue phone number

        // Return the populated Venue entity
        return venue;
    }
}
