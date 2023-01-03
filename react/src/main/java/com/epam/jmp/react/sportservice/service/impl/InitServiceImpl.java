package com.epam.jmp.react.sportservice.service.impl;

import java.util.Optional;

import org.reactivestreams.Subscriber;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.epam.jmp.react.sportservice.model.Sport;
import com.epam.jmp.react.sportservice.repository.SportRepository;
import com.epam.jmp.react.sportservice.service.InitService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitServiceImpl implements InitService {

    public static final String SPORT_URL = "https://sports.api.decathlon.com/sports";

    private final SportRepository sportRepository;

    private final Subscriber<Sport> sportSubscriber;

    private final WebClient webClient = WebClient.builder()
            .codecs(codecConfigurer -> codecConfigurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 64))
            .build();

    @Override
    public Flux<Sport> initialize() {
        return getSportFlux(new Gson())
                .flatMap(sportRepository::save)
                .doOnNext(sport -> log.info("Sport save"))
                .doOnError(ex -> log.error("Can't save sport ", ex));
    }

    @Override
    public Mono<Void> initializeWithBackpressure() {
        return Mono.fromRunnable(() -> getSportFlux(new Gson()).subscribe(sportSubscriber));
    }

    private Flux<Sport> getSportFlux(Gson gson) {
        return webClient.get()
                .uri(SPORT_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .flatMapMany(result -> Flux.fromIterable(gson.fromJson(result, JsonObject.class)
                        .get("data").getAsJsonArray())).map(JsonElement::getAsJsonObject)
                .map(sport -> Optional.ofNullable(sport.get("attributes"))
                        .map(JsonElement::getAsJsonObject)
                        .map(this::createSportModel))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Sport createSportModel(JsonObject json) {
        JsonElement name = json.get("name");
        if (name.isJsonNull()) {
            return null;
        }
        Sport sport = new Sport();
        sport.setName(name.getAsString());
        return sport;
    }
}
