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

}