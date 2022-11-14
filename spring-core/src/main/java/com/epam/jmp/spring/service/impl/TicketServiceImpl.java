package com.epam.jmp.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.jmp.spring.dao.TicketDao;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.service.TicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketDao.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new IllegalArgumentException("invalid page size or pageNum");
        }
        return ticketDao.getBookedTicketsByUser(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new IllegalArgumentException("invalid page size or pageNum");
        }
        return ticketDao.getBookedTicketsByEvent(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicketById(long ticketId) {
        return ticketDao.cancelTicketById(ticketId);
    }
}
