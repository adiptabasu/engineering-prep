import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Largest Rectangle in Histogram
 *
 * LeetCode: https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Difficulty: Hard
 *
 * Approach: Monotonic Stack
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LargestRectangleInHistogram {

    /**
     * Calculates the largest rectangular area in a histogram.
     *
     * Each bar has a width of 1.
     *
     * We maintain a stack of indices representing bars whose
     * maximum rectangle areas have not yet been calculated.
     *
     * When we encounter a shorter bar, it establishes the right
     * boundary for taller bars popped from the stack.
     *
     * The remaining stack top, if present, establishes the left
     * boundary for the popped bar.
     *
     * @param heights heights of the histogram bars
     * @return maximum rectangular area
     */
    public int largestRectangleArea(int[] heights) {

        // Store indices rather than heights so that we can
        // calculate the width of each rectangle.
        Deque<Integer> stack = new ArrayDeque<>();

        int largestAreaRectangle = 0;

        // Process the histogram from left to right.
        for (int i = 0; i < heights.length; i++) {

            /*
             * If the current bar is shorter than the bar at the
             * top of the stack, we have found the right boundary
             * for the taller bar.
             */
            while (!stack.isEmpty()
                    && heights[i] < heights[stack.peek()]) {

                // Remove the index of the bar whose area we calculate.
                int poppedIndex = stack.pop();

                /*
                 * The current index i is the right boundary.
                 *
                 * If the stack is empty, no shorter bar exists
                 * to the left, so the rectangle starts at index 0.
                 *
                 * Otherwise, stack.peek() is the left boundary.
                 *
                 * The boundary bars themselves are excluded.
                 */
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                // The popped bar determines the rectangle's height.
                int area = heights[poppedIndex] * width;

                // Keep track of the largest area found so far.
                largestAreaRectangle = Math.max(
                        largestAreaRectangle, area
                );
            }

            // Store the current bar's index for future processing.
            stack.push(i);
        }

        /*
         * Process any bars remaining in the stack.
         *
         * These bars have no shorter bar to their right within
         * the histogram, so their right boundary is the virtual
         * index heights.length.
         */
        while (!stack.isEmpty()) {

            int poppedIndex = stack.pop();

            /*
             * If the stack is empty, the rectangle extends from
             * index 0 through the last histogram bar.
             *
             * Otherwise, the remaining stack top establishes
             * the left boundary.
             */
            int width = stack.isEmpty()
                    ? heights.length
                    : heights.length - stack.peek() - 1;

            int area = heights[poppedIndex] * width;

            largestAreaRectangle = Math.max(
                    largestAreaRectangle, area
            );
        }

        return largestAreaRectangle;
    }

    /**
     * Runs test cases locally without requiring LeetCode.
     */
    public static void main(String[] args) {

        LargestRectangleInHistogram solution =
                new LargestRectangleInHistogram();

        // Standard example.
        runTest(
                solution,
                new int[]{2, 1, 5, 6, 2, 3},
                10
        );

        // Increasing heights.
        runTest(
                solution,
                new int[]{2, 4, 6},
                8
        );

        // All bars have equal heights.
        runTest(
                solution,
                new int[]{5, 5, 5},
                15
        );

        // A shorter bar limits the rectangle's width.
        runTest(
                solution,
                new int[]{2, 4, 3},
                6
        );

        // A single bar.
        runTest(
                solution,
                new int[]{7},
                7
        );

        // Descending heights.
        runTest(
                solution,
                new int[]{6, 4, 2},
                8
        );

        // Empty histogram.
        runTest(
                solution,
                new int[]{},
                0
        );
    }

    /**
     * Executes a test and prints whether it passed.
     */
    private static void runTest(
            LargestRectangleInHistogram solution,
            int[] heights,
            int expected
    ) {

        int actual = solution.largestRectangleArea(heights);

        boolean passed = actual == expected;

        System.out.printf(
                "Input: %s | Expected: %d | Actual: %d | %s%n",
                java.util.Arrays.toString(heights),
                expected,
                actual,
                passed ? "PASS" : "FAIL"
        );

        if (!passed) {
            throw new AssertionError(
                    "Test failed: expected "
                            + expected + ", but got " + actual
            );
        }
    }
}
