/**
 * Reference: Rotation Count in a Rotated Sorted Array
 *
 * Assumptions:
 * The input is non-empty, was strictly increasing before rotation, and has
 * distinct values. Under these assumptions, the minimum has one unique index.
 *
 * Rotation count equals the index of the minimum. For example, rotating
 * [1, 2, 3, 4, 5] right by two positions gives [4, 5, 1, 2, 3]; the minimum is
 * at index 2, so the rotation count is 2.
 *
 * Complexity:
 * Time: O(log n).
 * Auxiliary Space: O(1).
 *
 * Note:
 * With duplicates, a minimum value can appear at multiple indices, so a
 * unique rotation count may not be recoverable from the values alone.
 */
public class RotationCountInRotatedSortedArray {

    /**
     * Returns the number of right rotations of a strictly increasing array.
     *
     * @param nums non-empty rotated ascending array with distinct values
     * @return index of the minimum value, equal to the right-rotation count
     */
    public int rotationCount(int[] nums) {
        int minIndex = 0;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[low] <= nums[mid]) {
                // The left segment is sorted, so nums[low] is its minimum.
                // Save it before discarding this segment and searching right.
                if (nums[low] < nums[minIndex]) {
                    minIndex = low;
                }
                low = mid + 1;
            } else {
                // The left segment crosses the rotation, so the minimum is
                // at or before mid. Save mid as a candidate and search left.
                if (nums[mid] < nums[minIndex]) {
                    minIndex = mid;
                }
                high = mid - 1;
            }
        }

        // For distinct values, minIndex is the unique minimum's index. That
        // index is the count of right rotations from the original order.
        return minIndex;
    }
}
