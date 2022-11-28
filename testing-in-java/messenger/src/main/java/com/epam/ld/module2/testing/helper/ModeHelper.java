package com.epam.ld.module2.testing.helper;

public class ModeHelper {
    public static Mode getMode(String[] args) {
        return args.length == 6 ? Mode.FILE : Mode.CONSOLE;
    }
}
