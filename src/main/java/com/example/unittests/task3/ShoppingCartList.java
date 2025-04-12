package com.example.unittests.task3;

import java.math.BigDecimal;
import java.util.List;

record ShoppingCartList(
        List<Item> items,
        Promotion promotion,
        int totalProducts,
        BigDecimal totalPrice
) {
}
