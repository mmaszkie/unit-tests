package com.example.unittests.task3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShoppingCartTests {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    @Test
    public void shouldListEmptyShoppingCart() {
        // given/when
        ShoppingCartList result = shoppingCart.getShoppingCart();

        // then
        assertEquals(List.of(), result.items());
        assertNull(result.promotion());
        assertEquals(0, result.totalProducts());
        assertEquals(ZERO, result.totalPrice());
    }

}
