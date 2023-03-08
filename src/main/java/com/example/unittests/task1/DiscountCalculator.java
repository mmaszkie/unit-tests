package com.example.unittests.task1;

import java.math.BigDecimal;

public interface DiscountCalculator {

    Discount calculate(BigDecimal amount);

    enum Discount {
        NONE, STANDARD, HIGH
    }

}
