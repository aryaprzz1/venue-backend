package com.venue.service.impl;

import com.venue.dto.EventDto;
import com.venue.entity.Event;
import com.venue.entity.Venue;
import com.venue.exception.ResourceNotFoundException;
import com.venue.mapper.EventMapper;
import com.venue.repository.EventRepository;
import com.venue.service.EventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.venue.mapper.AddressMapper.venueRepository;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private EventRepository eventRepository;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        logger.info("Creating event: {}", eventDto);
        Event event = EventMapper.mapToEvent(eventDto);
        Event savedEvent = eventRepository.save(event);
        logger.info("Event created successfully with ID: {}", savedEvent.getEventId());
        return EventMapper.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto getEventById(Integer eventId) {
        logger.info("Fetching event with ID: {}", eventId);
        Event event = eventRepository.findById(Long.valueOf(eventId))
                .orElseThrow(() -> {
                    logger.error("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException("Event does not exist with the given id: " + eventId);
                });
        logger.info("Event found: {}", event);
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public List<EventDto> getAllEvent() {
        logger.info("Fetching all events");
        List<Event> events = eventRepository.findAll();
        logger.info("Total events found: {}", events.size());
        return events.stream().map(EventMapper::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(Integer eventId, EventDto updateEvent) {
        logger.info("Updating event with ID: {}", eventId);
        Event existingEvent = eventRepository.findById(Long.valueOf(eventId))
                .orElseThrow(() -> {
                    logger.error("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException("Event does not exist with the given id: " + eventId);
                });

        if (updateEvent.getOrganizerId() != null) {
            existingEvent.setOrganizerId(updateEvent.getOrganizerId());
            logger.info("Updated organizer ID to {}", updateEvent.getOrganizerId());
        }
        if (updateEvent.getVenueId() != null) {
            Venue venue = venueRepository.findById(Long.valueOf(updateEvent.getVenueId()))
                    .orElseThrow(() -> {
                        logger.error("Venue not found with ID: {}", updateEvent.getVenueId());
                        return new ResourceNotFoundException("Venue does not exist with the given id: " + updateEvent.getVenueId());
                    });
            existingEvent.setVenue(venue);
            logger.info("Updated venue ID to {}", updateEvent.getVenueId());
        }
        if (updateEvent.getBookingDate() != null) {
            existingEvent.setBookingDate(updateEvent.getBookingDate());
            logger.info("Updated booking date to {}", updateEvent.getBookingDate());
        }

        if (updateEvent.getEventDateTime() != null) {
            existingEvent.setEventDateTime(updateEvent.getEventDateTime());
            logger.info("Updated event date and time to {}", updateEvent.getEventDateTime());
        }

        Event savedEvent = eventRepository.save(existingEvent);
        logger.info("Event updated successfully with ID: {}", savedEvent.getEventId());
        return EventMapper.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto patchEvent(Integer eventId, EventDto updateEvent) {
        logger.info("Patching event with ID: {}", eventId);
        Event existingEvent = eventRepository.findById(Long.valueOf(eventId))
                .orElseThrow(() -> {
                    logger.error("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException("Event does not exist with the given id: " + eventId);
                });

        if (updateEvent.getOrganizerId() != null) {
            existingEvent.setOrganizerId(updateEvent.getOrganizerId());
            logger.info("Patched organizer ID to {}", updateEvent.getOrganizerId());
        }
        if (updateEvent.getVenueId() != null) {
            existingEvent.setVenueId(updateEvent.getVenueId());
            logger.info("Patched venue ID to {}", updateEvent.getVenueId());
        }
        if (updateEvent.getBookingDate() != null) {
            existingEvent.setBookingDate(updateEvent.getBookingDate());
            logger.info("Patched booking date to {}", updateEvent.getBookingDate());
        }
        if (updateEvent.getEventDateTime() != null) {
            existingEvent.setEventDateTime(updateEvent.getEventDateTime());
            logger.info("Patched event date and time to {}", updateEvent.getEventDateTime());
        }

        Event savedEvent = eventRepository.save(existingEvent);
        logger.info("Event patched successfully with ID: {}", savedEvent.getEventId());
        return EventMapper.mapToEventDto(savedEvent);
    }

    @Override
    public void deleteEvent(Integer eventId) {
        logger.info("Deleting event with ID: {}", eventId);
        Event existingEvent = eventRepository.findById(Long.valueOf(eventId))
                .orElseThrow(() -> {
                    logger.error("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException("Event does not exist with the given id: " + eventId);
                });
        eventRepository.delete(existingEvent);
        logger.info("Event with ID {} has been deleted successfully", eventId);
    }

}
