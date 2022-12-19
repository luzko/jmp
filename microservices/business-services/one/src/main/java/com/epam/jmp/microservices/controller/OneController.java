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
@RequestMapping("/one")
public class OneController {

    @Value("${config-server.address}")
    private String configAddress;

    @Value("${service-two.address}")
    private String twoAddress;

    @Autowired
    private RestTemplate restTemplate;

    @Monitor(name = "counter", type = DataSourceType.COUNTER, description = "Number of requests")
    private final AtomicInteger updateCount = new AtomicInteger(0);

    public OneController() {
        Monitors.registerObject("one", this);
    }

    @GetMapping
    public String print() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(configAddress + "/properties-from-dynamic", String.class, "service-one");
        return response.getBody() + updateCount.incrementAndGet();
    }

    @GetMapping("/service-two")
    public String printServiceTwo() {
        ResponseEntity<String> response = restTemplate.getForEntity(twoAddress + "/two", String.class);
        return response.getBody();
    }
}
