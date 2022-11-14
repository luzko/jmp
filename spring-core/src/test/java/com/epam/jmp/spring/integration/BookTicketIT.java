package com.epam.jmp.spring.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.jmp.spring.facade.BookingFacade;
import com.epam.jmp.spring.model.Event;
import com.epam.jmp.spring.model.Ticket;
import com.epam.jmp.spring.model.User;
import com.epam.jmp.spring.model.to.EventTo;
import com.epam.jmp.spring.model.to.UserTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@TestPropertySource("classpath:application.properties")
public class BookTicketIT {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void cancelTicketScenarioForExistUser() {
        BookingFacade bookingFacade = applicationContext.getBean("bookingFacade", BookingFacade.class);

        User existUser = bookingFacade.getUserById(1L);
        assertNotNull(existUser);

        List<Ticket> existTickets = bookingFacade.getBookedTickets(existUser, 1, 1);
        assertFalse(existTickets.isEmpty());

        boolean isCancel = bookingFacade.cancelTicket(existTickets.get(0).getId());
        assertTrue(isCancel);
    }

    @Test
    void ticketScenarioForNewUser() {
        BookingFacade bookingFacade = applicationContext.getBean("bookingFacade", BookingFacade.class);

        User newUser = bookingFacade.createUser(new UserTo(4L, "test", "test@gmail.com"));
        Event newEvent = bookingFacade.createEvent(new EventTo(4L, "test", new Date()));
        Ticket newTicket = bookingFacade.bookTicket(newUser.getId(), newEvent.getId(), 1, Ticket.Category.STANDARD);

        List<Ticket> existTickets = bookingFacade.getBookedTickets(newUser, 1, 1);
        assertFalse(existTickets.isEmpty());
        assertEquals(existTickets.get(0).getUserId(), newUser.getId());
        assertEquals(existTickets.get(0).getEventId(), newEvent.getId());
        assertEquals(existTickets.get(0).getPlace(), newTicket.getPlace());
    }

    @Test
    void ticketScenarioForExistUser() {
        BookingFacade bookingFacade = applicationContext.getBean("bookingFacade", BookingFacade.class);

        User existUser = bookingFacade.getUserByEmail("user2@gmail.com");
        assertNotNull(existUser);

        List<Event> existEvents = bookingFacade.getEventsByTitle("Event 3", 1, 1);
        assertFalse(existEvents.isEmpty());

        bookingFacade.bookTicket(existUser.getId(), existEvents.get(0).getId(), 10, Ticket.Category.PREMIUM);
        List<Ticket> existTickets = bookingFacade.getBookedTickets(existUser, 10, 1);

        assertEquals(existTickets.size(), 2);
        assertEquals(existTickets.get(0).getEventId(), 2);
        assertEquals(existTickets.get(0).getUserId(), existUser.getId());
        assertEquals(existTickets.get(0).getPlace(), 102);
        assertEquals(existTickets.get(1).getEventId(), 3);
        assertEquals(existTickets.get(1).getUserId(), existUser.getId());
        assertEquals(existTickets.get(1).getPlace(), 10);
    }
}
