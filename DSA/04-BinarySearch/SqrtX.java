/**
 * LeetCode 69: Sqrt(x)
 * https://leetcode.com/problems/sqrtx/
 *
 * Problem:
 * Return the integer square root of non-negative x, rounded down. In other
 * words, return the largest integer r such that r * r <= x.
 *
 * Approach: Binary-search possible answers and keep the largest midpoint
 * whose square does not exceed x.
 *
 * Key Insight:
 * As the candidate root increases, its square only increases. Thus candidates
 * whose square is at most x form a prefix of the search range.
 *
 * Why It Works:
 * When mid * mid <= x, mid is a valid answer, but a larger one may also work.
 * Save mid and search right. Otherwise, mid is too large, so search left.
 *
 * Complexity:
 * Time: O(log x) for x > 1.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Multiplying two ints before assigning the result to long. Cast before
 *   multiplication so the product itself is computed as a long.
 * - Searching for an exact square only; the answer can be between squares.
 * - Forgetting that x = 0 should return 0.
 */
public class SqrtX {

    /**
     * Returns floor(sqrt(x)).
     *
     * @param x non-negative integer
     * @return the greatest integer whose square is at most x
     */
    public int mySqrt(int x) {
        int low = 1;
        int high = x;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Cast before multiplying; otherwise int multiplication could
            // overflow before its result is assigned to a long variable.
            long power = (long) mid * mid;

            if (power <= x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}
