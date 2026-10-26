import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 503: Next Greater Element II
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * Problem:
 * For each position in a circular integer array, find the first value
 * strictly greater than the current value while moving to the right.
 * Return -1 when no such value exists.
 *
 * Example:
 * Input:  [1, 2, 1]
 * Output: [2, -1, 2]
 *
 * Approach: Reverse traversal with a monotonic stack.
 *
 * Treat the array as two consecutive copies by iterating over 2 * n virtual
 * positions. The modulo operation maps each virtual position back to the
 * original array. During the second half of this reverse traversal, the
 * stack contains candidates from the circular portion to the right.
 *
 * Before answering a position, remove stack values that are less than or
 * equal to the current value. They cannot be the strictly greater answer.
 * The top remaining value, if any, is the nearest greater candidate. Only
 * write answers during the original-array half of the traversal.
 *
 * Why It Works:
 * Every value to the right in circular order is visited before the current
 * position. The stack removes values that are no greater than a later,
 * closer value, so they cannot be the next greater answer for this position
 * or any earlier position that still needs them. After removing values that
 * are not strictly greater than the current one, the stack top is the
 * nearest remaining greater value. If the stack is empty, no greater value
 * exists in the rest of the circular traversal.
 *
 * Complexity:
 * Time: O(n). Each virtual value is pushed once and popped at most once.
 * Auxiliary Space: O(n) for the stack. The returned array uses O(n) space.
 *
 * Common Mistakes:
 * - Pop values strictly smaller than the current value, leaving equal values
 *   that do not satisfy the problem's strictly-greater requirement.
 * - Include an extra virtual position; the valid virtual indices are 0
 *   through 2 * n - 1.
 * - Write an answer during both halves, which obscures which pass computes
 *   the final answer.
 * - Forget that the array is circular and stop after reaching its end.
 */
public class NextGreaterElementII {

    /**
     * Returns the next greater value for each position in a circular array.
     *
     * @param nums input array
     * @return next greater values, or -1 where none exists
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nextGreater = new int[n];
        Deque<Integer> candidates = new ArrayDeque<>();

        // Scan two virtual copies from right to left to account for wrapping.
        for (int i = 2 * n - 1; i >= 0; i--) {
            int position = i % n;
            int current = nums[position];

            // Equal values are not strictly greater, so discard them too.
            while (!candidates.isEmpty() && candidates.peek() <= current) {
                candidates.pop();
            }

            // The first n virtual positions prepare candidates for wrapping.
            // The second n positions correspond to the original array answers.
            if (i < n) {
                nextGreater[position] = candidates.isEmpty()
                        ? -1
                        : candidates.peek();
            }

            // The current value may be the next greater candidate for an
            // earlier position in this reverse traversal.
            candidates.push(current);
        }

        return nextGreater;
    }
}
