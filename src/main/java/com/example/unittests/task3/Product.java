package com.example.unittests.task3;

import java.math.BigDecimal;

record Product(
        String name,
        BigDecimal cost,
        BigDecimal revenue,
        BigDecimal tax
) {
}
