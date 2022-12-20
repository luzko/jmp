package com.epam.jmp.messaging.impl.activemq;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("active")
@Slf4j
@EnableJms
public class EventConsumer {

    @JmsListener(destination = "create-event-request")
    public void createEvent(final String event) {
        log.info("Got Create Event message from ActiveMQ: {}", event);
    }

    @JmsListener(destination = "update-event-request")
    public void updateEvent(final String event) {
        log.info("Got Update Event message from ActiveMQ: {}", event);
    }

    @JmsListener(destination = "delete-event-request")
    public void deleteEvent(final Long id) {
        log.info("Got Delete Event message from ActiveMQ with id: {}", id);
    }
}
