import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 739: Daily Temperatures
 *
 * Problem:
 * Given an array of daily temperatures, return an array answer such that
 * answer[i] is the number of days you must wait after day i to get a warmer
 * temperature.
 *
 * If there is no future day with a warmer temperature, answer[i] = 0.
 *
 * LeetCode:
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Approach: Monotonic Stack (Traverse Right to Left)
 *
 * Key Idea:
 * Traverse the temperatures array from right to left.
 *
 * Maintain a stack of indices whose temperatures are candidates for being
 * the next warmer day for the current day.
 *
 * For each index i:
 *
 * 1. Pop indices while their temperatures are less than or equal to the
 *    current temperature. These indices cannot provide the answer for i.
 *
 * 2. If the stack is empty, no warmer day exists to the right, so answer[i] = 0.
 *
 * 3. Otherwise, the index at the top of the stack is the nearest warmer day.
 *    The distance is stack.peek() - i.
 *
 * 4. Push the current index onto the stack so it can be considered for
 *    earlier days.
 *
 * Why Store Indices Instead of Temperatures?
 * We need the temperatures to compare values, but we also need to calculate
 * the distance between two days. Indices provide both pieces of information.
 *
 * Why Use <= When Popping?
 * The problem requires a STRICTLY warmer temperature.
 * Equal temperatures do not qualify, so we remove indices whose temperatures
 * are less than or equal to the current temperature.
 *
 * Why Traverse Right to Left?
 * When processing a day, we have already processed every day to its right.
 * The stack therefore helps us find the nearest valid warmer day efficiently.
 *
 * Why Use peek() Instead of pop() to Calculate the Answer?
 * We only need to inspect the nearest warmer day's index.
 * peek() reads the top element without removing it.
 *
 * Complexity:
 * Time: O(n)
 *   Each index is pushed exactly once and popped at most once.
 *   Although there is a while loop inside the for loop, the total number
 *   of stack operations across the entire algorithm is O(n).
 *
 * Auxiliary Space: O(n)
 *   The stack can contain up to n indices.
 *
 * Common Mistakes:
 * 1. Using < instead of <= in the popping condition.
 *    Equal temperatures must also be removed.
 *
 * 2. Calculating the answer before removing invalid candidates.
 *
 * 3. Pushing the current index before calculating its answer.
 *    This could cause the current day to be considered its own next warmer day.
 *
 * 4. Using pop() instead of peek() to calculate the distance.
 *
 * 5. Storing the warmer temperature instead of the distance between days.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {

        // Stores indices of candidate warmer days.
        // The stack's top represents the nearest remaining candidate
        // to the right of the current index.
        Deque<Integer> rightClosestTemperatureStack = new ArrayDeque<>();

        // answer[i] stores the number of days until a warmer temperature.
        // Java initializes every element to 0, which is also the required
        // answer when no warmer day exists.
        int[] rightClosestTemperature = new int[temperatures.length];

        // Traverse from right to left so that future days are processed first.
        for (int i = temperatures.length - 1; i >= 0; i--) {

            /*
             * Step 1: Remove invalid candidates.
             *
             * If a candidate's temperature is less than or equal to today's
             * temperature, it cannot be the next warmer day for today.
             *
             * Equal temperatures are removed because we need a STRICTLY
             * warmer temperature.
             */
            while (!rightClosestTemperatureStack.isEmpty()
                    && temperatures[i] >= temperatures[rightClosestTemperatureStack.peek()]) {

                rightClosestTemperatureStack.pop();
            }

            /*
             * Step 2: Calculate the answer.
             *
             * If the stack is empty, there is no warmer day to the right.
             *
             * Otherwise, the index at the top of the stack is the nearest
             * warmer day. Subtract the current index to obtain the distance.
             */
            if (rightClosestTemperatureStack.isEmpty()) {
                rightClosestTemperature[i] = 0;
            } else {
                rightClosestTemperature[i] = rightClosestTemperatureStack.peek() - i;
            }

            /*
             * Step 3: Push the current index.
             *
             * This day may be the next warmer day for an earlier day.
             * Push only after calculating the current day's answer.
             */
            rightClosestTemperatureStack.push(i);
        }

        return rightClosestTemperature;
    }

    /**
     * Local test cases.
     *
     * These tests can be run directly from the IDE or command line.
     * They do not require LeetCode.
     */
    public static void main(String[] args) {

        DailyTemperatures solution = new DailyTemperatures();

        // Test 1: Standard example.
        test(
                solution,
                new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                new int[]{1, 1, 4, 2, 1, 1, 0, 0}
        );

        // Test 2: Warmer day immediately to the right.
        test(
                solution,
                new int[]{73, 74},
                new int[]{1, 0}
        );

        // Test 3: No warmer day to the right.
        test(
                solution,
                new int[]{74, 73},
                new int[]{0, 0}
        );

        // Test 4: Equal temperatures must be skipped.
        test(
                solution,
                new int[]{73, 73, 75},
                new int[]{2, 1, 0}
        );

        // Test 5: Strictly increasing temperatures.
        test(
                solution,
                new int[]{30, 40, 50, 60},
                new int[]{1, 1, 1, 0}
        );

        // Test 6: Strictly decreasing temperatures.
        test(
                solution,
                new int[]{90, 80, 70, 60},
                new int[]{0, 0, 0, 0}
        );

        // Test 7: Single element.
        test(
                solution,
                new int[]{50},
                new int[]{0}
        );

        // Test 8: All temperatures equal.
        test(
                solution,
                new int[]{70, 70, 70, 70},
                new int[]{0, 0, 0, 0}
        );

        // Test 9: Empty input.
        test(
                solution,
                new int[]{},
                new int[]{}
        );
    }

    /**
     * Executes a test case and verifies the result.
     */
    private static void test(
            DailyTemperatures solution,
            int[] temperatures,
            int[] expected
    ) {
        int[] actual = solution.dailyTemperatures(temperatures);

        if (Arrays.equals(actual, expected)) {
            System.out.println("PASS | Input: " + Arrays.toString(temperatures)
                    + " | Output: " + Arrays.toString(actual));
        } else {
            System.out.println("FAIL | Input: " + Arrays.toString(temperatures)
                    + " | Expected: " + Arrays.toString(expected)
                    + " | Actual: " + Arrays.toString(actual));

            throw new AssertionError("Test case failed.");
        }
    }
}
