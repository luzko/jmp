package com.epam.ld.module2.testing.parameter;

import java.util.Map;

public interface Parameter {
    void read();

    Map<String, String> getParameters();

    String getTemplate();
}
