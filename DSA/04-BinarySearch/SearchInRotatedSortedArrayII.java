/**
 * LeetCode 81: Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Problem:
 * Search for target in a non-decreasing array rotated at an unknown pivot.
 * Duplicate values are allowed. Return true if target appears.
 *
 * Approach: Check the midpoint. If the low, middle, and high values are all
 * equal, shrink both ends because neither endpoint can reveal which half is
 * sorted. Otherwise identify a sorted half and keep it only if target falls
 * within its value range.
 *
 * Key Insight:
 * Duplicates can hide the rotation boundary. When nums[low], nums[mid], and
 * nums[high] are equal and mid is not target, the equal endpoints can be
 * discarded safely, but that step may remove only one item from each side.
 *
 * Why It Works:
 * Outside the ambiguous equal-endpoint case, one half is sorted and its value
 * range tells us whether target can be there. In the ambiguous case, equal
 * endpoints cannot be target because mid was already checked, so removing
 * them cannot remove a match.
 *
 * Complexity:
 * Average Time: O(log n) when the sorted half can be identified.
 * Worst-case Time: O(n) when duplicates keep both halves ambiguous.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Assuming every iteration can discard half the range.
 * - Shrinking equal endpoints before checking whether mid is the target.
 * - Applying the distinct-value complexity guarantee to duplicate-heavy data.
 */
public class SearchInRotatedSortedArrayII {

    /**
     * Returns true if target occurs in the rotated, non-decreasing array.
     *
     * @param nums rotated ascending array that may contain duplicates
     * @param target value to find
     * @return true if target is present; otherwise false
     */
    public boolean search(int[] nums, int target) {
        boolean found = false;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (found) {
                break;
            }

            if (nums[mid] == target) {
                found = true;
            } else if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Equal endpoints hide which half is sorted; discard both.
                low++;
                high--;
            } else if (nums[low] <= nums[mid]) { // left half is sorted
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

        return found;
    }
}
