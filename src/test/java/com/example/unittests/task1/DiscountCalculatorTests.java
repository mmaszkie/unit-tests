package com.example.unittests.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

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

    }

    @Test
    public void shouldNotApplyDiscountWhenAmountIsZero() {

    }

    @Test
    public void shouldNotApplyDiscountWhenAmountIsTooLow() {

    }

    @Test
    public void shouldApplyStandardDiscountWhenAmountIsBetweenStandardAndHighDiscountLevels() {

    }

    @Test
    public void shouldApplyHighDiscountWhenAmountIsAboveHighDiscountLevel() {

    }

}
