package com.epam.ld.module2.testing.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModeHelperTest {
    @Test
    public void consoleModeTest() {
        String[] args = new String[0];
        Mode actual = ModeHelper.getMode(args);
        assertEquals(Mode.CONSOLE, actual);
    }

    @Test
    public void fileModeTest() {
        String[] args = {
                "template", "template.txt",
                "parameters", "parameters.txt",
                "output", "output.txt",
        };
        Mode actual = ModeHelper.getMode(args);
        assertEquals(Mode.FILE, actual);
    }
}