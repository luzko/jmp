package com.epam.ld.module2.testing.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ConsoleParameterTest {
    @Test
    public void readTemplateFromConsole() {
        String text = "Dear #{user}, the #{date} is your day!";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        Parameter parameter = new ConsoleParameter();
        parameter.read();
        String actual = parameter.getTemplate();
        assertEquals(text, actual);
    }

    @Test
    public void readParameterFromConsole() {
        String text = "Dear #{user}, the #{date} is your day!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        String parameter = parameters.keySet().stream()
                .map(key -> key + "=" + parameters.get(key))
                .collect(Collectors.joining(System.lineSeparator(), "",
                        System.lineSeparator() + "-end-" + System.lineSeparator()));
        System.setIn(new ByteArrayInputStream((text + System.lineSeparator() + parameter).getBytes()));
        Parameter param = new ConsoleParameter();
        param.read();
        Map<String, String> actual = param.getParameters();
        assertEquals(parameters.size(), actual.size());
        assertTrue(parameters.entrySet().stream()
                .allMatch(p -> p.getValue().equals(actual.get(p.getKey()))));
    }
}
