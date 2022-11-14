package com.epam.jmp.spring.dao;

import java.util.List;

import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.User;

public interface TicketDao extends BaseDao<Ticket> {
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum);

    boolean cancelTicketById(long ticketId);
}
