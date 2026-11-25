/**
 * LeetCode 1539: Kth Missing Positive Number
 * https://leetcode.com/problems/kth-missing-positive-number/
 *
 * Problem:
 * Given a strictly increasing array of positive integers, return the kth
 * positive integer that is missing from the array.
 *
 * Approach:
 * Binary-search for the first array index where the number of missing positive
 * integers up through arr[index] is at least k. At index i, that count is
 * arr[i] - (i + 1): there are arr[i] positive integers from 1 through arr[i],
 * and the array contains i + 1 of them up through that index.
 *
 * After the inclusive search, low is the number of array values before the
 * answer. The answer can be written either as low + k or high + 1 + k because
 * the loop ends with low == high + 1.
 *
 * Complexity:
 * Time: O(log n). Auxiliary Space: O(1).
 *
 * Common mistake:
 * The missing count is arr[i] - i - 1, not arr[i] - i + 1.
 */
public class KthMissingPositiveNumber {

    public int findKthPositive(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - mid - 1;

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // The loop ends with low == high + 1, so these forms are equivalent:
        return high + 1 + k;
        // return low + k;
    }
}
