package com.epam.jmp.spring.model.to;

import com.epam.jmp.spring.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketTo implements Ticket {
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
