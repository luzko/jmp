package com.epam.jmp.spring.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import com.epam.jmp.spring.dao.EventDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.exception.NotFoundException;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.to.EventTo;
import com.epam.jmp.spring.service.impl.EventServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void getEventByIdTest() {
        long eventId = 1;
        Event expected = new EventTo(eventId, "title", new Date());
        when(eventDao.getById(eventId)).thenReturn(expected);
        Event actual = eventService.getEventById(eventId);
        assertEquals(expected, actual);
    }

    @Test
    void getEventByIdNotFoundExceptionTest() {
        long eventId = 1;
        when(eventDao.getById(1)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> eventService.getEventById(eventId));
    }

    @Test
    void getEventsByTitleTest() {
        String title = "title";
        Event expected = new EventTo(1, title, new Date());
        when(eventDao.getEventsByTitle(title, 1, 1)).thenReturn(singletonList(expected));
        List<Event> actualEvents = eventService.getEventsByTitle(title, 1, 1);
        assertEquals(1, actualEvents.size());
        assertEquals(expected, actualEvents.get(0));
    }

    @Test
    void getEventsByTitleIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> eventService.getEventsByTitle("title", -1, -1));
    }

    @Test
    void getEventsForDayTest() {
        Date date = new Date();
        Event expected = new EventTo(1, "title", date);
        when(eventDao.getEventsForDay(date, 1, 1)).thenReturn(singletonList(expected));
        List<Event> actualEvents = eventService.getEventsForDay(date, 1, 1);
        assertEquals(1, actualEvents.size());
        assertEquals(expected, actualEvents.get(0));
    }

    @Test
    void getEventsForDayTestIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> eventService.getEventsForDay(new Date(), -1, -1));
    }

    @Test
    void createEventTest() {
        Event expected = new EventTo(1, "title", new Date());
        when(eventDao.create(expected)).thenReturn(expected);
        Event actual = eventService.createEvent(expected);
        assertEquals(expected, actual);
    }

    @Test
    void createEventAlreadyExistsExceptionTest() {
        when(eventDao.create(any())).thenThrow(AlreadyExistsException.class);
        assertThrows(AlreadyExistsException.class, () -> eventService.createEvent(any()));
    }

    @Test
    void updateEventTest() {
        Event expected = new EventTo(1, "title", new Date());
        when(eventDao.update(expected)).thenReturn(expected);
        Event actual = eventService.updateEvent(expected);
        assertEquals(expected, actual);
    }

    @Test
    void updateEventAlreadyExistsExceptionTest() {
        when(eventDao.update(any())).thenThrow(AlreadyExistsException.class);
        assertThrows(AlreadyExistsException.class, () -> eventService.updateEvent(any()));
    }

    @Test
    void deleteEventByIdTrueTest() {
        when(eventDao.deleteById(1)).thenReturn(true);
        assertTrue(eventService.deleteEventById(1));
    }

    @Test
    void deleteEventByIdFalseTest() {
        when(eventDao.deleteById(1)).thenReturn(false);
        assertFalse(eventService.deleteEventById(1));
    }
}
