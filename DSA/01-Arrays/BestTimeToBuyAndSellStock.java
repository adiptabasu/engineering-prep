/*
 * Problem: Best Time to Buy and Sell Stock
 *
 * LeetCode:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Difficulty: Easy
 *
 * Pattern:
 * Running Minimum + Running Maximum
 *
 * Key Idea:
 * For every day, assume we sell on that day.
 *
 * The best possible buying price before that day is the minimum
 * price we have seen so far.
 *
 * Therefore:
 *
 *     profit = currentPrice - cheapestPrice
 *
 * We keep track of the maximum profit seen during the scan.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

public class BestTimeToBuyAndSellStock
{
    /*
     * Approach:
     *
     * Maintain two variables:
     *
     * 1. cheapestPrice
     *    - The minimum stock price seen so far.
     *
     * 2. maxProfitAmt
     *    - The maximum profit found so far.
     *
     * For every price:
     *
     *     1. Update the cheapest price.
     *     2. Calculate the profit if we sell today.
     *     3. Update the maximum profit.
     *
     * Important:
     *
     * We never buy after we sell because cheapestPrice only contains
     * prices encountered before or at the current position.
     */
    public static int maxProfit(int[] prices)
    {
        int cheapestPrice = prices[0];
        int maxProfitAmt = 0;

        for (int i = 0; i < prices.length; i++)
        {
            /*
             * Invariant:
             * cheapestPrice is the minimum price seen so far.
             */
            if (prices[i] < cheapestPrice)
            {
                cheapestPrice = prices[i];
            }

            /*
             * Calculate the profit if we sell today.
             */
            int profit = prices[i] - cheapestPrice;

            /*
             * Keep the best profit found so far.
             */
            if (profit > maxProfitAmt)
            {
                maxProfitAmt = profit;
            }
        }

        return maxProfitAmt;
    }

    /*
     * Brute Force Approach:
     *
     * Try every possible buying day and every possible selling day
     * after it.
     *
     *     for each buy day:
     *         for each sell day after buy:
     *             calculate profit
     *
     * Time: O(n²)
     * Space: O(1)
     *
     * This works, but the single-pass solution is optimal.
     */

    /*
     * Why the O(n) solution works:
     *
     * Suppose:
     *
     * prices = [7, 1, 5, 3, 6, 4]
     *
     * Day     Price    Cheapest So Far    Profit Today    Max Profit
     * ---------------------------------------------------------------
     *  0        7             7                0              0
     *  1        1             1                0              0
     *  2        5             1                4              4
     *  3        3             1                2              4
     *  4        6             1                5              5
     *  5        4             1                3              5
     *
     * Answer = 5
     *
     * Buy at 1 and sell at 6.
     */

    /*
     * Common Mistakes:
     *
     * 1. Using the global minimum price without considering its position.
     *
     *    Example:
     *    [7, 6, 4, 3, 1]
     *
     *    There is no profitable transaction.
     *
     * 2. Calculating:
     *
     *       maxPrice - minPrice
     *
     *    without ensuring that the minimum occurs before the maximum.
     *
     * 3. Using nested loops when a single pass is sufficient.
     *
     * 4. Forgetting that we are allowed to make only ONE transaction.
     */

    /*
     * Interview Follow-ups:
     *
     * 1. What if multiple transactions were allowed?
     *    -> Different problem: Best Time to Buy and Sell Stock II.
     *
     * 2. What if there is a transaction fee?
     *    -> Requires a modified greedy/DP approach.
     *
     * 3. What if there is a cooldown after selling?
     *    -> Dynamic Programming.
     *
     * 4. What if we can hold multiple stocks?
     *    -> The problem model changes substantially.
     */

    public static void main(String[] args)
    {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));    // 0
        System.out.println(maxProfit(new int[]{1, 2}));             // 1
        System.out.println(maxProfit(new int[]{2, 1, 2, 1, 0, 1, 2})); // 2
    }
}
