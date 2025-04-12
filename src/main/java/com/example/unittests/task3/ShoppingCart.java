package com.example.unittests.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class ShoppingCart {

    private static final int BULK_PROMOTION_MIN_QUANTITY = 3;
    private static final BigDecimal DEFAULT_DISCOUNT_RATE = BigDecimal.ZERO;
    private static final String TEN_PERCENT_OFF_CODE = "SAVE10";
    private static final BigDecimal TEN_PERCENT_OFF_VALUE = new BigDecimal("0.10");

    private final Map<String, CartItem> items = new HashMap<>();
    private BigDecimal discountRate = DEFAULT_DISCOUNT_RATE;

    void addItem(CartItem item) {
        if (items.containsKey(item.productName())) {
            items.put(item.productName(), createMergedItem(item));
        } else {
            items.put(item.productName(), item);
        }
    }

    void applyPromoCode(String code) {
        if (TEN_PERCENT_OFF_CODE.equals(code)) {
            this.discountRate = TEN_PERCENT_OFF_VALUE;
        }
    }

    BigDecimal getTotalPrice() {
        BigDecimal subtotal = calculateInitialSubtotal();
        BigDecimal postPromotionTotal = applyBuyThreePayForTwoPromotion(subtotal);
        BigDecimal totalPrice = applyPromoCodeDiscount(postPromotionTotal);
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    int getItemsCount() {
        return items.size();
    }

    private CartItem createMergedItem(CartItem newItem) {
        CartItem existingItem = items.get(newItem.productName());
        return new CartItem(
                existingItem.productName(),
                existingItem.price(),
                existingItem.quantity() + newItem.quantity()
        );
    }

    private BigDecimal calculateInitialSubtotal() {
        return items.values().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal findCheapestItemPrice() {
        return items.values().stream()
                .map(CartItem::price)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal applyBuyThreePayForTwoPromotion(BigDecimal currentTotal) {
        if (items.size() >= BULK_PROMOTION_MIN_QUANTITY) {
            return currentTotal.subtract(findCheapestItemPrice());
        }
        return currentTotal;
    }

    private BigDecimal applyPromoCodeDiscount(BigDecimal currentTotal) {
        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discountRate);
        return currentTotal.multiply(discountMultiplier);
    }

}