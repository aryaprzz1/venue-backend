package com.venue.service;

import com.venue.dto.VenueDto;

import java.util.List;

public interface VenueService {
    VenueDto createVenue(VenueDto venueDto);

    VenueDto getVenueById(Integer venueId);

    List<VenueDto> getAllVenue();

    VenueDto putVenue(Integer venueId, VenueDto updatedVenue);

    VenueDto updateVenue(Integer VenueDto, VenueDto updatedVenue);

    void deleteVenue(Integer venueId);

    VenueDto patchVenue(Integer venueId, VenueDto updateVenue);
}
