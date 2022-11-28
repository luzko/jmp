package com.epam.ld.module2.testing.mail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class ConsoleMailServerTest {
    @Test
    void sendConsoleTest() {
        MailServer mailServer = new ConsoleMailServer();
        String addresses = "some@example.com";
        String messageText = "text";
        PrintStream stream = mock(PrintStream.class);
        System.setOut(stream);
        mailServer.send(addresses, messageText);
        verify(stream).format("%s%n%s", "some@example.com", "text");
    }
}
