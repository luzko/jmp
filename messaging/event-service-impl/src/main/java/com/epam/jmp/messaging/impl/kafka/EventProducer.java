package com.epam.jmp.messaging.impl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.epam.jmp.messaging.api.EventMessaging;
import com.epam.jmp.messaging.model.Event;

@Component
@Profile("kafka")
public class EventProducer implements EventMessaging {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void createEvent(Event event) {
        kafkaTemplate.send("create-event-notification", "Creating event" + event.toString());
    }

    @Override
    public void updateEvent(Event event) {
        kafkaTemplate.send("create-event-notification", "Updating event" + event.toString());
    }

    @Override
    public void deleteEvent(Long id) {
        kafkaTemplate.send("create-event-notification", "Removing event with id: " + id);
    }
}
