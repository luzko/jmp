package com.epam.ld.module2.testing.mail;

public class ConsoleMailServer implements MailServer {
    @Override
    public void send(String addresses, String messageContent) {
        System.out.format("%s%n%s", addresses, messageContent);
    }
}
