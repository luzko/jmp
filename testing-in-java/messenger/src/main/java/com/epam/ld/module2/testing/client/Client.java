package com.epam.ld.module2.testing.client;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Client.
 */
public class Client {
    private String addresses;
    private Map<String, String> parameters;

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

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
