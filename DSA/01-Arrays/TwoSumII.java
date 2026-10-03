/**
 * LeetCode: Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Problem:
 * Given a 1-indexed array of integers numbers that is already sorted
 * in non-decreasing order, find two numbers such that they add up to
 * a specific target.
 *
 * Return the indices of the two numbers as:
 *
 *     [index1, index2]
 *
 * The returned indices are 1-indexed.
 *
 * Example:
 *
 * Input:
 * numbers = [2, 7, 11, 15]
 * target = 9
 *
 * Output:
 * [1, 2]
 *
 * Because:
 *
 *     2 + 7 = 9
 *
 * ---------------------------------------------------------------
 *
 * Brute Force:
 *
 * Check every pair of elements.
 *
 * Time:  O(n^2)
 * Space: O(1)
 *
 * HashMap Approach:
 *
 * We could use the same complement lookup technique as the original
 * Two Sum problem.
 *
 * Time:  O(n)
 * Space: O(n)
 *
 * However, the input is already sorted, so we can do better in space.
 *
 * ---------------------------------------------------------------
 *
 * Optimized Approach:
 *
 * Use two pointers:
 *
 *     left  = beginning of the array
 *     right = end of the array
 *
 * At every step calculate:
 *
 *     currentSum = numbers[left] + numbers[right]
 *
 * Because the array is sorted:
 *
 * 1. If currentSum > target:
 *
 *        Move right left.
 *
 *    Moving left right would only increase the sum, so the only useful
 *    way to reduce the sum is to decrease the right value.
 *
 * 2. If currentSum < target:
 *
 *        Move left right.
 *
 *    Moving right left would only decrease the sum, so the only useful
 *    way to increase the sum is to increase the left value.
 *
 * 3. If currentSum == target:
 *
 *        We found the answer.
 *
 * ---------------------------------------------------------------
 *
 * Key Insight:
 *
 * The sorted input provides monotonic behavior:
 *
 *     Move left  -> sum increases
 *     Move right -> sum decreases
 *
 * Therefore every comparison tells us exactly which pointer to move.
 *
 * No lookahead is required.
 *
 * ---------------------------------------------------------------
 *
 * Why It Works:
 *
 * Initially, the pointers represent the smallest and largest possible
 * values.
 *
 * If their sum is too large, the largest value must be reduced.
 *
 * If their sum is too small, the smallest value must be increased.
 *
 * Since the array is sorted, neither decision can eliminate a valid
 * solution incorrectly.
 *
 * ---------------------------------------------------------------
 *
 * Complexity:
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * Each pointer only moves toward the other pointer and therefore the
 * total number of pointer movements is at most O(n).
 *
 * ---------------------------------------------------------------
 *
 * Common Mistakes:
 *
 * 1. Forgetting that the result is 1-indexed.
 *
 * 2. Moving the wrong pointer:
 *
 *        sum > target -> right--
 *        sum < target -> left++
 *
 * 3. Using HashMap unnecessarily when the sorted property allows O(1)
 *    extra space.
 *
 * 4. Using lookahead logic to decide which pointer to move.
 *    The sorted property already gives us a guaranteed decision.
 *
 * ---------------------------------------------------------------
 *
 * Interview Follow-ups:
 *
 * 1. Why is this O(n)?
 *    Both pointers only move inward and each moves at most n times.
 *
 * 2. Why don't we need a HashMap?
 *    The sorted order provides enough information to eliminate
 *    impossible pairs.
 *
 * 3. What changes if the input is not sorted?
 *    We could use a HashMap for O(n) expected time, or sort the array
 *    first if preserving original indices is handled separately.
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {

        int leftPointer = 0;
        int rightPointer = numbers.length - 1;

        int[] returnData = new int[2];

        while (leftPointer < rightPointer) {

            int currentSum =
                    numbers[leftPointer] + numbers[rightPointer];

            // Sum is too large.
            // Decrease the larger value.
            if (currentSum > target) {
                rightPointer--;
            }

            // Sum is too small.
            // Increase the smaller value.
            else if (currentSum < target) {
                leftPointer++;
            }

            // Target found.
            else {
                // LeetCode requires 1-indexed positions.
                returnData[0] = leftPointer + 1;
                returnData[1] = rightPointer + 1;

                return returnData;
            }
        }

        return returnData;
    }

    public static void main(String[] args) {

        TwoSumII solution = new TwoSumII();

        test(solution, new int[]{2, 7, 11, 15}, 9);
        test(solution, new int[]{2, 3, 4}, 6);
        test(solution, new int[]{-1, 0}, -1);
        test(solution, new int[]{1, 2, 3, 4, 4, 9}, 8);
    }

    private static void test(
            TwoSumII solution,
            int[] numbers,
            int target) {

        int[] result = solution.twoSum(numbers, target);

        System.out.println(
                "Input: "
                        + java.util.Arrays.toString(numbers)
                        + ", Target: "
                        + target
                        + " -> Output: "
                        + java.util.Arrays.toString(result)
        );
    }
}
