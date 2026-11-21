# Peak Finding with Binary Search

## 1. What counts as a peak?

An index `i` is a peak if its value is greater than its adjacent values. At an endpoint, only the one neighbor that exists needs to be smaller. A peak is a local maximum; it does not have to be the largest value in the whole array.

For example, in `[1, 2, 3, 1]`, index `2` with value `3` is a peak. In `[1, 2, 1, 3, 5, 6, 4]`, either index `1` or index `5` is a valid answer.

LeetCode 162 guarantees that adjacent values differ. That condition is important: when the midpoint is not a peak and the slope falls to its right, we can conclude its left neighbor is strictly greater.

## 2. Check the endpoints first

The endpoints have only one neighbor, so check them directly:

- If the array has one element, index `0` is a peak.
- If `nums[0] > nums[1]`, index `0` is a peak.
- If `nums[n - 1] > nums[n - 2]`, index `n - 1` is a peak.

If neither endpoint is a peak, the answer must be in the interior. Set `low = 1` and `high = n - 2`. This lets the main loop use `while (low <= high)` while safely accessing both `nums[mid - 1]` and `nums[mid + 1]`.

## 3. Follow the slope

At each interior midpoint:

1. If `nums[mid]` is greater than both neighbors, return `mid`.
2. If `nums[mid] < nums[mid + 1]`, values rise to the right. Keep the right half by setting `low = mid + 1`.
3. Otherwise, values fall to the right. Since `mid` was not a peak and adjacent values are unequal, `nums[mid - 1] > nums[mid]`. Keep the left half by setting `high = mid - 1`.

This is binary search over a slope rather than over a sorted array. Each comparison keeps a half that is guaranteed to contain at least one peak.

## 4. Inclusive-loop implementation

```java
public int findPeakElement(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    if (high == 0) {
        return low;
    }

    if (nums[low] > nums[low + 1]) {
        return low;
    } else if (nums[high] > nums[high - 1]) {
        return high;
    }

    // Neither endpoint is a peak. The remaining candidates are interior.
    low++;
    high--;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        }

        if (nums[mid] < nums[mid + 1]) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    // The problem guarantees a peak, so this fallback is unreachable.
    return -1;
}
```

The saved, fully commented implementation is [FindPeakElement.java](../FindPeakElement.java).

## 5. Why the neighbor accesses are safe

After the endpoint checks, `low` is at least `1` and `high` is at most `nums.length - 2`. Every midpoint in the loop therefore has a valid left and right neighbor. The loop may include `low == high`: that single candidate is still an interior index, so both neighbor accesses remain valid.

The single-element case returns before endpoint neighbor checks. For a two-element array, one endpoint must be greater than the other, so one of the endpoint checks returns its peak before the interior interval is created.

## 6. Why a peak exists in the retained half

If values rise from `mid` to `mid + 1`, continue moving right along the rising direction. The values must eventually turn downward, creating a peak, or reach the right endpoint while rising. The endpoint check already ruled out that endpoint as the peak, so a peak must occur in the interior before it.

If values fall from `mid` to `mid + 1` and `mid` itself was not a peak, then the left neighbor must be greater than `nums[mid]`. Moving left follows a rising direction in reverse; it must eventually reach a peak or the already-checked left endpoint. Thus the left half still contains a peak.

The search returns any peak. It does not need to find the global maximum, and different valid paths can return different peaks when the array has several local maxima.

## 7. Complexity

Each iteration discards about half of the interior candidate range, so the time complexity is **O(log n)**. The search stores only a few indices, so auxiliary space is **O(1)**.

## 8. Common mistakes

- **Returning `low` when the right endpoint is a peak.** Its index is `high`.
- **Starting the interior search at index `0` or ending at `n - 1`.** Then `mid - 1` or `mid + 1` could be out of bounds.
- **Using `low <= high` without first restricting the interval to interior indices.** The inclusive condition is safe here because endpoint peaks are checked first and the loop range excludes both endpoints.
- **Keeping the wrong half.** Rising to the right means search right; falling to the right means search left.
- **Assuming the returned peak must be the global maximum.** The problem accepts any local peak.
- **Ignoring equal adjacent values.** Plateaus invalidate this strict-slope reasoning and need different handling.

## 9. Review prompts

1. Why does checking the endpoints let the search start at `low = 1` and end at `high = n - 2`?
2. When values rise from `mid` to `mid + 1`, why must a peak exist to the right?
3. If values fall to the right but `mid` is not a peak, what must be true about `nums[mid - 1]`?
4. Why is `mid + 1` safe even when the loop condition is `low <= high`?
5. Why can the returned peak differ from the global maximum?

## Related problems

- [LeetCode 162 — Find Peak Element](https://leetcode.com/problems/find-peak-element/)
- [LeetCode 852 — Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/)
