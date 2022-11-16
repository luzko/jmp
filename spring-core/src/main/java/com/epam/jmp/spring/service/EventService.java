package com.epam.jmp.spring.service;

import java.util.Date;
import java.util.List;

import com.epam.jmp.spring.model.Event;

public interface EventService {
    Event getEventById(long eventId);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    Event createEvent(Event event);

    Event updateEvent(Event event);

    boolean deleteEventById(long eventId);
}
