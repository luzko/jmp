package com.epam.jmp.messaging.impl.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.epam.jmp.messaging.api.EventMessaging;
import com.epam.jmp.messaging.model.Event;

@Component
@Profile("rabbitmq")
public class EventProducer implements EventMessaging {

    @Autowired
    RabbitTemplate template;

    @Override
    public void createEvent(Event event) {
        template.convertAndSend(RabbitMqConfig.CREATE_EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY,
                "CREATING EVENT: " + event.toString());
    }

    @Override
    public void updateEvent(Event event) {
        template.convertAndSend(RabbitMqConfig.UPDATE_EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY,
                "UPDATING EVENT: " + event.toString());
    }

    @Override
    public void deleteEvent(Long id) {
        template.convertAndSend(RabbitMqConfig.DELETE_EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY,
                "REMOVING EVENT WITH ID: " + id);
    }
}
