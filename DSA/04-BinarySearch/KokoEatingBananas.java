import java.util.Arrays;

/**
 * LeetCode 875: Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * Problem:
 * Find the minimum positive integer bananas-per-hour speed that lets Koko
 * finish all piles within h hours. She eats from only one pile in each hour.
 *
 * Approach: Binary-search speeds from 1 to the size of the largest pile. For
 * each candidate speed, sum the hours needed for every pile, rounding each
 * pile's time up to a whole hour.
 *
 * Key Insight:
 * If a speed is feasible, every faster speed is feasible too. Feasibility is
 * monotone, so binary search can find its first true value.
 *
 * Why It Works:
 * A feasible midpoint is a possible minimum, so save it and search slower.
 * An infeasible midpoint is too slow, so search faster. The maximum-pile
 * speed is feasible because the problem guarantees h >= piles.length.
 *
 * Complexity:
 * Time: O(n log M), where n is piles.length and M is the largest pile.
 * Auxiliary Space: O(1), excluding the constant-size stream machinery.
 *
 * Common Mistakes:
 * - Starting the speed range at the smallest pile; speed 1 can be valid.
 * - Using floor division for hours; a partial pile still takes a full hour.
 * - Calling Math.ceil after integer division; the division has already
 *   truncated the fractional part.
 */
public class KokoEatingBananas {

    /**
     * Returns the minimum eating speed that finishes all piles within h hours.
     *
     * @param piles positive pile sizes
     * @param h available hours, at least the number of piles
     * @return the minimum feasible positive integer speed
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canEatInTime(piles, mid, h)) {
                // This speed works; keep it and look for a slower one.
                high = mid - 1;
                if (mid < ans) {
                    ans = mid;
                }
            } else {
                // This speed is too slow, so try faster speeds.
                low = mid + 1;
            }
        }

        return ans;
    }

    /**
     * Checks whether all piles can be eaten within the available hours.
     */
    private boolean canEatInTime(int[] piles, int eatingSpeed, int timeAvailable) {
        int currentTotalEatingTime = 0;

        for (int pile : piles) {
            // Integer division gives full hours; add one if bananas remain.
            currentTotalEatingTime += pile / eatingSpeed
                    + (pile % eatingSpeed == 0 ? 0 : 1);

            // More hours cannot become feasible by checking later piles.
            if (currentTotalEatingTime > timeAvailable) {
                break;
            }
        }

        return currentTotalEatingTime <= timeAvailable;
    }
}
