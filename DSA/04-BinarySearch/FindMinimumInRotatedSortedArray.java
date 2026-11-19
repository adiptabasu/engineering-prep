/**
 * LeetCode 153: Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Problem:
 * Find the minimum value in a non-empty ascending array that has been
 * rotated at an unknown pivot. Values are distinct.
 *
 * Approach: Track the smallest candidate value seen so far. If the left
 * segment is sorted, its first value is its minimum and the search continues
 * right. Otherwise, the rotation point is in the left segment; record mid
 * and continue left.
 *
 * Key Insight:
 * In a rotated strictly increasing array, a segment that crosses the pivot
 * has a drop; a segment that does not cross the pivot is sorted.
 *
 * Why It Works:
 * Each iteration records a value that may be the global minimum, then removes
 * a portion of the candidate range that cannot contain a smaller value than
 * the recorded candidate. The minimum is retained in currentMin even after
 * its index is removed from the active range.
 *
 * Complexity:
 * Time: O(log n).
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Applying the distinct-value reasoning to the duplicate variant.
 * - Forgetting that this implementation initializes from nums[0] and assumes
 *   the problem's non-empty-array guarantee.
 */
public class FindMinimumInRotatedSortedArray {

    /**
     * Returns the minimum value in the non-empty rotated array.
     *
     * @param nums non-empty rotated ascending array with distinct values
     * @return minimum value
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int currentMin = nums[low];

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[low] <= nums[mid]) { // left segment is sorted
                currentMin = Math.min(currentMin, nums[low]);
                low = mid + 1;
            } else {
                currentMin = Math.min(currentMin, nums[mid]);
                high = mid - 1;
            }
        }

        return currentMin;
    }
}
