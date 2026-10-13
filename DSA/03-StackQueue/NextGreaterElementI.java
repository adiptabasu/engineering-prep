import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 496: Next Greater Element I
 *
 * Problem URL:
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * Problem:
 * Given two integer arrays nums1 and nums2, where nums1 is a subset
 * of nums2 and all values in nums2 are distinct, find the next greater
 * element for every element in nums1.
 *
 * The next greater element of a value x is the first element greater
 * than x that appears to its right in nums2.
 *
 * If no such element exists, the answer is -1.
 *
 * Example:
 *
 * Input:
 * nums1 = [4, 1, 2]
 * nums2 = [1, 3, 4, 2]
 *
 * Output:
 * [-1, 3, -1]
 *
 * Explanation:
 * - 4 has no greater element to its right, so the answer is -1.
 * - 1's next greater element is 3.
 * - 2 has no greater element to its right, so the answer is -1.
 *
 * Approach:
 * Monotonic Stack + HashMap + Auxiliary Array
 *
 * Key Insight:
 * When processing an element from left to right, if it is greater
 * than the elements represented by indices at the top of the stack,
 * it is the next greater element for those elements.
 *
 * We use a stack of indices rather than values so that we can update
 * the answer array at the correct positions.
 *
 * Algorithm:
 *
 * Phase 1: Find the next greater element for every position in nums2.
 *
 * 1. Create a stack to store indices of elements whose next greater
 *    element has not yet been found.
 *
 * 2. Iterate through nums2:
 *    a. While the stack is not empty and the current element is
 *       greater than the element at the index on top of the stack:
 *       - Pop the index.
 *       - Record the current element as the next greater element
 *         for that index.
 *
 *    b. Push the current index onto the stack.
 *    c. Store the current element's index in a HashMap.
 *
 * 3. After processing nums2, any indices remaining in the stack
 *    have no greater element to their right. Assign -1 to them.
 *
 * Phase 2: Construct the result for nums1.
 *
 * 4. For every value in nums1:
 *    - Find its index in nums2 using the HashMap.
 *    - Retrieve its precomputed next greater element.
 *    - Store that result in the corresponding position in the
 *      returned array.
 *
 * Why It Works:
 *
 * The stack stores indices of elements waiting for their next
 * greater element.
 *
 * When a new element is greater than the element at the top of
 * the stack, it is the first greater element encountered to the
 * right of that stacked element.
 *
 * Once an index is popped, its answer is known and it never needs
 * to be processed again.
 *
 * Any indices remaining after the traversal have no greater
 * element to their right, so their answers are -1.
 *
 * The HashMap lets us translate each value from nums1 into its
 * corresponding position in nums2, allowing us to retrieve the
 * precomputed answer efficiently.
 *
 * Complexity Analysis:
 *
 * Let:
 * n = nums2.length
 * m = nums1.length
 *
 * Time Complexity: O(n + m)
 *
 * - Each index is pushed onto the stack exactly once.
 * - Each index is popped at most once.
 * - Building the index map takes O(n) average time.
 * - Retrieving the answers for nums1 takes O(m) average time.
 *
 * Auxiliary Space Complexity: O(n)
 *
 * - Stack: O(n)
 * - HashMap: O(n)
 * - Next-greater array: O(n)
 *
 * The returned array requires an additional O(m) space.
 * Therefore, total space including the output is O(n + m).
 *
 * Common Mistakes:
 *
 * 1. Storing values instead of indices when using an auxiliary
 *    answer array. Indices identify the exact positions to update.
 *
 * 2. Forgetting to push the current index after resolving smaller
 *    elements from the stack.
 *
 * 3. Checking stack.peek() before checking whether the stack is empty.
 *
 * 4. Forgetting to assign -1 to elements remaining in the stack.
 *
 * 5. Confusing the next greater element with the maximum element
 *    to the right. We need the first greater element, not the largest.
 *
 * 6. Forgetting that nums1 and nums2 can have different lengths
 *    and that the output order must match nums1.
 *
 * 7. Assuming the nested while loop makes the algorithm O(n^2).
 *    Each index is pushed once and popped at most once, giving O(n)
 *    total stack-processing time.
 *
 * Important Assumption:
 * All elements in nums2 are distinct, as guaranteed by the problem.
 */
public class NextGreaterElementI {

    /**
     * Finds the next greater element for every value in nums1.
     *
     * @param nums1 array containing the values whose answers are required
     * @param nums2 array in which next greater elements are searched
     * @return array containing the next greater element for each value
     *         in nums1, or -1 when no greater element exists
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Stores indices of elements waiting for their next greater element.
        Deque<Integer> elementStack = new ArrayDeque<>();

        // Maps each value in nums2 to its index.
        // This works because nums2 contains distinct values.
        Map<Integer, Integer> numMap = new HashMap<>();

        // Stores the next greater element for every position in nums2.
        int[] maxValueToRight = new int[nums2.length];

        // Phase 1: Compute next greater elements for nums2.
        for (int i = 0; i < nums2.length; i++) {

            // The current value resolves the next greater element
            // for every smaller unresolved value at the top of the stack.
            while (!elementStack.isEmpty()
                    && nums2[elementStack.peek()] < nums2[i]) {

                // Retrieve the index whose next greater element is known.
                int previousIndex = elementStack.pop();

                // Record the current value as its next greater element.
                maxValueToRight[previousIndex] = nums2[i];
            }

            // The current index may still need a greater element
            // from a future position, so add it to the stack.
            elementStack.push(i);

            // Record the current value's position for later lookups.
            numMap.put(nums2[i], i);
        }

        // Any indices remaining in the stack have no greater element
        // to their right.
        while (!elementStack.isEmpty()) {
            maxValueToRight[elementStack.pop()] = -1;
        }

        // Phase 2: Retrieve answers in the order requested by nums1.
        int[] returnArray = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {

            // Find the position of nums1[i] in nums2, then retrieve
            // its precomputed next greater element.
            returnArray[i] = maxValueToRight[numMap.get(nums1[i])];
        }

        return returnArray;
    }

    /**
     * Local test cases.
     *
     * Run this main() method to test the solution without LeetCode.
     */
    public static void main(String[] args) {

        NextGreaterElementI solution = new NextGreaterElementI();

        int[][] nums1Cases = {
            {4, 1, 2},
            {2, 4},
            {1, 3, 5, 2, 4}
        };

        int[][] nums2Cases = {
            {1, 3, 4, 2},
            {1, 2, 3, 4},
            {6, 5, 4, 3, 2, 1, 7}
        };

        int[][] expectedResults = {
            {-1, 3, -1},
            {3, -1},
            {7, 7, 7, 7, 7}
        };

        for (int i = 0; i < nums1Cases.length; i++) {

            int[] actual = solution.nextGreaterElement(
                nums1Cases[i],
                nums2Cases[i]
            );

            boolean passed = java.util.Arrays.equals(
                actual,
                expectedResults[i]
            );

            System.out.printf(
                "Test %d | Expected: %s | Actual: %s | %s%n",
                i + 1,
                java.util.Arrays.toString(expectedResults[i]),
                java.util.Arrays.toString(actual),
                passed ? "PASS" : "FAIL"
            );
        }
    }
}
