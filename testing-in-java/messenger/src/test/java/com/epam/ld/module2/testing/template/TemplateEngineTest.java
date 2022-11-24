package com.epam.ld.module2.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.epam.ld.module2.testing.Client;

public class TemplateEngineTest {
    @Test
    public void generateMessageFromPlainText() {
        String expected = "test";
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(new Template(), new Client());
        assertEquals(expected, actual);
    }
}
