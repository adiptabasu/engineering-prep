import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 456: 132 Pattern
 * https://leetcode.com/problems/132-pattern/
 *
 * Problem:
 * Determine whether there are indices i < j < k such that
 * nums[i] < nums[k] < nums[j].
 *
 * Example:
 * Input:  [3, 5, 0, 4]
 * Output: true
 * Explanation: nums[0] = 3 < nums[3] = 4 < nums[1] = 5.
 *
 * Approach: Right-to-left scan with a monotonic stack.
 *
 * Key Insight:
 * Scan from right to left. Keep a stack of possible high values (the "3")
 * and a separate candidate for the middle value (the "2"). If the current
 * value is smaller than that middle candidate, the current value supplies
 * the earlier low value (the "1") and the pattern is complete.
 *
 * While scanning, when the current value is greater than values on the
 * stack, each popped value can serve as the middle value after the current
 * value. Save the popped value as the middle candidate, then push the current
 * value for earlier positions to consider.
 *
 * Why It Works:
 * The saved middle candidate is a value at a later index that is smaller
 * than a larger value between it and the current scan position. Therefore,
 * if the current value is smaller than the saved candidate, all three
 * values occur in the required index order and satisfy the strict
 * inequalities. Popping a stack value smaller than the current value creates
 * such a high/middle pair. The popped value is no longer needed as a high
 * candidate: current is larger, occurs earlier, and the popped value is now
 * saved as a valid middle candidate.
 *
 * Complexity:
 * Time: O(n). Each value is pushed once and popped at most once.
 * Auxiliary Space: O(n) for the stack.
 *
 * Common Mistakes:
 * - Mixing the value order 1 < 2 < 3 with the array's index order. The
 *   pattern appears as low, high, middle in the array.
 * - Using a suffix maximum as the only candidate; a larger later value can
 *   hide a useful smaller middle value.
 * - Checking the current value against the middle candidate after popping.
 *   The current value is the potential low and must be checked first.
 * - Using non-strict comparisons. All three values must satisfy strict
 *   inequalities.
 */
public class Find132Pattern {

    /**
     * Returns whether nums contains a 132 pattern.
     *
     * @param nums input array
     * @return true if indices i < j < k satisfy nums[i] < nums[k] < nums[j]
     */
    public boolean find132pattern(int[] nums) {
        Deque<Integer> possibleHighValues = new ArrayDeque<>();
        int middleCandidate = 0;
        boolean hasMiddleCandidate = false;

        // Moving right to left makes every saved candidate occur after the
        // current index in the original array.
        for (int i = nums.length - 1; i >= 0; i--) {
            int current = nums[i];

            // The current value is the low value; the saved candidate already
            // has a larger high value between it and this index.
            if (hasMiddleCandidate && current < middleCandidate) {
                return true;
            }

            // Each smaller popped value can be the middle value paired with
            // current as a larger value to its left.
            while (!possibleHighValues.isEmpty()
                    && possibleHighValues.peek() < current) {
                middleCandidate = possibleHighValues.pop();
                hasMiddleCandidate = true;
            }

            // This value may be a high value for an earlier low value.
            possibleHighValues.push(current);
        }

        return false;
    }
}
