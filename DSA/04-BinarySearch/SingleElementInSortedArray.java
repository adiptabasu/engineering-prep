/**
 * LeetCode 540: Single Element in a Sorted Array
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 *
 * Problem:
 * In a sorted array, every value appears twice except one value that appears
 * once. Return the single value in O(log n) time and O(1) extra space.
 *
 * Approach: Align mid to an even index so it is the expected first index of a
 * pair before the single element. If nums[mid] equals nums[mid + 1], that pair
 * is intact and the single element is to the right. Otherwise, it is at mid or
 * to the left.
 *
 * Key Insight:
 * Before the single element, pairs begin at even indices. After it, the first
 * value of each pair begins at an odd index. The pair alignment changes at the
 * single element.
 *
 * Why It Works:
 * Each comparison determines which side contains the alignment change, so
 * half of the remaining candidate range can be removed.
 *
 * Complexity:
 * Time: O(log n).
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Checking mid + 1 without first ensuring mid is aligned to an even index.
 * - Moving low by one instead of skipping the intact pair with mid + 2.
 * - Moving high to mid - 1 when mid may itself be the single value.
 */
public class SingleElementInSortedArray {

    /**
     * Returns the value that occurs once in the sorted array.
     *
     * @param nums sorted array where exactly one value occurs once and all
     *             other values occur twice
     * @return the single-occurrence value
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}
