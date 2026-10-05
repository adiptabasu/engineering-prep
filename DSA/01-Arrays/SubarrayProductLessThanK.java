/*
 * LeetCode:
 * https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * Problem:
 * Given an array of positive integers nums and an integer k,
 * return the number of contiguous subarrays where the product
 * of all the elements is strictly less than k.
 *
 * Example:
 *
 * nums = [10, 5, 2, 6]
 * k = 100
 *
 * Valid subarrays:
 *
 * [10]
 * [5]
 * [2]
 * [6]
 * [10,5]
 * [5,2]
 * [2,6]
 * [5,2,6]
 *
 * Answer = 8
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * We could start at every index and keep multiplying elements
 * while extending the subarray.
 *
 * Example:
 *
 * [10, 5, 2]
 *
 * Starting at index 0:
 *
 * [10]       -> 10
 * [10,5]     -> 50
 * [10,5,2]   -> 100
 *
 * Starting at index 1:
 *
 * [5]        -> 5
 * [5,2]      -> 10
 *
 * Starting at index 2:
 *
 * [2]        -> 2
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
 * The problem gives us an important constraint:
 *
 *     nums[i] > 0
 *
 * Because all numbers are positive, multiplying by another
 * number can only maintain or increase the product.
 *
 * This gives us the monotonic behavior needed for a
 * variable-size sliding window.
 *
 * If the product becomes too large, we can safely shrink the
 * window from the left.
 *
 *
 * ============================================================
 * SLIDING WINDOW
 * ============================================================
 *
 * Maintain a window:
 *
 *     [left ... right]
 *
 * and keep track of its product.
 *
 * When adding nums[right] causes:
 *
 *     product >= k
 *
 * we remove elements from the left until:
 *
 *     product < k
 *
 * The important invariant is:
 *
 *     AFTER THE SHRINKING STEP,
 *     [left ... right] is always a valid window.
 *
 *
 * ============================================================
 * WHY POSITIVE NUMBERS MATTER
 * ============================================================
 *
 * Suppose:
 *
 * product = 50
 * k = 100
 *
 * The window is valid.
 *
 * If we extend it with another positive number:
 *
 *     50 * 2 = 100
 *
 * the product becomes invalid.
 *
 * Removing elements from the left can only decrease the
 * product or keep it unchanged.
 *
 * Therefore we can safely shrink the window until it becomes
 * valid again.
 *
 * This monotonic behavior would not hold if negative numbers
 * or arbitrary zero values were allowed.
 *
 *
 * ============================================================
 * COUNTING THE VALID SUBARRAYS
 * ============================================================
 *
 * This is the most important part of the problem.
 *
 * Once [left ... right] is a valid window, how many valid
 * subarrays END at right?
 *
 * Every starting position from left through right creates
 * exactly one contiguous subarray ending at right.
 *
 * For example:
 *
 *     [5, 2, 6]
 *      L     R
 *
 * Valid subarrays ending at 6:
 *
 *     [6]
 *     [2,6]
 *     [5,2,6]
 *
 * Number of subarrays:
 *
 *     right - left + 1
 *
 * Therefore:
 *
 *     numberOfSubArrays += right - left + 1
 *
 *
 * ============================================================
 * IMPORTANT: WE DON'T COUNT ALL SUBARRAYS IN THE WINDOW
 * ============================================================
 *
 * At every right index, we only count the NEW subarrays that
 * end at that right index.
 *
 * Example:
 *
 * [10]
 *
 * right = 0
 *
 * New subarrays:
 *
 * [10]
 *
 * Count = 1
 *
 *
 * Then:
 *
 * [10,5]
 *
 * right = 1
 *
 * New subarrays ending at 5:
 *
 * [5]
 * [10,5]
 *
 * Count = 2
 *
 * We do NOT count [10] again because it was already counted
 * when right = 0.
 *
 *
 * ============================================================
 * WHY WE SHRINK WITH WHILE, NOT IF
 * ============================================================
 *
 * The product might remain invalid after removing only one
 * element.
 *
 * Example:
 *
 * nums = [10,10,10]
 * k = 50
 *
 * Product:
 *
 * 10 * 10 * 10 = 1000
 *
 * Remove the first 10:
 *
 * 1000 / 10 = 100
 *
 * Still invalid because:
 *
 * 100 >= 50
 *
 * Therefore we must continue removing elements until the
 * window becomes valid.
 *
 * Hence:
 *
 *     while (product >= k)
 *
 * rather than:
 *
 *     if (product >= k)
 *
 *
 * ============================================================
 * IMPORTANT EDGE CASE: k <= 1
 * ============================================================
 *
 * This is an important constraint-based optimization.
 *
 * The problem guarantees that nums contains positive integers.
 *
 * Therefore:
 *
 *     nums[i] >= 1
 *
 * The smallest possible product of any NON-EMPTY subarray
 * is therefore:
 *
 *     1
 *
 * But the problem requires:
 *
 *     product < k
 *
 * Consider:
 *
 *     k = 1
 *
 * We would need:
 *
 *     product < 1
 *
 * But every non-empty subarray has product >= 1.
 *
 * Therefore there are NO valid subarrays when:
 *
 *     k <= 1
 *
 * We can immediately return 0.
 *
 * This also prevents an important runtime problem.
 *
 * Example:
 *
 * nums = [2]
 * k = 1
 *
 * If we entered the normal sliding-window logic:
 *
 * product = 2
 *
 * Since:
 *
 *     2 >= 1
 *
 * we would try to remove nums[0].
 *
 * After removing it:
 *
 * product = 1
 * left = 1
 *
 * But:
 *
 *     1 >= 1
 *
 * is still true.
 *
 * The algorithm could then attempt to access nums[1],
 * which does not exist.
 *
 * Therefore:
 *
 *     if (k <= 1)
 *     {
 *         return 0;
 *     }
 *
 * must happen BEFORE entering the sliding-window logic.
 *
 *
 * ============================================================
 * ALGORITHM
 * ============================================================
 *
 * 1. If k <= 1, return 0.
 *
 * 2. Initialize:
 *
 *        left = 0
 *        product = 1
 *        numberOfSubArrays = 0
 *
 * 3. Move right through the array.
 *
 * 4. Multiply the current element into product.
 *
 * 5. While product >= k:
 *
 *        divide out nums[left]
 *        move left forward
 *
 * 6. Once the window is valid:
 *
 *        numberOfSubArrays += right - left + 1
 *
 * 7. Return the total.
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity:
 * O(n)
 *
 * Although there is a while loop inside the for loop, both
 * pointers only move forward.
 *
 * right moves from 0 to n - 1 once.
 *
 * left also moves from 0 to n - 1 at most once across the
 * entire algorithm.
 *
 * Therefore the total number of pointer movements is O(n).
 *
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
 * 1. Using <= k instead of < k.
 *
 *    The problem requires the product to be STRICTLY less
 *    than k.
 *
 *
 * 2. Using if instead of while when shrinking.
 *
 *    Removing one element might not be enough to make the
 *    window valid.
 *
 *
 * 3. Replacing the count instead of accumulating it.
 *
 *    Wrong:
 *
 *        count = right - left + 1;
 *
 *    Correct:
 *
 *        count += right - left + 1;
 *
 *
 * 4. Counting all subarrays inside the window.
 *
 *    We only count the new subarrays ending at right.
 *
 *
 * 5. Forgetting the k <= 1 edge case.
 *
 *    No positive-integer product can be less than or equal
 *    to 1.
 *
 *
 * 6. Trying to use this exact sliding-window approach when
 *    negative numbers are allowed.
 *
 *    The monotonic product property would no longer hold.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Why is this O(n) despite having a nested while loop?
 *
 * A:
 * Both left and right only move forward. Each element enters
 * the window once and leaves the window at most once.
 *
 *
 * Q: Why do we count right - left + 1?
 *
 * A:
 * Every possible starting position from left through right
 * produces one valid subarray ending at right.
 *
 *
 * Q: Why can't we use this approach with negative numbers?
 *
 * A:
 * Multiplication by negative values destroys the monotonic
 * behavior that allows us to safely shrink the window.
 *
 *
 * Q: What happens when k <= 1?
 *
 * A:
 * Return 0 immediately because nums contains positive
 * integers and therefore every non-empty product is >= 1.
 */

public class SubarrayProductLessThanK
{
    public int numSubarrayProductLessThanK(int[] nums, int k)
    {
        /*
         * Every element is a positive integer.
         *
         * Therefore every non-empty subarray has a product
         * greater than or equal to 1.
         *
         * Since we need:
         *
         *     product < k
         *
         * there can be no valid subarray when k <= 1.
         *
         * This check also prevents the sliding window from
         * shrinking beyond the valid array boundaries.
         */
        if (k <= 1)
        {
            return 0;
        }

        int numberOfSubArrays = 0;
        int left = 0;
        int product = 1;

        /*
         * right represents the end of the current window.
         */
        for (int right = 0; right < nums.length; right++)
        {
            /*
             * Expand the window by including nums[right].
             */
            product *= nums[right];

            /*
             * If the product is too large, shrink the window
             * from the left until the product becomes strictly
             * less than k.
             *
             * We use while instead of if because removing one
             * element may not be enough.
             */
            while (product >= k)
            {
                product /= nums[left];
                left++;
            }

            /*
             * At this point:
             *
             *     product < k
             *
             * Therefore [left ... right] is a valid window.
             *
             * Every starting position between left and right
             * produces a valid subarray ending at right.
             *
             * Example:
             *
             * [5, 2, 6]
             *  L     R
             *
             * Valid subarrays ending at 6:
             *
             * [6]
             * [2,6]
             * [5,2,6]
             *
             * Count = 3
             *
             * Which is:
             *
             * right - left + 1
             */
            numberOfSubArrays += right - left + 1;
        }

        return numberOfSubArrays;
    }


    /*
     * ============================================================
     * LOCAL TESTS
     * ============================================================
     */
    public static void main(String[] args)
    {
        SubarrayProductLessThanK solution =
            new SubarrayProductLessThanK();


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{10, 5, 2, 6},
                100
            )
        );
        // Expected: 8


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{1, 1, 1},
                2
            )
        );
        // Expected: 6
        //
        // [1]
        // [1]
        // [1]
        // [1,1]
        // [1,1]
        // [1,1,1]


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{10, 10, 10},
                50
            )
        );
        // Expected: 3
        //
        // [10]
        // [10]
        // [10]


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{2},
                1
            )
        );
        // Expected: 0
        //
        // Important edge case:
        // product = 2 is not < 1.


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{1},
                2
            )
        );
        // Expected: 1


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{1, 2, 3},
                1
            )
        );
        // Expected: 0


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{1, 2, 3},
                2
            )
        );
        // Expected: 1
        // [1]


        System.out.println(
            solution.numSubarrayProductLessThanK(
                new int[]{1, 2, 3},
                10
            )
        );
        // Expected: 6
        //
        // Every non-empty subarray has product < 10.
    }
}
