package com.epam.jmp.react.sportservice.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;

import com.epam.jmp.react.sportservice.model.Sport;
import com.epam.jmp.react.sportservice.repository.SportRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportSubscriber implements Subscriber<Sport> {

    private static final int REQUEST_LIMIT = 20;

    private final SportRepository sportRepository;

    private Subscription subscription;

    private int onNextElementIndex;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(REQUEST_LIMIT);
    }

    @Override
    public void onNext(Sport sport) {
        sportRepository.save(sport).subscribe();
        onNextElementIndex++;
        if (onNextElementIndex % REQUEST_LIMIT == 0) {
            subscription.request(REQUEST_LIMIT);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error during loading", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Init is completed");
    }
}
