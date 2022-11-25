package com.epam.ld.module2.testing.template;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.InvalidDataException;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    private static final Pattern parameterPattern = Pattern.compile("#\\{(\\S+)}");

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        Matcher matcher = parameterPattern.matcher(template.getTemplateText());
        StringBuffer message = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(message, getParameter(matcher.group(1), client.getParameters()));
        }
        matcher.appendTail(message);
        return message.toString();
    }

    private String getParameter(String parameter, Map<String, String> parameters) {
        if (parameters.containsKey(parameter)) {
            return parameters.get(parameter);
        } else {
            throw new InvalidDataException("There is no such " + parameter);
        }
    }
}
