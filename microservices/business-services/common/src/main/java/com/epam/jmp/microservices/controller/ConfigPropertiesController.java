package com.epam.jmp.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestController
public class ConfigPropertiesController {

    private final DynamicStringProperty propertyOneWithDynamic =
            DynamicPropertyFactory.getInstance().getStringProperty("com.epam.jmp.microservices.property", "not found!");

    @GetMapping("/properties-from-dynamic/")
    public String getPropertyValue() {
        return propertyOneWithDynamic.getName() + ":" + propertyOneWithDynamic.get();
    }
}
