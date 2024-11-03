package com.venue.controller;
import com.venue.dto.VenueDto;
import com.venue.service.VenueService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/venue")
@AllArgsConstructor
public class VenueController {

    private static final Logger logger = LoggerFactory.getLogger(VenueController.class);
    private VenueService venueService;

    // Build Add Venue REST API
    @PostMapping
    public ResponseEntity<VenueDto> createVenue(@RequestBody VenueDto venueDto) {
        logger.info("Received request to create a new venue: {}", venueDto);
        VenueDto savedVenue = venueService.createVenue(venueDto);
        logger.info("Successfully created a new venue with ID: {}", savedVenue.getVenueId());
        return new ResponseEntity<>(savedVenue, HttpStatus.CREATED);
    }

    // Build Get Venue by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<VenueDto> getVenueById(@PathVariable("id") Integer venueId) {
        logger.info("Received request to retrieve venue with ID: {}", venueId);
        VenueDto venueDto = venueService.getVenueById(venueId);
        if (venueDto != null) {
            logger.info("Successfully retrieved venue with ID: {}", venueId);
        } else {
            logger.warn("No venue found with ID: {}", venueId);
        }
        return ResponseEntity.ok(venueDto);
    }

    // Build Get All Venues REST API
    @GetMapping
    public ResponseEntity<List<VenueDto>> getAllVenue() {
        logger.info("Received request to retrieve all venues");
        List<VenueDto> venues = venueService.getAllVenue();
        if (venues.isEmpty()) {
            logger.warn("No venues found");
        } else {
            logger.info("Successfully retrieved {} venues", venues.size());
        }
        return ResponseEntity.ok(venues);
    }

    // Build Update Venue REST API (PUT)
    @PutMapping("/{venueId}")
    public ResponseEntity<VenueDto> putVenue(@PathVariable Integer venueId, @RequestBody VenueDto updatedVenue) {
        logger.info("Received request to fully update venue with ID: {}", venueId);
        VenueDto venueDto = venueService.putVenue(venueId, updatedVenue);
        logger.info("Successfully updated venue with ID: {}", venueId);
        return ResponseEntity.ok(venueDto);
    }

    // Build Partial Update Venue (PATCH)
    @PatchMapping("{id}")
    public ResponseEntity<String> patchVenue(@PathVariable("id") Integer venueId, @RequestBody VenueDto updateVenue) {
        logger.info("Received request to partially update venue with ID: {}", venueId);
        logger.info("Successfully patched venue with ID: {}", venueId);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }
    // Build Delete Venue REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable("id") Integer venueId) {
        logger.info("Received request to delete venue with ID: {}", venueId);
        venueService.deleteVenue(venueId);
        logger.info("Venue with ID {} has been deleted successfully", venueId);
        return new ResponseEntity<>("Venue deleted successfully!", HttpStatus.OK);
    }
}
