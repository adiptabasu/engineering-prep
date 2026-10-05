import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode:
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Problem:
 * Given an integer array nums and an integer k, return the total number
 * of continuous subarrays whose elements sum to k.
 *
 * Example:
 * nums = [1, 1, 1], k = 2
 *
 * Valid subarrays:
 * [1, 1]  -> indices 0..1
 * [1, 1]  -> indices 1..2
 *
 * Answer = 2
 *
 *
 * ------------------------------------------------------------
 * BRUTE FORCE APPROACH
 * ------------------------------------------------------------
 *
 * We could consider every possible starting index and keep extending
 * the subarray while maintaining its sum.
 *
 * Example:
 *
 * [1, 2, 3]
 *
 * Start at index 0:
 *   [1]       -> sum = 1
 *   [1, 2]    -> sum = 3
 *   [1, 2, 3] -> sum = 6
 *
 * Start at index 1:
 *   [2]       -> sum = 2
 *   [2, 3]    -> sum = 5
 *
 * Start at index 2:
 *   [3]       -> sum = 3
 *
 * This takes O(n^2) time.
 *
 *
 * ------------------------------------------------------------
 * WHY SLIDING WINDOW DOES NOT WORK GENERALLY
 * ------------------------------------------------------------
 *
 * With positive numbers, we can sometimes use a sliding window because
 * adding an element always increases the sum and removing an element
 * always decreases the sum.
 *
 * But this problem allows negative numbers.
 *
 * Example:
 *
 * nums = [2, 2, -1]
 * k = 3
 *
 * [2, 2] has sum 4.
 *
 * A sliding-window approach might conclude:
 * "The sum is already greater than k, so this window cannot work."
 *
 * But adding -1 gives:
 *
 * 2 + 2 - 1 = 3
 *
 * Therefore [2, 2, -1] is a valid subarray.
 *
 * Negative numbers destroy the monotonic property required by
 * the usual sliding-window approach.
 *
 *
 * ------------------------------------------------------------
 * PREFIX SUM
 * ------------------------------------------------------------
 *
 * Prefix Sum means keeping a running cumulative sum.
 *
 * Example:
 *
 * nums = [2, 4, 1, 3]
 *
 * Start:
 *
 * prefix = 0
 *
 * After 2:
 * prefix = 2
 *
 * After 4:
 * prefix = 6
 *
 * After 1:
 * prefix = 7
 *
 * After 3:
 * prefix = 10
 *
 * Conceptually:
 *
 *          checkpoint
 *              |
 *              v
 *  0 ---- 2 ---- 6 ---- 7 ---- 10
 *
 *
 * Now suppose we want to know the sum of a subarray.
 *
 * Imagine:
 *
 * 0 ---- 2 ---- 6 -------- 10
 *       ^                    ^
 *       |                    |
 *    earlier               current
 *
 * The elements between those two checkpoints have sum:
 *
 * 10 - 2 = 8
 *
 * Therefore:
 *
 * subarray sum = currentPrefixSum - previousPrefixSum
 *
 *
 * ------------------------------------------------------------
 * DERIVING THE HASHMAP SOLUTION
 * ------------------------------------------------------------
 *
 * We want:
 *
 * currentPrefixSum - previousPrefixSum = k
 *
 * Rearranging:
 *
 * previousPrefixSum = currentPrefixSum - k
 *
 * So when we reach a new prefix sum, we simply ask:
 *
 * "Have I previously seen a prefix sum equal to
 *  currentPrefixSum - k?"
 *
 * If yes, every occurrence of that previous prefix sum represents
 * one valid subarray ending at the current index.
 *
 *
 * Example:
 *
 * nums = [1, 2, 1]
 * k = 3
 *
 * Prefix sums:
 *
 * Start:
 * 0
 *
 * +1 -> 1
 * +2 -> 3
 * +1 -> 4
 *
 * At prefix = 3:
 *
 * prefix - k
 * = 3 - 3
 * = 0
 *
 * We previously saw prefix sum 0.
 *
 * Therefore:
 *
 * 3 - 0 = 3
 *
 * The subarray [1, 2] has sum 3.
 *
 *
 * At prefix = 4:
 *
 * prefix - k
 * = 4 - 3
 * = 1
 *
 * We previously saw prefix sum 1.
 *
 * Therefore:
 *
 * 4 - 1 = 3
 *
 * The subarray [2, 1] has sum 3.
 *
 *
 * ------------------------------------------------------------
 * WHY WE NEED A FREQUENCY MAP
 * ------------------------------------------------------------
 *
 * We don't use a Set because the same prefix sum can occur multiple
 * times, and each occurrence can represent a different valid subarray.
 *
 * Example:
 *
 * nums = [0, 0, 0]
 * k = 0
 *
 * Valid subarrays:
 *
 * [0]       -> index 0
 * [0]       -> index 1
 * [0]       -> index 2
 * [0, 0]    -> indices 0..1
 * [0, 0]    -> indices 1..2
 * [0, 0, 0] -> indices 0..2
 *
 * Total = 6
 *
 * The prefix sum is 0 at every checkpoint.
 *
 * Therefore we need to know HOW MANY TIMES we have seen each
 * prefix sum.
 *
 * Hence:
 *
 * Map<prefixSum, frequency>
 *
 *
 * ------------------------------------------------------------
 * WHY WE INITIALIZE 0 -> 1
 * ------------------------------------------------------------
 *
 * Before processing any elements, our cumulative sum is 0.
 *
 * We have seen that prefix sum once:
 *
 * prefixSumMap = { 0 -> 1 }
 *
 * This allows us to correctly count subarrays that start at index 0.
 *
 * Example:
 *
 * nums = [3]
 * k = 3
 *
 * Current prefix sum = 3
 *
 * We need:
 *
 * 3 - 3 = 0
 *
 * Since 0 exists in the map, we count [3].
 *
 * Without the initial 0 -> 1 entry, this subarray would be missed.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT ORDER OF OPERATIONS
 * ------------------------------------------------------------
 *
 * For every element:
 *
 * 1. Update the current prefix sum.
 * 2. Look for prefixSum - k in the map.
 * 3. Add the number of occurrences to the answer.
 * 4. Insert the CURRENT prefix sum into the map.
 *
 * The lookup must happen BEFORE inserting the current prefix sum.
 *
 * Why?
 *
 * The map is supposed to represent PREFIX SUMS FROM PREVIOUS
 * POSITIONS.
 *
 * If we insert the current prefix first, the current position could
 * incorrectly be used as its own starting checkpoint.
 *
 * Example:
 *
 * nums = [0]
 * k = 0
 *
 * Initially:
 *
 * {0 -> 1}
 *
 * Current prefix = 0
 *
 * We first ask:
 *
 * How many previous 0s?
 *
 * Answer = 1
 *
 * Therefore we correctly count [0].
 *
 * Only after that do we insert the current 0:
 *
 * {0 -> 2}
 *
 *
 * ------------------------------------------------------------
 * OPTIMIZED APPROACH
 * ------------------------------------------------------------
 *
 * Time Complexity:
 * O(n)
 *
 * We traverse the array once.
 * HashMap operations are O(1) on average.
 *
 * Space Complexity:
 * O(n)
 *
 * In the worst case, we can have O(n) distinct prefix sums.
 *
 *
 * ------------------------------------------------------------
 * KEY INSIGHT
 * ------------------------------------------------------------
 *
 * Don't search for subarrays directly.
 *
 * Instead, search for pairs of prefix sums.
 *
 * If:
 *
 * currentPrefix - previousPrefix = k
 *
 * then the elements between those two prefix checkpoints form a
 * subarray whose sum is k.
 *
 * Therefore:
 *
 * previousPrefix = currentPrefix - k
 *
 * The HashMap lets us find those previous checkpoints in O(1)
 * average time.
 */
public class SubarraySumEqualsK
{
    public int subarraySum(int[] nums, int k)
    {
        int output = 0;
        int prefixSum = 0;

        /*
         * Map:
         *
         * key   = prefix sum
         * value = number of times that prefix sum has occurred
         *
         * We have seen prefix sum 0 once before processing the array.
         */
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);

        for (int i = 0; i < nums.length; i++)
        {
            /*
             * Move to the next cumulative-sum checkpoint.
             */
            prefixSum += nums[i];

            /*
             * We need:
             *
             * currentPrefix - previousPrefix = k
             *
             * Therefore:
             *
             * previousPrefix = currentPrefix - k
             *
             * Ask the map how many previous checkpoints had
             * exactly this prefix sum.
             *
             * IMPORTANT:
             * We perform this lookup BEFORE inserting the current
             * prefix sum into the map.
             */
            output += prefixSumMap.getOrDefault(prefixSum - k, 0);

            /*
             * Now record the current checkpoint.
             *
             * It can be used as a previous checkpoint for future
             * elements.
             */
            prefixSumMap.put(
                prefixSum,
                prefixSumMap.getOrDefault(prefixSum, 0) + 1
            );
        }

        return output;
    }


    /*
     * ------------------------------------------------------------
     * LOCAL TESTS
     * ------------------------------------------------------------
     */
    public static void main(String[] args)
    {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();

        System.out.println(
            solution.subarraySum(
                new int[]{1, 1, 1},
                2
            )
        );
        // Expected: 2


        System.out.println(
            solution.subarraySum(
                new int[]{1, 2, 3},
                3
            )
        );
        // Expected: 2
        // [1, 2]
        // [3]


        System.out.println(
            solution.subarraySum(
                new int[]{1, -1, 0},
                0
            )
        );
        // Expected: 3
        // [1, -1]
        // [0]
        // [1, -1, 0]


        System.out.println(
            solution.subarraySum(
                new int[]{0, 0, 0},
                0
            )
        );
        // Expected: 6


        System.out.println(
            solution.subarraySum(
                new int[]{2, 2, -1},
                3
            )
        );
        // Expected: 1
        // [2, 2, -1]


        System.out.println(
            solution.subarraySum(
                new int[]{1, 2, 1, 2},
                3
            )
        );
        // Expected: 2
        // [1, 2]
        // [2, 1]
    }
}
