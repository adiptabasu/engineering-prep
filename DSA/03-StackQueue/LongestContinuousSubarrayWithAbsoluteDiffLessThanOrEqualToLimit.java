import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1438: Longest Continuous Subarray With Absolute Diff Less Than or
 * Equal to Limit
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 *
 * Problem:
 * Return the length of the longest non-empty subarray where the difference
 * between every pair of values is at most limit.
 *
 * Approach: Sliding window with one monotonic deque for the maximum and one
 * for the minimum.
 *
 * Key Insight:
 * The largest difference between any pair in a window is its maximum minus
 * its minimum. Keep indices in a decreasing-value deque for the maximum and
 * an increasing-value deque for the minimum. Their front values give the
 * current window's extrema in O(1).
 *
 * Why It Works:
 * When the window becomes invalid, advance its left edge until max - min is
 * within limit again. Each deque removes indices that are dominated by a
 * newer value or have left the window. Once the window is valid, its length
 * is a candidate for the longest valid subarray.
 *
 * Complexity:
 * Time: O(n). Each index is added to and removed from each deque at most once.
 * Auxiliary Space: O(n) in the worst case for the two deques.
 *
 * Common Mistakes:
 * - Comparing a stored index directly with a value; look up nums[index].
 * - Advancing left on every iteration instead of only while max - min > limit.
 * - Forgetting to remove a deque-front index when it leaves the window.
 * - Updating the best length before the window is valid again.
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    /**
     * Returns the length of the longest subarray whose max-minus-min is at
     * most limit.
     *
     * @param nums input values
     * @param limit maximum allowed difference
     * @return longest valid subarray length
     */
    public int longestSubarray(int[] nums, int limit) {
        int longest = 0;
        Deque<Integer> maxIndexDeque = new ArrayDeque<>();
        Deque<Integer> minIndexDeque = new ArrayDeque<>();
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // Keep maximum candidates in decreasing value order.
            while (!maxIndexDeque.isEmpty()
                    && nums[maxIndexDeque.peekLast()] <= nums[right]) {
                maxIndexDeque.removeLast();
            }
            maxIndexDeque.addLast(right);

            // Keep minimum candidates in increasing value order.
            while (!minIndexDeque.isEmpty()
                    && nums[minIndexDeque.peekLast()] >= nums[right]) {
                minIndexDeque.removeLast();
            }
            minIndexDeque.addLast(right);

            // Shrink only while the current window violates the limit.
            while (nums[maxIndexDeque.peekFirst()]
                    - nums[minIndexDeque.peekFirst()] > limit) {
                while (!maxIndexDeque.isEmpty()
                        && maxIndexDeque.peekFirst() <= left) {
                    maxIndexDeque.removeFirst();
                }
                while (!minIndexDeque.isEmpty()
                        && minIndexDeque.peekFirst() <= left) {
                    minIndexDeque.removeFirst();
                }
                left++;
            }

            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }
}
