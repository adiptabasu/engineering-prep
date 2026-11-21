/**
 * LeetCode 162: Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 *
 * Problem:
 * Return the index of any peak in an array where adjacent values differ. A
 * peak is strictly greater than its existing neighbors. The required time is
 * O(log n).
 *
 * Approach: Check whether either endpoint is already a peak. Then search the
 * interior. If the slope rises from mid to mid + 1, a peak exists to the
 * right; otherwise a peak exists to the left.
 *
 * Key Insight:
 * A rising slope cannot continue forever without reaching a peak: it either
 * turns downward at a peak or reaches the right endpoint, which is itself a
 * peak. The same reasoning applies in the other direction.
 *
 * Why It Works:
 * Each slope comparison keeps a half that is guaranteed to contain at least
 * one peak. Endpoint checks handle peaks that are outside the interior search
 * interval.
 *
 * Complexity:
 * Time: O(log n).
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Returning low when the right endpoint is a peak; its index is high.
 * - Reading mid - 1 or mid + 1 without keeping mid inside the array.
 * - Assuming the returned peak must be the global maximum.
 */
public class FindPeakElement {

    /**
     * Returns the index of any peak element.
     *
     * @param nums non-empty array with no equal adjacent values
     * @return an index whose value is greater than its existing neighbors
     */
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        if (high == 0) {
            return low;
        }

        if (nums[low] > nums[low + 1]) {
            return low;
        } else if (nums[high] > nums[high - 1]) {
            return high;
        }

        // The endpoints are not peaks. Search only interior indices so each
        // midpoint has both a left and right neighbor available.
        low++;
        high--;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] < nums[mid + 1]) {
                // The slope rises to the right, so a peak exists in that half.
                low = mid + 1;
            } else {
                // The slope falls to the right, so a peak exists to the left.
                high = mid - 1;
            }
        }

        // The problem guarantees a peak; reaching this line would mean that
        // guarantee or the search invariant was violated.
        return -1;
    }
}
