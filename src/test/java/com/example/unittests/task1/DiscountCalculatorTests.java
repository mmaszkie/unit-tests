package com.example.unittests.task1;

import com.example.unittests.task1.DiscountCalculator.Discount;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.example.unittests.task1.DiscountCalculator.Discount.HIGH;
import static com.example.unittests.task1.DiscountCalculator.Discount.NONE;
import static com.example.unittests.task1.DiscountCalculator.Discount.STANDARD;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    @ParameterizedTest
    @MethodSource("tooLowAmountProvider")
    public void shouldNotApplyDiscountWhenAmountIsTooLow(Integer input) {
        // given
        BigDecimal amount = valueOf(input);

        // when
        Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(NONE, actualDiscount);
    }

    @ParameterizedTest
    @MethodSource("amountWithDifferentDiscountProvider")
    public void shouldApplyDifferentDiscountWhenAmountIsBetweenAppropriateDiscountLevels(
            Integer input,
            Discount expectedDiscount
    ) {
        // given
        BigDecimal amount = valueOf(input);

        // when
        Discount actualDiscount = discountCalculator.calculate(amount);

        // then
        assertEquals(expectedDiscount, actualDiscount);
    }

    private static Stream<Integer> incorrectAmountProvider() {
        return Stream.of(null, -1);
    }

    private static Stream<Integer> tooLowAmountProvider() {
        return Stream.of(0, 500);
    }

    private static Stream<Arguments> amountWithDifferentDiscountProvider() {
        return Stream.of(
                arguments(2500, STANDARD),
                arguments(6500, HIGH)
        );
    }

}
