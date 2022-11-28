package com.epam.ld.module2.testing.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ModeHelperTest {
    @Test
    public void consoleModeTest() {
        Mode actual = ModeHelper.getMode(new String[0]);
        assertEquals(Mode.CONSOLE, actual);
    }

    @Test
    public void fileModeTest() {
        Mode actual = ModeHelper.getMode(
                new String[] {"template", "template.txt", "parameters", "parameters.txt", "output", "output.txt"});
        assertEquals(Mode.FILE, actual);
    }

    @Test
    public void incorrectCountParameterArgumentExceptionTest() {
        assertThrows(ParameterArgumentException.class,
                () -> ModeHelper.getMode(new String[] {"someParam", "some value"}));
    }

    @Test
    public void incorrectParameterArgumentExceptionTest() {
        assertThrows(ParameterArgumentException.class,
                () -> ModeHelper.getMode(new String[] {"param1", "value1", "param2", "value2", "param3", "value3"}));
    }
}
