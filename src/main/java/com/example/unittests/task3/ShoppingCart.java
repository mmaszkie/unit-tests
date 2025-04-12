package com.example.unittests.task3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

class ShoppingCart {

    private final Map<String, Integer> products = new HashMap<>();

    ShoppingCartList getShoppingCart() {
        var items = createItemsList();
        var totalPrice = calculateTotalPrice(items);
        return new ShoppingCartList(
                items,
                null,
                items.size(),
                totalPrice
        );
    }

    void addProduct(String productName) {
        var quantity = products.getOrDefault(productName, 0);
        products.put(productName, quantity + 1);
    }

    private List<Item> createItemsList() {
        return products.entrySet().stream()
                .map(ShoppingCart::createItem)
                .toList();
    }

    private static Item createItem(Map.Entry<String, Integer> entry) {
        var productName = entry.getKey();
        var finalPrice = PRODUCTS_DATABASE.get(productName).finalPrice();
        var quantity = entry.getValue();
        return new Item(
                productName,
                finalPrice,
                quantity
        );
    }

    private BigDecimal calculateTotalPrice(List<Item> items) {
        return items.stream()
                .map(Item::priceWithVAT)
                .reduce(ZERO, BigDecimal::add);
    }

    private static final Map<String, Product> PRODUCTS_DATABASE = Map.of(
            "Iceberg",
            new Product(
                    "Iceberg",
                    BigDecimal.valueOf(1.55),
                    BigDecimal.valueOf(15.0),
                    BigDecimal.valueOf(21.0)
            ),
            "Tomato",
            new Product(
                    "Tomato",
                    BigDecimal.valueOf(0.52),
                    BigDecimal.valueOf(15.0),
                    BigDecimal.valueOf(21.0)
            ),
            "Chicken",
            new Product(
                    "Chicken",
                    BigDecimal.valueOf(1.34),
                    BigDecimal.valueOf(12.0),
                    BigDecimal.valueOf(21.0)
            ),
            "Bread",
            new Product(
                    "Bread",
                    BigDecimal.valueOf(0.71),
                    BigDecimal.valueOf(12.0),
                    BigDecimal.valueOf(10.0)
            ),
            "Corn",
            new Product(
                    "Corn",
                    BigDecimal.valueOf(1.21),
                    BigDecimal.valueOf(12.0),
                    BigDecimal.valueOf(10.0)
            )
    );

}
