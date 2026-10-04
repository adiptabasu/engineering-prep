/*
 * Problem:
 * Maximum Average Subarray I
 *
 * LeetCode:
 * https://leetcode.com/problems/maximum-average-subarray-i/
 *
 *
 * ============================================================
 * LEARNING NOTE — FIXED-SIZE SLIDING WINDOW
 * ============================================================
 *
 * This problem reinforces Sliding Window after:
 *
 *     1. Longest Substring Without Repeating Characters
 *     2. Minimum Size Subarray Sum
 *
 * The important difference is that this is a FIXED-SIZE
 * Sliding Window.
 *
 * The window must always contain exactly k elements.
 *
 *
 * FIXED-SIZE WINDOW PATTERN
 * -------------------------
 *
 *     Add nums[right]
 *
 *     If window size > k:
 *         Remove nums[left]
 *         Move left
 *
 *     If window size == k:
 *         Evaluate the window
 *
 *
 * Unlike Minimum Size Subarray Sum, we do NOT shrink
 * repeatedly. We remove exactly one element whenever the
 * window becomes larger than k.
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * We could calculate the sum of every subarray of length k.
 *
 * There are approximately n possible windows.
 *
 * If we calculate each sum from scratch:
 *
 *     Time:  O(n * k)
 *     Space: O(1)
 *
 *
 * ============================================================
 * OPTIMIZED APPROACH — SLIDING WINDOW
 * ============================================================
 *
 * Instead of recalculating every window's sum:
 *
 *     [1, 12, -5, -6]
 *
 * when moving to:
 *
 *     [12, -5, -6, 50]
 *
 * we:
 *
 *     1. Add 50
 *     2. Remove 1
 *
 * Therefore:
 *
 *     newSum = oldSum + nums[right] - nums[left]
 *
 * This allows every window sum to be calculated in O(1).
 *
 *
 * ============================================================
 * EXAMPLE
 * ============================================================
 *
 * nums = [1,12,-5,-6,50,3]
 * k = 4
 *
 * Window 1:
 *
 *     [1,12,-5,-6]
 *     sum = 2
 *     average = 0.5
 *
 * Window 2:
 *
 *     [12,-5,-6,50]
 *     sum = 51
 *     average = 12.75
 *
 * Window 3:
 *
 *     [-5,-6,50,3]
 *     sum = 42
 *     average = 10.5
 *
 * Answer = 12.75
 *
 *
 * ============================================================
 * WINDOW INVARIANT
 * ============================================================
 *
 * After adjusting the left pointer:
 *
 *     right - left + 1 <= k
 *
 * Whenever:
 *
 *     right - left + 1 == k
 *
 * the current window contains exactly k elements and can
 * therefore be evaluated.
 *
 *
 * ============================================================
 * IMPORTANT JAVA DETAIL
 * ============================================================
 *
 * Do NOT use:
 *
 *     Double.MIN_VALUE
 *
 * as the initial maximum.
 *
 * Double.MIN_VALUE is the smallest POSITIVE non-zero double,
 * not the most negative double.
 *
 * We therefore use:
 *
 *     -Double.MAX_VALUE
 *
 * as the initial value.
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time:
 *     O(n)
 *
 * Each element enters the window once and leaves the window
 * at most once.
 *
 * Space:
 *     O(1)
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Forgetting to subtract nums[left] when the window
 *    becomes larger than k.
 *
 * 2. Evaluating the window BEFORE shrinking it.
 *
 *    When size becomes k + 1, we must first remove the
 *    outgoing element so that the window becomes exactly k.
 *
 * 3. Using "else if" for the two window conditions.
 *
 *    The correct sequence is:
 *
 *        if (window > k)
 *            shrink
 *
 *        if (window == k)
 *            evaluate
 *
 *    These need to be independent checks because the first
 *    operation can change the window size.
 *
 * 4. Using integer division:
 *
 *        currentSum / k
 *
 *    Both are integers, so this would perform integer
 *    division.
 *
 *    Cast to double first:
 *
 *        (double) currentSum / k
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Why is this O(n)?
 *
 * A: Each element is added once and removed at most once.
 *
 *
 * Q: Why don't we need to calculate the average of every
 *    window from scratch?
 *
 * A: All windows have the same size k, so maximizing the
 *    average is equivalent to maximizing the sum. We can
 *    maintain the sum incrementally.
 *
 *
 * Q: Could we compare sums instead of averages?
 *
 * A: Yes. Since every window contains exactly k elements:
 *
 *     average = sum / k
 *
 * Since k is constant, the window with the largest sum also
 * has the largest average.
 *
 *
 * Q: What type of Sliding Window is this?
 *
 * A: Fixed-size Sliding Window.
 */
public class MaximumAverageSubarrayI
{
    public static double findMaxAverage(int[] nums, int k)
    {
        double maxAverage = -Double.MAX_VALUE;

        int left = 0;
        int currentSum = 0;

        /*
         * right expands the window one element at a time.
         */
        for (int right = 0; right < nums.length; right++)
        {
            currentSum += nums[right];

            /*
             * If the window is too large, remove the element
             * that is leaving the window.
             */
            if (right - left + 1 > k)
            {
                currentSum -= nums[left];
                left++;
            }

            /*
             * At this point, if the window size is exactly k,
             * evaluate its average.
             */
            if (right - left + 1 == k)
            {
                double average = (double) currentSum / k;

                maxAverage = Math.max(maxAverage, average);
            }
        }

        return maxAverage;
    }


    /*
     * Local tests.
     */
    public static void main(String[] args)
    {
        System.out.println(
                findMaxAverage(
                        new int[]{1, 12, -5, -6, 50, 3},
                        4
                )
        ); // Expected: 12.75

        System.out.println(
                findMaxAverage(
                        new int[]{5},
                        1
                )
        ); // Expected: 5.0

        System.out.println(
                findMaxAverage(
                        new int[]{-1, -2, -3, -4},
                        2
                )
        ); // Expected: -1.5

        System.out.println(
                findMaxAverage(
                        new int[]{0, 1, 1, 3, 3},
                        4
                )
        ); // Expected: 2.0
    }
}
