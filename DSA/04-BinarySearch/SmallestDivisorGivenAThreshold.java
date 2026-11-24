import java.util.Arrays;

/**
 * LeetCode 1283: Find the Smallest Divisor Given a Threshold
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Problem:
 * Find the smallest positive divisor such that the sum of each nums[i]
 * divided by the divisor, rounded up, is at most threshold.
 *
 * Approach: Binary-search divisors from 1 to the largest number. For each
 * candidate, sum the rounded-up quotients and check whether the sum is within
 * threshold.
 *
 * Key Insight:
 * As the divisor grows, each rounded-up quotient stays the same or decreases.
 * Therefore a feasible divisor makes every larger divisor feasible too.
 *
 * Why It Works:
 * A feasible midpoint may be the smallest valid divisor, so save it and search
 * left. An infeasible midpoint is too small, so search right.
 *
 * Complexity:
 * Time: O(n log M), where n is nums.length and M is the largest number.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Starting the divisor range at the smallest array value; divisor 1 is valid.
 * - Using floor division instead of rounding each quotient up.
 * - Moving right after a feasible divisor instead of checking smaller ones.
 */
public class SmallestDivisorGivenAThreshold {

    /**
     * Returns the smallest divisor whose rounded-up quotient sum is within
     * threshold.
     *
     * @param nums positive integers
     * @param threshold maximum allowed quotient sum
     * @return smallest feasible positive divisor
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = Arrays.stream(nums).max().getAsInt();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isLesserThanOrEqualToThreshold(nums, threshold, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    /**
     * Checks whether dividing every value by divisor, rounding each quotient
     * up, produces a sum no greater than threshold.
     */
    private boolean isLesserThanOrEqualToThreshold(
            int[] nums, int threshold, int divisor) {
        int sum = 0;

        for (int num : nums) {
            sum += num / divisor + (num % divisor == 0 ? 0 : 1);
            if (sum > threshold) {
                break;
            }
        }

        return sum <= threshold;
    }
}
