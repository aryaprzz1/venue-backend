package com.venue.service;

import com.venue.dto.EventDto;
// import com.venue.entity.Event;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);

    EventDto getEventById(Integer eventId);

    List<EventDto> getAllEvent();

    EventDto updateEvent(Integer eventId, EventDto updateEvent);

    EventDto patchEvent(Integer eventId, EventDto updateEvent);

    void deleteEvent(Integer eventId);


}
