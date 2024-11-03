package com.venue.mapper;

import com.venue.dto.AddressDto;
import com.venue.entity.Address;
import com.venue.entity.Venue;
import com.venue.repository.VenueRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    // Static reference to VenueRepository, used to fetch Venue entities from the database
    public static VenueRepository venueRepository;

    // Constructor to inject the VenueRepository through dependency injection
    public AddressMapper(VenueRepository venueRepository) {
        // Assign the injected repository instance to the static variable
        AddressMapper.venueRepository = venueRepository;
    }

    public static AddressDto mapToAddressDto(Address address) {
        // Check if the provided Address entity is null to prevent null pointer exceptions
        if (address == null) {
            return null; // Return null if no Address is provided
        }
        // Create and return a new AddressDto by extracting values from the Address entity
        return new AddressDto(
                address.getAddressId(),   // Extract the address ID
                address.getBuildingName(), // Extract the building name
                address.getRoomArea(),     // Extract the room/area details
                address.getCampusName(),   // Extract the campus name
                address.getLandmark(),     // Extract the landmark information
                address.getVenue() != null ? address.getVenue().getVenueId() : null // Extract the venue ID if present, otherwise null
        );
    }

    public static Address mapToAddress(AddressDto addressDto) {
        // Check if the provided AddressDto is null to prevent null pointer exceptions
        if (addressDto == null) {
            return null; // Return null if no AddressDto is provided
        }

        // Create a new Address entity and populate it with data from the AddressDto
        Address address = new Address();
        address.setAddressId(addressDto.getAddressId());    // Set the address ID
        address.setBuildingName(addressDto.getBuildingName()); // Set the building name
        address.setRoomArea(addressDto.getRoomArea());         // Set the room/area details
        address.setCampusName(addressDto.getCampusName());     // Set the campus name
        address.setLandmark(addressDto.getLandmark());         // Set the landmark information

        // If the venueId is present in the AddressDto, fetch the corresponding Venue entity from the database
        if (addressDto.getVenueId() != null) {
            // Find the Venue entity by ID using the VenueRepository
            Venue venue = AddressMapper.venueRepository.findById(Long.valueOf(addressDto.getVenueId()))
                    .orElseThrow(() -> new RuntimeException("Venue not found with ID: " + addressDto.getVenueId()));
            // Set the Venue entity in the Address object
            address.setVenue(venue);
        }
        // Return the populated Address entity
        return address;
    }
}
