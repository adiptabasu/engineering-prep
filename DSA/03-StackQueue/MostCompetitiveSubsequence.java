import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1673: Find the Most Competitive Subsequence
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/
 *
 * Problem:
 * Return the lexicographically smallest subsequence of nums with length k.
 * The relative order of the selected elements must match their order in nums.
 *
 * Example:
 * Input:  nums = [3, 5, 2, 6], k = 2
 * Output: [2, 6]
 *
 * Approach: Greedy monotonic stack with a deletion budget.
 *
 * Key Insight:
 * A smaller current value improves the result if it can replace a larger
 * earlier candidate while still leaving enough elements to build a
 * subsequence of length k. At most nums.length - k elements may be removed.
 *
 * Scan from left to right. While the stack's last value is larger than the
 * current value and removals remain, discard that larger value. Push the
 * current value, then use any remaining removals on the stack's end. When
 * removals remain after the scan, the stack is nondecreasing, so removing its
 * last values preserves the smallest prefix.
 *
 * Why It Works:
 * Lexicographic order is decided by the first differing position. Replacing
 * a larger earlier candidate with a smaller later value makes the result
 * smaller, provided enough elements remain to complete length k. The
 * deletion budget enforces that feasibility. If the scan ends with unused
 * removals, the remaining suffix is nondecreasing, so removing its rightmost
 * values preserves the smallest possible prefix. Reversing the stack pop
 * order restores the subsequence's original order.
 *
 * Complexity:
 * Time: O(n). Each value is pushed once and popped at most once.
 * Auxiliary Space: O(n) for the stack. The returned array uses O(k) space.
 *
 * Common Mistakes:
 * - Choosing the globally smallest k values without preserving their order.
 * - Popping a larger value after the deletion budget is exhausted.
 * - Forgetting to remove unused deletions from the end after the scan.
 * - Decrementing the remaining deletion count both in the loop condition
 *   and in its body.
 * - Popping values into the result from left to right, reversing their order.
 */
public class MostCompetitiveSubsequence {

    /**
     * Returns the lexicographically smallest subsequence of length k.
     *
     * @param nums input array
     * @param k required subsequence length
     * @return most competitive subsequence
     */
    public int[] mostCompetitive(int[] nums, int k) {
        int[] result = new int[k];
        int removalsRemaining = nums.length - k;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int value : nums) {
            // Replace larger earlier values while enough removals remain to
            // still select k elements overall.
            while (!stack.isEmpty()
                    && stack.peek() > value
                    && removalsRemaining > 0) {
                stack.pop();
                removalsRemaining--;
            }

            stack.push(value);
        }

        // If no later smaller value used these removals, discard from the end.
        while (removalsRemaining > 0) {
            stack.pop();
            removalsRemaining--;
        }

        // The stack top is the last selected value, so fill the result backward.
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
