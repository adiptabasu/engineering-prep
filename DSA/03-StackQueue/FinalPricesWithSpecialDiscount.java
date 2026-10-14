import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 1475: Final Prices With a Special Discount in a Shop
 *
 * Problem:
 * Given an array of prices, find the final price of each item after applying
 * a discount.
 *
 * For each item at index i, its discount is the price at the first index j
 * to its right such that:
 *
 *     j > i AND prices[j] <= prices[i]
 *
 * If no such index exists, the item receives no discount.
 *
 * LeetCode:
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 *
 * Approach: Monotonic Stack (Traverse Right to Left)
 *
 * Key Idea:
 * Traverse the array from right to left while maintaining a stack of indices.
 *
 * For each index i:
 *
 * 1. Pop indices whose prices are greater than prices[i].
 *    These prices cannot be the discount for the current item.
 *
 * 2. If the stack is not empty, the price at the top of the stack is the
 *    nearest qualifying discount.
 *
 * 3. Calculate the final price:
 *        finalPrice[i] = prices[i] - prices[stack.peek()]
 *
 * 4. Push the current index onto the stack.
 *
 * Why Store Indices Instead of Prices?
 * Indices let us access the corresponding prices and identify the nearest
 * qualifying item to the right.
 *
 * Why Do We Pop Only When the Stack's Price Is Greater?
 * The problem allows a discount whose price is LESS THAN OR EQUAL TO the
 * current price.
 *
 * Therefore, equal prices are valid discounts and must not be removed.
 *
 * Why Traverse Right to Left?
 * We process all items to the right before processing the current item.
 * The stack helps us identify the nearest qualifying discount efficiently.
 *
 * Why Initialize the Output with prices.clone()?
 * Items without a qualifying discount must retain their original prices.
 * Cloning the input ensures those prices remain unchanged unless a discount
 * is found.
 *
 * Why Use peek() Instead of pop() to Apply the Discount?
 * We only need to read the discount price. We do not need to remove its index
 * when calculating the current item's final price.
 *
 * Complexity:
 * Time: O(n)
 *   Every index is pushed once and popped at most once.
 *
 * Auxiliary Space: O(n)
 *   The stack can hold up to n indices.
 *
 * Common Mistakes:
 * 1. Popping when prices[stack.peek()] >= prices[i].
 *    This incorrectly removes equal prices, which are valid discounts.
 *
 * 2. Reversing the subtraction.
 *    The final price is the original price minus the discount.
 *
 * 3. Calculating the discount inside the popping loop.
 *    The loop should only remove invalid candidates.
 *
 * 4. Calling peek() or pop() without checking whether the stack is empty.
 *
 * 5. Initializing the output with zeros instead of preserving original prices.
 */
public class FinalPricesWithSpecialDiscount {

    public int[] finalPrices(int[] prices) {

        // Stores indices of potential discount prices.
        Deque<Integer> stack = new ArrayDeque<>();

        // Preserve original prices for items that receive no discount.
        int[] output = prices.clone();

        // Traverse from right to left.
        for (int i = prices.length - 1; i >= 0; i--) {

            /*
             * Step 1: Remove invalid discount candidates.
             *
             * A price greater than the current price cannot be its discount.
             *
             * Notice that we use '<', not '<='.
             * Equal prices qualify as discounts in this problem.
             */
            while (!stack.isEmpty()
                    && prices[i] < prices[stack.peek()]) {

                stack.pop();
            }

            /*
             * Step 2: Apply the discount if a candidate exists.
             *
             * The top of the stack represents the nearest price to the right
             * that is less than or equal to the current price.
             */
            if (!stack.isEmpty()) {
                output[i] = prices[i] - prices[stack.peek()];
            }

            /*
             * Step 3: Add the current index.
             *
             * This item may serve as a discount for an earlier item.
             */
            stack.push(i);
        }

        return output;
    }

    /**
     * Local test cases.
     * Run this class directly to verify the implementation.
     */
    public static void main(String[] args) {

        FinalPricesWithSpecialDiscount solution =
                new FinalPricesWithSpecialDiscount();

        // Test 1: Standard example.
        test(
                solution,
                new int[]{8, 4, 6, 2, 3},
                new int[]{4, 2, 4, 2, 3}
        );

        // Test 2: Increasing prices.
        test(
                solution,
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5}
        );

        // Test 3: Decreasing prices.
        test(
                solution,
                new int[]{5, 4, 3, 2, 1},
                new int[]{1, 1, 1, 1, 1}
        );

        // Test 4: Equal prices qualify for discounts.
        test(
                solution,
                new int[]{5, 5, 5},
                new int[]{0, 0, 5}
        );

        // Test 5: A single item receives no discount.
        test(
                solution,
                new int[]{10},
                new int[]{10}
        );

        // Test 6: Empty input.
        test(
                solution,
                new int[]{},
                new int[]{}
        );

        // Test 7: No qualifying discount for the first item.
        test(
                solution,
                new int[]{3, 4, 5},
                new int[]{3, 4, 5}
        );
    }

    /**
     * Runs a test case and compares actual and expected results.
     */
    private static void test(
            FinalPricesWithSpecialDiscount solution,
            int[] prices,
            int[] expected
    ) {
        int[] actual = solution.finalPrices(prices);

        if (Arrays.equals(actual, expected)) {
            System.out.println(
                    "PASS | Input: " + Arrays.toString(prices)
                    + " | Output: " + Arrays.toString(actual)
            );
        } else {
            System.out.println(
                    "FAIL | Input: " + Arrays.toString(prices)
                    + " | Expected: " + Arrays.toString(expected)
                    + " | Actual: " + Arrays.toString(actual)
            );

            throw new AssertionError("Test case failed.");
        }
    }
}
