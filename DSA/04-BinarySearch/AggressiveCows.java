import java.util.Arrays;

/**
 * GeeksforGeeks: Aggressive Cows
 * https://www.geeksforgeeks.org/problems/aggressive-cows/1
 *
 * Problem:
 * Place k cows in distinct stall positions so that the minimum distance
 * between any two placed cows is as large as possible.
 *
 * Approach:
 * Sort the stall positions, then binary-search the minimum allowed distance.
 * For a candidate distance, place the first cow at the first stall and each
 * following cow at the earliest stall far enough from the previous cow.
 *
 * Key insight:
 * If distance d is feasible, every smaller distance is feasible too. The
 * feasible distances form a prefix, so save a feasible midpoint and search
 * right to find the largest one.
 *
 * Bounds:
 * - 1 is feasible because stall positions are distinct integers and k does
 *   not exceed the number of stalls.
 * - The largest possible distance is the span from the first to last stall.
 *
 * Complexity:
 * Sorting takes O(n log n). The search takes O(n log D), where D is the stall
 * span. The auxiliary space used by the placement check is O(1).
 */
public class AggressiveCows {

    public int aggressiveCows(int[] arr, int k) {
        Arrays.sort(arr);

        int low = 1;
        int high = arr[arr.length - 1] - arr[0];
        int maxVal = low;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canFitTheCowWithSize(arr, k, mid)) {
                maxVal = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxVal;
    }

    /** Greedily places cows in the earliest stalls that satisfy the gap. */
    private boolean canFitTheCowWithSize(int[] arr, int k, int distance) {
        int cowsPlaced = 1;
        int lastCowPositionIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[lastCowPositionIndex] >= distance) {
                cowsPlaced++;
                lastCowPositionIndex = i;

                if (cowsPlaced >= k) {
                    return true;
                }
            }
        }

        return false;
    }
}
