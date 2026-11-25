/**
 * LeetCode 1011: Capacity To Ship Packages Within D Days
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * Problem:
 * Ship packages in their given order within the allowed number of days.
 * Return the smallest ship capacity that makes this possible.
 *
 * Main approach: Binary-search the capacity. For a candidate capacity, greedily
 * load each day's ship as much as possible without splitting or reordering a
 * package. Larger capacities never require more days, so feasibility changes
 * monotonically from false to true.
 *
 * Bounds in the main method:
 * - The heaviest package is a lower bound: every package must fit by itself.
 * - The sum of all package weights is a feasible upper bound: everything can
 *   be shipped in one day.
 *
 * Alternative bound strategy:
 * shipWithinDaysWithExpandingUpperBound starts at the heaviest package and
 * doubles the upper bound until it is feasible. This is useful to remember as
 * a general technique when a feasible upper bound is not immediately known.
 *
 * Complexity:
 * Let S be the sum of all weights. The main method takes O(n log S) time and
 * O(1) auxiliary space. Each capacity check scans the packages once.
 *
 * Common mistakes:
 * - Comparing the number of days required with the capacity instead of days.
 * - Splitting a package or changing the package order.
 * - Searching below the heaviest package or above an unproven upper bound.
 */
public class CapacityToShipPackagesWithinDDays {

    /** Finds the minimum capacity using direct, guaranteed bounds. */
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;

        // Compute both bounds in one pass: max package and total weight.
        for (int weight : weights) {
            low = Math.max(low, weight);
            high += weight;
        }

        return binarySearchCapacity(weights, days, low, high);
    }

    /**
     * Reference alternative: find a feasible upper bound by doubling.
     * The LeetCode constraints keep this capacity within the int range.
     */
    public int shipWithinDaysWithExpandingUpperBound(int[] weights, int days) {
        int low = 0;
        for (int weight : weights) {
            low = Math.max(low, weight);
        }

        int high = low;
        while (!possibleToShip(weights, days, high)) {
            high *= 2;
        }

        return binarySearchCapacity(weights, days, low, high);
    }

    private int binarySearchCapacity(int[] weights, int days, int low, int high) {
        int minimumCapacity = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (possibleToShip(weights, days, mid)) {
                minimumCapacity = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return minimumCapacity;
    }

    /**
     * Greedily counts the days needed for one candidate capacity. If adding a
     * package would exceed capacity, that package starts the next day's load.
     */
    private boolean possibleToShip(int[] weights, int days, int capacity) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                daysUsed++;
                if (daysUsed > days) {
                    return false;
                }
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }

        return true;
    }
}
