# Rotation Count in a Rotated Sorted Array

## 1. What does rotation count mean?

Start with a strictly increasing array such as:

```text
[1, 2, 3, 4, 5]
```

Rotate it to the right by two positions:

```text
[4, 5, 1, 2, 3]
```

The rotation count is `2`. In a rotated, strictly increasing array with distinct values, the smallest value marks where the original order starts again. Its index is the number of right rotations.

This reference method assumes the array is non-empty and has distinct values. With duplicates, the minimum can occur at multiple indices, so the original rotation count may not be uniquely determined.

## 2. Search for the minimum's index

Use a closed interval `[low, high]` and keep `minIndex` as the best candidate seen. Compare the left endpoint with the midpoint:

- If `nums[low] <= nums[mid]`, the left segment is sorted. Save its first index as a minimum candidate, then move `low` to `mid + 1` to search the right segment.
- Otherwise, the left segment crosses the rotation. Save `mid` as a candidate, then set `high = mid - 1` to search left.

The loop continues while `low <= high`. The candidate is an index, so compare `nums[low]` or `nums[mid]` with `nums[minIndex]` before replacing it. When the loop ends, `minIndex` holds the minimum's index and therefore the rotation count.

## 3. Worked example

For `[4, 5, 1, 2, 3]`:

| Step | `low..high` | `mid` | Comparison | Candidate / next range |
|---:|---|---:|---|---|
| 1 | `0..4` | `2` | `4 > 1`, save `2` and search left | candidate `2`, range `0..1` |
| 2 | `0..1` | `0` | `[4, 5]` is sorted, save `0`, search right | candidate `2` (value `1` is smaller), range `1..1` |
| 3 | `1..1` | `1` | one value remains in the active range | candidate stays `2`, range `2..1` |

The minimum is at index `2`, so the array was rotated right twice.

## 4. Java reference implementation

See [RotationCountInRotatedSortedArray.java](../RotationCountInRotatedSortedArray.java). It returns the minimum's index rather than the minimum value.

```java
public int rotationCount(int[] nums) {
    int minIndex = 0;
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[low] <= nums[mid]) {
            if (nums[low] < nums[minIndex]) {
                minIndex = low;
            }
            low = mid + 1;
        } else {
            if (nums[mid] < nums[minIndex]) {
                minIndex = mid;
            }
            high = mid - 1;
        }
    }

    return minIndex;
}
```

## 5. Complexity and distinction

- Time: **O(log n)**
- Auxiliary space: **O(1)**

LeetCode 153 asks for the minimum **value** in an array with distinct values. LeetCode 154 asks for the minimum **value** when duplicates are allowed. This reference helper returns the minimum's **index** as the rotation count and relies on distinct values for that index to be unique.
