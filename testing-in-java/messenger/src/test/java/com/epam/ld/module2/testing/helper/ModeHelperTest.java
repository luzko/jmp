package com.epam.ld.module2.testing.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModeHelperTest {
    @Test
    public void consoleModeTest() {
        String[] args = new String[0];
        ModeHelper modeHelper = new ModeHelper(args);
        Mode actual = modeHelper.getMode();
        assertEquals(Mode.CONSOLE, actual);
    }

    @Test
    public void fileModeTest() {
        String[] args = {
                "template", "template.txt",
                "parameters", "parameters.txt",
                "output", "output.txt",
        };
        ModeHelper modeHelper = new ModeHelper(args);
        Mode actual = modeHelper.getMode();
        assertEquals(Mode.File, actual);
    }
}
