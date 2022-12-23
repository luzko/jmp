package com.epam.jmp.messaging.impl.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("rabbitmq")
@Slf4j
public class EventConsumer {

    @RabbitListener(queues = "create-event-request")
    public void createEvent(final Message event) {
        log.info("Create Event message from RabbitMQ: {}", new String(event.getBody()));
    }

    @RabbitListener(queues = "update-event-request")
    public void updateEvent(final Message event) {
        log.info("Update Event message from RabbitMQ: {}", new String(event.getBody()));
    }

    @RabbitListener(queues = "delete-event-request")
    public void deleteEvent(final Message message) {
        log.info("Delete Event message from RabbitMQ: {}", new String(message.getBody()));
    }
}
