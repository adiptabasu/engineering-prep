# Find the Minimum in a Rotated Sorted Array

## 1. The array's shape

Start with a strictly increasing array, then rotate it around a pivot. For example:

```text
Original: [0, 1, 2, 4, 5, 6, 7]
Rotated:  [4, 5, 6, 7, 0, 1, 2]
```

The minimum is at the rotation point, where the values drop from a large value back to a small one. The whole array is not sorted, but each side of that drop is sorted.

LeetCode 153 guarantees a non-empty array with distinct values. LeetCode 154 allows duplicates; the duplicate case needs an extra step because equal endpoint values can hide which side contains the rotation.

## 2. Track a candidate with an inclusive range

Use the inclusive interval `[low, high]` and continue while `low <= high`. Compare `nums[mid]` with the right endpoint:

- If `nums[mid] > nums[high]`, the drop must be to the right of `mid`; set `low = mid + 1`.
- Otherwise, `mid` may be the minimum. Save it if its value is smaller than the best candidate, then search left with `high = mid - 1`.

The saved candidate remains available even after it leaves the active interval. When the loop ends, return the value at the saved minimum index.

```java
public int findMinWithCandidate(int[] nums) {
    int minIndex = 0;
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else {
            if (nums[mid] < nums[minIndex]) {
                minIndex = mid;
            }
            high = mid - 1;
        }
    }

    return nums[minIndex];
}
```

This inclusive-loop version relies on distinct values and is one way to solve [LeetCode 153](../FindMinimumInRotatedSortedArray.java). The saved implementation uses the alternate inclusive candidate-tracking approach in the next section.

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

Find the minimum in `[4, 5, 6, 7, 0, 1, 2]` using the midpoint/right-endpoint approach. The candidate starts at index `0` (value `4`):

| Step | Range | Midpoint and right endpoint | Decision |
|---:|---|---|---|
| 1 | `0..6` | `mid=3` (`7`), `high=6` (`2`) | `7 > 2`, minimum is right; set `low=4`. Candidate remains index `0`. |
| 2 | `4..6` | `mid=5` (`1`), `high=6` (`2`) | Save index `5`; set `high=4`. |
| 3 | `4..4` | `mid=4` (`0`), `high=4` (`0`) | Save index `4`; set `high=3`. |

The loop ends with index `4` saved as the minimum.

## 5. Why a sorted, unrotated range is handled

If the array is not rotated, its values increase from beginning to end. In the midpoint/right-endpoint method, `nums[mid] <= nums[high]`, so candidate indices move left until index `0` is saved as the minimum.

The answer-tracking method also works: the left segment is sorted each time, so it records the first value and eventually returns that minimum.

## 6. Complexity and comparison

Both approaches take **O(log n)** time and **O(1)** extra space. The midpoint/right-endpoint approach tracks a candidate index, while the submitted left/mid approach tracks the best minimum value seen. The two approaches have the same asymptotic complexity; one is not a faster growth rate than the other.

## 7. When duplicates are allowed

If `nums[low] == nums[mid] == nums[high]`, these three equal values do not reveal which half contains the rotation. Before shrinking the range, compare both endpoints with the best minimum candidate. Then increment `low` and decrement `high`. Saving both first matters because either endpoint could hold a minimum that would be lost when removed.

When the three values are not all equal, the sorted-left-segment reasoning can still guide the search. See the commented [LeetCode 154 implementation](../FindMinimumInRotatedSortedArrayII.java) for candidate tracking. Duplicates can force the algorithm to shrink only a little at a time, so the worst-case time becomes **O(n)**, although many inputs still take **O(log n)**.

This solves for the minimum **value**. For the distinct-value rotation-count reference, see [Rotation Count](rotation-count.md).

## 8. Common mistakes

- **Moving `high` to `mid - 1` without saving `mid` first.** `mid` may itself be the minimum, so keep its value as a candidate before removing it from the interval.
- **Assuming the minimum is always at index zero or at the midpoint.** Use the ordered sides to narrow the candidate range.
- **Ignoring duplicate values.** Equality may hide the rotation point; compare and save both endpoints before shrinking in the duplicate variant.
- **Assuming one implementation is asymptotically faster.** Both standard approaches are logarithmic for distinct values.

## 9. Review prompts

1. Why does `nums[mid] > nums[high]` put the minimum to the right of `mid`?
2. Why must the `mid` candidate be saved before setting `high = mid - 1`?
3. Why does the search continue while `low <= high`?
4. In the `currentMin` version, why must we preserve candidates from discarded segments?
5. When `nums[low] == nums[mid] == nums[high]`, why must both endpoints be considered before shrinking?
6. Why can duplicates make the worst-case running time linear?

## Related problems

- [LeetCode 153 — Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [LeetCode 154 — Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)
