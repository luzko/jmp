package com.epam.jmp.spring.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import com.epam.jmp.spring.dao.TicketDao;
import com.epam.jmp.spring.exception.AlreadyExistsException;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.to.EventTo;
import com.epam.jmp.spring.model.to.TicketTo;
import com.epam.jmp.spring.model.to.UserTo;
import com.epam.jmp.spring.service.impl.TicketServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void bookTicketTest() {
        Ticket expected = new TicketTo(1, 1, 1, Ticket.Category.STANDARD, 111);
        when(ticketDao.bookTicket(1, 1, 111, Ticket.Category.STANDARD)).thenReturn(expected);
        Ticket actual = ticketService.bookTicket(1, 1, 111, Ticket.Category.STANDARD);
        assertEquals(expected, actual);
    }

    @Test
    void bookTicketAlreadyExistsExceptionTest() {
        when(ticketDao.bookTicket(1, 1, 111, Ticket.Category.STANDARD)).thenThrow(AlreadyExistsException.class);
        assertThrows(AlreadyExistsException.class, () -> ticketService.bookTicket(1, 1, 111, Ticket.Category.STANDARD));
    }

    @Test
    void getBookedTicketsByUserTest() {
        Ticket expected = new TicketTo(1, 1, 1, Ticket.Category.STANDARD, 111);
        UserTo user = new UserTo(1, "name", "name@gmail.com");
        when(ticketDao.getBookedTicketsByUser(user, 1, 1)).thenReturn(singletonList(expected));
        List<Ticket> actualTickets = ticketService.getBookedTicketsByUser(user, 1, 1);
        assertEquals(1, actualTickets.size());
        assertEquals(expected, actualTickets.get(0));
    }

    @Test
    void getBookedTicketsByUserIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.getBookedTicketsByUser(new UserTo(), -1, -1));
    }

    @Test
    void getBookedTicketsByEventTest() {
        Ticket expected = new TicketTo(1, 1, 1, Ticket.Category.STANDARD, 111);
        EventTo event = new EventTo(1, "event", new Date());
        when(ticketDao.getBookedTicketsByEvent(event, 1, 1)).thenReturn(singletonList(expected));
        List<Ticket> actualTickets = ticketService.getBookedTicketsByEvent(event, 1, 1);
        assertEquals(1, actualTickets.size());
        assertEquals(expected, actualTickets.get(0));
    }

    @Test
    void getBookedTicketsByEventIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class,
                () -> ticketService.getBookedTicketsByEvent(new EventTo(), -1, -1));
    }

    @Test
    void cancelTicketByIdTrueTest() {
        when(ticketDao.cancelTicketById(1)).thenReturn(true);
        assertTrue(ticketService.cancelTicketById(1));
    }

    @Test
    void cancelTicketByIdFalseTest() {
        when(ticketDao.cancelTicketById(1)).thenReturn(false);
        assertFalse(ticketService.cancelTicketById(1));
    }
}
