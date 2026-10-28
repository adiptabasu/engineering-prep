import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 907: Sum of Subarray Minimums
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 *
 * Problem:
 * Return the sum of the minimum value of every non-empty contiguous
 * subarray, modulo 1_000_000_007.
 *
 * Approach: Monotonic stack of (minimum, count) groups.
 *
 * Key Insight:
 * For each array position, group all subarrays ending there by their minimum.
 * When a new value arrives, it becomes the minimum for its one-element
 * subarray and for every previous group whose minimum is greater than or
 * equal to it. Those groups can be merged by adding their counts.
 *
 * The stack stores these groups in strictly increasing minimum order.
 * `endingSum` is the sum of the minimums of all subarrays ending at the
 * current position. Popping a group removes its old contribution; pushing
 * the merged group adds the contribution using the new minimum. Add
 * `endingSum` to the answer at each position to include all subarrays ending
 * there.
 *
 * Why It Works:
 * Every subarray has a unique ending position. At each position, the stack
 * accounts for every subarray ending there exactly once through its
 * minimum-count groups. Summing `endingSum` over all positions therefore
 * sums the minimum of every non-empty subarray exactly once.
 *
 * Complexity:
 * Time: O(n). Each group is pushed once and popped at most once.
 * Auxiliary Space: O(n) for the stack.
 *
 * Common Mistakes:
 * - Tracking only the minimum of the whole prefix; subarrays ending at the
 *   same index can have different minimums.
 * - Counting groups without merging their old contributions out of
 *   `endingSum` before adding the merged group.
 * - Using int for products or the running sum; intermediate values can
 *   overflow before applying the modulo.
 */
public class SumOfSubarrayMinimums {

    private static final int MOD = 1_000_000_007;

    /**
     * Returns the sum of all subarray minimums modulo MOD.
     *
     * @param arr input array
     * @return sum of subarray minimums modulo MOD
     */
    public int sumSubarrayMins(int[] arr) {
        Deque<Group> minimaStack = new ArrayDeque<>();
        long endingSum = 0;
        long totalSum = 0;

        for (int value : arr) {
            int count = 1;

            // The new value becomes the minimum for each popped group's subarrays.
            while (!minimaStack.isEmpty()
                    && minimaStack.peek().minimum >= value) {
                Group group = minimaStack.pop();
                endingSum -= (long) group.minimum * group.count;
                count += group.count;
            }

            minimaStack.push(new Group(value, count));
            endingSum += (long) value * count;
            totalSum = (totalSum + endingSum) % MOD;
        }

        return (int) totalSum;
    }

    private static class Group {
        private final int minimum;
        private final int count;

        private Group(int minimum, int count) {
            this.minimum = minimum;
            this.count = count;
        }
    }
}
