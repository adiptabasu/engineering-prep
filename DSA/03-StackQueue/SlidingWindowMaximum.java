import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 239: Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Problem:
 * Return the maximum value in every contiguous window of size k.
 *
 * Approach: Maintain a monotonic deque of candidate maximum indices.
 *
 * Key Insight:
 * The deque stores indices in increasing order, while their corresponding
 * values decrease from front to back. The front is therefore the maximum in
 * the current window. When a new value arrives, smaller or equal values at
 * the back can be discarded: the new index is later and at least as large, so
 * those older values can never become a future window maximum. Remove expired
 * indices from the front as the window moves.
 *
 * Why It Works:
 * Every discarded back index is dominated by the new value, which remains in
 * the window longer. Every expired front index lies outside the current
 * window. Among the remaining candidates, the front has the largest value,
 * so it is the current window maximum.
 *
 * Complexity:
 * Time: O(n). Each index is added once and removed at most once.
 * Auxiliary Space: O(k) for the deque.
 *
 * Common Mistakes:
 * - Storing values instead of indices, which makes it hard to detect expiry.
 * - Removing an index only when it equals the left edge; use the window
 *   boundary comparison so all expired candidates are removed.
 * - Keeping a running sum; the result needs the maximum candidate at the
 *   deque front.
 */
public class SlidingWindowMaximum {

    /**
     * Returns the maximum value for each window of size k.
     *
     * @param nums input values
     * @param k window size
     * @return maximum value for each window
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length + 1 - k];
        Deque<Integer> maxIndexDeque = new ArrayDeque<>();
        int resultIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            // Valid window indices are >= i - k + 1; remove anything older.
            while (!maxIndexDeque.isEmpty()
                    && maxIndexDeque.peekFirst() <= i - k) {
                maxIndexDeque.removeFirst();
            }

            // The new value dominates every smaller-or-equal candidate behind it.
            while (!maxIndexDeque.isEmpty()
                    && nums[maxIndexDeque.peekLast()] <= nums[i]) {
                maxIndexDeque.removeLast();
            }

            maxIndexDeque.addLast(i);

            if (i >= k - 1) {
                result[resultIndex++] = nums[maxIndexDeque.peekFirst()];
            }
        }

        return result;
    }
}
