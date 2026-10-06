/*
 * LeetCode:
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Problem:
 * Given an integer array nums, return the length of the longest
 * strictly increasing subsequence.
 *
 * A subsequence does not need to be contiguous.
 * Elements can be skipped, but their original order must be preserved.
 *
 * Example:
 *
 * nums = [10,9,2,5,3,7,101,18]
 *
 * Longest increasing subsequence:
 * [2,3,7,101]
 *
 * Answer = 4
 *
 *
 * ---------------------------------------------------------------
 * Brute Force
 * ---------------------------------------------------------------
 *
 * We could generate every possible subsequence and check whether
 * it is increasing.
 *
 * There are 2^n possible subsequences, so this becomes exponential:
 *
 * Time: O(2^n)
 *
 * This is not practical.
 *
 *
 * ---------------------------------------------------------------
 * Dynamic Programming Approach
 * ---------------------------------------------------------------
 *
 * Define:
 *
 *     dp[i] = length of the longest increasing subsequence
 *             that ends exactly at nums[i].
 *
 * The important phrase is "ends exactly at nums[i]".
 *
 * For example:
 *
 * nums = [2,5,7]
 *
 * dp:
 *
 *     2 -> [2]       -> 1
 *     5 -> [2,5]     -> 2
 *     7 -> [2,5,7]   -> 3
 *
 *
 * Every element can form a subsequence by itself.
 *
 * Therefore:
 *
 *     dp[i] = 1
 *
 * initially.
 *
 *
 * Now consider a previous index j.
 *
 * If:
 *
 *     nums[j] < nums[i]
 *
 * then nums[i] can be appended to an increasing subsequence
 * ending at nums[j].
 *
 * Therefore:
 *
 *     dp[i] = max(dp[i], dp[j] + 1)
 *
 * We check every previous j where:
 *
 *     j < i
 *     nums[j] < nums[i]
 *
 *
 * ---------------------------------------------------------------
 * Why Do We Need a Global Maximum?
 * ---------------------------------------------------------------
 *
 * The longest increasing subsequence does not necessarily end
 * at the last element.
 *
 * Example:
 *
 *     nums = [1,2,3,0]
 *
 * dp:
 *
 *     [1,2,3,1]
 *
 * The answer is 3, not dp[3] = 1.
 *
 * Therefore, we keep track of the largest dp[i].
 *
 *
 * ---------------------------------------------------------------
 * Complexity
 * ---------------------------------------------------------------
 *
 * We compare every element with every previous element.
 *
 * Time:  O(n^2)
 * Space: O(n)
 */

public class LongestIncreasingSubsequence
{
    public int lengthOfLIS(int[] nums)
    {
        int maxLengthOfSequence = 0;

        /*
         * dp[i] represents the length of the longest increasing
         * subsequence that ends exactly at index i.
         */
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++)
        {
            /*
             * Every individual element is itself an increasing
             * subsequence of length 1.
             */
            dp[i] = 1;

            /*
             * Look at every element before nums[i].
             */
            for (int j = 0; j < i; j++)
            {
                /*
                 * If nums[j] is smaller than nums[i], we can append
                 * nums[i] to the increasing subsequence ending at j.
                 */
                if (nums[j] < nums[i])
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            /*
             * The longest increasing subsequence can end anywhere
             * in the array, not necessarily at the final element.
             */
            maxLengthOfSequence =
                    Math.max(maxLengthOfSequence, dp[i]);
        }

        return maxLengthOfSequence;
    }

    /*
     * Local testing.
     */
    public static void main(String[] args)
    {
        LongestIncreasingSubsequence solution =
                new LongestIncreasingSubsequence();

        System.out.println(
                solution.lengthOfLIS(
                        new int[]{10,9,2,5,3,7,101,18}
                )
        ); // Expected: 4

        System.out.println(
                solution.lengthOfLIS(
                        new int[]{0,1,0,3,2,3}
                )
        ); // Expected: 4

        System.out.println(
                solution.lengthOfLIS(
                        new int[]{7,7,7,7,7,7,7}
                )
        ); // Expected: 1

        System.out.println(
                solution.lengthOfLIS(
                        new int[]{1,2,3,0}
                )
        ); // Expected: 3
    }
}
