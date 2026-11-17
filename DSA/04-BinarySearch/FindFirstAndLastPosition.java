/**
 * LeetCode 34: Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Problem:
 * Given a sorted array that may contain duplicates, return the first and last
 * index of target. Return {-1, -1} if target does not appear.
 *
 * Approach: Find the lower bound (first value >= target) and the upper bound
 * (first value > target). If the lower-bound position contains target, the
 * matching range is from lowerBound to upperBound - 1.
 *
 * Key Insight:
 * Equal values occupy a contiguous range in a sorted array. Lower bound gives
 * its first index; upper bound gives the position immediately after its last.
 *
 * Why It Works:
 * Each helper finds the first position satisfying a monotone comparison. The
 * lower bound is checked before indexing to handle an absent target safely.
 *
 * Complexity:
 * Time: O(log n) for two binary searches.
 * Auxiliary Space: O(1).
 *
 * Common Mistakes:
 * - Returning as soon as any equal midpoint is found; it may not be an edge.
 * - Forgetting that upperBound is one past the final match.
 * - Indexing nums[lowerBound] without first checking that it is in range.
 */
public class FindFirstAndLastPosition {

    /**
     * Returns the first and last indices of target, or {-1, -1} if absent.
     *
     * @param nums sorted array that may contain duplicate values
     * @param target value whose range is requested
     * @return two-element array containing first and last matching indices
     */
    public int[] searchRange(int[] nums, int target) {
        int firstIndex = lowerBound(nums, target);

        // lowerBound can return nums.length, so check before indexing nums.
        if (firstIndex == nums.length || nums[firstIndex] != target) {
            return new int[] {-1, -1};
        }

        int afterLastIndex = upperBound(nums, target);
        return new int[] {firstIndex, afterLastIndex - 1};
    }

    /** Returns the first index whose value is greater than or equal to target. */
    private int lowerBound(int[] nums, int target) {
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

    /** Returns the first index whose value is strictly greater than target. */
    private int upperBound(int[] nums, int target) {
        int answer = nums.length;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }
}
