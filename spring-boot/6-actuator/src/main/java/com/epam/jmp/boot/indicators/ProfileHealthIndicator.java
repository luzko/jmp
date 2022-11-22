package com.epam.jmp.boot.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileHealthIndicator implements HealthIndicator {
    @Autowired
    private Environment environment;

    @Override
    public Health health() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            return Health.up().withDetail("profile", activeProfiles).build();
        }
        return Health.down().withDetail("profile", "no active profile").build();
    }
}
