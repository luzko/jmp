package com.epam.jmp.service;

import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.exception.NotFoundException;
import com.epam.jmp.model.Event;
import com.epam.jmp.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public void createEvent(final Event event) {
        repository.save(event);
    }

    @Override
    public void updateEvent(final Event event) {
        getEvent(event.getId());
        repository.save(event);
    }

    @Override
    public void deleteEvent(final Long eventId) {
        repository.deleteById(eventId);
    }

    @Override
    public Event getEvent(final Long eventId) {
        return repository.findById(eventId).orElseThrow(() -> new NotFoundException("Even not found"));
    }

    @Override
    public List<Event> getAllEvents() {
        return stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllEventsByTitle(final String title) {
        return repository.findAllByTitle(title);
    }
}
