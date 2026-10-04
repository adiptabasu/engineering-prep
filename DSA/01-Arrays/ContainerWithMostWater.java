/**
 * Container With Most Water
 *
 * LeetCode:
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Problem:
 * Given an integer array height where height[i] represents the height
 * of a vertical line at position i, find two lines that together with
 * the x-axis form a container that holds the most water.
 *
 * Example:
 *
 *     height = [1,8,6,2,5,4,8,3,7]
 *
 *     Answer = 49
 *
 * The optimal pair is:
 *
 *     height[1] = 8
 *     height[8] = 7
 *
 *     width  = 8 - 1 = 7
 *     height = min(8, 7) = 7
 *
 *     area = 7 * 7 = 49
 *
 *
 * ============================================================
 * BRUTE FORCE
 * ============================================================
 *
 * Try every possible pair of lines.
 *
 * For every pair (i, j):
 *
 *     width = j - i
 *     limitingHeight = min(height[i], height[j])
 *     area = width * limitingHeight
 *
 * Keep the maximum area.
 *
 * Time: O(n^2)
 * Space: O(1)
 *
 *
 * ============================================================
 * OPTIMIZED APPROACH — TWO POINTERS
 * ============================================================
 *
 * Start with the widest possible container:
 *
 *     left  = 0
 *     right = n - 1
 *
 * Calculate the area of the current pair.
 *
 * Then move the pointer corresponding to the SHORTER line.
 *
 *
 * ============================================================
 * KEY INSIGHT — WHY MOVE THE SHORTER SIDE?
 * ============================================================
 *
 * The area is:
 *
 *     area = width * min(leftHeight, rightHeight)
 *
 * Suppose:
 *
 *     leftHeight  = 3
 *     rightHeight = 8
 *
 * The current container is limited by 3:
 *
 *     area = width * 3
 *
 * If we move the RIGHT pointer:
 *
 *     width decreases
 *
 * But the limiting height is still potentially limited by the
 * left height of 3.
 *
 * Therefore, moving the taller side cannot produce a better
 * container while the shorter side remains unchanged.
 *
 * Instead, move the shorter side:
 *
 *     left++
 *
 * This gives us a chance to encounter a taller line.
 *
 * Therefore:
 *
 *     if height[left] < height[right]:
 *         left++
 *
 *     else:
 *         right--
 *
 *
 * ============================================================
 * WHY START FROM THE EXTREMES?
 * ============================================================
 *
 * The initial pair has the maximum possible width.
 *
 * Every future pair has a smaller width.
 *
 * Therefore, once we move a pointer inward, we must compensate
 * for the smaller width by finding a taller limiting boundary.
 *
 * This is exactly why the shorter boundary is discarded.
 *
 *
 * ============================================================
 * ALGORITHM
 * ============================================================
 *
 * 1. Set left = 0.
 *
 * 2. Set right = height.length - 1.
 *
 * 3. While left < right:
 *
 *       a. Calculate the current area.
 *
 *       b. Update maxArea.
 *
 *       c. Move the shorter pointer inward.
 *
 * 4. Return maxArea.
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time: O(n)
 *
 * Both pointers only move toward each other.
 *
 * Each pointer can move at most n times.
 *
 * Space: O(1)
 *
 * No additional data structure is required.
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Moving the taller pointer instead of the shorter pointer.
 *
 * 2. Using:
 *
 *      right - left + 1
 *
 *    for width.
 *
 *    The distance between two positions is:
 *
 *      right - left
 *
 * 3. Forgetting that the container height is determined by:
 *
 *      min(height[left], height[right])
 *
 * 4. Using nested loops and accidentally writing an O(n^2)
 *    solution when a Two Pointer solution is possible.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * 1. Why is this O(n)?
 *
 *    Because left and right only move inward. No pointer ever
 *    moves backward.
 *
 * 2. Why can't we use the tallest two lines?
 *
 *    Because the distance between the lines also matters.
 *    A very tall pair that is close together may hold less water
 *    than a slightly shorter pair that is much farther apart.
 *
 * 3. What happens when both heights are equal?
 *
 *    Either pointer can be moved. The implementation below moves
 *    the right pointer.
 *
 *
 * ============================================================
 * LEARNING NOTE
 * ============================================================
 *
 * This problem demonstrates an important Two Pointer pattern:
 *
 *     Start from the extremes
 *             ↓
 *     Calculate current result
 *             ↓
 *     Identify the limiting side
 *             ↓
 *     Move the limiting pointer
 *
 * The key isn't simply remembering:
 *
 *     "Move the shorter pointer."
 *
 * The important reasoning is:
 *
 *     Width always decreases.
 *     Therefore, to improve the area, the limiting height must
 *     have an opportunity to increase.
 *
 * This reasoning will become especially important in harder
 * Two Pointer problems.
 */
public class ContainerWithMostWater
{
    public int maxArea(int[] height)
    {
        int maxAreaValue = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right)
        {
            int width = right - left;

            int limitingHeight = Math.min(
                    height[left],
                    height[right]
            );

            int areaValue = width * limitingHeight;

            maxAreaValue = Math.max(
                    maxAreaValue,
                    areaValue
            );

            /*
             * The shorter side limits the current area.
             *
             * Moving the taller side would reduce the width
             * without giving us a chance to increase the
             * limiting height.
             */
            if (height[left] < height[right])
            {
                left++;
            }
            else
            {
                right--;
            }
        }

        return maxAreaValue;
    }

    public static void main(String[] args)
    {
        ContainerWithMostWater solution =
                new ContainerWithMostWater();

        System.out.println(
                solution.maxArea(
                        new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}
                )
        ); // Expected: 49

        System.out.println(
                solution.maxArea(
                        new int[]{1, 1}
                )
        ); // Expected: 1

        System.out.println(
                solution.maxArea(
                        new int[]{4, 3, 2, 1, 4}
                )
        ); // Expected: 16

        System.out.println(
                solution.maxArea(
                        new int[]{1, 2, 1}
                )
        ); // Expected: 2
    }
}
