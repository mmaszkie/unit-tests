package com.example.unittests.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class ShoppingCart {

    private final Map<String, CartItem> items = new HashMap<>();
    private BigDecimal discountRate = BigDecimal.ZERO;

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

    int getItemsCount() {
        return items.size();
    }

    void applyPromoCode(String code) {
        if ("SAVE10".equals(code)) {
            this.discountRate = new BigDecimal("0.10");
        }
    }

    BigDecimal getTotalPrice() {
        BigDecimal subtotal = items.values().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (items.size() >= 3) {
            BigDecimal cheapestItemPrice = items.values().stream()
                    .map(CartItem::price)
                    .min(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);

            subtotal = subtotal.subtract(cheapestItemPrice);
        }

        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discountRate);
        BigDecimal totalPrice = subtotal.multiply(discountMultiplier);

        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

}