package com.example.unittests.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class ShoppingCart {

    private final Map<String, CartItem> items = new HashMap<>();

    void addItem(CartItem item) {
        if (items.containsKey(item.productName())) {
            CartItem existingItem = items.get(item.productName());
            CartItem mergedItem = new CartItem(
                    existingItem.productName(),
                    existingItem.price(),
                    existingItem.quantity() + item.quantity()
            );
            items.put(item.productName(), mergedItem);
        } else {
            items.put(item.productName(), item);
        }
    }

    BigDecimal getTotalPrice() {
        BigDecimal total = items.values().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    int getItemsCount() {
        return items.size();
    }

    void applyPromoCode(String code) {
        throw new RuntimeException("Not implemented yet");
    }

}