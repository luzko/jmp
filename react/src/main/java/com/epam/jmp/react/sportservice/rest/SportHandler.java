package com.epam.jmp.react.sportservice.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.epam.jmp.react.sportservice.model.Sport;
import com.epam.jmp.react.sportservice.service.InitService;
import com.epam.jmp.react.sportservice.service.SportService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SportHandler {

    private final InitService initService;

    private final SportService sportService;

    public Mono<ServerResponse> initialize() {
        return ServerResponse
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(initService.initialize(), Sport.class));
    }

    public Mono<ServerResponse> createSport(ServerRequest serverRequest) {
        return sportService.create(serverRequest.pathVariable("sportName"))
                .flatMap(sport -> ServerResponse.status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sport))
                .onErrorResume(ex -> Mono.just(ex.getMessage())
                        .flatMap(error -> ServerResponse.badRequest()
                                .bodyValue(error)));
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return sportService.getById(Long.valueOf(serverRequest.pathVariable("id")))
                .flatMap(sport -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sport))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> search(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(sportService
                        .searchByName(serverRequest.queryParam("q").orElse("")), Sport.class));
    }

    public Mono<ServerResponse> initializeWithBackpressure() {
        return initService
                .initializeWithBackpressure()
                .then(ServerResponse.ok().bodyValue("Init with backpressure"));
    }

    public Mono<ServerResponse> deleteAll() {
        return sportService.deleteAll()
                .flatMap(s -> ServerResponse.noContent().build());
    }
}
