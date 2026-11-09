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
 * Approach: Count the subarrays for which each array position is the minimum.
 *
 * Key Insight:
 * For each index i, find the nearest smaller value to its left and right.
 * If their indices are left and right, respectively, then arr[i] is the
 * minimum of (i - left) * (right - i) subarrays. The left boundary keeps
 * equal values as candidates, while the right boundary removes equal values;
 * this assigns subarrays with duplicate minimums consistently to one index.
 *
 * Why It Works:
 * Choosing a valid starting position left of i and ending position right of i
 * forms exactly one subarray in which arr[i] is a minimum. The asymmetric
 * handling of equal values ensures every subarray is counted once even when
 * its minimum occurs more than once. Summing each index's value times its
 * subarray count therefore gives the sum of all subarray minimums.
 *
 * Complexity:
 * Time: O(n). Each index is pushed and popped at most once in each pass.
 * Auxiliary Space: O(n) for the two boundary arrays and stacks.
 *
 * Common Mistakes:
 * - Using the same equality rule in both passes, which can miscount duplicate
 *   minimums.
 * - Using int for the contribution product; cast before multiplication to
 *   avoid overflow.
 * - Using -1 for a missing right boundary; arr.length is the exclusive bound.
 */
public class SumOfSubarrayMinimumsByBoundaries {

    private static final int MOD = 1_000_000_007;

    /**
     * Returns the sum of all subarray minimums modulo MOD.
     *
     * @param arr input array
     * @return sum of subarray minimums modulo MOD
     */
    public int sumSubarrayMins(int[] arr) {
        long sumOfElements = 0;

        int[] prevSmaller = prevSmallerElement(arr);
        int[] nextSmaller = nextSmallerElement(arr);

        for (int i = 0; i < arr.length; i++) {
            long contribution = (long) arr[i]
                    * (i - prevSmaller[i])
                    * (nextSmaller[i] - i);
            sumOfElements = (sumOfElements + contribution) % MOD;
        }

        return (int) sumOfElements;
    }

    /** Returns the index of the previous smaller-or-equal value, or -1. */
    public int[] prevSmallerElement(int[] arr) {
        int[] previous = new int[arr.length];
        Deque<Integer> increasingStack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!increasingStack.isEmpty()
                    && arr[increasingStack.peek()] > arr[i]) {
                increasingStack.pop();
            }

            previous[i] = increasingStack.isEmpty() ? -1 : increasingStack.peek();
            increasingStack.push(i);
        }

        return previous;
    }

    /** Returns the index of the next strictly smaller value, or arr.length. */
    public int[] nextSmallerElement(int[] arr) {
        int[] next = new int[arr.length];
        Deque<Integer> increasingStack = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!increasingStack.isEmpty()
                    && arr[increasingStack.peek()] >= arr[i]) {
                increasingStack.pop();
            }

            next[i] = increasingStack.isEmpty() ? arr.length : increasingStack.peek();
            increasingStack.push(i);
        }

        return next;
    }
}
