import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

/**
 * Problem: Trapping Rain Water
 *
 * LeetCode: https://leetcode.com/problems/trapping-rain-water/
 *
 * Difficulty: Hard
 *
 * Approach: Monotonic Stack
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class TrappingRainWaterStack {

    /**
     * Calculates the total amount of water trapped between bars.
     *
     * The stack stores indices of bars in non-increasing height order.
     *
     * When the current bar is taller than the bar at the top
     * of the stack, we may have found the right boundary of a valley.
     *
     * We pop the valley's bottom and use the remaining stack top
     * as its left boundary.
     *
     * @param height heights of the elevation map
     * @return total trapped water
     */
    public int trap(int[] height) {

        int trappedWater = 0;

        // Store indices rather than heights because we need
        // to calculate distances between the boundaries.
        Deque<Integer> stack = new ArrayDeque<>();

        // Process bars from left to right.
        for (int i = 0; i < height.length; i++) {

            /*
             * If the current bar is taller than the bar at the
             * top of the stack, we may have found a right boundary
             * for a valley.
             */
            while (!stack.isEmpty()
                    && height[i] > height[stack.peek()]) {

                // Remove the index representing the valley's bottom.
                int bottom = stack.pop();

                /*
                 * Without a left boundary, no water can be trapped
                 * for this popped bottom.
                 *
                 * A left boundary exists only if the stack is
                 * non-empty after popping.
                 */
                if (stack.isEmpty()) {
                    continue;
                }

                /*
                 * The current index i is the right boundary.
                 * The remaining stack top is the left boundary.
                 *
                 * Exclude both boundaries when calculating width.
                 */
                int width = i - stack.peek() - 1;

                /*
                 * The water level is limited by the shorter
                 * of the left and right boundaries.
                 *
                 * Subtract the bottom bar's height to obtain
                 * the water depth above that bar.
                 */
                int depth = Math.min(
                        height[i],
                        height[stack.peek()]
                ) - height[bottom];

                // Calculate the water trapped in this valley.
                int boundWater = depth * width;

                // Add it to the total.
                trappedWater += boundWater;
            }

            // Push the current index for future processing.
            stack.push(i);
        }

        return trappedWater;
    }

    /**
     * Runs local tests without requiring LeetCode.
     */
    public static void main(String[] args) {

        TrappingRainWaterStack solution =
                new TrappingRainWaterStack();

        // Standard LeetCode example.
        runTest(
                solution,
                new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},
                6
        );

        // Deep valley bounded by taller bars.
        runTest(
                solution,
                new int[]{4, 2, 0, 3, 2, 5},
                9
        );

        // Multiple valleys.
        runTest(
                solution,
                new int[]{3, 0, 2, 0, 4},
                7
        );

        // Small valley.
        runTest(
                solution,
                new int[]{3, 1, 2},
                1
        );

        // Increasing heights: no water trapped.
        runTest(
                solution,
                new int[]{1, 2},
                0
        );

        // Decreasing heights: no water trapped.
        runTest(
                solution,
                new int[]{4, 3, 2, 1},
                0
        );

        // Empty elevation map.
        runTest(
                solution,
                new int[]{},
                0
        );

        // All bars have equal heights.
        runTest(
                solution,
                new int[]{3, 3, 3},
                0
        );
    }

    /**
     * Executes a test and prints whether it passed.
     */
    private static void runTest(
            TrappingRainWaterStack solution,
            int[] height,
            int expected
    ) {

        int actual = solution.trap(height);

        boolean passed = actual == expected;

        System.out.printf(
                "Input: %s | Expected: %d | Actual: %d | %s%n",
                Arrays.toString(height),
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
