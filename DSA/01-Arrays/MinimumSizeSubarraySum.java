import java.util.*;

/*
 * Problem:
 * Minimum Size Subarray Sum
 *
 * LeetCode:
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Given:
 * - A positive integer target.
 * - An array of positive integers nums.
 *
 * Find:
 * - The minimum length of a contiguous subarray whose sum >= target.
 * - Return 0 if no such subarray exists.
 *
 *
 * ============================================================
 * LEARNING NOTE — SLIDING WINDOW
 * ============================================================
 *
 * This problem is an important follow-up to:
 *
 * Longest Substring Without Repeating Characters
 *
 * Both problems use Variable-Size Sliding Window, but the
 * optimization is different.
 *
 * Longest Substring:
 *
 *     Expand -> become invalid -> shrink until valid
 *     -> record maximum
 *
 * Minimum Size Subarray Sum:
 *
 *     Expand -> become valid -> shrink while still valid
 *     -> record minimum at every step
 *
 * The key idea is:
 *
 *     RIGHT expands the window.
 *     LEFT shrinks the window.
 *
 * Because both pointers only move forward, the total number
 * of pointer movements is O(n), even though there is a while
 * loop inside another loop.
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * We could consider every possible starting index and extend
 * the subarray to the right while maintaining its sum.
 *
 * Example:
 *
 *     [2, 3, 1, 2, 4, 3]
 *
 * Start at index 0:
 *     [2]
 *     [2,3]
 *     [2,3,1]
 *     ...
 *
 * Then repeat from index 1, index 2, etc.
 *
 * This can require O(n^2) time.
 *
 *
 * ============================================================
 * OPTIMIZED APPROACH — SLIDING WINDOW
 * ============================================================
 *
 * Since every number is POSITIVE, adding a number to the right
 * can only increase the sum, and removing a number from the
 * left can only decrease the sum.
 *
 * Therefore:
 *
 *     sum < target
 *         -> expand right
 *
 *     sum >= target
 *         -> current window is valid
 *         -> try removing elements from the left
 *            to make it as short as possible
 *
 *
 * Example:
 *
 *     target = 7
 *     nums = [2,3,1,2,4,3]
 *
 * Eventually:
 *
 *     [2,4,3]
 *     sum = 9
 *     length = 3
 *
 * Shrink:
 *
 *     [4,3]
 *     sum = 7
 *     length = 2  <-- better answer
 *
 * Shrink again:
 *
 *     [3]
 *     sum = 3
 *
 * Now the window is invalid, so stop shrinking.
 *
 *
 * ============================================================
 * WINDOW INVARIANT
 * ============================================================
 *
 * Whenever:
 *
 *     sum >= target
 *
 * the current window [left ... right] is a valid candidate.
 *
 * We therefore:
 *
 *     1. Record its length.
 *     2. Remove nums[left].
 *     3. Move left forward.
 *     4. Repeat while the window remains valid.
 *
 * This guarantees that we examine the smallest valid window
 * ending at every right position.
 *
 *
 * ============================================================
 * WHY THE NESTED WHILE IS STILL O(n)
 * ============================================================
 *
 * At first glance:
 *
 *     while (right < nums.length)
 *     {
 *         while (sum >= target)
 *         {
 *             left++;
 *         }
 *     }
 *
 * may look like O(n^2).
 *
 * But it isn't.
 *
 * RIGHT moves from 0 -> n-1 only once.
 *
 * LEFT also moves from 0 -> n-1 only once.
 *
 * No pointer ever moves backwards.
 *
 * Therefore:
 *
 *     right moves at most n times
 *     left moves at most n times
 *
 * Total = O(n)
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Using:
 *
 *        right - left
 *
 *    instead of:
 *
 *        right - left + 1
 *
 *    because both endpoints are included in the window.
 *
 *
 * 2. Shrinking only once.
 *
 *    We need:
 *
 *        while (sum >= target)
 *
 *    rather than:
 *
 *        if (sum >= target)
 *
 *    because there may be several smaller valid windows.
 *
 *
 * 3. Checking minSize only before shrinking.
 *
 *    A shorter valid window can appear DURING the shrinking
 *    process.
 *
 *
 * 4. Initializing minSize to nums.length.
 *
 *    That makes it difficult to distinguish:
 *
 *        "The answer is nums.length"
 *
 *    from:
 *
 *        "No valid subarray exists."
 *
 *    Integer.MAX_VALUE gives us a clean sentinel value.
 *
 *
 * 5. Forgetting that the problem requires POSITIVE integers.
 *
 *    This property is what makes this Sliding Window approach
 *    work.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Why does this approach depend on positive integers?
 *
 * A: Because adding an element always increases the sum and
 *    removing an element always decreases it. With negative
 *    numbers, this monotonic behavior disappears and this
 *    Sliding Window strategy is no longer sufficient.
 *
 *
 * Q: Why is the time complexity O(n)?
 *
 * A: Each element is added to the window once by right and
 *    removed from the window at most once by left.
 *
 *
 * Q: Why do we check minSize before removing nums[left]?
 *
 * A: Because the current window is valid at that moment.
 *    Removing nums[left] may make it invalid.
 *
 *
 * Q: What is the difference from Longest Substring Without
 *    Repeating Characters?
 *
 * A:
 *
 *    Longest Substring:
 *        Invalid -> shrink -> become valid
 *        Optimize maximum length.
 *
 *    Minimum Subarray:
 *        Valid -> shrink -> become invalid
 *        Optimize minimum length.
 */
public class MinimumSizeSubarraySum
{
    /*
     * Returns the minimum length of a contiguous subarray
     * whose sum is greater than or equal to target.
     *
     * Returns 0 if no such subarray exists.
     */
    public static int minSubArrayLen(int target, int[] nums)
    {
        int minSize = Integer.MAX_VALUE;

        int left = 0;
        int sum = 0;

        /*
         * right expands the sliding window.
         */
        for (int right = 0; right < nums.length; right++)
        {
            sum += nums[right];

            /*
             * The current window is valid.
             *
             * Keep shrinking from the left while it remains
             * valid, because we are looking for the MINIMUM
             * possible window.
             */
            while (sum >= target)
            {
                /*
                 * Both left and right are included in the
                 * current window.
                 *
                 * Therefore:
                 *
                 *     length = right - left + 1
                 */
                minSize = Math.min(minSize, right - left + 1);

                /*
                 * Remove the leftmost element and shrink.
                 */
                sum -= nums[left];
                left++;
            }
        }

        /*
         * If minSize was never updated, no valid subarray
         * exists.
         */
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }


    /*
     * Local tests.
     */
    public static void main(String[] args)
    {
        System.out.println(
                minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3})
        ); // Expected: 2

        System.out.println(
                minSubArrayLen(4, new int[]{1, 4, 4})
        ); // Expected: 1

        System.out.println(
                minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1})
        ); // Expected: 0

        System.out.println(
                minSubArrayLen(7, new int[]{7})
        ); // Expected: 1

        System.out.println(
                minSubArrayLen(15, new int[]{1, 2, 3, 4, 5})
        ); // Expected: 5
    }
}
