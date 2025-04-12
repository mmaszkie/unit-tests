package com.example.unittests.task3;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTests {

    private final ShoppingCart cart = new ShoppingCart();

    @Test
    public void shouldCalculateTotalPriceForAddedItems() {
        // given
        CartItem mouse = new CartItem("Wireless Mouse", new BigDecimal("30.00"), 1);
        CartItem book = new CartItem("Java Book", new BigDecimal("50.00"), 2);

        // when
        cart.addItem(mouse);
        cart.addItem(book);

        // then
        assertEquals(new BigDecimal("130.00"), cart.getTotalPrice());
    }

    @Test
    public void shouldMergeQuantitiesForDuplicatedProducts() {
        // given
        CartItem laptop1 = new CartItem("Laptop", new BigDecimal("1000.00"), 1);
        CartItem laptop2 = new CartItem("Laptop", new BigDecimal("1000.00"), 1);

        // when
        cart.addItem(laptop1);
        cart.addItem(laptop2);

        // then
        assertEquals(new BigDecimal("2000.00"), cart.getTotalPrice());
        assertEquals(1, cart.getItemsCount());
    }

}