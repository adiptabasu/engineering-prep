# Find the Minimum in a Rotated Sorted Array

## 1. The array's shape

Start with a strictly increasing array, then rotate it around a pivot. For example:

```text
Original: [0, 1, 2, 4, 5, 6, 7]
Rotated:  [4, 5, 6, 7, 0, 1, 2]
```

The minimum is at the rotation point, where the values drop from a large value back to a small one. The whole array is not sorted, but each side of that drop is sorted.

LeetCode 153 guarantees a non-empty array with distinct values. Duplicates change the reasoning and are not covered by these implementations.

## 2. Candidate-range approach

Maintain `[low, high]` as the interval that could still contain the rotation point. Compare `nums[mid]` with the last value `nums[high]`:

- If `nums[mid] > nums[high]`, the drop must be to the right of `mid`; set `low = mid + 1`.
- Otherwise, the minimum is at `mid` or to its left; keep `mid` by setting `high = mid`.

When `low == high`, that index is the minimum. This approach does not need a separate answer variable.

```java
public int findMinByRange(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }

    return nums[low];
}
```

This version illustrates the compact range-shrinking approach discussed alongside [LeetCode 153](../FindMinimumInRotatedSortedArray.java). It relies on distinct values.

## 3. Track the best candidate seen

Another valid approach records the smallest useful value as it searches. In the submitted implementation, compare the start and midpoint:

- If `nums[low] <= nums[mid]`, the segment from `low` through `mid` is sorted. Its minimum is `nums[low]`; record it and continue right, where the rotation point might be.
- Otherwise, that left segment crosses the drop. The minimum lies at or before `mid`; record `nums[mid]` as a candidate and continue left.

```java
int currentMin = nums[0];
int low = 0;
int high = nums.length - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[low] <= nums[mid]) {
        currentMin = Math.min(currentMin, nums[low]);
        low = mid + 1;
    } else {
        currentMin = Math.min(currentMin, nums[mid]);
        high = mid - 1;
    }
}

return currentMin;
```

`currentMin` remembers values from portions already removed from the active interval. The complete saved implementation is [FindMinimumInRotatedSortedArray.java](../FindMinimumInRotatedSortedArray.java).

## 4. A worked trace

Find the minimum in `[4, 5, 6, 7, 0, 1, 2]` using the candidate-range approach:

| Step | Range | Midpoint and right endpoint | Decision |
|---:|---|---|---|
| 1 | `0..6` | `mid=3` (`7`), `high=6` (`2`) | `7 > 2`, so the drop is right of mid; set `low=4`. |
| 2 | `4..6` | `mid=5` (`1`), `high=6` (`2`) | `1 <= 2`, so keep mid and search left; set `high=5`. |
| 3 | `4..5` | `mid=4` (`0`), `high=5` (`1`) | `0 <= 1`, so set `high=4`. |

Now `low == high == 4`, and `nums[4]` is the minimum.

## 5. Why a sorted, unrotated range is handled

If the array is not rotated, its values increase from beginning to end. In the candidate-range method, `nums[mid] <= nums[high]`, so `high` repeatedly moves left until the interval contains only index `0`.

The answer-tracking method also works: the left segment is sorted each time, so it records the first value and eventually returns that minimum.

## 6. Complexity and comparison

Both approaches take **O(log n)** time and **O(1)** extra space. The candidate-range approach is shorter because the active range itself always contains the answer. The tracked-candidate approach explicitly remembers a minimum from the range it discards. The two approaches have the same asymptotic complexity; one is not a faster growth rate than the other.

## 7. Common mistakes

- **Using `low <= high` with the range-only method and returning `nums[low]` afterward.** The range method is written for `low < high`, preserving at least one candidate and stopping when the bounds meet.
- **Moving `high` to `mid - 1` in the range-only method.** `mid` may itself be the minimum, so keep it with `high = mid`.
- **Assuming the minimum is always at index zero or at the midpoint.** Use the ordered sides to narrow the candidate range.
- **Ignoring duplicate values.** Equality may hide the rotation point; use the separate duplicate variant when the constraints allow duplicates.
- **Assuming one implementation is asymptotically faster.** Both standard approaches are logarithmic for distinct values.

## 8. Review prompts

1. Why does `nums[mid] > nums[high]` put the minimum to the right of `mid`?
2. Why does the other case keep `mid` rather than discard it?
3. What does `low == high` mean?
4. In the `currentMin` version, why must we preserve candidates from discarded segments?
5. What changes when duplicates are allowed?

## Related problems

- [LeetCode 153 — Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [LeetCode 154 — Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)
