/*
 * LeetCode:
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Problem:
 * Given an integer array nums, find a contiguous non-empty subarray
 * that has the largest product and return the product.
 *
 * Example:
 *
 * nums = [2, 3, -2, 4]
 *
 * The maximum-product subarray is:
 *
 * [2, 3] -> 6
 *
 * Answer = 6
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * We could consider every possible starting position and keep
 * multiplying elements as we extend the subarray.
 *
 * Example:
 *
 * [2, 3, -2, 4]
 *
 * Starting at index 0:
 *
 * [2]          -> 2
 * [2,3]        -> 6
 * [2,3,-2]     -> -12
 * [2,3,-2,4]   -> -48
 *
 * Starting at index 1:
 *
 * [3]          -> 3
 * [3,-2]       -> -6
 * [3,-2,4]     -> -24
 *
 * And so on.
 *
 * There are O(n^2) possible contiguous subarrays.
 *
 * Therefore:
 *
 * Time Complexity:  O(n^2)
 * Space Complexity: O(1)
 *
 *
 * ============================================================
 * WHY A SIMPLE RUNNING PRODUCT DOES NOT WORK
 * ============================================================
 *
 * A first instinct might be:
 *
 *     runningProduct *= nums[i]
 *
 * and keep the largest running product.
 *
 * The problem is that a negative product can become a very large
 * positive product when multiplied by another negative number.
 *
 * Example:
 *
 * nums = [-2, 3, -4]
 *
 * After processing 3:
 *
 * current product = -6
 *
 * This looks bad.
 *
 * But when -4 arrives:
 *
 * -6 * -4 = 24
 *
 * Therefore, we cannot discard negative products just because
 * they are currently small.
 *
 *
 * ============================================================
 * CORE OBSERVATION
 * ============================================================
 *
 * At every index, we need to know two things:
 *
 * 1. The maximum product of a subarray ending at this index.
 * 2. The minimum product of a subarray ending at this index.
 *
 * Why do we need the minimum?
 *
 * Because multiplying by a negative number reverses the ordering.
 *
 * Example:
 *
 *     -6 * -4 = 24
 *
 * A minimum/very negative product can therefore become our
 * maximum product when another negative number arrives.
 *
 *
 * ============================================================
 * STATE DEFINITIONS
 * ============================================================
 *
 * currentMaxProduct:
 *
 * The maximum product of any subarray that MUST end at the
 * current index.
 *
 *
 * currentMinProduct:
 *
 * The minimum product of any subarray that MUST end at the
 * current index.
 *
 *
 * maxProduct:
 *
 * The maximum product found anywhere in the array so far.
 *
 *
 * Important distinction:
 *
 * currentMaxProduct/currentMinProduct
 *     -> local state ending at the current index
 *
 * maxProduct
 *     -> global answer across everything processed so far
 *
 *
 * ============================================================
 * DERIVING THE STATE TRANSITION
 * ============================================================
 *
 * Suppose:
 *
 * currentNum = x
 *
 * and the previous states are:
 *
 * previousMax
 * previousMin
 *
 * A subarray ending at the current position has only three
 * possibilities:
 *
 * 1. Start a new subarray at x:
 *
 *        x
 *
 * 2. Extend the previous maximum-product subarray:
 *
 *        previousMax * x
 *
 * 3. Extend the previous minimum-product subarray:
 *
 *        previousMin * x
 *
 * Therefore:
 *
 * newMax = max(
 *              x,
 *              previousMax * x,
 *              previousMin * x
 *          )
 *
 * and:
 *
 * newMin = min(
 *              x,
 *              previousMax * x,
 *              previousMin * x
 *          )
 *
 *
 * ============================================================
 * WHY WE SAVE THE PREVIOUS VALUES
 * ============================================================
 *
 * This is extremely important.
 *
 * Both newMax and newMin must be calculated using the SAME
 * previousMax and previousMin.
 *
 * We must NOT update one and then use that updated value to
 * calculate the other.
 *
 * Example:
 *
 * previousMax = 3
 * previousMin = -6
 * currentNum = -4
 *
 * Candidates:
 *
 * 3  * -4 = -12
 * -6 * -4 = 24
 * -4       = -4
 *
 * Therefore:
 *
 * newMax = 24
 * newMin = -12
 *
 * So we first save:
 *
 *     int prevMaxProduct = currentMaxProduct;
 *     int prevMinProduct = currentMinProduct;
 *
 * and then calculate both new states from those saved values.
 *
 *
 * ============================================================
 * ZERO HANDLING
 * ============================================================
 *
 * Zero is naturally handled by the state transition.
 *
 * Example:
 *
 * nums = [2, 3, 0, 4, 5]
 *
 * Before zero:
 *
 * currentMax = 6
 *
 * At zero:
 *
 * newMax = 0
 * newMin = 0
 *
 * This effectively ends the previous subarray.
 *
 * Then 4 can start a new subarray:
 *
 * [4] -> 4
 *
 * and then:
 *
 * [4,5] -> 20
 *
 * No special zero-handling code is required.
 *
 *
 * ============================================================
 * ALGORITHM
 * ============================================================
 *
 * Initialize all states using nums[0].
 *
 * For every subsequent number:
 *
 * 1. Save the previous maximum and minimum.
 * 2. Calculate the new minimum.
 * 3. Calculate the new maximum.
 * 4. Update the global maximum.
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
 * We only maintain a constant number of variables.
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Tracking only the maximum product.
 *
 *    Wrong because a negative minimum can become the maximum
 *    after multiplication by another negative number.
 *
 *
 * 2. Updating currentMinProduct before calculating
 *    currentMaxProduct.
 *
 *    Both states must use the PREVIOUS maximum and minimum.
 *
 *
 * 3. Forgetting to consider starting a new subarray.
 *
 *    The current number itself must be one of the candidates.
 *
 *
 * 4. Confusing currentMaxProduct with maxProduct.
 *
 *    currentMaxProduct:
 *        best product ending HERE.
 *
 *    maxProduct:
 *        best product found ANYWHERE so far.
 *
 *
 * 5. Special-casing zero unnecessarily.
 *
 *    The recurrence already handles zero correctly.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Why can't we use a normal sliding window?
 *
 * A:
 * Product does not have the required monotonic behavior,
 * especially when negative numbers and zero are present.
 *
 *
 * Q: Why track the minimum?
 *
 * A:
 * A negative minimum can become the maximum when multiplied
 * by another negative number.
 *
 *
 * Q: Can we reduce the space complexity further?
 *
 * A:
 * No meaningful asymptotic improvement is possible because
 * the algorithm is already O(1) auxiliary space.
 *
 *
 * Q: What pattern does this problem demonstrate?
 *
 * A:
 * State-based dynamic programming. We retain the minimum and
 * maximum useful states from the previous position and use
 * them to construct the states for the current position.
 */

public class MaximumProductSubarray
{
    public int maxProduct(int[] nums)
    {
        /*
         * The first element must be considered as a valid
         * subarray by itself.
         *
         * We cannot initialize these values to 0 because the
         * input can contain only negative numbers.
         *
         * Example:
         *
         * nums = [-2]
         *
         * Correct answer = -2
         */
        int maxProduct = nums[0];

        int currentMinProduct = nums[0];
        int currentMaxProduct = nums[0];

        for (int i = 1; i < nums.length; i++)
        {
            int currentNum = nums[i];

            /*
             * Save the previous states.
             *
             * Both the new maximum and the new minimum must
             * be calculated using these OLD values.
             */
            int prevMaxProduct = currentMaxProduct;
            int prevMinProduct = currentMinProduct;

            /*
             * There are three possibilities for the new minimum:
             *
             * 1. Start a new subarray with currentNum.
             * 2. Extend the previous maximum product.
             * 3. Extend the previous minimum product.
             *
             * We take the smallest of the three.
             */
            currentMinProduct = Math.min(
                currentNum,
                Math.min(
                    prevMaxProduct * currentNum,
                    prevMinProduct * currentNum
                )
            );

            /*
             * Same three candidates, but this time we want
             * the largest one.
             */
            currentMaxProduct = Math.max(
                currentNum,
                Math.max(
                    prevMaxProduct * currentNum,
                    prevMinProduct * currentNum
                )
            );

            /*
             * currentMaxProduct only describes the best product
             * ending at THIS index.
             *
             * maxProduct stores the best result found anywhere
             * in the array so far.
             */
            maxProduct = Math.max(
                maxProduct,
                currentMaxProduct
            );
        }

        return maxProduct;
    }


    /*
     * ============================================================
     * LOCAL TESTS
     * ============================================================
     */
    public static void main(String[] args)
    {
        MaximumProductSubarray solution = new MaximumProductSubarray();

        System.out.println(
            solution.maxProduct(
                new int[]{2, 3, -2, 4}
            )
        );
        // Expected: 6
        // [2, 3] -> 6


        System.out.println(
            solution.maxProduct(
                new int[]{-2, 3, -4}
            )
        );
        // Expected: 24
        // [-2, 3, -4] -> 24


        System.out.println(
            solution.maxProduct(
                new int[]{-2, 3}
            )
        );
        // Expected: 3
        // [3] -> 3


        System.out.println(
            solution.maxProduct(
                new int[]{2, 3, 0, 4, 5}
            )
        );
        // Expected: 20
        // [4, 5] -> 20


        System.out.println(
            solution.maxProduct(
                new int[]{-2}
            )
        );
        // Expected: -2


        System.out.println(
            solution.maxProduct(
                new int[]{0, 2}
            )
        );
        // Expected: 2


        System.out.println(
            solution.maxProduct(
                new int[]{-2, 0, -1}
            )
        );
        // Expected: 0
    }
}
