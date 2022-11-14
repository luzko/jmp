package com.epam.jmp.spring.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.epam.jmp.spring.dao.TicketDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.model.to.TicketTo;
import com.epam.jmp.spring.storage.InMemoryStorage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDao {
    private final InMemoryStorage inMemoryStorage;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        List<TicketTo> tickets = getData();
        tickets.stream()
                .filter(ticket -> ticket.getUserId() == userId
                        && ticket.getEventId() == eventId
                        && ticket.getCategory() == category
                        && ticket.getPlace() == place)
                .findFirst()
                .ifPresent(ticket -> {
                    throw new AlreadyExistsException("Ticket already exists");
                });

        long id = tickets.isEmpty() ? 1 : tickets.size() + 1;
        TicketTo ticket = new TicketTo(id, eventId, userId, category, place);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        List<Ticket> tickets = getData().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .collect(Collectors.toList());
        return paginate(tickets, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        List<Ticket> tickets = getData().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .collect(Collectors.toList());
        return paginate(tickets, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicketById(long ticketId) {
        return getData().removeIf(ticket -> ticket.getId() == ticketId);
    }

    private List<TicketTo> getData() {
        return (List<TicketTo>) inMemoryStorage.getData(TicketTo.class);
    }
}
