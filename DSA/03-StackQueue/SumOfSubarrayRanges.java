import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 2104: Sum of Subarray Ranges
 * https://leetcode.com/problems/sum-of-subarray-ranges/
 *
 * Problem:
 * Return the sum of the ranges of every non-empty contiguous subarray. A
 * subarray's range is its maximum value minus its minimum value.
 *
 * Approach: Count each element's contribution as a maximum and as a minimum.
 *
 * Key Insight:
 * For each index, the distances to the previous and next boundary determine
 * how many subarrays use that element as their maximum or minimum. Sum the
 * maximum contributions, then subtract the minimum contributions. Each pair
 * of passes uses asymmetric comparisons so duplicate extrema are assigned
 * consistently to one index.
 *
 * Why It Works:
 * Every subarray contributes its maximum once to the maximum total and its
 * minimum once to the minimum total. Their difference is exactly that
 * subarray's range. Summing these per-index contributions therefore gives
 * the sum of all subarray ranges.
 *
 * Complexity:
 * Time: O(n). Each index is pushed and popped at most once per pass.
 * Auxiliary Space: O(n) for the boundary arrays and stacks.
 *
 * Common Mistakes:
 * - Subtracting maximum contributions from minimum contributions instead of
 *   the other way around.
 * - Using the same equality rule in both boundary passes, which can
 *   double-count or omit subarrays with duplicate extrema.
 * - Multiplying as int before widening to long.
 */
public class SumOfSubarrayRanges {

    /**
     * Returns the sum of all subarray ranges.
     *
     * @param nums input array
     * @return sum of subarray ranges
     */
    public long subArrayRanges(int[] nums) {
        long sumOfRanges = 0;

        int[] prevSmaller = prevSmallerElement(nums);
        int[] nextSmaller = nextSmallerElement(nums);
        int[] prevGreater = prevGreaterElement(nums);
        int[] nextGreater = nextGreaterElement(nums);

        for (int i = 0; i < nums.length; i++) {
            long maximumContribution = (long) nums[i]
                    * (i - prevGreater[i])
                    * (nextGreater[i] - i);
            long minimumContribution = (long) nums[i]
                    * (i - prevSmaller[i])
                    * (nextSmaller[i] - i);

            sumOfRanges += maximumContribution - minimumContribution;
        }

        return sumOfRanges;
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

    /** Returns the index of the previous greater-or-equal value, or -1. */
    public int[] prevGreaterElement(int[] arr) {
        int[] previous = new int[arr.length];
        Deque<Integer> decreasingStack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!decreasingStack.isEmpty()
                    && arr[decreasingStack.peek()] < arr[i]) {
                decreasingStack.pop();
            }

            previous[i] = decreasingStack.isEmpty() ? -1 : decreasingStack.peek();
            decreasingStack.push(i);
        }

        return previous;
    }

    /** Returns the index of the next strictly greater value, or arr.length. */
    public int[] nextGreaterElement(int[] arr) {
        int[] next = new int[arr.length];
        Deque<Integer> decreasingStack = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!decreasingStack.isEmpty()
                    && arr[decreasingStack.peek()] <= arr[i]) {
                decreasingStack.pop();
            }

            next[i] = decreasingStack.isEmpty() ? arr.length : decreasingStack.peek();
            decreasingStack.push(i);
        }

        return next;
    }
}
