package com.example.unittests.task1;

import com.example.unittests.task1.DiscountCalculator.Discount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
class DiscountController {

    private final DiscountCalculator discountCalculator;

    DiscountController(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    @GetMapping("/discount")
    Discount calculateDiscount(@RequestParam BigDecimal amount) {
        return discountCalculator.calculate(amount);
    }

}
