package com.epam.jmp.spring.service.impl;

import static java.util.Optional.ofNullable;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.jmp.spring.dao.EventDao;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.service.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDao eventDao;

    @Override
    public Event getEventById(long eventId) {
        return ofNullable(eventDao.getById(eventId))
                .orElseThrow(() -> new NotFoundException("Event doesn't exist"));
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new IllegalArgumentException("invalid page size or pageNum");
        }
        return eventDao.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new IllegalArgumentException("invalid page size or pageNum");
        }
        return eventDao.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventDao.create(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDao.update(event);
    }

    @Override
    public boolean deleteEventById(long eventId) {
        return eventDao.deleteById(eventId);
    }
}
