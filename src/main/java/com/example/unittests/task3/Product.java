package com.example.unittests.task3;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static java.math.RoundingMode.UP;

record Product(
        String name,
        BigDecimal cost,
        BigDecimal revenue,
        BigDecimal tax
) {

    BigDecimal finalPrice() {
        BigDecimal pricePerUnit = calculatePricePerUnit();
        return calculateFinalPriceWithTax(pricePerUnit);
    }

    private BigDecimal calculatePricePerUnit() {
        BigDecimal revenueAmount = calculateRevenueAmount();
        return cost().add(revenueAmount).setScale(2, UP);
    }

    private BigDecimal calculateRevenueAmount() {
        return cost().multiply(revenue()).divide(BigDecimal.valueOf(100), HALF_UP);
    }

    private BigDecimal calculateFinalPriceWithTax(BigDecimal pricePerUnit) {
        BigDecimal taxAmount = calculateTaxAmount(pricePerUnit);
        return pricePerUnit.add(taxAmount).setScale(2, UP);
    }

    private BigDecimal calculateTaxAmount(BigDecimal pricePerUnit) {
        return pricePerUnit.multiply(tax()).divide(BigDecimal.valueOf(100), HALF_UP);
    }

}
