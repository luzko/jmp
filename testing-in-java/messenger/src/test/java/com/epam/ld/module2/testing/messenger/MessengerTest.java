package com.epam.ld.module2.testing.messenger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.epam.ld.module2.testing.client.Client;
import com.epam.ld.module2.testing.mail.MailServer;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class MessengerTest {
    @Test
    public void sendMessageSpyTest() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = spy(TemplateEngine.class);
        Template template = new Template("");
        Client client = new Client("test@gmail.com");
        when(templateEngine.generateMessage(template, client)).thenReturn("text");
        Messenger messenger = new Messenger(mailServer, templateEngine);
        messenger.sendMessage(client, template);
        verify(mailServer).send("test@gmail.com", "text");
    }

    @Test
    public void sendMessagePartialMockTest() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = new TemplateEngine();
        Template template = new Template("Hello #{user}");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user", "Dzmitry");
        Client client = new Client("test@gmail.com", parameters);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        messenger.sendMessage(client, template);
        verify(mailServer).send("test@gmail.com", "Hello Dzmitry");
    }
}
