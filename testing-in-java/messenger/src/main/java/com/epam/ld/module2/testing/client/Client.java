package com.epam.ld.module2.testing.client;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Client.
 */
public class Client {
    private final String addresses;
    private final Map<String, String> parameters;

    public Client(String addresses) {
        this.addresses = addresses;
        this.parameters = new HashMap<>();
    }

    public Client(String addresses, Map<String, String> parameters) {
        this.addresses = addresses;
        this.parameters = parameters;
    }

    public String getAddresses() {
        return addresses;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
