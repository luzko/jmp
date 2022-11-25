package com.epam.ld.module2.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.epam.ld.module2.testing.Client;

public class TemplateEngineTest {
    @Test
    public void generateMessageTextTest() {
        String expected = "Test";
        String address = "test1@gmail.com";
        Template template = new Template(expected);
        Client client = new Client(address);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageTextParameterTest() {
        String text = "Dear #{user}";
        String expected = "Dear user@test.com";
        String address = "user1@gmail.com";
        String user = "user@test.com";
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        Template template = new Template(text);
        Client client = new Client(address, params);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageTextParametersTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day!";
        String address = "user1@gmail.com";
        String user = "Dzmitry";
        String date = "2022-11-11";
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("date", date);
        Template template = new Template(text);
        Client client = new Client(address, params);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageWithoutParameterTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String address = "user1@example.com";
        String user = "Dzmitry";
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        Template template = new Template(text);
        Client client = new Client(address, params);
        TemplateEngine templateEngine = new TemplateEngine();
        assertThrows(InvalidDataException.class, () -> templateEngine.generateMessage(template, client));
    }
}
