package com.epam.ld.module2.testing.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.epam.ld.module2.testing.exception.ParameterArgumentException;

class ModeHelperTest {
    @Test
    public void consoleModeTest() {
        Mode actual = ModeHelper.getMode(new String[0]);
        assertEquals(Mode.CONSOLE, actual);
    }

    @Test
    public void fileModeTest() {
        Mode actual = ModeHelper.getMode(
                new String[] {
                        "template", "template.txt", "parameters", "parameters.txt", "output", "console-output.txt"
                });
        assertEquals(Mode.FILE, actual);
    }

    @ParameterizedTest
    @MethodSource("wrongArgumentsNumber")
    public void incorrectCountParameterArgumentExceptionTest(String[] args) {
        assertThrows(ParameterArgumentException.class, () -> ModeHelper.getMode(args));
    }

    @ParameterizedTest
    @MethodSource("wrongArgumentsName")
    public void incorrectParameterArgumentExceptionTest() {
        assertThrows(ParameterArgumentException.class,
                () -> ModeHelper.getMode(new String[] {"param1", "value1", "param2", "value2", "param3", "value3"}));
    }

    private static Stream<Arguments> wrongArgumentsNumber() {
        return Stream.of(
                Arguments.of((Object) new String[] {"param", "value"}),
                Arguments.of((Object) new String[] {"some", "param", "value"})
        );
    }

    private static Stream<Arguments> wrongArgumentsName() {
        return Stream.of(
                Arguments.of((Object) new String[] {"template", "path1", "parameters", "path2", "output", "path3"}),
                Arguments.of((Object) new String[] {"template", "path4", "parameters", "path5", "output", "path6"})
        );
    }
}
