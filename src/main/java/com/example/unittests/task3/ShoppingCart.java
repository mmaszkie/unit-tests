package com.example.unittests.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    void addItem(CartItem item) {
        items.add(item);
    }

    BigDecimal getTotalPrice() {
        BigDecimal total = items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    int getItemsCount() {
       throw new RuntimeException("Not implemented yet");
    }

}