/**
 * Reusable binary-search boundary helpers for a sorted integer array.
 *
 * Both methods return an insertion boundary in the range 0..nums.length.
 * They work with duplicate values and use an inclusive search range.
 */
public class BinarySearchBounds {

    /**
     * Finds the lower bound: the first index i for which nums[i] >= target.
     *
     * If every value is smaller than target, returns nums.length. If target
     * occurs more than once, returns the index of its first occurrence.
     *
     * @param nums sorted array
     * @param target boundary value
     * @return first index with a value >= target, or nums.length
     */
    public int lowerBound(int[] nums, int target) {
        int answer = nums.length;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                // This index qualifies; keep it and look for an earlier one.
                answer = mid;
                high = mid - 1;
            } else {
                // nums[mid] is too small, so all earlier values are too small.
                low = mid + 1;
            }
        }

        return answer;
    }

    /**
     * Finds the upper bound: the first index i for which nums[i] > target.
     *
     * Returns the position after the last occurrence when target is present.
     * If no value is greater than target, returns nums.length.
     *
     * @param nums sorted array
     * @param target boundary value
     * @return first index with a value > target, or nums.length
     */
    public int upperBound(int[] nums, int target) {
        int answer = nums.length;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                // Strictly greater qualifies; keep it and look farther left.
                answer = mid;
                high = mid - 1;
            } else {
                // Equal values are not past the target, so continue to the right.
                low = mid + 1;
            }
        }

        return answer;
    }
}
