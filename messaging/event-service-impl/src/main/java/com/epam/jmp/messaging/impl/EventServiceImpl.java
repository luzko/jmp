package com.epam.jmp.messaging.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.jmp.messaging.api.EventMessaging;
import com.epam.jmp.messaging.api.EventService;
import com.epam.jmp.messaging.dto.EventDto;
import com.epam.jmp.messaging.exception.NotFoundException;
import com.epam.jmp.messaging.model.Event;
import com.epam.jmp.messaging.repository.EventRepository;

public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EventRepository repository;

    @Autowired(required = false)
    private EventMessaging messaging;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event create = repository.save(mapToEntity(eventDto));
        messaging.createEvent(create);
        return mapToDto(create);
    }

    @Override
    public EventDto updateEvent(Long id, EventDto eventDto) {
        Event event = repository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));

        event.setTitle(eventDto.getTitle());
        event.setPlace(eventDto.getPlace());
        event.setSpeaker(eventDto.getSpeaker());
        event.setEventType(eventDto.getEventType());
        event.setDateTime(eventDto.getDateTime());

        Event update = repository.save(event);
        messaging.updateEvent(update);
        return mapToDto(update);
    }

    @Override
    public EventDto getEvent(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        return mapToDto(event);
    }

    @Override
    public EventDto deleteEvent(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        messaging.deleteEvent(id);
        repository.delete(event);
        return mapToDto(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private Event mapToEntity(EventDto eventDto) {
        return mapper.map(eventDto, Event.class);
    }

    private EventDto mapToDto(Event event) {
        return mapper.map(event, EventDto.class);
    }
}
