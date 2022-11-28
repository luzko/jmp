package com.epam.ld.module2.testing.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ConsoleMailServerTest {
    @Test
    void sendConsoleTest() {
        MailServer mailServer = new ConsoleMailServer();
        String addresses = "some@example.com";
        String messageText = "text";
        PrintStream stream = mock(PrintStream.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(stream);
        mailServer.send(addresses, messageText);
        verify(stream).println(captor.capture());
        assertEquals(captor.getValue(), "some@example.com text");
    }
}
