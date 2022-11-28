package com.epam.ld.module2.testing.mail;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.epam.ld.module2.testing.exception.MailFileException;

class FileMailServerTest {
    @Test
    public void sendFileTest(@TempDir Path tempDir) throws IOException {
        Path output = tempDir.resolve("file");
        String addresses = "test@gmail.com";
        String message = "text";
        MailServer mailServer = new FileMailServer(output);
        mailServer.send(addresses, message);
        assertTrue(Files.exists(output));
        List<String> expected = Arrays.asList("test@gmail.com", "text");
        List<String> actual = Files.readAllLines(output);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void sendFileMailFileExceptionTest() {
        Path output = mock(Path.class);
        String addresses = "test@gmail.com";
        String message = "text";
        MailServer mailServer = new FileMailServer(output);
        assertThrows(MailFileException.class, () -> mailServer.send(addresses, message));
    }
}
