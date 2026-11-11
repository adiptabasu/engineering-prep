import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1856: Maximum Subarray Min-Product
 * https://leetcode.com/problems/maximum-subarray-min-product/
 *
 * Problem:
 * For every non-empty subarray, multiply its minimum value by its sum. Return
 * the maximum such product modulo 1_000_000_007.
 *
 * Approach: For each index, use monotonic-stack boundaries to find the widest
 * span where nums[i] is a minimum, then use prefix sums to get that span's sum.
 *
 * Key Insight:
 * The previous smaller-or-equal and next strictly smaller indices bound a
 * span in which nums[i] is a minimum. Since every nums value is positive,
 * extending the subarray within that span increases its sum without lowering
 * its minimum. Thus the widest valid span gives the best min-product for i.
 * Asymmetric equality handling assigns duplicate minimums consistently.
 *
 * Why It Works:
 * Every subarray has at least one minimum index. For each index, the boundary
 * span captures all positions that can be included while keeping nums[i] as
 * a minimum under the chosen tie rule. The full span maximizes the sum for
 * that minimum because all values are positive. Taking the maximum candidate
 * across indices therefore considers an optimal subarray.
 *
 * Prefix convention:
 * prefix[k] is the sum of nums[0..k-1]. For boundaries left and right, the
 * valid span is [left + 1, right - 1], whose sum is
 * prefix[right] - prefix[left + 1].
 *
 * Complexity:
 * Time: O(n). Each index is pushed and popped at most once in each stack pass.
 * Auxiliary Space: O(n) for the boundary arrays, prefix sums, and stacks.
 *
 * Common Mistakes:
 * - Using nextSmaller[i - 1] instead of nextSmaller[i] in the prefix lookup.
 * - Casting the best product to int before applying the modulo.
 * - Multiplying as int; widen nums[i] to long before the product.
 * - Applying modulo to each candidate before comparing; compare full products
 *   and apply modulo only to the final maximum.
 */
public class MaximumSubarrayMinProduct {

    private static final int MOD = 1_000_000_007;

    /**
     * Returns the maximum min-product modulo MOD.
     *
     * @param nums positive input values
     * @return maximum min-product modulo MOD
     */
    public int maxSumMinProduct(int[] nums) {
        int[] previous = prevSmallerElement(nums);
        int[] next = nextSmallerElement(nums);
        long[] prefix = prefixSum(nums);
        long bestMinProduct = 0;

        for (int i = 0; i < nums.length; i++) {
            long spanSum = prefix[next[i]] - prefix[previous[i] + 1];
            long minProduct = (long) nums[i] * spanSum;
            bestMinProduct = Math.max(bestMinProduct, minProduct);
        }

        return (int) (bestMinProduct % MOD);
    }

    /**
     * Finds the previous smaller-or-equal value's index for each position.
     * Popping only values greater than nums[i] leaves equal values available
     * as boundaries, which pairs with the strictly smaller right boundary to
     * assign duplicate minima consistently.
     */
    private int[] prevSmallerElement(int[] arr) {
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

    /**
     * Finds the next strictly smaller value's index for each position.
     * Popping greater-than-or-equal values makes the remaining top the nearest
     * strictly smaller boundary. arr.length is the exclusive right sentinel.
     */
    private int[] nextSmallerElement(int[] arr) {
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

    /** Builds exclusive prefix sums: prefix[k] is the sum of arr[0..k-1]. */
    private long[] prefixSum(int[] arr) {
        long[] prefix = new long[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
        return prefix;
    }
}
