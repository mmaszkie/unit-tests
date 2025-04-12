# 📋 Lab Manual: Shopping cart implementation (TDD)

The specification below contains different steps for developing the `ShoppingCart` system. Following the TDD methodology, for each step you should first create a test case in your test class (**RED** phase), then implement the minimum logic required to pass the test (**GREEN** phase), and optionally clean up the code (**REFACTOR** phase).

**Data model (Java record):**
*public record CartItem(String productName, java.math.BigDecimal price, int quantity) {}*

---

### Step 1: Basic item addition and total calculation
* **Description:** The shopping cart should allow adding products and automatically calculate the total price based on each item's price and quantity (price * quantity).
* **Acceptance Criteria:** Adding 1 item of `"Wireless Mouse"` priced at `30.00` and 2 items of `"Java Book"` priced at `50.00` each results in a total cart price of `130.00`.
* **Implementation Tips:**
  - Use a standard `List<CartItem>` to store added elements.
  - Sum up the total price using a stream with `.map()` and `.reduce(BigDecimal.ZERO, BigDecimal::add)`.

### Step 2: Handling duplicate products (merge quantities)
* **Description:** If a product with the same name is added multiple times, the system should increment the quantity of the existing item instead of creating a new line item.
* **Acceptance Criteria:** Adding 1 `"Laptop"` (`1000.00`) and then adding another 1 `"Laptop"` (`1000.00`) must result in exactly 1 unique line item with a total quantity of 2 and a cart total of `2000.00`.
* **Implementation Tips:**
  - Refactor internal storage from a `List` to a `Map<String, CartItem>`.
  - Since Java records are immutable, instantiate a `new CartItem(...)` with the merged quantity to overwrite the entry.

### Step 3: Percentage promo codes
* **Description:** The system should support promotional coupon codes. Applying the valid code `"SAVE10"` applies a 10% flat discount to the entire cart value. Invalid codes must be ignored.
* **Acceptance Criteria:** For a cart subtotal of `100.00`, applying the code `"SAVE10"` drops the final total to `90.00`. An invalid code leaves the total at `100.00`.
* **Implementation Tips:**
  - Add a `discountRate` field as a `BigDecimal` to track the active modifier.
  - Apply the discount calculation at the end of the total method using `BigDecimal.ONE.subtract(discountRate)`.

### Step 4: "Buy 3, pay for 2" promotion (cheapest item free)
* **Description:** If the cart contains 3 or more different products, the single cheapest product (by unit price) becomes completely free. This promotion must be applied before any percentage promo codes.
* **Acceptance Criteria:** A cart containing 1 `"Book"` (`50.00`), 1 `"Video Game"` (`120.00`), and 1 `"Pen"` (`10.00`) has 3 different products. The `"Pen"` (`10.00`) becomes free, leading to a subtotal of `170.00`. Combined with `"SAVE10"`, the final total is: 170.00 * 0.90 = 153.00.
* **Implementation Tips:**
  - Check the number of different products using `items.size()`.
  - Find the minimum price unit using `.min(BigDecimal::compareTo)` and subtract it from the subtotal. Enforce 2 decimal places with `RoundingMode.HALF_UP`.

### Step 5: Pure refactoring phase (clean code & SRP)
* **Description:** Clean up and modularize the architecture of the `getTotalPrice()` method once all previous functional steps are completely green. No new business features should be introduced.
* **Acceptance Criteria:** The `getTotalPrice()` method behaves like a high-level "table of contents". Complex blocks are extracted into distinct, well-named private helper methods, and magic values are moved to constants. All tests must still pass perfectly.
* **Implementation Tips:**
  - Extract distinct responsibilities into private methods like `calculateInitialSubtotal()` and `applyBuyThreePayForTwoPromotion()`.
  - Move literal strings and numeric rates into descriptive `private static final` constants.