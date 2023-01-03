package com.epam.jmp.react.sportservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.epam.jmp.react.sportservice.rest.SportHandler;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class ReactConfig {

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    public RouterFunction<ServerResponse> sportRoute(SportHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/v1/sport/initialize"), serverRequest -> handler.initialize())
                .andRoute(RequestPredicates.POST("/api/v1/sport/{sportName}"), handler::createSport)
                .andRoute(RequestPredicates.GET("/api/v1/sport/{id}"), handler::getById)
                .andRoute(RequestPredicates.GET("/api/v1/sport"), handler::search)
                .andRoute(RequestPredicates.DELETE("/api/v1/sport"), serverRequest -> handler.deleteAll())
                .andRoute(RequestPredicates.POST("/api/v2/sport/initialize"),
                        serverRequest -> handler.initializeWithBackpressure());
    }
}
