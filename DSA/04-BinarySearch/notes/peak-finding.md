# Peak Finding with Binary Search

## 1. What counts as a peak?

An index `i` is a peak if its value is greater than its adjacent values. At an endpoint, only the one neighbor that exists needs to be smaller. A peak is a local maximum; it does not have to be the largest value in the whole array.

For example, in `[1, 2, 3, 1]`, index `2` with value `3` is a peak. The array is not globally sorted, but its local slope tells us which direction contains a peak.

## 2. Use the slope around the midpoint

Compare `nums[mid]` with `nums[mid + 1]`:

- If `nums[mid] < nums[mid + 1]`, the slope rises to the right. There must be a peak somewhere to the right, so discard `mid` and the left side.
- Otherwise, under the usual guarantee that adjacent values differ, `nums[mid] > nums[mid + 1]`. The slope falls, so a peak is at `mid` or to its left; keep `mid` as a candidate by moving `high` to `mid`.

The search maintains a closed interval `[low, high]` that contains at least one peak. The loop stops when only one candidate index remains.

## 3. Implementation for the usual strict-neighbor problem

```java
public int findPeakElement(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] < nums[mid + 1]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }

    return low;
}
```

The condition `low < high` guarantees `mid < high`, so `mid + 1` stays within the array. When `low == high`, the remaining index is a peak.

## 4. Why a peak exists in the chosen direction

If the slope rises at `mid`, keep moving right while values rise. Either the sequence eventually drops, making the previous value a peak, or it reaches the endpoint while rising, making the final element a peak under the endpoint definition. The falling case is symmetric: a peak occurs at or before `mid`.

The algorithm does not need to find the globally highest value. It only needs to maintain a region guaranteed to contain some local peak.

## 5. Complexity

Each comparison discards about half of the current interval, so time is **O(log n)**. The method is iterative and uses **O(1)** extra space.

## 6. Assumptions and variants

The common LeetCode version guarantees `nums[i] != nums[i + 1]` for adjacent indices. That strictness makes the two-slope reasoning straightforward. If equal adjacent values or plateaus are allowed, this exact implementation may need different handling.

A mountain array is a more specific shape: values strictly increase up to one peak, then strictly decrease. This peak search finds the summit index; searching for a target on either side then uses ordinary ascending or descending binary search.

## 7. Common mistakes

- **Using exact-search updates.** `high = mid` is intentional here: `mid` may itself be a peak.
- **Using `low <= high`.** This implementation reads `nums[mid + 1]`; with `low == high`, that can go out of bounds.
- **Returning the global maximum by scanning.** The binary-search reasoning only promises a local peak, which is enough for this problem.
- **Ignoring input guarantees.** Plateaus can invalidate the strict-slope proof.

## 8. Review prompts

1. Why is `mid + 1` always valid when `low < high`?
2. Why does a rising slope let us discard the left part?
3. Why is `high = mid` used when the slope falls?
4. Why can the returned peak differ from the global maximum in a multi-peak array?

## Related problems

- [LeetCode 162 — Find Peak Element](https://leetcode.com/problems/find-peak-element/)
- [LeetCode 852 — Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/)
