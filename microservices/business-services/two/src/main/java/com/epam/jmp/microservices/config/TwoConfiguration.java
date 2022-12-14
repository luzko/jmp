package com.epam.jmp.microservices.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.servo.publish.BasicMetricFilter;
import com.netflix.servo.publish.MonitorRegistryMetricPoller;
import com.netflix.servo.publish.PollRunnable;
import com.netflix.servo.publish.PollScheduler;
import com.netflix.servo.publish.graphite.GraphiteMetricObserver;

import brave.sampler.Sampler;

@Configuration
public class TwoConfiguration {

    @Value("${graphite.address}")
    private String address;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler alwaysSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @PostConstruct
    public void init() {
        GraphiteMetricObserver observer = new GraphiteMetricObserver("service-one", address);
        MonitorRegistryMetricPoller poller = new MonitorRegistryMetricPoller();
        BasicMetricFilter basicMetricFilter = new BasicMetricFilter(true);
        PollRunnable poll = new PollRunnable(poller, basicMetricFilter, observer);
        PollScheduler.getInstance().start();
        PollScheduler.getInstance().addPoller(poll, 1, SECONDS);
    }
}
