import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 901: Online Stock Span
 *
 * Problem:
 * Design a class that calculates the stock span for each incoming stock price.
 *
 * The span of a stock's price is the number of consecutive days, ending today
 * and going backward, for which the stock price was less than or equal to
 * today's price.
 *
 * LeetCode:
 * https://leetcode.com/problems/online-stock-span/
 *
 * Approach: Monotonic Stack with Span Aggregation
 *
 * Key Idea:
 * Maintain a stack of pairs:
 *   [0] = stock price
 *   [1] = accumulated span for that price
 *
 * For each incoming price:
 *
 * 1. Initialize span = 1, counting the current day.
 *
 * 2. While the stack is not empty and the top price is less than or equal
 *    to today's price:
 *      - Pop the top entry.
 *      - Add its accumulated span to the current span.
 *
 * 3. Push the current price and its calculated span onto the stack.
 *
 * 4. Return the calculated span.
 *
 * Why Do We Pop Prices Less Than or Equal to Today's Price?
 * Those previous prices do not stop today's span. Today's price is high
 * enough to include the days represented by those entries.
 *
 * Why Store the Accumulated Span?
 * Suppose an entry contains [75, 4].
 * This means the price 75 represents a span of four consecutive days.
 *
 * If today's price is 85, we can add 4 to today's span instead of examining
 * those four days individually.
 *
 * This is called span aggregation.
 *
 * Why Does the Stack Work?
 * After processing each price, the stack maintains candidate prices in
 * strictly decreasing order from bottom to top.
 *
 * When a new price arrives, entries that are less than or equal to it
 * are removed because the new price dominates those entries for future
 * span calculations.
 *
 * Complexity:
 *
 * Amortized Time per next() Call: O(1)
 * Every entry is pushed exactly once and popped at most once.
 * Across n calls, the total number of stack operations is O(n).
 *
 * Worst-Case Time for a Single next() Call: O(n)
 * One call may pop many entries from the stack.
 *
 * Auxiliary Space: O(n)
 * In the worst case, the stack retains all n entries.
 *
 * Common Mistakes:
 *
 * 1. Using < instead of <=.
 *    Equal prices must also contribute to the span.
 *
 * 2. Incrementing span by only 1 when popping.
 *    Each popped entry represents multiple days, so add its stored span.
 *
 * 3. Pushing only the price.
 *    We must also store the accumulated span for efficient future calls.
 *
 * 4. Forgetting to initialize span to 1.
 *    The current day always contributes one day to its own span.
 */
public class StockSpanner {

    /*
     * Each entry is an int array:
     *
     * entry[0] = stock price
     * entry[1] = accumulated span
     *
     * The top of the stack contains the most recently added candidate.
     */
    private final Deque<int[]> stack;

    public StockSpanner() {
        stack = new ArrayDeque<>();
    }

    /**
     * Processes the next stock price and returns its stock span.
     *
     * @param price the current stock price
     * @return the number of consecutive days whose prices are
     *         less than or equal to the current price
     */
    public int next(int price) {

        // The current day always contributes one to the span.
        int span = 1;

        /*
         * Remove previous entries whose prices are less than or equal
         * to today's price.
         *
         * Each popped entry represents multiple consecutive days.
         * Add its entire accumulated span instead of counting those
         * days individually.
         */
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        /*
         * Store today's price together with its accumulated span.
         * This entry may contribute to the span of future prices.
         */
        stack.push(new int[]{price, span});

        return span;
    }

    /**
     * Local test cases.
     *
     * Run this class directly to verify the implementation.
     * These tests do not require LeetCode.
     */
    public static void main(String[] args) {

        StockSpanner spanner = new StockSpanner();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] expectedSpans = {1, 1, 1, 2, 1, 4, 6};

        for (int i = 0; i < prices.length; i++) {

            int actualSpan = spanner.next(prices[i]);

            if (actualSpan != expectedSpans[i]) {
                throw new AssertionError(
                        "Failed for price " + prices[i]
                        + ": expected span " + expectedSpans[i]
                        + ", got " + actualSpan
                );
            }

            System.out.println(
                    "PASS | Price: " + prices[i]
                    + " | Span: " + actualSpan
            );
        }

        // Test equal prices using a fresh instance.
        StockSpanner equalPriceSpanner = new StockSpanner();

        int[] equalPrices = {50, 50, 50};
        int[] equalExpected = {1, 2, 3};

        for (int i = 0; i < equalPrices.length; i++) {

            int actualSpan = equalPriceSpanner.next(equalPrices[i]);

            if (actualSpan != equalExpected[i]) {
                throw new AssertionError(
                        "Equal-price test failed at index " + i
                );
            }
        }

        System.out.println("PASS | Equal-price test");

        // Test strictly decreasing prices using a fresh instance.
        StockSpanner decreasingSpanner = new StockSpanner();

        int[] decreasingPrices = {100, 90, 80};
        int[] decreasingExpected = {1, 1, 1};

        for (int i = 0; i < decreasingPrices.length; i++) {

            int actualSpan = decreasingSpanner.next(decreasingPrices[i]);

            if (actualSpan != decreasingExpected[i]) {
                throw new AssertionError(
                        "Decreasing-price test failed at index " + i
                );
            }
        }

        System.out.println("PASS | Decreasing-price test");
    }
}
