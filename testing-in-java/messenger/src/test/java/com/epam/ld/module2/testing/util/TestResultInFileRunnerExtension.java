package com.epam.ld.module2.testing.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestResultInFileRunnerExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String result = (context.getExecutionException().isPresent() ? "fail" : "success") + System.lineSeparator();
        byte[] strToBytes = result.getBytes();
        Path path = Paths.get("src\\test\\resources\\result.txt");
        Files.write(path, strToBytes, StandardOpenOption.APPEND);
    }
}
