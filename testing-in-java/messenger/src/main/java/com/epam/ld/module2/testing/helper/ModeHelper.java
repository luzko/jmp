package com.epam.ld.module2.testing.helper;

import com.epam.ld.module2.testing.exception.ParameterArgumentException;

public class ModeHelper {
    private static final String TEMPLATE = "template";
    private static final String PARAMETERS = "parameters";
    private static final String OUTPUT = "output";
    private static final int NUMBER_OF_ARGUMENTS = 6;

    public static Mode getMode(String[] args) {
        if (args.length == 0) {
            return Mode.CONSOLE;
        } else if (args.length == NUMBER_OF_ARGUMENTS) {
            validateArguments(args);
            return Mode.FILE;
        } else {
            throw new ParameterArgumentException("Incorrect number of arguments");
        }
    }

    private static void validateArguments(String[] args) {
        if (!(TEMPLATE.equals(args[0]) && PARAMETERS.equals(args[2]) && OUTPUT.equals(args[4]))) {
            throw new ParameterArgumentException("Incorrect arguments");
        }
    }
}
