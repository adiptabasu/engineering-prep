/**
 * LeetCode: 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * Problem:
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that:
 *
 *     i != j
 *     i != k
 *     j != k
 *
 * and:
 *
 *     nums[i] + nums[j] + nums[k] == 0
 *
 * The returned triplets must not contain duplicates.
 *
 * Example:
 *
 * Input:
 * [-1, 0, 1, 2, -1, -4]
 *
 * Output:
 * [
 *     [-1, -1, 2],
 *     [-1, 0, 1]
 * ]
 *
 * ---------------------------------------------------------------
 *
 * Brute Force:
 *
 * Use three nested loops to examine every possible combination
 * of three elements.
 *
 * Time:  O(n^3)
 * Space: O(1) excluding the output.
 *
 * This is too slow for large inputs.
 *
 * ---------------------------------------------------------------
 *
 * Optimized Approach:
 *
 * 1. Sort the array.
 *
 * 2. Fix one element using index i.
 *
 * 3. Use two pointers for the remaining portion:
 *
 *        left  = i + 1
 *        right = nums.length - 1
 *
 * 4. Calculate:
 *
 *        nums[i] + nums[left] + nums[right]
 *
 * 5. Since the array is sorted:
 *
 *        sum < 0 -> left++
 *        sum > 0 -> right--
 *        sum == 0 -> triplet found
 *
 * 6. Skip duplicate values for i, left, and right.
 *
 * ---------------------------------------------------------------
 *
 * Key Insight:
 *
 * 3Sum can be reduced to repeated Two Sum II problems.
 *
 * After fixing nums[i], we need:
 *
 *     nums[left] + nums[right] = -nums[i]
 *
 * Since the array is sorted, the two-pointer technique can solve
 * this part in linear time for each fixed i.
 *
 * ---------------------------------------------------------------
 *
 * Duplicate Handling:
 *
 * Sorting makes duplicate handling straightforward.
 *
 * Fixed element:
 *
 *     if (i > 0 && nums[i] == nums[i - 1])
 *         continue;
 *
 * This prevents using the same fixed value multiple times.
 *
 * After finding a valid triplet:
 *
 *     left++;
 *     right--;
 *
 * Then skip duplicate values:
 *
 *     while (left < right && nums[left] == nums[left - 1])
 *         left++;
 *
 *     while (left < right && nums[right] == nums[right + 1])
 *         right--;
 *
 * ---------------------------------------------------------------
 *
 * Why It Works:
 *
 * Because the array is sorted:
 *
 * - Increasing left increases the sum.
 * - Decreasing right decreases the sum.
 *
 * Therefore each comparison tells us which direction is guaranteed
 * to move us closer to zero.
 *
 * Duplicate values are skipped so that each unique value combination
 * is added only once.
 *
 * ---------------------------------------------------------------
 *
 * Complexity:
 *
 * Sorting:            O(n log n)
 * Outer loop:         O(n)
 * Two-pointer scans:  O(n^2)
 *
 * Overall Time:  O(n^2)
 * Space:        O(1) auxiliary space, excluding output and sorting
 *               implementation details.
 *
 * ---------------------------------------------------------------
 *
 * Common Mistakes:
 *
 * 1. Forgetting to sort the array.
 *
 * 2. Using three nested loops -> O(n^3).
 *
 * 3. Adding indices instead of values to the result.
 *
 * 4. Not skipping duplicate fixed values.
 *
 * 5. Not skipping duplicate left/right values after finding a triplet.
 *
 * 6. Forgetting to move both pointers after finding a valid triplet.
 *
 * ---------------------------------------------------------------
 *
 * Interview Follow-ups:
 *
 * 1. Why does sorting help?
 *    It enables two-pointer movement and makes duplicate handling easy.
 *
 * 2. Why is the solution O(n^2)?
 *    There are O(n) choices for the fixed element and an O(n)
 *    two-pointer scan for each one.
 *
 * 3. Could HashSet be used instead?
 *    Yes. A HashSet-based approach can achieve O(n^2) expected time,
 *    but duplicate handling becomes more involved.
 *
 * 4. Why not use TreeSet?
 *    TreeSet removes duplicate values, but duplicate values can be
 *    required to form valid triplets such as [-1, -1, 2].
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> threePairs = new ArrayList<>();

        for (int leftFixed = 0;
             leftFixed < nums.length - 2;
             leftFixed++) {

            // Skip duplicate fixed values.
            if (leftFixed > 0
                    && nums[leftFixed] == nums[leftFixed - 1]) {
                continue;
            }

            int left = leftFixed + 1;
            int right = nums.length - 1;

            while (left < right) {

                int currentSum =
                        nums[leftFixed] + nums[left] + nums[right];

                if (currentSum < 0) {

                    // Need a larger sum.
                    left++;

                } else if (currentSum > 0) {

                    // Need a smaller sum.
                    right--;

                } else {

                    // Found a valid triplet.
                    List<Integer> triplet = new ArrayList<>();

                    triplet.add(nums[leftFixed]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);

                    threePairs.add(triplet);

                    // Move both pointers after finding a triplet.
                    left++;
                    right--;

                    // Skip duplicate left values.
                    while (left < right
                            && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values.
                    while (left < right
                            && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }

        return threePairs;
    }

    public static void main(String[] args) {

        ThreeSum solution = new ThreeSum();

        test(
                solution,
                new int[]{-1, 0, 1, 2, -1, -4}
        );

        test(
                solution,
                new int[]{0, 1, 1}
        );

        test(
                solution,
                new int[]{0, 0, 0}
        );

        test(
                solution,
                new int[]{-1, -1, 2, 2}
        );

        test(
                solution,
                new int[]{}
        );

        test(
                solution,
                new int[]{1, 2, -2, -1}
        );
    }

    private static void test(
            ThreeSum solution,
            int[] nums) {

        System.out.println(
                "Input:  "
                        + Arrays.toString(nums)
                        + " -> Output: "
                        + solution.threeSum(nums)
        );
    }
}
