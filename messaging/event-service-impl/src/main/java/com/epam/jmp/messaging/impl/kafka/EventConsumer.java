package com.epam.jmp.messaging.impl.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("kafka")
@Slf4j
public class EventConsumer {

    @KafkaListener(topics = "create-event-request", groupId = "new-group")
    public void createEvent(final String event) {
        log.info("Create Event message from Kafka: {}", event);
    }

    @KafkaListener(topics = "update-event-request", groupId = "new-group")
    public void updateEvent(final String event) {
        log.info("Update Event message from Kafka: {}", event);
    }

    @KafkaListener(topics = "delete-event-request", groupId = "new-group")
    public void deleteEvent(final Long id) {
        log.info("Delete Event message from Kafka: {}", id);
    }
}
