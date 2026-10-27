import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 962: Maximum Width Ramp
 * https://leetcode.com/problems/maximum-width-ramp/
 *
 * Problem:
 * Find the largest width j - i such that i < j and nums[i] <= nums[j].
 * Return 0 if no positive-width ramp exists.
 *
 * Example:
 * Input:  [6, 0, 8, 2, 1, 5]
 * Output: 4
 * Explanation: nums[1] = 0 <= nums[5] = 5, giving width 5 - 1 = 4.
 *
 * Approach: Monotonic stack of candidate left indices.
 *
 * Key Insight:
 * A left index is useful only when its value is smaller than every value
 * before it. Any earlier index with a value less than or equal to a later
 * index is a better starting point for every valid right endpoint, so the
 * later index can be discarded as a candidate.
 *
 * First, scan left to right and push indices that set a new prefix minimum.
 * Then scan right to left. When nums[j] is at least the value at the top
 * candidate index i, calculate j - i and pop i. The first valid endpoint
 * encountered for i is its farthest-right endpoint, so it gives the widest
 * ramp that starts there.
 *
 * Why It Works:
 * The stack stores candidate starting indices in increasing index order
 * with strictly decreasing values. Any index omitted during the first scan
 * has an earlier index with an equal or smaller value, which can form an
 * equal or wider ramp with any right endpoint that works for the omitted
 * index. During the reverse scan, right endpoints are considered from
 * farthest to nearest. Once a candidate start matches, no later scan step
 * can give it a wider ramp, so it can be removed permanently.
 *
 * Complexity:
 * Time: O(n). Every candidate index is pushed once and popped at most once.
 * Auxiliary Space: O(n) for the candidate stack.
 *
 * Common Mistakes:
 * - Removing an earlier candidate when a later value is smaller. The earlier
 *   index may still form a wider ramp with a future endpoint.
 * - Keeping every index instead of only new prefix minima.
 * - Using if instead of while in the reverse scan; one endpoint may match
 *   several candidate starts.
 * - Using a strict comparison for the ramp check. Equal endpoint values are
 *   allowed, so use nums[i] <= nums[j].
 * - Pushing equal prefix minima; the earlier equal index is a better start.
 */
public class MaximumWidthRamp {

    /**
     * Returns the maximum width of a valid ramp.
     *
     * @param nums input array
     * @return maximum ramp width, or 0 if no positive-width ramp exists
     */
    public int maxWidthRamp(int[] nums) {
        Deque<Integer> candidateStarts = new ArrayDeque<>();
        int maximumWidth = 0;

        // Keep only indices whose values are new strict prefix minima.
        for (int i = 0; i < nums.length; i++) {
            if (candidateStarts.isEmpty()
                    || nums[i] < nums[candidateStarts.peek()]) {
                candidateStarts.push(i);
            }
        }

        // The first matching right endpoint for each start is the farthest one.
        for (int j = nums.length - 1; j >= 0; j--) {
            while (!candidateStarts.isEmpty()
                    && nums[candidateStarts.peek()] <= nums[j]) {
                maximumWidth = Math.max(maximumWidth, j - candidateStarts.pop());
            }
        }

        return maximumWidth;
    }
}
