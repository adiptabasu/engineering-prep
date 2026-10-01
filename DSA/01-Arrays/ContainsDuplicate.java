import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains Duplicate
 *
 * LeetCode:
 * https://leetcode.com/problems/contains-duplicate/
 *
 * ------------------------------------------------------------
 * Problem
 * ------------------------------------------------------------
 * Given an integer array nums, return true if any value appears
 * at least twice in the array, and return false if every element
 * is distinct.
 *
 * Example:
 *
 * Input:
 * [1, 2, 3, 1]
 *
 * Output:
 * true
 *
 *
 * ------------------------------------------------------------
 * Approach 1 — HashMap
 * ------------------------------------------------------------
 * We can maintain a HashMap containing every number we have
 * already encountered.
 *
 * For every number:
 *
 *     If it already exists in the map:
 *         A duplicate has been found.
 *
 *     Otherwise:
 *         Add it to the map.
 *
 * However, the HashMap stores unnecessary information because
 * we do not need to associate a value with anything else.
 *
 * We only need to know:
 *
 *     "Have I seen this value before?"
 *
 * Therefore, HashSet is a better data structure.
 *
 *
 * ------------------------------------------------------------
 * Approach 2 — HashSet (Preferred)
 * ------------------------------------------------------------
 * A HashSet stores only unique values.
 *
 * For every number:
 *
 *     Add the number to the Set.
 *
 * HashSet.add() returns:
 *
 *     true  -> the value was not previously present
 *     false -> the value already existed
 *
 * Therefore:
 *
 *     if (!set.add(num))
 *         duplicate found
 *
 *
 * ------------------------------------------------------------
 * Why HashSet is the better choice
 * ------------------------------------------------------------
 * HashMap:
 *
 *     value -> value
 *
 * We don't need the second value.
 *
 * HashSet:
 *
 *     value
 *
 * This represents exactly the information required by the
 * problem.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 * Time:
 *     O(n) expected
 *
 * We traverse the array once.
 * HashSet insertion/lookup is expected O(1).
 *
 * Space:
 *     O(n)
 *
 * In the worst case, every element is unique and therefore
 * the Set contains n elements.
 *
 *
 * ------------------------------------------------------------
 * Alternative — Sorting
 * ------------------------------------------------------------
 * Another approach is to sort the array first.
 *
 * Example:
 *
 *     [4, 1, 3, 2, 3]
 *
 * becomes:
 *
 *     [1, 2, 3, 3, 4]
 *
 * Duplicate values will then be adjacent.
 *
 * Complexity:
 *
 *     Time  -> O(n log n)
 *     Space -> depends on the sorting algorithm
 *
 * This demonstrates an important algorithmic trade-off:
 *
 *     HashSet:
 *         Faster expected time
 *         More memory
 *
 *     Sorting:
 *         Slower asymptotic time
 *         Potentially less auxiliary memory
 *
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using nested loops unnecessarily
 *
 *    This results in O(n²) time.
 *
 * 2. Using a HashMap when only membership information is needed
 *
 *    A HashSet expresses the requirement more directly.
 *
 * 3. Forgetting that HashSet contains unique values
 *
 *    Adding an existing value does not create another entry.
 *
 * 4. Not understanding HashSet.add()
 *
 *    add() returns false when the value already exists.
 *
 *
 * ------------------------------------------------------------
 * Interview Takeaway
 * ------------------------------------------------------------
 * Before selecting a data structure, ask:
 *
 *     "What information do I actually need?"
 *
 * If we only need to know whether an element has been seen,
 * a Set is usually more appropriate than a Map.
 */
public class ContainsDuplicate {

    /**
     * HashMap implementation.
     *
     * This works correctly but stores unnecessary information.
     *
     * Time: O(n) expected
     * Space: O(n)
     */
    public boolean containsDuplicateMap(int[] nums) {

        Map<Integer, Integer> integerMap = new HashMap<>();

        for (int num : nums) {

            if (integerMap.containsKey(num)) {
                return true;
            }

            integerMap.put(num, num);
        }

        return false;
    }

    /**
     * Preferred HashSet implementation.
     *
     * HashSet.add() returns:
     *
     * true  -> value was newly added
     * false -> value already existed
     *
     * Time: O(n) expected
     * Space: O(n)
     */
    public boolean containsDuplicateSet(int[] nums) {

        Set<Integer> integerSet = new HashSet<>();

        for (int num : nums) {

            if (!integerSet.add(num)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Local test cases.
     */
    public static void main(String[] args) {

        ContainsDuplicate solution = new ContainsDuplicate();

        // Test 1
        int[] nums1 = {1, 2, 3, 1};

        System.out.println(
                "[1, 2, 3, 1] -> "
                        + solution.containsDuplicateSet(nums1)
        );

        // Test 2
        int[] nums2 = {1, 2, 3, 4};

        System.out.println(
                "[1, 2, 3, 4] -> "
                        + solution.containsDuplicateSet(nums2)
        );

        // Test 3
        int[] nums3 = {1, 1, 1, 3, 3};

        System.out.println(
                "[1, 1, 1, 3, 3] -> "
                        + solution.containsDuplicateSet(nums3)
        );

        // Test 4 — single element
        int[] nums4 = {1};

        System.out.println(
                "[1] -> "
                        + solution.containsDuplicateSet(nums4)
        );

        // Test 5 — negative values
        int[] nums5 = {-1, -2, -3, -1};

        System.out.println(
                "[-1, -2, -3, -1] -> "
                        + solution.containsDuplicateSet(nums5)
        );
    }
}
