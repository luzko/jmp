package com.epam.jmp.boot.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.epam.jmp.boot.repository.PersonRepository;

@Component
public class EmptyTableHealthIndicator implements HealthIndicator {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Health health() {
        if (!personRepository.findAll().isEmpty()) {
            return Health.up().withDetail("table", "NOT EMPTY").build();
        }
        return Health.down().withDetail("table", "EMPTY").build();
    }
}
