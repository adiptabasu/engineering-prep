import java.util.Arrays;

/**
 * LeetCode 410: Split Array Largest Sum
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Problem:
 * Split a non-negative integer array nums into k non-empty contiguous
 * subarrays so that the largest subarray sum is minimized.
 *
 * Approach:
 * Binary-search the allowed maximum subarray sum. For a candidate limit,
 * greedily extend the current contiguous subarray until adding the next element
 * would exceed the limit, then start a new subarray. The limit is feasible
 * when this uses at most k subarrays.
 *
 * Key insight / invariant:
 * Feasibility is monotone: if maxSubArraySum works, every larger limit also
 * works. When mid is feasible, save it as a candidate and search left
 * (high = mid - 1) for a smaller maximum sum; otherwise search right.
 * Accepting <= k subarrays is valid because if greedy needs fewer than k
 * groups, any multi-element group can be split until exactly k non-empty
 * subarrays exist (since k <= nums.length) without raising the maximum sum.
 *
 * Bounds:
 * - Lower bound: max(nums), since every element must belong to some subarray.
 * - Upper bound: sum(nums), since one subarray containing all elements is
 *   always within the sum.
 * - long avoids overflow if the total sum exceeds Integer.MAX_VALUE.
 *
 * Edge cases:
 * - nums.length < k: impossible to form k non-empty subarrays, returns -1.
 * - Zero elements (nums[i] == 0): if all elements are 0, currentSum stays 0
 *   and currentNumberOfSplits stays 0 <= k (still returns true for k >= 1).
 *
 * Complexity:
 * O(n log S) time, where n = nums.length and S = sum(nums); O(1) auxiliary
 * space.
 */
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }

        long minOfMaxSum = Arrays.stream(nums).asLongStream().sum();
        long low = Arrays.stream(nums).max().getAsInt();
        long high = minOfMaxSum;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (isSubArraySumPossible(nums, k, mid)) {
                // mid is feasible; keep the smallest feasible sum seen so far
                // and search left for a tighter upper limit.
                minOfMaxSum = minOfMaxSum < mid ? minOfMaxSum : mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) minOfMaxSum;
    }

    /**
     * Returns whether nums can be partitioned into at most numberOfSplits
     * contiguous subarrays with each subarray sum <= maxSubArraySum.
     */
    private boolean isSubArraySumPossible(
            int[] nums, int numberOfSplits, long maxSubArraySum) {
        int currentNumberOfSplits = 0;
        long currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (currentSum + nums[i] <= maxSubArraySum) {
                currentSum += nums[i];
            } else {
                // Close the completed subarray and start a new one at nums[i].
                currentNumberOfSplits++;
                currentSum = nums[i];
            }

            if (currentNumberOfSplits > numberOfSplits) {
                return false;
            }
        }

        // Count the trailing non-empty subarray accumulated after the last split
        // (for non-zero sums; starting currentNumberOfSplits at 1 avoids this).
        if (currentSum > 0) {
            currentNumberOfSplits++;
        }

        return currentNumberOfSplits <= numberOfSplits;
    }
}
