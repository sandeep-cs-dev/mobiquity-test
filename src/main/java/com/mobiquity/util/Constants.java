package com.mobiquity.util;

import java.math.BigDecimal;

public class Constants {
    // declare a constant Regular expression to match input value
    public static final String INPUT_VALIDATION_REGEX =
            "\\d+\\s*:\\s*(\\(\\d+(?:\\.\\d{1,2})?,\\d+(?:\\.\\d{1,2})?,[\\p{Sc}]\\d+(?:\\.\\d{1,2})?\\)\\s*)+";
    public static final BigDecimal PACKAGE_ALLOWED_CAPACITY = BigDecimal.valueOf(100);
    public static final BigDecimal ITEM_MAX_WEIGHT = BigDecimal.valueOf(100);
    public static final BigDecimal ITEM_MAX_COST = BigDecimal.valueOf(100);
    public static final Integer MAX_ITEM = 15;
}

