import java.util.Arrays;

/**
 * LeetCode 1482: Minimum Number of Days to Make m Bouquets
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * Problem:
 * Return the minimum day on which at least m bouquets can be made, with each
 * bouquet using k adjacent flowers. Return -1 if there are not enough flowers.
 *
 * Approach: Binary-search the day between the earliest and latest bloom days.
 * For a candidate day, count bouquets from consecutive flowers that have
 * bloomed by that day.
 *
 * Key Insight:
 * If m bouquets can be made on a day, they can also be made on every later
 * day. Feasibility changes monotonically from false to true.
 *
 * Why It Works:
 * A feasible midpoint may be the minimum day, so save it and search earlier.
 * If it is infeasible, search later. Within a run of adjacent bloomed flowers,
 * each group of k flowers forms one bouquet; an unbloomed flower ends the run.
 *
 * Complexity:
 * Time: O(n log R), where R = max(bloomDay) - min(bloomDay) + 1.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Multiplying m * k as an int; the product can overflow.
 * - Combining flowers across an unbloomed gap.
 * - Moving the day range right when a candidate day is already feasible.
 */
public class MinimumNumberOfDaysToMakeMBouquets {

    /**
     * Returns the minimum day needed to make m bouquets of k adjacent flowers.
     *
     * @param bloomDay bloom day for each flower
     * @param m number of bouquets required
     * @param k adjacent flowers required per bouquet
     * @return minimum feasible day, or -1 if there are too few flowers
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int highDays = Arrays.stream(bloomDay).max().getAsInt();
        int lowDays = Arrays.stream(bloomDay).min().getAsInt();
        int daysNeeded = highDays;
        long flowersNeeded = (long) m * k;

        if (flowersNeeded > bloomDay.length) {
            return -1;
        } else {
            while (lowDays <= highDays) {
                int midDay = lowDays + (highDays - lowDays) / 2;

                if (ableToCreateTheBouquets(bloomDay, m, k, midDay)) {
                    daysNeeded = midDay;
                    highDays = midDay - 1;
                } else {
                    lowDays = midDay + 1;
                }
            }
        }

        return daysNeeded;
    }

    /**
     * Counts bouquets that can be formed from contiguous bloomed flowers.
     */
    private boolean ableToCreateTheBouquets(
            int[] bloomDay, int m, int k, int currentDay) {
        int count = 0;
        int bouquetNumber = 0;

        for (int bloom : bloomDay) {
            if (bloom <= currentDay) {
                count++;
            } else {
                bouquetNumber += count / k;
                count = 0;
                if (bouquetNumber >= m) {
                    return true;
                }
            }
        }

        if (count > 0) {
            bouquetNumber += count / k;
        }
        return bouquetNumber >= m;
    }
}
