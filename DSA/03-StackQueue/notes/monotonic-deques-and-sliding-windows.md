# Monotonic deques and sliding windows

This note builds on two ideas: a **window** is a contiguous part of an array, and a **deque** lets us add or remove candidates at both ends. A monotonic deque keeps only the indices that could still be the maximum or minimum of the current window.

## What is a sliding window?

A window is an interval of array positions. For example, with `[1, 3, -1, -3, 5]` and window size 3, the windows are `[1,3,-1]`, `[3,-1,-3]`, and `[-1,-3,5]`.

The **left** and **right** indices mark the current window. In a fixed-size window of width `k`, its left boundary is determined by the right boundary: `left = right - k + 1`. In a variable-size window, we move `left` only as needed to restore a condition.

## Why keep indices in a deque?

An index tells us the value (`nums[index]`) and whether it has fallen outside the window. If we stored only values, equal values could make it unclear which occurrence has expired.

For a maximum deque:

- Keep indices in increasing order from front to back.
- Keep their values in decreasing order from front to back.
- The front index points to the maximum value.

When a new value arrives, remove smaller-or-equal candidates from the back. The new value is at least as large and occurs later, so any future window that still contains the older candidate will also contain the new one. The older candidate can never be the maximum again.

For a minimum deque, reverse the comparisons: values increase from front to back, and a new value removes larger-or-equal candidates from the back. The front is the minimum.

Each index is added once and removed at most once from each deque, so the full scan is O(n), even though an iteration may remove several candidates.

## Fixed-size window maximum

At right index `r`, a size-`k` window begins at `r - k + 1`. Any index smaller than that is expired. Equivalently, remove a front index when `index <= r - k`.

For `nums = [1, 3, -1]` and `k = 3`:

```text
read 1: deque [0:1]
read 3: remove 0:1 from the back; deque [1:3]
read -1: append it; deque [1:3, 2:-1]
window is full: front is index 1, so maximum is nums[1] = 3
```

The first full window ends when `r = k - 1`. On each later step, first remove expired front indices, then remove dominated back candidates, then add the new index. If a full window exists, write the value at the front index.

## Variable-size window with a range limit

To find the longest window whose values differ by at most `limit`, keep both a maximum deque and a minimum deque. The biggest difference between any two values in the window is its maximum minus its minimum.

The window is valid when:

```text
nums[maxDeque.front] - nums[minDeque.front] <= limit
```

For `[8, 2, 4, 7]` with `limit = 4`:

1. Start with `[8]`, which is valid.
2. Add `2`; `[8, 2]` is invalid because `8 - 2 = 6`. Move `left` past `8`; `[2]` is valid again.
3. Add `4`; `[2, 4]` is valid because `4 - 2 = 2`.
4. Add `7`; `[2, 4, 7]` is invalid because `7 - 2 = 5`. Move `left` past `2`; `[4, 7]` is valid because `7 - 4 = 3`.

When `left` moves past an index, remove it from a deque's front if it is there. Continue shrinking while the window is invalid; only then compare `right - left + 1` with the best length so far.

## Keep the two window rules separate

| Window type | How the left boundary changes |
|---|---|
| Fixed size `k` | Set by `right - k + 1`; remove indices before it |
| Variable size with a condition | Advance `left` while the current window violates the condition |

The fixed-size maximum problem has no `max - min > limit` condition. The variable-size problem does not have a fixed `k` boundary.

## Practice links

[Sliding Window Maximum](../SlidingWindowMaximum.java) · [Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](../LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java)
