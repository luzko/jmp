package com.epam.ld.module2.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.epam.ld.module2.testing.client.Client;

class TemplateEngineDynamicTest {
    @TestFactory
    Stream<DynamicTest> templateEngineHappyPath() {
        List<String> templates = new ArrayList<>();
        templates.add("#{user} #{date}");
        templates.add("#{car} #{model}");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "param");
        parameters.put("date", "param");
        parameters.put("car", "param");
        parameters.put("model", "param");

        return templates.stream()
                .map(template -> DynamicTest.dynamicTest("Template: " + template,
                                () -> {
                                    String message = new TemplateEngine().generateMessage(
                                            new Template(template), new Client("some@example.com", parameters));
                                    assertEquals("param param", message);
                                }
                        )
                );
    }
}
