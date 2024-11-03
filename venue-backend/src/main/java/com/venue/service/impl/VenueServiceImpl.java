package com.venue.service.impl;

import com.venue.dto.VenueDto;
import com.venue.entity.Venue;
import com.venue.exception.ResourceNotFoundException;
import com.venue.mapper.VenueMapper;
import com.venue.repository.VenueRepository;
import com.venue.service.VenueService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
    private VenueRepository venueRepository;

    @Override
    public VenueDto createVenue(VenueDto venueDto) {
        logger.info("Creating a new venue: {}", venueDto);
        Venue venue = VenueMapper.mapToVenue(venueDto);
        Venue savedVenue = venueRepository.save(venue);
        logger.info("Venue created successfully with ID: {}", savedVenue.getVenueId());
        return VenueMapper.mapToVenueDto(savedVenue);
    }

    @Override
    public VenueDto getVenueById(Integer venueId) {
        logger.info("Attempting to find venue with ID: {}", venueId);
        Venue venue = venueRepository.findById(Long.valueOf(venueId))
                .orElseThrow(() -> {
                    logger.error("Venue with ID: {} not found", venueId);
                    return new ResourceNotFoundException("Venue not found");
                });
        logger.info("Venue with ID: {} found", venueId);
        return VenueMapper.mapToVenueDto(venue);
    }

    @Override
    public List<VenueDto> getAllVenue() {
        logger.info("Retrieving all venues");
        List<Venue> venues = venueRepository.findAll();
        List<VenueDto> venueDtos = venues.stream()
                .sorted(Comparator.comparing(Venue::getVenueId)) // Sort by venueId
                .map(VenueMapper::mapToVenueDto)
                .collect(Collectors.toList());
        logger.info("Retrieved {} venues", venueDtos.size());
        return venueDtos;
    }

    @Override
    public VenueDto putVenue(Integer venueId, VenueDto updatedVenue) {
        logger.info("Attempting to update venue with ID: {}", venueId);
        Venue existingVenue = venueRepository.findById(Long.valueOf(venueId))
                .orElseThrow(() -> new ResourceNotFoundException("Venue does not exist with given id: " + venueId));

        // Update the venue with the provided details
        existingVenue.setVenueName(updatedVenue.getVenueName());
        existingVenue.setCapacity(updatedVenue.getCapacity());
        existingVenue.setStatus(updatedVenue.getStatus());
        existingVenue.setPhoneNo(updatedVenue.getPhoneNo());

        // Save the updated venue
        Venue updatedVenueEntity = venueRepository.save(existingVenue);
        logger.info("Venue with ID: {} updated successfully", venueId);
        return VenueMapper.mapToVenueDto(updatedVenueEntity);
    }

    @Override
    public VenueDto updateVenue(Integer venueId, VenueDto updatedVenue) {
        logger.info("Attempting to update venue with ID: {}", venueId);
        Venue venue = venueRepository.findById(Long.valueOf(venueId))
                .orElseThrow(() -> new ResourceNotFoundException("Venue does not exist with given id: " + venueId));

        // Update the venue with the provided details
        venue.setVenueId(updatedVenue.getVenueId());
        venue.setVenueName(updatedVenue.getVenueName());
        venue.setCapacity(updatedVenue.getCapacity());
        venue.setStatus(updatedVenue.getStatus());
        venue.setPhoneNo(updatedVenue.getPhoneNo());

        // Save the updated venue
        Venue updatedVenueEntity = venueRepository.save(venue);
        logger.info("Venue with ID: {} updated successfully", venueId);
        return VenueMapper.mapToVenueDto(updatedVenueEntity);
    }

    @Override
    public void deleteVenue(Integer venueId) {
        logger.info("Attempting to delete venue with ID: {}", venueId);
        Venue venue = venueRepository.findById(Long.valueOf(venueId))
                .orElseThrow(() -> {
                    logger.error("Venue with ID: {} not found, cannot delete", venueId);
                    return new ResourceNotFoundException("Venue not found");
                });

        venueRepository.delete(venue);
        logger.info("Venue with ID: {} deleted successfully", venueId);
    }

    @Override
    public VenueDto patchVenue(Integer venueId, VenueDto updateVenue) {
        logger.info("Attempting to patch venue with ID: {}", venueId);
        Venue existingVenue = venueRepository.findById(Long.valueOf(venueId))
                .orElseThrow(() -> new ResourceNotFoundException("Venue does not exist with given id: " + venueId));

        // Update only the fields that are not null in the updateVenue DTO
        if (updateVenue.getVenueName() != null) {
            existingVenue.setVenueName(updateVenue.getVenueName());
        }
        if (updateVenue.getCapacity() != null) {
            existingVenue.setCapacity(updateVenue.getCapacity());
        }
        if (updateVenue.getStatus() != null) {
            existingVenue.setStatus(updateVenue.getStatus());
        }
        if (updateVenue.getPhoneNo() != null) {
            existingVenue.setPhoneNo(updateVenue.getPhoneNo());
        }

        // Save the partially updated venue
        Venue updatedVenueEntity = venueRepository.save(existingVenue);
        logger.info("Venue with ID: {} patched successfully", venueId);
        return VenueMapper.mapToVenueDto(updatedVenueEntity);
    }
}
