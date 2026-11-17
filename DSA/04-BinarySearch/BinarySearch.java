/**
 * LeetCode 704: Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Problem:
 * Given a sorted array of distinct integers and a target, return the target's
 * index, or -1 if it is not present.
 *
 * Approach: Search the inclusive index range [low, high], checking its middle
 * element. Because the array is sorted, each comparison lets us discard the
 * middle element and half of the remaining range.
 *
 * Key Insight:
 * Keep low and high as the first and last indices that may still contain the
 * target. If nums[mid] is too small, every index through mid is too small; if
 * nums[mid] is too large, every index from mid onward is too large.
 *
 * Why It Works:
 * Sorted order makes each comparison rule out half the remaining candidates.
 * The range shrinks until the target is found or low passes high, meaning no
 * candidate indices remain.
 *
 * Complexity:
 * Time: O(log n), because the candidate range is halved each iteration.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Using low < high with an inclusive range can skip checking the last index.
 * - Setting low = mid or high = mid can leave the range unchanged and loop
 *   forever; exclude the already-checked middle index with +/- 1.
 * - Calculating (low + high) / 2 can overflow for very large indices. The
 *   difference-based expression used here avoids that sum.
 */
public class BinarySearch {

    /**
     * Returns the index of target in the sorted array, or -1 if it is absent.
     *
     * @param nums sorted array of distinct integers
     * @param target value to find
     * @return target index, or -1 when not found
     */
    public int search(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        // Both endpoints are still candidates, so keep searching while low <= high.
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) {
                // The target can only be to the right of the checked midpoint.
                low = mid + 1;
            } else {
                // The target can only be to the left of the checked midpoint.
                high = mid - 1;
            }
        }

        return index;
    }
}
