
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 735: Asteroid Collision
 *
 * Problem:
 * https://leetcode.com/problems/asteroid-collision/
 *
 * Approach: Stack
 *
 * Positive asteroids move right; negative asteroids move left.
 * A collision occurs only when a positive asteroid is followed
 * by a negative asteroid.
 *
 * For each asteroid:
 * 1. Push positive asteroids directly.
 * 2. For a negative asteroid, repeatedly compare it with the
 *    positive asteroid at the top of the stack.
 * 3. If the top asteroid is larger, the current asteroid explodes.
 * 4. If both have equal size, both explode.
 * 5. If the current asteroid is larger, pop the top and continue.
 * 6. Push the current asteroid if it survives all collisions.
 *
 * Time Complexity: O(n)
 * Each asteroid is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * The stack can contain up to n surviving asteroids.
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currentAsteroid : asteroids) {

            // Right-moving asteroids cannot collide with
            // earlier right-moving asteroids.
            if (currentAsteroid > 0) {
                stack.push(currentAsteroid);
            } else {
                boolean currentAsteroidBlasted = false;

                // Collisions are possible only when the stack top
                // moves right and the current asteroid moves left.
                while (!stack.isEmpty()
                        && stack.peek() > 0
                        && !currentAsteroidBlasted) {

                    int latestAsteroid = stack.peek();

                    // Compare magnitudes using long to avoid overflow
                    // when negating Integer.MIN_VALUE.
                    long currentMagnitude = -(long) currentAsteroid;

                    if (latestAsteroid > currentMagnitude) {
                        // The top asteroid survives.
                        currentAsteroidBlasted = true;

                    } else if (latestAsteroid == currentMagnitude) {
                        // Equal-sized asteroids destroy each other.
                        stack.pop();
                        currentAsteroidBlasted = true;

                    } else {
                        // The top asteroid explodes.
                        stack.pop();
                    }
                }

                // Preserve the current asteroid if it survives.
                if (!currentAsteroidBlasted) {
                    stack.push(currentAsteroid);
                }
            }
        }

        // Stack top is the rightmost survivor.
        // Fill the array backwards to restore left-to-right order.
        int[] result = new int[stack.size()];

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        AsteroidCollision solution = new AsteroidCollision();

        // Example 1: The -5 asteroid destroys the 2, then explodes
        // against the larger 10.
        System.out.println(
            java.util.Arrays.toString(
                solution.asteroidCollision(new int[]{10, 2, -5})
            )
        ); // Expected: [10]

        // Example 2: Equal-sized asteroids destroy each other.
        System.out.println(
            java.util.Arrays.toString(
                solution.asteroidCollision(new int[]{8, -8})
            )
        ); // Expected: []

        // Example 3: No collision occurs.
        System.out.println(
            java.util.Arrays.toString(
                solution.asteroidCollision(new int[]{5, 10, 15})
            )
        ); // Expected: [5, 10, 15]

        // Example 4: The negative asteroid destroys both smaller
        // right-moving asteroids.
        System.out.println(
            java.util.Arrays.toString(
                solution.asteroidCollision(new int[]{3, 5, -10})
            )
        ); // Expected: [-10]

        // Example 5: A left-moving asteroid survives because
        // there is no right-moving asteroid to collide with.
        System.out.println(
            java.util.Arrays.toString(
                solution.asteroidCollision(new int[]{-2, -1})
            )
        ); // Expected: [-2, -1]
    }
}

