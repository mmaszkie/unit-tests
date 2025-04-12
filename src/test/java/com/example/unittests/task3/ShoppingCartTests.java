package com.example.unittests.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.UNNECESSARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTests {

    private ShoppingCart shoppingCart;

    @BeforeEach
    public void beforeEach() {
        shoppingCart = new ShoppingCart();
    }

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

    @Test
    public void shouldAddProductToShoppingCart() {
        // given
        List<Item> expectedItems = List.of(
                new Item("Iceberg", newBigDecimal(2.17), 1),
                new Item("Tomato", newBigDecimal(0.73), 1),
                new Item("Chicken", newBigDecimal(1.83), 1),
                new Item("Bread", newBigDecimal(0.88), 1),
                new Item("Corn", newBigDecimal(1.50), 1)
        );

        // when
        shoppingCart.addProduct("Iceberg");
        shoppingCart.addProduct("Tomato");
        shoppingCart.addProduct("Chicken");
        shoppingCart.addProduct("Bread");
        shoppingCart.addProduct("Corn");

        // then
        ShoppingCartList result = shoppingCart.getShoppingCart();
        List<Item> actualItems = result.items();

        assertEquals(expectedItems.size(), actualItems.size());
        assertTrue(expectedItems.containsAll(actualItems));

        assertNull(result.promotion());
        assertEquals(5, result.totalProducts());
        assertEquals(newBigDecimal(7.11), result.totalPrice());
    }

    private BigDecimal newBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(2, UNNECESSARY);
    }

}
