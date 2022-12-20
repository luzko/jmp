package com.epam.jmp.messaging.api;

import java.util.List;

import com.epam.jmp.messaging.dto.EventDto;

public interface EventService {

    EventDto createEvent(EventDto event);

    EventDto updateEvent(Long id, EventDto event);

    EventDto getEvent(Long id);

    EventDto deleteEvent(Long id);

    List<EventDto> getAllEvents();
}
