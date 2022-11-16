package com.epam.jmp.spring.facade.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.jmp.spring.facade.BookingFacade;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.service.EventService;
import com.epam.jmp.spring.service.TicketService;
import com.epam.jmp.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    @Override
    public Event getEventById(long eventId) {
        log.info("Get event by id {}", eventId);
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("Get events by title {}", title);
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        log.info("Get events for day {}", day);
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        log.info("Create event {}", event);
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        log.info("Update event {}", event);
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        log.info("Delete event {}", eventId);
        return eventService.deleteEventById(eventId);
    }

    @Override
    public User getUserById(long userId) {
        log.info("Get user by id {}", userId);
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Get user by email {}", email);
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("Get users by name {}", name);
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        log.info("Create user {}", user);
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        log.info("Update user {}", user);
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        log.info("Delete user {}", userId);
        return userService.deleteUserById(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        log.info("Book ticket: userId {}, eventId {}, place {}", userId, eventId, place);
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("Get booked tickets {}", user);
        return ticketService.getBookedTicketsByUser(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info("Get booked tickets {}", event);
        return ticketService.getBookedTicketsByEvent(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        log.info("Cancel ticket {}", ticketId);
        return ticketService.cancelTicketById(ticketId);
    }
}
