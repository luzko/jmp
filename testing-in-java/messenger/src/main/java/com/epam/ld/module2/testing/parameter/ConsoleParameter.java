package com.epam.ld.module2.testing.parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleParameter implements Parameter {
    private String template;
    private final Map<String, String> parameters;

    ConsoleParameter() {
        this.parameters = new HashMap<>();
    }

    @Override
    public void read() {
        System.out.println("Template:");
        Scanner scanner = new Scanner(System.in);
        template = scanner.nextLine();
        System.out.format("Parameters for template, to end '%s'%n", "-end-");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("-end-")) {
                break;
            }
            String[] part = line.split("=");
            parameters.put(part[0], part[1]);
        }
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String getTemplate() {
        return template;
    }
}
