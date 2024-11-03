package com.venue.controller;

import com.venue.dto.EventDto;
import com.venue.service.EventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private EventService eventService;

    // Build Add Event REST API
    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        logger.info("Received request to create a new event: {}", eventDto);
        EventDto savedEvent = eventService.createEvent(eventDto);
        logger.info("Successfully created a new event with ID: {}", savedEvent.getEventId());
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Build Get Event by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("id") Integer eventId) {
        logger.info("Received request to retrieve event with ID: {}", eventId);
        EventDto eventDto = eventService.getEventById(eventId);
        if (eventDto != null) {
            logger.info("Successfully retrieved event with ID: {}", eventId);
        } else {
            logger.warn("No event found with ID: {}", eventId);
        }
        return ResponseEntity.ok(eventDto);
    }

    // Build Get All Events REST API
    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        logger.info("Received request to retrieve all events");
        List<EventDto> events = eventService.getAllEvent();
        if (events.isEmpty()) {
            logger.warn("No events found");
        } else {
            logger.info("Successfully retrieved {} events", events.size());
        }
        return ResponseEntity.ok(events);
    }


    // Build Update Event REST API (PUT)
    @PutMapping("{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("id") Integer eventId, @RequestBody EventDto updateEvent) {
        logger.info("Received request to update event with ID: {}", eventId);
        EventDto eventDto = eventService.updateEvent(eventId, updateEvent);
        logger.info("Successfully updated event with ID: {}", eventId);
        return ResponseEntity.ok(eventDto);
    }

    // Build Partial Update Event (PATCH)
    @PatchMapping("{id}")
    public ResponseEntity<EventDto> patchEvent(@PathVariable("id") Integer eventId, @RequestBody EventDto updateEvent) {
        logger.info("Received request to patch event with ID: {}", eventId);
        EventDto patchedEvent = eventService.patchEvent(eventId, updateEvent);
        logger.info("Successfully patched event with ID: {}", eventId);
        return ResponseEntity.ok(patchedEvent);
    }

    // Build Delete Event REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Integer eventId) {
        logger.info("Received request to delete event with ID: {}", eventId);
        eventService.deleteEvent(eventId);
        logger.info("Event with ID {} has been deleted successfully", eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
