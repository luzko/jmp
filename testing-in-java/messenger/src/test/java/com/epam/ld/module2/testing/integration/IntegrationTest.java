package com.epam.ld.module2.testing.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import com.epam.ld.module2.testing.Main;

public class IntegrationTest {
    @Test
    @DisabledIfSystemProperty(named = "prop", matches = "true")
    public void consoleModeTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File input = new File(Objects.requireNonNull(classLoader.getResource("input.txt")).getFile());
        try (FileInputStream in = new FileInputStream(input);
                PrintStream out = new PrintStream(Files.newOutputStream(
                        Paths.get("src\\test\\resources\\console-output.txt")), false)) {
            System.setIn(in);
            System.setOut(out);
            Main.main(new String[] {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisabledIfSystemProperty(named = "prop", matches = "true")
    public void fileModeTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File template = new File(Objects.requireNonNull(classLoader.getResource("template.txt")).getFile());
        File parameters = new File(Objects.requireNonNull(classLoader.getResource("parameters.txt")).getFile());
        String[] args = {
                "template", template.getAbsolutePath(),
                "parameters", parameters.getAbsolutePath(),
                "output", "src\\test\\resources\\file-output.txt"
        };
        Main.main(args);
    }
}
