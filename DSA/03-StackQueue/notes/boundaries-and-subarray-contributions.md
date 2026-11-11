# Boundaries and subarray contributions

These notes build on arrays, contiguous subarrays, and the monotonic-stack boundary pattern. The goal is to count many subarrays without generating each one individually.

## What is a subarray?

A **subarray** is a contiguous slice of an array. In `[3, 1, 2]`, `[1, 2]` is a subarray because its values are next to each other; `[3, 2]` is not, because it skips the middle value.

There are only `n * (n + 1) / 2` non-empty subarrays in an array of length `n`, but checking each subarray can still be too slow when the array is large. A contribution method groups subarrays by which index supplies their minimum or maximum.

## How boundaries give a width

Pick an index `i`. Suppose `L` is the nearest boundary index to its left, and `R` is the nearest boundary index to its right. If `values[i]` is the minimum for subarrays in this span, a subarray can:

- start at any index from `L + 1` through `i` — `i - L` choices;
- end at any index from `i` through `R - 1` — `R - i` choices.

The number of subarrays assigned to `i` is therefore:

```text
(i - L) * (R - i)
```

If no boundary exists, use `L = -1` or `R = n`, where `n` is the array length. These are outside-array sentinels that make the distance formula work at the edges.

## Worked example: sum of subarray minimums

For `[3, 1, 2]`, index 1 has value `1`. It has no smaller-or-equal value to its left, so `L = -1`; it has no strictly smaller value to its right, so `R = 3`.

```text
left choices  = 1 - (-1) = 2   (start at 0 or 1)
right choices = 3 - 1    = 2   (end at 1 or 2)
subarrays where this 1 is the chosen minimum = 2 * 2 = 4
```

Those four subarrays are `[3,1]`, `[1]`, `[3,1,2]`, and `[1,2]`. Their minimum is 1. Each index contributes its value times its assigned subarray count; adding the contributions gives the sum of all subarray minimums.

## Equal values: assign ties once

Consider `[2, 2]`. It has three non-empty subarrays: `[2]` at index 0, `[2]` at index 1, and `[2,2]`. The two equal values both could appear to be the minimum of `[2,2]`, so the boundary rules must assign that subarray to only one index.

The saved helper convention for minimum contributions is:

- On the left, find the previous **smaller-or-equal** value (`<=` the current value; pop only values `>` it).
- On the right, find the next **strictly smaller** value (`<` the current value; pop values `>=` it).

This gives the left equal value the wider right span and makes the right equal value's left boundary stop at the first one. The subarray `[2,2]` is counted once. For maximum contributions, use the mirrored rule: previous greater-or-equal and next strictly greater.

## Minimum and maximum contributions

For each index, count how many subarrays use it as their minimum and multiply by its value. Summing across the indices gives the sum of subarray minimums. Do the same for maximums. A subarray's range is `maximum - minimum`, so:

```text
sum of subarray ranges = sum of maximum contributions - sum of minimum contributions
```

In Java, widen a factor to `long` before multiplying. Otherwise Java may overflow during `int * int * int` before storing the result in a `long`.

## Histogram rectangles

In a histogram, each bar has a height. If a bar of height `h` is the shortest bar in a rectangle, the nearest lower bars on either side stop that rectangle from extending farther. If their indices are `L` and `R`, the width is `R - L - 1`, and the area is `h * width`.

A monotonic stack finds these nearest lower boundaries for all bars in linear time. Equal heights need consistent handling so the same maximal rectangle is not lost.

## Maximum min-product and prefix sums

A min-product is `minimum value * sum of values in the same subarray`. Fix index `i` as the minimum. Its smaller boundaries give the widest span where it can remain a minimum. Because the problem's values are positive, adding more values within that span increases the sum without changing the minimum. So the widest span is the best candidate for that index.

To get a span sum quickly, define an **exclusive prefix sum**: `prefix[k]` is the sum of the first `k` values, so `prefix[0] = 0`. With boundaries `L` and `R`, the valid span is `[L + 1, R - 1]` and its sum is:

```text
prefix[R] - prefix[L + 1]
```

Compare full products first. Apply the problem's modulo only to the final maximum, not to each candidate before comparing.

## Practice links

[Sum of Subarray Minimums](../SumOfSubarrayMinimums.java) · [Boundary-based alternate](../SumOfSubarrayMinimumsByBoundaries.java) · [Sum of Subarray Ranges](../SumOfSubarrayRanges.java) · [Maximum Subarray Min-Product](../MaximumSubarrayMinProduct.java) · [Largest Rectangle in Histogram](../LargestRectangleInHistogram.java)
