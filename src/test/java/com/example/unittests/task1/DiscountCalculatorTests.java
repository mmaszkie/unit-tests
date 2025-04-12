package com.example.unittests.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static com.example.unittests.task1.DiscountCalculator.Discount.HIGH;
import static com.example.unittests.task1.DiscountCalculator.Discount.NONE;
import static com.example.unittests.task1.DiscountCalculator.Discount.STANDARD;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountCalculatorTests {

    private final DiscountCalculator discountCalculator = new SimpleDiscountCalculator();

    @Test
    public void shouldThrowExceptionWhenAmountIsNull() {
        // given
        BigDecimal amount = null;

        // when
        Executable actualDiscount = () -> discountCalculator.calculate(amount);

        // then
        RuntimeException actualException = assertThrows(RuntimeException.class, actualDiscount);
        assertEquals("Amount value must be greater than or equal to zero", actualException.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsLessThanZero() {
        // given
        BigDecimal amount = valueOf(-1);

        // when
        Executable actualDiscount = () -> discountCalculator.calculate(amount);

        // then
        RuntimeException actualException = assertThrows(RuntimeException.class, actualDiscount);
        assertEquals("Amount value must be greater than or equal to zero", actualException.getMessage());
    }

    @Test
    public void shouldNotApplyDiscountWhenAmountIsZero() {
        // given
        BigDecimal amount = valueOf(0);

        // when
        DiscountCalculator.Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(NONE, actualDiscount);
    }

    @Test
    public void shouldNotApplyDiscountWhenAmountIsTooLow() {
        // given
        BigDecimal amount = valueOf(500);

        // when
        DiscountCalculator.Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(NONE, actualDiscount);
    }

    @Test
    public void shouldApplyStandardDiscountWhenAmountIsBetweenStandardAndHighDiscountLevels() {
        // given
        BigDecimal amount = valueOf(2500);

        // when
        DiscountCalculator.Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(STANDARD, actualDiscount);
    }

    @Test
    public void shouldApplyHighDiscountWhenAmountIsAboveHighDiscountLevel() {
        // given
        BigDecimal amount = valueOf(6500);

        // when
        DiscountCalculator.Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(HIGH, actualDiscount);
    }

}
