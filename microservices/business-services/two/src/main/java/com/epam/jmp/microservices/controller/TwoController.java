package com.epam.jmp.microservices.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import com.netflix.servo.monitor.Monitors;

@RestController
@RequestMapping("/two")
public class TwoController {

    @Value("${config-server.address}")
    private String configAddress;

    @Value("${service-one.address}")
    private String oneAddress;

    @Autowired
    private RestTemplate restTemplate;

    @Monitor(name = "counter", type = DataSourceType.COUNTER, description = "Number of requests")
    private final AtomicInteger updateCount = new AtomicInteger(0);

    public TwoController() {
        Monitors.registerObject("twoController", this);
    }

    @GetMapping
    public String print() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(configAddress + "/properties-from-dynamic", String.class, "service-two");
        return response.getBody() + updateCount.incrementAndGet();
    }

    @GetMapping("/service-one")
    public String printServiceOne() {
        ResponseEntity<String> response = restTemplate.getForEntity(oneAddress + "/one", String.class);
        return response.getBody();
    }
}
