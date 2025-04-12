package com.example.unittests.task1;

import com.example.unittests.task1.DiscountCalculator.Discount;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountCalculatorTests {

    private final DiscountCalculator discountCalculator = new SimpleDiscountCalculator();

    @ParameterizedTest
    @MethodSource("incorrectAmountProvider")
    public void shouldThrowExceptionWhenAmountIsIncorrect(Integer input) {
        // given
        BigDecimal amount = input != null ? valueOf(input) : null;

        // when
        Executable actualDiscount = () -> discountCalculator.calculate(amount);

        // then
        RuntimeException actualException = assertThrows(RuntimeException.class, actualDiscount);
        assertEquals("Amount value must be greater than or equal to zero", actualException.getMessage());
    }

    public void shouldNotApplyDiscountWhenAmountIsTooLow(Integer input) {

    }

    public void shouldApplyDifferentDiscountWhenAmountIsBetweenAppropriateDiscountLevels(
            Integer input,
            Discount expectedDiscount
    ) {

    }

    private static Stream<Integer> incorrectAmountProvider() {
        return Stream.of(null, -1);
    }

}
