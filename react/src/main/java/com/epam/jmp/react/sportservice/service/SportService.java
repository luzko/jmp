package com.epam.jmp.react.sportservice.service;

import com.epam.jmp.react.sportservice.model.Sport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SportService {

    Mono<Sport> getById(Long sportId);

    Mono<Sport> create(String sportName);

    Flux<Sport> searchByName(String sportName);

    Mono<Void> deleteAll();
}
