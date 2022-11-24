package com.epam.jmp.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineRunnerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CommandLineRunnerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello World!");
    }
}
