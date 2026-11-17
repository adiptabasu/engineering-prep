/**
 * LeetCode 33: Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Problem:
 * Search for target in an ascending array that has been rotated at an
 * unknown pivot. Values are distinct. Return its index, or -1 if absent.
 *
 * Approach: At each midpoint, identify which half is sorted. Check whether
 * target falls within that half's value range; keep the possible half and
 * discard the other one.
 *
 * Key Insight:
 * With distinct values, at least one side of mid remains sorted even though
 * the entire rotated array is not sorted.
 *
 * Why It Works:
 * The endpoints of the sorted half describe its full value range. If target
 * lies in that range, continue there; otherwise it can only be in the other
 * half. Each step removes about half the remaining indices.
 *
 * Complexity:
 * Time: O(log n).
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Assuming the whole rotated array is sorted.
 * - Keeping a half without checking whether target is within its value range.
 * - Applying the distinct-value logic unchanged to the duplicate variant.
 */
public class SearchInRotatedSortedArray {

    /**
     * Returns target's index in the rotated array, or -1 if it is absent.
     *
     * @param nums rotated ascending array of distinct values
     * @param target value to find
     * @return target index or -1
     */
    public int search(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                break;
            }

            if (nums[low] <= nums[mid]) { // left half is sorted
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // right half is sorted
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return index;
    }
}
