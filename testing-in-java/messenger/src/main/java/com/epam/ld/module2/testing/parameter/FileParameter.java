package com.epam.ld.module2.testing.parameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.ld.module2.testing.exception.FileReadingException;

public class FileParameter implements Parameter {
    private final Path templatePath;
    private final Path parametersPath;
    private final Map<String, String> parameters;
    private String template;

    public FileParameter(Path templatePath, Path parametersPath) {
        this.templatePath = templatePath;
        this.parametersPath = parametersPath;
        this.parameters = new HashMap<>();
    }

    @Override
    public void read() {
        try {
            template = Files.readAllLines(templatePath).stream()
                    .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));
            List<String> parameterLines = Files.readAllLines(parametersPath);
            parameterLines.forEach(line -> {
                String[] parametersParts = line.split("=");
                parameters.put(parametersParts[0], parametersParts[1]);
            });
        } catch (IOException e) {
            throw new FileReadingException("Issue while reading from file.");
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
