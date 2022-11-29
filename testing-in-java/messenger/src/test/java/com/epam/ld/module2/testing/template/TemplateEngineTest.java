package com.epam.ld.module2.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.epam.ld.module2.testing.client.Client;
import com.epam.ld.module2.testing.exception.InvalidDataException;
import com.epam.ld.module2.testing.util.TemplateTest;

public class TemplateEngineTest {
    @TemplateTest
    public void generateMessageTextTest() {
        String expected = "Test";
        Template template = new Template(expected);
        Client client = new Client("test1@gmail.com");
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @TemplateTest
    public void generateMessageTextParameterTest() {
        String text = "Dear #{user}";
        String expected = "Dear user@test.com";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "user@test.com");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @TemplateTest
    public void generateMessageTextParametersTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @TemplateTest
    public void generateMessageWithoutParameterTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        assertThrows(InvalidDataException.class, () -> templateEngine.generateMessage(template, client));
    }

    @TemplateTest
    public void generateMessageWithExtraParameterTest() {
        String text = "Dear #{user}, the #{date} is your day!";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day!";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        parameters.put("extra1", "extra1");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @TemplateTest
    public void generateMessageWithSameParameterTest() {
        String text = "Dear #{user}, the #{date} is your day! Your name is #{user}. Today is #{date}";
        String expected = "Dear Dzmitry, the 2022-11-11 is your day! Your name is Dzmitry. Today is 2022-11-11";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        parameters.put("date", "2022-11-11");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }

    @TemplateTest
    public void generateMessageForLatin1CharsetInVariablesAndTemplate() {
        String textTemplate = "#{user} ýøû balance is low, please top up your balance.";
        String text = new String(textTemplate.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1);
        String expected = "ßër ýøû balance is low, please top up your balance.";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "ßër");
        Template template = new Template(text);
        Client client = new Client("user1@gmail.com", parameters);
        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        assertEquals(expected, actual);
    }
}
