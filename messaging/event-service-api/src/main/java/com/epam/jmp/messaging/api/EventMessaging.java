package com.epam.jmp.messaging.api;

import com.epam.jmp.messaging.model.Event;

public interface EventMessaging {

    void createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long id);
}
