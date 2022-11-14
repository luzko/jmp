package com.epam.jmp.spring.dao.impl;

import static java.util.Optional.ofNullable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.epam.jmp.spring.dao.EventDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.to.EventTo;
import com.epam.jmp.spring.storage.InMemoryStorage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventDaoImpl implements EventDao {
    private final InMemoryStorage inMemoryStorage;

    @Override
    public Event getById(long id) {
        return getData().stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event create(Event event) {
        if (ofNullable(getById(event.getId())).isEmpty()) {
            getData().add(new EventTo(event.getId(), event.getTitle(), event.getDate()));
            return event;
        }
        throw new AlreadyExistsException("Event already exists");
    }

    @Override
    public Event update(Event event) {
        ofNullable(getById(event.getId()))
                .ifPresentOrElse(eventTo -> {
                    eventTo.setTitle(event.getTitle());
                    eventTo.setDate(event.getDate());
                }, () -> {
                    throw new NotFoundException("Event doesn't exist");
                });
        return event;
    }

    @Override
    public boolean deleteById(long id) {
        return getData().removeIf(event -> event.getId() == id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> events = getData().stream()
                .filter(event -> event.getTitle().equals(title))
                .collect(Collectors.toList());
        return paginate(events, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> events = getData().stream()
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
        return paginate(events, pageSize, pageSize);
    }

    private List<EventTo> getData() {
        return (List<EventTo>) inMemoryStorage.getData(EventTo.class);
    }
}
