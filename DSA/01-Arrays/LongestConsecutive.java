/**
 * LeetCode: Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Problem:
 * Given an unsorted array of integers, return the length of the longest
 * consecutive elements sequence.
 *
 * Example:
 *
 * Input:  [100, 4, 200, 1, 3, 2]
 * Output: 4
 *
 * The longest consecutive sequence is:
 *
 * 1 -> 2 -> 3 -> 4
 *
 * Approach:
 *
 * Store all numbers in a HashSet so that we can perform average O(1)
 * membership checks.
 *
 * For each number, only start building a sequence if num - 1 does not
 * exist in the set. This means num is the beginning of a consecutive
 * sequence.
 *
 * Once we find a sequence start, keep checking num + 1, num + 2, etc.
 *
 * Key Insight:
 *
 * Do not start a sequence from every number.
 *
 * If:
 *
 *     1 -> 2 -> 3 -> 4
 *
 * then only 1 should start the traversal because:
 *
 *     1 - 1 = 0  -> not present
 *
 * But:
 *
 *     2 - 1 = 1  -> present
 *     3 - 1 = 2  -> present
 *     4 - 1 = 3  -> present
 *
 * Therefore 2, 3, and 4 cannot be sequence starts.
 *
 * This prevents repeatedly traversing the same sequence.
 *
 * Why It Works:
 *
 * Every number belongs to at most one forward sequence traversal.
 * Only sequence starts trigger the while loop, so despite the nested
 * loop structure, the total number of iterations across all sequence
 * traversals is O(n).
 *
 * Complexity:
 *
 * Time:  O(n) average
 * Space: O(n)
 *
 * HashSet operations such as add() and contains() are O(1) on average.
 *
 * Common Mistakes:
 *
 * 1. Using TreeSet:
 *    TreeSet provides O(log n) membership checks, so the solution becomes
 *    O(n log n) rather than the intended O(n) average.
 *
 * 2. Sorting the array:
 *    Sorting gives O(n log n) time and is unnecessary.
 *
 * 3. Starting a sequence from every number:
 *    This can repeatedly traverse the same sequence and lead to O(n²)
 *    behavior.
 *
 * 4. Forgetting duplicates:
 *    HashSet naturally removes duplicates, which is useful here.
 *
 * Interview Follow-up:
 *
 * Why isn't the nested while loop O(n²)?
 *
 * Because the while loop only runs for sequence starts, and each number
 * can be visited during a sequence traversal at most once.
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {

        Set<Integer> numbers = new HashSet<>();

        // Store all numbers for O(1) average membership checks.
        for (int num : nums) {
            numbers.add(num);
        }

        int longest = 0;

        // Examine each unique number.
        for (int num : numbers) {

            // Only start a sequence if num is the beginning.
            if (!numbers.contains(num - 1)) {

                int currentStartOfSequence = num;
                int currentLongest = 1;

                // Expand forward while consecutive numbers exist.
                while (numbers.contains(currentStartOfSequence + 1)) {
                    currentStartOfSequence++;
                    currentLongest++;
                }

                longest = Math.max(longest, currentLongest);
            }
        }

        return longest;
    }

    public static void main(String[] args) {

        LongestConsecutive solution = new LongestConsecutive();

        test(solution, new int[]{100, 4, 200, 1, 3, 2});
        test(solution, new int[]{1, 2, 2, 3});
        test(solution, new int[]{-2, -1, 0, 1});
        test(solution, new int[]{});
        test(solution, new int[]{10});
        test(solution, new int[]{0, 0, 0});
        test(solution, new int[]{9, 1, 4, 7, 3, 2, 6, 5});
    }

    private static void test(LongestConsecutive solution, int[] nums) {

        int result = solution.longestConsecutive(nums);

        System.out.println(
                "Input: "
                        + java.util.Arrays.toString(nums)
                        + " -> Output: "
                        + result
        );
    }
}
