package com.epam.ld.module2.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.InvalidDataException;

public class TemplateEngineTest {
    @Test
    public void generateMessageTextTest() {
        String expected = "Test";
        String addresses = "test1@gmail.com";
        Template template = new Template(expected);
        Client client = new Client(addresses);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageTextParameterTest() {
        String text = "Dear #{user}";
        String expected = "Dear user@test.com";
        String addresses = "user1@gmail.com";
        String user = "user@test.com";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        Template template = new Template(text);
        Client client = new Client(addresses, parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageTextParametersTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day!";
        String addresses = "user1@gmail.com";
        String user = "Dzmitry";
        String date = "2022-11-11";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        parameters.put("date", date);
        Template template = new Template(text);
        Client client = new Client(addresses, parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageWithoutParameterTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String addresses = "user1@gmail.com";
        String user = "Dzmitry";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        Template template = new Template(text);
        Client client = new Client(addresses, parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        assertThrows(InvalidDataException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void generateMessageWithExtraParameterTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day!";
        String addresses = "user1@gmail.com";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        parameters.put("extra1", "extra1");
        Template template = new Template(text);
        Client client = new Client(addresses, parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @Test
    public void generateMessageWithSameParameterTest() {
        String text = "Dear #{user}, the #{date} is your day! Your name is #{user}. Today is #{date}";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day! Your name is Dzmitry. Today is 2022-11-11";
        String addresses = "user1@gmail.com";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        Template template = new Template(text);
        Client client = new Client(addresses, parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }
}
