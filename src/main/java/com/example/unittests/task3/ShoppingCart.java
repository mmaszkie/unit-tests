package com.example.unittests.task3;

import java.math.BigDecimal;
import java.util.Map;

class ShoppingCart {

    ShoppingCartList getShoppingCart() {
        throw new RuntimeException("Not implemented yet");
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
