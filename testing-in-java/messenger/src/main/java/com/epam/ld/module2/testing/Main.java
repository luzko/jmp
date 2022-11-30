package com.epam.ld.module2.testing;

import java.nio.file.Paths;

import com.epam.ld.module2.testing.client.Client;
import com.epam.ld.module2.testing.helper.Mode;
import com.epam.ld.module2.testing.helper.ModeHelper;
import com.epam.ld.module2.testing.mail.ConsoleMailServer;
import com.epam.ld.module2.testing.mail.FileMailServer;
import com.epam.ld.module2.testing.mail.MailServer;
import com.epam.ld.module2.testing.messenger.Messenger;
import com.epam.ld.module2.testing.parameter.ConsoleParameter;
import com.epam.ld.module2.testing.parameter.FileParameter;
import com.epam.ld.module2.testing.parameter.Parameter;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class Main {
    public static void main(String[] args) {
        Parameter parameter;
        MailServer mailServer;
        try {
            if (ModeHelper.getMode(args).equals(Mode.FILE)) {
                parameter = new FileParameter(Paths.get(args[1]), Paths.get(args[3]));
                mailServer = new FileMailServer(Paths.get(args[5]));
            } else {
                parameter = new ConsoleParameter();
                mailServer = new ConsoleMailServer();
            }
            parameter.read();
            Messenger messenger = new Messenger(mailServer, new TemplateEngine());
            Client client = new Client("test@gmail.com", parameter.getParameters());
            Template template = new Template(parameter.getTemplate());
            messenger.sendMessage(client, template);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
