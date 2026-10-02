/**
 * LeetCode: Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Problem:
 * Given an integer array nums, return an array answer such that
 * answer[i] is equal to the product of all elements of nums except nums[i].
 *
 * Requirements:
 * - Do not use division.
 * - O(n) time.
 * - O(1) extra space, excluding the output array.
 *
 * Approach:
 *
 * We can express:
 *
 *     answer[i] =
 *         product of everything to the LEFT of i
 *         *
 *         product of everything to the RIGHT of i
 *
 * First pass:
 * Store the prefix product in answer[i].
 *
 * Second pass:
 * Traverse from right to left while maintaining a running suffix
 * product. Multiply that suffix product into answer[i].
 *
 * Example:
 *
 * nums = [1, 2, 3, 4]
 *
 * After the left-to-right pass:
 *
 * answer = [1, 1, 2, 6]
 *
 * These represent:
 *
 * answer[0] = 1
 * answer[1] = 1
 * answer[2] = 1 * 2
 * answer[3] = 1 * 2 * 3
 *
 * During the right-to-left pass:
 *
 * answer[3] = 6 * 1  = 6
 * answer[2] = 2 * 4  = 8
 * answer[1] = 1 * 12 = 12
 * answer[0] = 1 * 24 = 24
 *
 * Result:
 *
 * [24, 12, 8, 6]
 *
 * Key Insight:
 *
 * We do not need separate prefix and suffix arrays.
 * The output array itself can store the prefix products,
 * while a single variable maintains the suffix product.
 *
 * Invariants:
 *
 * Forward pass:
 *     leftProd = product of all elements strictly before i
 *
 * Backward pass:
 *     rightProd = product of all elements strictly after i
 *
 * Why it works:
 *
 * Every element except nums[i] is either:
 * - to the left of i, or
 * - to the right of i.
 *
 * Multiplying those two independent products gives exactly
 * the product of every element except nums[i].
 *
 * Important:
 * This naturally handles zero values without any special cases.
 *
 * Complexity:
 * Time:  O(n)
 * Space: O(1) auxiliary space, excluding the output array.
 *
 * Common Mistakes:
 * 1. Using division. This fails with zero values and violates the requirement.
 * 2. Overwriting the prefix product during the second pass instead of
 *    multiplying the suffix product into it.
 * 3. Updating leftProd/rightProd using answer[i] instead of nums[i].
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int[] productArr = new int[nums.length];

        // First pass:
        // Store the product of all elements strictly to the left of i.
        int leftProd = 1;

        for (int i = 0; i < nums.length; i++) {

            productArr[i] = leftProd;

            // Include nums[i] for the next position.
            leftProd *= nums[i];
        }

        // Second pass:
        // Multiply the product of all elements strictly to the right of i.
        int rightProd = 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            // Preserve the left product and combine it with the right product.
            productArr[i] *= rightProd;

            // Include nums[i] for the next position to the left.
            rightProd *= nums[i];
        }

        return productArr;
    }

    public static void main(String[] args) {

        ProductExceptSelf solution = new ProductExceptSelf();

        test(solution, new int[]{1, 2, 3, 4});
        test(solution, new int[]{-1, 1, 0, -3, 3});
        test(solution, new int[]{1, 2, 0, 4});
        test(solution, new int[]{0, 0});
        test(solution, new int[]{2, 3});
    }

    private static void test(ProductExceptSelf solution, int[] nums) {

        int[] result = solution.productExceptSelf(nums);

        System.out.println(
                "Input:  " + java.util.Arrays.toString(nums)
                        + " -> Output: " + java.util.Arrays.toString(result)
        );
    }
}
