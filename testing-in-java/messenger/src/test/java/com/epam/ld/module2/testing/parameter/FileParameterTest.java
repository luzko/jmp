package com.epam.ld.module2.testing.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileParameterTest {
    @Test
    public void readTemplateFromFile(@TempDir Path dir) throws IOException {
        Path text = dir.resolve("text");
        Path parameters = dir.resolve("parameters");
        List<String> textLines = Arrays.asList("Dear #{user}", "the #{date} is your day!");
        List<String> parametersLines = Arrays.asList("user=Dzmitry", "date=2022-11-11");
        Files.write(text, textLines);
        Files.write(parameters, parametersLines);
        Parameter param = new FileParameter(text, parameters);
        param.read();
        String expected = textLines.stream()
                .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));
        String actual = param.getTemplate();
        assertEquals(expected, actual);
    }

    @Test
    public void readParameterFromFile(@TempDir Path dir) throws IOException {
        Path text = dir.resolve("text");
        Path parameters = dir.resolve("parameters");
        List<String> textLines = Arrays.asList("Dear #{user}", "the #{date} is your day!");
        List<String> parametersLines = Arrays.asList("user=Dzmitry", "date=2022-11-11");
        Files.write(text, textLines);
        Files.write(parameters, parametersLines);
        Parameter param = new FileParameter(text, parameters);
        param.read();
        Map<String, String> expected = new HashMap<>();
        expected.put("user", "Dzmitry");
        expected.put("date", "2022-11-11");
        Map<String, String> actual = param.getParameters();
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.entrySet().stream()
                .allMatch(p -> p.getValue().equals(actual.get(p.getKey()))));
    }
}
