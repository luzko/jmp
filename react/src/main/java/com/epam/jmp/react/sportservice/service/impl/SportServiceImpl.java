package com.epam.jmp.react.sportservice.service.impl;

import org.springframework.stereotype.Service;

import com.epam.jmp.react.sportservice.model.Sport;
import com.epam.jmp.react.sportservice.repository.SportRepository;
import com.epam.jmp.react.sportservice.service.SportService;

import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;

    @Override
    public Mono<Sport> getById(Long sportId) {
        return sportRepository.findById(sportId);
    }

    @Override
    public Mono<Sport> create(String name) {
        return sportRepository.findSportByName(name)
                .flatMap(sport -> Mono.error(new R2dbcDataIntegrityViolationException("Sport already exists")))
                .switchIfEmpty(sportRepository.save(new Sport(name)))
                .cast(Sport.class);
    }

    @Override
    public Flux<Sport> searchByName(String name) {
        return sportRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Mono<Void> deleteAll() {
        return sportRepository.deleteAll();
    }
}
