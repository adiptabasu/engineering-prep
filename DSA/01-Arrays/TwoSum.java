import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 *
 * LeetCode:
 * https://leetcode.com/problems/two-sum/
 *
 * ------------------------------------------------------------
 * Problem
 * ------------------------------------------------------------
 * Given an integer array nums and an integer target, return
 * the indices of the two numbers such that they add up to
 * target.
 *
 * Each input has exactly one solution, and the same element
 * cannot be used twice.
 *
 * Example:
 *
 * nums = [2, 7, 11, 15]
 * target = 9
 *
 * Output:
 * [0, 1]
 *
 * Because:
 * nums[0] + nums[1] = 2 + 7 = 9
 *
 *
 * ------------------------------------------------------------
 * Approach
 * ------------------------------------------------------------
 * Use a HashMap to store numbers that we have already seen.
 *
 * The HashMap stores:
 *
 *     number -> index
 *
 * For every element:
 *
 *     requiredNumber = target - currentNumber
 *
 * We then check whether requiredNumber has already been seen.
 *
 * If it has:
 *     We have found the required pair.
 *
 * If it has not:
 *     Store the current number and its index for future
 *     iterations.
 *
 *
 * ------------------------------------------------------------
 * Why this works
 * ------------------------------------------------------------
 * Instead of checking every possible pair, we remember the
 * elements that we have already processed.
 *
 * For each current number, we only need to perform one
 * HashMap lookup to determine whether its complement exists.
 *
 * Example:
 *
 * nums = [2, 7, 11, 15]
 * target = 9
 *
 * i = 0
 * current = 2
 * required = 9 - 2 = 7
 *
 * 7 is not in the map.
 * Store:
 *
 *     2 -> 0
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * current = 7
 * required = 9 - 7 = 2
 *
 * 2 exists in the map at index 0.
 *
 * Therefore:
 *
 *     [0, 1]
 *
 *
 * ------------------------------------------------------------
 * Why do we check before inserting?
 * ------------------------------------------------------------
 * Consider:
 *
 * nums = [3, 3]
 * target = 6
 *
 * First 3:
 *     required = 3
 *     3 is not in the map
 *     store 3 -> 0
 *
 * Second 3:
 *     required = 3
 *     3 exists at index 0
 *
 * Therefore:
 *
 *     [0, 1]
 *
 * Checking before inserting ensures that the matching number
 * always comes from a previously processed element.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 * Time:
 *     O(n)
 *
 * We traverse the array once.
 * HashMap lookup and insertion are average O(1).
 *
 * Space:
 *     O(n)
 *
 * In the worst case, the HashMap can contain up to n elements.
 *
 *
 * ------------------------------------------------------------
 * Common mistakes
 * ------------------------------------------------------------
 *
 * 1. Using two nested loops
 *
 *    This produces O(n²) time complexity.
 *
 * 2. Building the entire HashMap first and then searching
 *
 *    This can cause problems with duplicate values because
 *    later occurrences overwrite earlier indices.
 *
 * 3. Inserting the current element before checking the map
 *
 *    This can potentially cause an element to be matched
 *    with itself.
 *
 * 4. Forgetting that HashMap operations are average O(1),
 *    not an absolute worst-case guarantee.
 *
 *
 * ------------------------------------------------------------
 * Interview takeaway
 * ------------------------------------------------------------
 * The important pattern is:
 *
 *     "For the current value, what value do I need?"
 *
 * Instead of searching the array for that value, use a
 * HashMap to remember what has already been seen.
 *
 * This transforms the brute-force O(n²) solution into an
 * average O(n) solution.
 */
public class TwoSum {

    /**
     * Finds two indices whose corresponding values add up to
     * the target.
     *
     * @param nums   input array
     * @param target target sum
     * @return indices of the two numbers
     */
    public static int[] twoSum(int[] nums, int target) {

        // Stores:
        // number -> index
        //
        // Only previously processed elements are stored.
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int currentNumber = nums[i];

            // The number we need to find in order to reach target.
            int requiredNumber = target - currentNumber;

            // Check whether the required number has already
            // appeared in the array.
            if (seen.containsKey(requiredNumber)) {

                return new int[]{
                        seen.get(requiredNumber),
                        i
                };
            }

            // Store the current number only after checking
            // for its complement.
            seen.put(currentNumber, i);
        }

        // The problem guarantees a solution.
        // This is included to satisfy Java's return requirements.
        return new int[0];
    }

    /**
     * Local test cases.
     */
    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        System.out.println(
                "Result: [" + result[0] + ", " + result[1] + "]"
        );

        // Duplicate-value test
        int[] duplicateNums = {3, 3};
        int duplicateTarget = 6;

        int[] duplicateResult = twoSum(
                duplicateNums,
                duplicateTarget
        );

        System.out.println(
                "Duplicate test: ["
                        + duplicateResult[0]
                        + ", "
                        + duplicateResult[1]
                        + "]"
        );
    }
}

