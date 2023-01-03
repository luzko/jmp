package com.epam.jmp.react.sportservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp.react.sportservice.model.Sport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, Long> {

    Mono<Sport> findSportByName(String name);

    Flux<Sport> findByNameContainingIgnoreCase(String name);
}
