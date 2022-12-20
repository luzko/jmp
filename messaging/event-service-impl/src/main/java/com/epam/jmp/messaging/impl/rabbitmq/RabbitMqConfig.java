package com.epam.jmp.messaging.impl.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitMqConfig {
    public static final String QUEUE_NAME = "eventQueue";
    public static final String EXCHANGE_NAME = "eventExchange";
    public static final String CREATE_EXCHANGE_NAME = "create-event-notification";
    public static final String DELETE_EXCHANGE_NAME = "delete-event-notification";
    public static final String UPDATE_EXCHANGE_NAME = "update-event-notification";
    public static final String ROUTING_KEY = "eventRoutingKey";

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange createExchange() {
        return new TopicExchange(UPDATE_EXCHANGE_NAME);
    }

    @Bean
    Binding createBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connection) {
        SimpleMessageListenerContainer myContainer = new SimpleMessageListenerContainer();
        myContainer.setConnectionFactory(connection);
        return myContainer;
    }
}
