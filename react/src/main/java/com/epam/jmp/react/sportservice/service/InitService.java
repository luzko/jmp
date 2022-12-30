package com.epam.jmp.react.sportservice.service;

import com.epam.jmp.react.sportservice.model.Sport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InitService {

    Flux<Sport> initialize();

    Mono<Void> initializeWithBackpressure();
}
