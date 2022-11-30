package com.epam.ld.module2.testing.mail;

import static java.lang.String.format;
import static java.nio.file.StandardOpenOption.APPEND;

import java.nio.file.Files;
import java.nio.file.Path;

import com.epam.ld.module2.testing.exception.MailFileException;

public class FileMailServer implements MailServer {
    private final Path output;

    public FileMailServer(Path output) {
        this.output = output;
    }

    @Override
    public void send(String addresses, String messageContent) {
        try {
            Files.write(output, format("%s%n", addresses).getBytes());
            Files.write(output, messageContent.getBytes(), APPEND);
        } catch (Exception e) {
            throw new MailFileException("Issue while writing to file.");
        }
    }
}
