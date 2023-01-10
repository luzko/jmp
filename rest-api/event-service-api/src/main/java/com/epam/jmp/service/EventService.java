package com.epam.jmp.service;

import java.util.List;

import com.epam.jmp.model.Event;

public interface EventService {

    void createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long eventId);

    Event getEvent(Long eventId);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);
}
