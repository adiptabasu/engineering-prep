# Search in a Rotated Sorted Array

## 1. What does “rotated” mean?

A sorted array can be shifted at some pivot while keeping the order on both sides. For example, rotating `[0, 1, 2, 4, 5, 6, 7]` can produce:

```text
[4, 5, 6, 7, 0, 1, 2]
```

The whole array is no longer sorted from left to right, so ordinary binary search cannot directly compare the target with the midpoint and discard a side. However, when values are distinct, at least one side of the midpoint is still sorted.

## 2. Identify the sorted half

Use `low`, `mid`, and `high` as indices:

- If `nums[low] <= nums[mid]`, the left half `[low, mid]` is sorted.
- Otherwise the right half `[mid, high]` is sorted.

Once you identify a sorted half, its endpoint values tell you whether the target could be inside it. If it could, keep that half. Otherwise, keep the other half.

## 3. Worked example

Search for `0` in `[4, 5, 6, 7, 0, 1, 2]`:

| Step | Range | Midpoint | Decision |
|---:|---|---|---|
| 1 | `0..6` | index `3`, value `7` | Left side `4..7` is sorted; `0` is outside it, so search `4..6`. |
| 2 | `4..6` | index `5`, value `1` | Left side `0..1` is sorted; `0` is in it, so search `4..4`. |
| 3 | `4..4` | index `4`, value `0` | Found. |

At every step, sorted order on one side tells us whether to discard that side or the other one.

## 4. Implementation for distinct values

```java
public int searchRotated(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[low] <= nums[mid]) { // left half is sorted
            if (nums[low] <= target && target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        } else { // right half is sorted
            if (nums[mid] < target && target <= nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    return -1;
}
```

The range tests are inclusive at the outside endpoint and exclusive at `mid`, because `mid` was already checked. This matches the inclusive index interval `[low, high]`.

## 5. Why duplicates make it harder

With duplicates, `nums[low] == nums[mid] == nums[high]` does not tell us which side is sorted. For example, equal endpoint values can hide the rotation point. A common approach is to shrink both ends by one when all three values are equal:

```java
if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
    low++;
    high--;
    continue;
}
```

This is safe because the midpoint has already been checked and the equal endpoints have the same value. However, shrinking one item at a time can take `O(n)` time in the worst case. With distinct values, the search is `O(log n)`.

## 6. Complexity and assumptions

- **Distinct values:** `O(log n)` time and `O(1)` extra space.
- **Duplicates allowed:** `O(log n)` on many inputs, but `O(n)` worst-case time because ambiguity may force one-step shrinking.
- The code assumes the array was formed by rotating a sorted ascending array and searches for an exact target.

## 7. Common mistakes

- **Treating the entire rotated array as sorted.** Only one side around the midpoint is guaranteed sorted.
- **Choosing a side before checking the target.** If `nums[mid] == target`, return it immediately.
- **Incorrect range endpoints.** The checked midpoint is excluded; use `< nums[mid]` or `> nums[mid]` at its boundary.
- **Assuming duplicates have the same logarithmic guarantee.** Equal values can conceal which half is ordered.
- **Using this approach for a different rotation condition.** Confirm the problem's promise about order and duplicates.

## 8. When to recognize this pattern

Look for an array that was sorted and then rotated, often with a prompt asking for an index in `O(log n)`. Before coding, state why one half is sorted and the exact value interval that would contain the target.

## 9. Review prompts

1. Why is at least one half sorted when values are distinct?
2. How do the sorted half's endpoints tell us whether the target can be there?
3. Why can duplicates force linear time?
4. Which comparisons exclude the already-checked midpoint?

## Related problem

[LeetCode 33 — Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
