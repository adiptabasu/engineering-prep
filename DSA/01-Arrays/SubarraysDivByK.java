/*
 * LeetCode: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * Problem:
 * Given an integer array nums and an integer k, return the number of
 * non-empty subarrays whose sum is divisible by k.
 *
 * Core Idea:
 * Use prefix sums and store the frequency of each prefix sum's remainder
 * when divided by k.
 *
 * If two prefix sums have the same remainder:
 *
 *     prefixSum2 % k == prefixSum1 % k
 *
 * then:
 *
 *     (prefixSum2 - prefixSum1) % k == 0
 *
 * Therefore, the subarray between those two prefix sums has a sum
 * divisible by k.
 */

public class SubarraysDivByK
{
    public int subarraysDivByK(int[] nums, int k)
    {
        int subarrayCount = 0;
        int prefixSum = 0;

        /*
         * remainderFrequency[r] stores how many prefix sums
         * encountered so far had remainder r.
         *
         * Since every possible remainder is between 0 and k-1,
         * an array is sufficient instead of a HashMap.
         */
        int[] remainderFrequency = new int[k];

        /*
         * An empty prefix sum has:
         *
         *     prefixSum = 0
         *     remainder = 0
         *
         * We need to record this before processing the array so that
         * subarrays starting at index 0 can be counted.
         *
         * Example:
         *
         * nums = [2, 3], k = 5
         *
         * prefixSum becomes 5.
         * Since remainder 0 has already appeared once (the empty prefix),
         * [2, 3] is correctly counted.
         */
        remainderFrequency[0]++;

        for (int i = 0; i < nums.length; i++)
        {
            // Add the current number to the cumulative prefix sum.
            prefixSum += nums[i];

            /*
             * Java's % operator returns the remainder, which can be negative.
             *
             * Example:
             *
             *     -2 % 5 = -2
             *
             * However, mathematically, -2 has the same modulo-5 class as 3:
             *
             *     -2 ≡ 3 (mod 5)
             *
             * Since remainderFrequency is an array, we need a remainder
             * between 0 and k-1.
             *
             * The following converts a negative remainder into its
             * equivalent positive remainder:
             *
             *     ((prefixSum % k) + k) % k
             *
             * Example:
             *
             *     prefixSum = -2, k = 5
             *
             *     (-2 % 5)       = -2
             *     (-2 + 5)       = 3
             *     3 % 5          = 3
             *
             * So the normalized remainder is 3.
             *
             * We cannot simply use Math.abs(prefixSum % k), because
             * absolute value does NOT give the mathematically equivalent
             * modulo class in general.
             */
            int remainder = ((prefixSum % k) + k) % k;

            /*
             * If this remainder has appeared before, every previous
             * prefix sum with the same remainder forms a subarray whose
             * sum is divisible by k.
             *
             * Therefore, the frequency directly tells us how many
             * new valid subarrays end at the current index.
             */
            subarrayCount += remainderFrequency[remainder];

            // Record the current prefix sum's remainder for future subarrays.
            remainderFrequency[remainder]++;
        }

        return subarrayCount;
    }

    /*
     * Complexity:
     *
     * Time:  O(n)
     * Space: O(k)
     *
     * We process every element once and maintain a frequency array
     * containing k possible remainders.
     */
}
