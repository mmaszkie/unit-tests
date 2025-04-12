package com.example.unittests.task3;

import java.math.BigDecimal;

record Item(
        String productName,
        BigDecimal priceWithVAT,
        int quantity
) {
}
