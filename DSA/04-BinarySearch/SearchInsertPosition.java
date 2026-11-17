/**
 * LeetCode 35: Search Insert Position
 * https://leetcode.com/problems/search-insert-position/
 *
 * Problem:
 * Given a sorted array of distinct integers and a target, return its index if
 * present. Otherwise, return the index where it should be inserted to keep the
 * array sorted.
 *
 * Approach: Find the lower bound: the first index whose value is greater than
 * or equal to target. If no such index exists, return nums.length.
 *
 * Key Insight:
 * When nums[mid] >= target, mid is a valid answer, but an earlier index may
 * also qualify. Save mid and continue searching to the left.
 *
 * Why It Works:
 * Sorted order means every index to the left of a value smaller than target
 * is also too small. For a value greater than or equal to target, the index
 * may be the first suitable position, so retain it and search earlier indices.
 *
 * Complexity:
 * Time: O(log n), because the candidate range is halved each iteration.
 * Auxiliary Space: O(1).
 *
 * Related Reference:
 * See BinarySearchBounds.java for lower-bound and upper-bound implementations,
 * including how to adapt the comparison for duplicate values.
 */
public class SearchInsertPosition {

    /**
     * Returns target's index, or its insertion position if it is absent.
     *
     * @param nums sorted array of distinct integers
     * @param target value to find or insert
     * @return target index or insertion position
     */
    public int searchInsert(int[] nums, int target) {
        int answer = nums.length;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }
}
