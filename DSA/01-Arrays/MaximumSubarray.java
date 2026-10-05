/*
 * LeetCode:
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Problem:
 * Given an integer array nums, find the contiguous subarray with
 * the largest sum and return its sum.
 *
 * Example:
 *
 * nums = [-2,1,-3,4,-1,2,1,-5,4]
 *
 * The maximum-sum subarray is:
 *
 * [4,-1,2,1]
 *
 * Sum:
 *
 * 4 + (-1) + 2 + 1 = 6
 *
 * Answer = 6
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * We could consider every possible starting position and extend
 * the subarray one element at a time.
 *
 * Example:
 *
 * [-2,1,-3,4]
 *
 * Starting at index 0:
 *
 * [-2]          -> -2
 * [-2,1]        -> -1
 * [-2,1,-3]     -> -4
 * [-2,1,-3,4]   -> 0
 *
 * Starting at index 1:
 *
 * [1]           -> 1
 * [1,-3]        -> -2
 * [1,-3,4]      -> 2
 *
 * And so on.
 *
 * There are O(n^2) possible contiguous subarrays.
 *
 * Time Complexity:  O(n^2)
 * Space Complexity: O(1)
 *
 *
 * ============================================================
 * KEY OBSERVATION
 * ============================================================
 *
 * At every index, we only need to answer:
 *
 * "What is the maximum sum of a subarray that MUST end here?"
 *
 * Suppose the current number is x.
 *
 * There are only two possibilities:
 *
 * 1. Start a new subarray at x.
 *
 *        x
 *
 * 2. Extend the best subarray ending at the previous index.
 *
 *        previousBest + x
 *
 * Therefore:
 *
 * currentBest = max(
 *     x,
 *     previousBest + x
 * )
 *
 *
 * ============================================================
 * WHY THIS WORKS
 * ============================================================
 *
 * Consider:
 *
 * [-2, 1, -3, 4]
 *
 * At -2:
 *
 * Best subarray ending here:
 *
 * [-2] -> -2
 *
 *
 * At 1:
 *
 * Two choices:
 *
 * [1]       -> 1
 * [-2,1]    -> -1
 *
 * Therefore:
 *
 * currentBest = 1
 *
 * We discard the -2 because carrying it forward would make the
 * sum smaller.
 *
 *
 * At -3:
 *
 * Two choices:
 *
 * [-3]      -> -3
 * [1,-3]    -> -2
 *
 * Therefore:
 *
 * currentBest = -2
 *
 *
 * At 4:
 *
 * Two choices:
 *
 * [4]           -> 4
 * [1,-3,4]      -> 2
 *
 * Therefore:
 *
 * currentBest = 4
 *
 * This demonstrates the key idea:
 *
 * If the previous best sum is hurting the current subarray,
 * start fresh.
 *
 *
 * ============================================================
 * KADANE'S ALGORITHM
 * ============================================================
 *
 * Kadane's Algorithm applies exactly this recurrence:
 *
 *     currentMax = max(nums[i], currentMax + nums[i])
 *
 * We also maintain a global answer:
 *
 *     globalMaxSum
 *
 * because the best subarray ending at the current position is
 * not necessarily the best subarray in the entire array.
 *
 *
 * ============================================================
 * LOCAL STATE VS GLOBAL STATE
 * ============================================================
 *
 * currentMax:
 *
 * Maximum sum of a subarray that MUST end at the current index.
 *
 *
 * globalMaxSum:
 *
 * Maximum subarray sum found anywhere in the array so far.
 *
 *
 * Example:
 *
 * nums = [-2, 1, -3, 4, -1, 2, 1]
 *
 * currentMax values:
 *
 * -2 -> 1 -> -2 -> 4 -> 3 -> 5 -> 6
 *
 * globalMaxSum values:
 *
 * -2 -> 1 -> 1 -> 4 -> 4 -> 5 -> 6
 *
 * Final answer:
 *
 * 6
 *
 *
 * ============================================================
 * WHY WE INITIALIZE WITH nums[0]
 * ============================================================
 *
 * We cannot initialize currentMax and globalMaxSum to 0.
 *
 * Consider:
 *
 * nums = [-5, -2, -8]
 *
 * The correct answer is:
 *
 * -2
 *
 * because [-2] is the largest-sum non-empty subarray.
 *
 * If we initialized the answer to 0, we would incorrectly return
 * 0, which is not a valid answer because the problem requires a
 * non-empty subarray.
 *
 * Therefore:
 *
 *     currentMax = nums[0]
 *     globalMaxSum = nums[0]
 *
 *
 * ============================================================
 * ALGORITHM
 * ============================================================
 *
 * 1. Initialize currentMax and globalMaxSum using nums[0].
 *
 * 2. Starting from index 1:
 *
 *      a. Decide whether to start a new subarray or extend the
 *         previous one.
 *
 *      b. Update currentMax.
 *
 *      c. Update globalMaxSum.
 *
 * 3. Return globalMaxSum.
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity:
 * O(n)
 *
 * We process every element exactly once.
 *
 * Space Complexity:
 * O(1)
 *
 * Only a constant number of variables are used.
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Initializing the answer to 0.
 *
 *    This fails when every number is negative.
 *
 *
 * 2. Tracking only currentMax.
 *
 *    currentMax tells us about the subarray ending at the current
 *    position. We still need globalMaxSum to remember the best
 *    result encountered anywhere.
 *
 *
 * 3. Forgetting that the subarray must be contiguous.
 *
 *    We can only extend the immediately previous subarray.
 *
 *
 * 4. Confusing "best ending here" with "best overall".
 *
 *    These are two different states.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Can Kadane's Algorithm return the actual subarray?
 *
 * A:
 * Yes. We can maintain start and end indices and update them
 * whenever we decide to start a new subarray or find a new global
 * maximum.
 *
 *
 * Q: What if all numbers are negative?
 *
 * A:
 * Initializing the states with nums[0] ensures that the largest
 * individual negative number is returned.
 *
 *
 * Q: What is the main idea behind Kadane's Algorithm?
 *
 * A:
 * At every position, decide whether the previous subarray is
 * helping or hurting the current number.
 *
 * If it is hurting, start a new subarray.
 *
 *
 * Q: Why is this considered a dynamic-programming-style solution?
 *
 * A:
 * The solution maintains the optimal state for the previous
 * position and uses it to construct the optimal state for the
 * current position.
 */

public class MaximumSubarray
{
    public int maxSubArray(int[] nums)
    {
        /*
         * Best sum of a subarray ending at the current index.
         *
         * We initialize with nums[0] because the subarray must
         * contain at least one element.
         */
        int currentMax = nums[0];

        /*
         * Best subarray sum found anywhere in the array so far.
         */
        int globalMaxSum = nums[0];

        for (int i = 1; i < nums.length; i++)
        {
            /*
             * We have two choices:
             *
             * 1. Start a new subarray with nums[i].
             *
             * 2. Extend the best subarray that ended at i - 1.
             *
             * Choose whichever gives the larger sum.
             */
            currentMax = Math.max(
                nums[i],
                currentMax + nums[i]
            );

            /*
             * currentMax only represents the best subarray
             * ending at the current position.
             *
             * Keep track of the best result found anywhere.
             */
            globalMaxSum = Math.max(
                globalMaxSum,
                currentMax
            );
        }

        return globalMaxSum;
    }


    /*
     * ============================================================
     * LOCAL TESTS
     * ============================================================
     */
    public static void main(String[] args)
    {
        MaximumSubarray solution = new MaximumSubarray();

        System.out.println(
            solution.maxSubArray(
                new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}
            )
        );
        // Expected: 6
        // [4,-1,2,1] -> 6


        System.out.println(
            solution.maxSubArray(
                new int[]{1}
            )
        );
        // Expected: 1


        System.out.println(
            solution.maxSubArray(
                new int[]{5, 4, -1, 7, 8}
            )
        );
        // Expected: 23


        System.out.println(
            solution.maxSubArray(
                new int[]{-5, -2, -8}
            )
        );
        // Expected: -2


        System.out.println(
            solution.maxSubArray(
                new int[]{-2, -1}
            )
        );
        // Expected: -1


        System.out.println(
            solution.maxSubArray(
                new int[]{-2, 1}
            )
        );
        // Expected: 1
    }
}
