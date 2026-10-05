/**
 * Trapping Rain Water
 *
 * LeetCode:
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Problem:
 * Given an array of non-negative integers representing an elevation map
 * where the width of each bar is 1, calculate how much water can be
 * trapped after raining.
 *
 * Example:
 *
 *     height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *
 *     Answer = 6
 *
 *
 * ============================================================
 * CORE OBSERVATION
 * ============================================================
 *
 * For any position i, the amount of water that can be stored above
 * that position depends on the tallest wall to its left and the
 * tallest wall to its right.
 *
 * The water level is determined by the SHORTER of those two walls.
 *
 * Therefore:
 *
 *     water[i] =
 *         min(maxLeft, maxRight) - height[i]
 *
 * If the result is negative, no water can be stored at that
 * position.
 *
 *
 * ============================================================
 * APPROACH 1 — PREFIX / SUFFIX MAXIMUMS
 * ============================================================
 *
 * For every position i, store:
 *
 *     highestToLeft[i]
 *         = highest wall strictly to the left of i
 *
 *     highestToRight[i]
 *         = highest wall strictly to the right of i
 *
 * Then:
 *
 *     water[i] =
 *         min(highestToLeft[i], highestToRight[i])
 *         - height[i]
 *
 *
 * Example:
 *
 *             left wall       right wall
 *                 ↓               ↓
 *
 *                 █       █       █
 *                 █   💧  █       █
 *             █   █   💧  █   █   █
 *             █   █   💧  █   █   █
 *             ---------------------
 *                     ↑
 *                  current
 *
 * The shorter surrounding wall determines the maximum water
 * level at the current position.
 *
 *
 * ============================================================
 * APPROACH 1 — ALGORITHM
 * ============================================================
 *
 * First pass:
 *
 *     Traverse from left to right.
 *
 *     Before processing height[i], highestLeft represents the
 *     highest wall strictly before i.
 *
 *     Store that value in highestToLeft[i].
 *
 *     Then update highestLeft using height[i].
 *
 *
 * Second pass:
 *
 *     Traverse from right to left.
 *
 *     Before processing height[i], highestRight represents the
 *     highest wall strictly after i.
 *
 *     Store that value in highestToRight[i].
 *
 *     Then update highestRight using height[i].
 *
 *
 * Third pass:
 *
 *     Calculate the water at every position.
 *
 *
 * ============================================================
 * APPROACH 1 — COMPLEXITY
 * ============================================================
 *
 * Time: O(n)
 *
 * We perform three linear passes over the array.
 *
 * Space: O(n)
 *
 * We store two arrays of size n.
 *
 *
 * ============================================================
 * APPROACH 2 — TWO POINTERS
 * ============================================================
 *
 * The prefix/suffix solution tells us:
 *
 *     water[i] =
 *         min(maxLeft, maxRight) - height[i]
 *
 * But do we really need to store maxLeft and maxRight for
 * every position?
 *
 * No.
 *
 * We can process the array from both ends and maintain only:
 *
 *     left
 *     right
 *     highestLeft
 *     highestRight
 *     trappedWater
 *
 * This reduces auxiliary space from O(n) to O(1).
 *
 *
 * ============================================================
 * TWO POINTER KEY INSIGHT
 * ============================================================
 *
 * We have two pointers:
 *
 *              left                 right
 *                ↓                     ↓
 *
 *     [ ... ... ... ... ... ... ... ... ... ]
 *
 * We also maintain:
 *
 *     highestLeft
 *     highestRight
 *
 * The important question is:
 *
 *     Which side can we safely process?
 *
 *
 * Suppose:
 *
 *     height[left] <= height[right]
 *
 * Then the current left boundary is no taller than the current
 * right boundary.
 *
 * Therefore, the right side already contains a wall at least as
 * tall as height[left].
 *
 * This means the amount of water at the current left position is
 * determined entirely by the best wall we have seen on the left.
 *
 * We can safely process the left position.
 *
 *
 * Conversely:
 *
 *     height[left] > height[right]
 *
 * means the right side is the limiting side.
 *
 * Therefore, we can safely process the right position using
 * highestRight.
 *
 *
 * This gives us the rule:
 *
 *     if height[left] <= height[right]
 *         process left
 *     else
 *         process right
 *
 *
 * ============================================================
 * WHY CAN WE PROCESS THE LEFT SIDE SAFELY?
 * ============================================================
 *
 * Consider:
 *
 *     height[left] <= height[right]
 *
 * We know there is a right boundary at least as tall as the
 * current left boundary.
 *
 * Therefore, the right side cannot be the limiting factor for
 * the current left position.
 *
 * The limiting boundary is determined by the left side.
 *
 * So if:
 *
 *     highestLeft > height[left]
 *
 * the current position can hold:
 *
 *     highestLeft - height[left]
 *
 * units of water.
 *
 *
 * Example:
 *
 *     highestLeft = 5
 *     height[left] = 2
 *
 *     water = 5 - 2
 *           = 3
 *
 * We don't need the exact highest wall on the right because we
 * already know the current right boundary is at least as tall
 * as the current left boundary.
 *
 *
 * ============================================================
 * WHY CAN WE PROCESS THE RIGHT SIDE SAFELY?
 * ============================================================
 *
 * This is the exact mirror image.
 *
 * If:
 *
 *     height[left] > height[right]
 *
 * then the current right boundary is the limiting side.
 *
 * We know there is a left boundary at least as tall as it.
 *
 * Therefore, the amount of water at the current right position
 * is determined by highestRight.
 *
 * If:
 *
 *     highestRight > height[right]
 *
 * then:
 *
 *     water = highestRight - height[right]
 *
 *
 * ============================================================
 * IMPORTANT INVARIANT
 * ============================================================
 *
 * When processing the left side:
 *
 *     height[left] <= height[right]
 *
 * therefore the right side provides a sufficient boundary.
 *
 * When processing the right side:
 *
 *     height[left] > height[right]
 *
 * therefore the left side provides a sufficient boundary.
 *
 * This is what allows us to avoid storing the complete
 * highestToLeft[] and highestToRight[] arrays.
 *
 *
 * ============================================================
 * TWO POINTER ALGORITHM
 * ============================================================
 *
 * 1. Set:
 *
 *        left = 0
 *        right = n - 1
 *
 * 2. Set:
 *
 *        highestLeft = 0
 *        highestRight = 0
 *
 * 3. While left < right:
 *
 *        If height[left] <= height[right]:
 *
 *            Update highestLeft.
 *
 *            If the current height is below highestLeft,
 *            water can be trapped here.
 *
 *            Move left forward.
 *
 *        Otherwise:
 *
 *            Update highestRight.
 *
 *            If the current height is below highestRight,
 *            water can be trapped here.
 *
 *            Move right backward.
 *
 * 4. Return trappedWater.
 *
 *
 * ============================================================
 * COMPLEXITY — TWO POINTER APPROACH
 * ============================================================
 *
 * Time: O(n)
 *
 * Both pointers only move toward each other.
 *
 *     left  → moves forward
 *     right → moves backward
 *
 * Neither pointer ever moves backwards.
 *
 * Therefore, every position is processed at most once.
 *
 * Space: O(1)
 *
 * We only maintain a fixed number of variables.
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Using the current height as the boundary instead of the
 *    maximum height encountered so far.
 *
 *
 * 2. Updating highestLeft / highestRight after calculating
 *    water.
 *
 *    The current position must first be considered as a
 *    potential new maximum boundary.
 *
 *
 * 3. Using the wrong pointer movement.
 *
 *    The pointer corresponding to the smaller current height
 *    must be processed.
 *
 *
 * 4. Forgetting that the water level is determined by the
 *    shorter boundary.
 *
 *
 * 5. Assuming that two pointers automatically means O(1) space.
 *
 *    The first prefix/suffix solution also uses linear time,
 *    but stores information for every position.
 *
 *    The optimized solution stores only running state.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * 1. Why is the prefix/suffix approach O(n) space?
 *
 *    Because it stores two arrays containing information for
 *    every position.
 *
 *
 * 2. Why is the Two Pointer solution O(1) space?
 *
 *    Because only the current pointers and running maximums
 *    are stored.
 *
 *
 * 3. Why can't we simply process whichever side has the smaller
 *    highest boundary?
 *
 *    The decision is based on the current boundary heights and
 *    the guarantee that the opposite current boundary is high
 *    enough to resolve the current side.
 *
 *
 * 4. What if height[left] == height[right]?
 *
 *    Either side can safely be processed. This implementation
 *    processes the left side.
 *
 *
 * ============================================================
 * LEARNING NOTE
 * ============================================================
 *
 * This problem is an excellent example of optimization through
 * removal of unnecessary state.
 *
 * First we derive:
 *
 *     water[i] = min(maxLeft, maxRight) - height[i]
 *
 * Then we build the straightforward O(n)-space solution.
 *
 * Finally we recognize that we don't need the historical
 * maxLeft/maxRight values for every position.
 *
 * We only need the running values relevant to the current
 * pointers.
 *
 * Therefore:
 *
 *     O(n) time + O(n) space
 *
 * becomes:
 *
 *     O(n) time + O(1) space
 *
 * This is an important interview pattern:
 *
 *     Store everything first
 *              ↓
 *     Identify what information is actually needed
 *              ↓
 *     Replace stored history with running state
 *              ↓
 *     Reduce auxiliary space
 */
public class TrappingRainWater
{
    /**
     * Approach 1:
     * Prefix/Suffix Maximum Arrays
     *
     * Time: O(n)
     * Space: O(n)
     */
    public int trap(int[] height)
    {
        int trappedWater = 0;

        int[] highestToLeft = new int[height.length];
        int[] highestToRight = new int[height.length];

        int highestLeft = 0;
        int highestRight = 0;

        /*
         * Build both maximum arrays in one traversal.
         *
         * highestToLeft[i] stores the highest wall strictly
         * to the left of i.
         *
         * highestToRight[i] stores the highest wall strictly
         * to the right of i.
         */
        for (int i = 0; i < height.length; i++)
        {
            highestToLeft[i] = highestLeft;

            highestToRight[height.length - i - 1] = highestRight;

            highestLeft = Math.max(
                    highestLeft,
                    height[i]
            );

            highestRight = Math.max(
                    highestRight,
                    height[height.length - i - 1]
            );
        }

        /*
         * Calculate the amount of water that can be stored
         * at every position.
         */
        for (int i = 0; i < height.length; i++)
        {
            int waterAtIndex =
                    Math.min(
                            highestToLeft[i],
                            highestToRight[i]
                    ) - height[i];

            /*
             * If the current position is higher than one of
             * the surrounding boundaries, no water can be
             * stored here.
             */
            if (waterAtIndex < 0)
            {
                waterAtIndex = 0;
            }

            trappedWater += waterAtIndex;
        }

        return trappedWater;
    }

    /**
     * Approach 2:
     * Two Pointers
     *
     * Time: O(n)
     * Space: O(1)
     *
     * This is the optimized solution.
     */
    public int trapAdvanced(int[] height)
    {
        int trappedWater = 0;

        int left = 0;
        int right = height.length - 1;

        /*
         * highestLeft:
         *
         * Highest wall encountered while moving from the
         * left side toward the center.
         *
         * highestRight:
         *
         * Highest wall encountered while moving from the
         * right side toward the center.
         */
        int highestLeft = 0;
        int highestRight = 0;

        /*
         * Process the array from both ends.
         *
         * At every step we determine which side is safe to
         * resolve.
         */
        while (left < right)
        {
            /*
             * If the current left wall is shorter than or equal
             * to the current right wall, the right side already
             * provides a boundary at least as high as the
             * current left boundary.
             *
             * Therefore, the water at the current left position
             * can be determined using highestLeft.
             */
            if (height[left] <= height[right])
            {
                /*
                 * First update the maximum wall seen from
                 * the left.
                 *
                 * The current position itself may become the
                 * new highest left boundary.
                 */
                highestLeft = Math.max(
                        highestLeft,
                        height[left]
                );

                /*
                 * If the current height is below the highest
                 * wall we've already encountered on the left,
                 * the difference represents trapped water.
                 *
                 * Example:
                 *
                 *     highestLeft = 5
                 *     height[left] = 2
                 *
                 *     trapped = 5 - 2 = 3
                 */
                if (highestLeft > height[left])
                {
                    trappedWater +=
                            highestLeft - height[left];
                }

                /*
                 * This position has now been completely
                 * processed, so move the left pointer inward.
                 */
                left++;
            }
            else
            {
                /*
                 * The current right wall is shorter.
                 *
                 * Therefore, the left side already provides
                 * a boundary at least as high as the current
                 * right boundary.
                 *
                 * We can safely resolve the current right
                 * position using highestRight.
                 */
                highestRight = Math.max(
                        highestRight,
                        height[right]
                );

                /*
                 * If the current right height is below the
                 * highest wall encountered from the right,
                 * the difference is trapped water.
                 */
                if (highestRight > height[right])
                {
                    trappedWater +=
                            highestRight - height[right];
                }

                /*
                 * This position has now been completely
                 * processed, so move the right pointer inward.
                 */
                right--;
            }
        }

        return trappedWater;
    }

    public static void main(String[] args)
    {
        TrappingRainWater solution =
                new TrappingRainWater();

        int[] example1 =
                {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(
                solution.trap(example1)
        ); // Expected: 6

        System.out.println(
                solution.trapAdvanced(example1)
        ); // Expected: 6

        int[] example2 =
                {4, 2, 0, 3, 2, 5};

        System.out.println(
                solution.trap(example2)
        ); // Expected: 9

        System.out.println(
                solution.trapAdvanced(example2)
        ); // Expected: 9

        int[] example3 =
                {1, 2, 3, 4, 5};

        System.out.println(
                solution.trap(example3)
        ); // Expected: 0

        System.out.println(
                solution.trapAdvanced(example3)
        ); // Expected: 0

        int[] example4 =
                {5, 4, 3, 2, 1};

        System.out.println(
                solution.trap(example4)
        ); // Expected: 0

        System.out.println(
                solution.trapAdvanced(example4)
        ); // Expected: 0
    }
}
