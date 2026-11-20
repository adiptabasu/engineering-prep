/**
 * LeetCode 154: Find Minimum in Rotated Sorted Array II
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Problem:
 * Find the minimum in a non-empty rotated ascending array. Unlike LeetCode
 * 153, this array can contain duplicate values.
 *
 * Approach: Track the index of the smallest candidate seen so far. When the
 * left, middle, and right values are equal, the comparison cannot tell which
 * side contains the rotation, so record both endpoints and shrink both ends.
 * Otherwise, use the sorted-left-segment test to continue narrowing the range.
 *
 * Key Insight:
 * Equal endpoint values can hide the rotation direction. They may be removed
 * only after their values are considered as minimum candidates. Keeping the
 * best candidate outside the active range lets it survive after shrinking.
 *
 * Why It Works:
 * Each branch either discards a segment that cannot improve the current
 * minimum or removes equal endpoints after recording them. The active range
 * therefore continues to contain any not-yet-recorded smaller value.
 *
 * Complexity:
 * Time: O(log n) in typical cases, O(n) worst case when duplicates prevent
 *       choosing a side.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Shrinking equal endpoints without first comparing both to the best value.
 * - Claiming O(log n) worst-case time despite the duplicate ambiguity.
 * - Confusing the returned minimum value with its index / rotation count.
 */
public class FindMinimumInRotatedSortedArrayII {

    /**
     * Returns the minimum value in a non-empty rotated ascending array that
     * may contain duplicates.
     *
     * @param nums non-empty rotated ascending array
     * @return the minimum value
     */
    public int findMin(int[] nums) {
        int minIndex = 0;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Equal values at both ends hide which side contains the
                // rotation. Either endpoint may be the minimum, so save both
                // candidates before removing them from the active range.
                if (nums[low] < nums[minIndex]) {
                    minIndex = low;
                }
                if (nums[high] < nums[minIndex]) {
                    minIndex = high;
                }

                low++;
                high--;
                continue;
            }

            if (nums[low] <= nums[mid]) { // the left segment is sorted
                // The first value of a sorted segment is its minimum. Keep it
                // as a candidate before moving beyond this segment.
                if (nums[low] < nums[minIndex]) {
                    minIndex = low;
                }
                low = mid + 1;
            } else {
                // Here the left segment crosses the rotation. The minimum is
                // in its candidate range, so record mid and continue left.
                if (nums[mid] < nums[minIndex]) {
                    minIndex = mid;
                }
                high = mid - 1;
            }
        }

        return nums[minIndex];
    }
}
