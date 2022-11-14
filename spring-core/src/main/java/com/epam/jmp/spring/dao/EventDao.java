package com.epam.jmp.spring.dao;

import java.util.Date;
import java.util.List;

import com.epam.jmp.spring.model.Event;

public interface EventDao extends BaseDao<Event>, CrudDao<Event> {
    @Override
    Event getById(long id);

    @Override
    Event create(Event obj);

    @Override
    Event update(Event obj);

    @Override
    boolean deleteById(long id);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
}
