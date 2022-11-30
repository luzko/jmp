package com.epam.ld.module2.testing.mail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class ConsoleMailServerTest {
    @Test
    void sendConsoleTest() {
        MailServer mailServer = new ConsoleMailServer();
        PrintStream stream = mock(PrintStream.class);
        System.setOut(stream);
        mailServer.send("some@example.com", "text");
        verify(stream).format("%s%n%s", "some@example.com", "text");
    }
}
