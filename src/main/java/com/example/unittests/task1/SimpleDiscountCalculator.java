package com.example.unittests.task1;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.unittests.task1.DiscountCalculator.Discount.HIGH;
import static com.example.unittests.task1.DiscountCalculator.Discount.NONE;
import static com.example.unittests.task1.DiscountCalculator.Discount.STANDARD;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Component
class SimpleDiscountCalculator implements DiscountCalculator {

    private static final BigDecimal STANDARD_DISCOUNT_AMOUNT_LEVEL = valueOf(1000);
    private static final BigDecimal HIGH_DISCOUNT_AMOUNT_LEVEL = valueOf(5000);

    @Override
    public Discount calculate(BigDecimal amount) {
        validateAmount(amount);
        return toDiscount(amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(ZERO) < 0) {
            throw new RuntimeException("Amount value must be greater than or equal to zero");
        }
    }

    private Discount toDiscount(BigDecimal amount) {
        if (amountQualifiesForStandardDiscount(amount)) {
            return STANDARD;
        }
        if (amountQualifiesForHighDiscount(amount)) {
            return HIGH;
        }
        return NONE;
    }

    private boolean amountQualifiesForStandardDiscount(BigDecimal amount) {
        return amount.compareTo(STANDARD_DISCOUNT_AMOUNT_LEVEL) >= 0 && amount.compareTo(HIGH_DISCOUNT_AMOUNT_LEVEL) < 0;
    }

    private boolean amountQualifiesForHighDiscount(BigDecimal amount) {
        return amount.compareTo(HIGH_DISCOUNT_AMOUNT_LEVEL) >= 0;
    }

}
