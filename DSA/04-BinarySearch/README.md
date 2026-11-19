# 04 — Binary Search

**Status: In progress — 6 problems recorded**

This section starts with binary search over a sorted array. The central idea is to use order to rule out half of the remaining candidate indices after each comparison.

If binary search is new to you, begin with the [beginner-friendly notes](notes/README.md), then try the problem from its prompt before opening the implementation.

## Problem List

| # | Problem | Main pattern to recall | Solution |
|---:|---|---|---|
| 1 | Binary Search | Inclusive candidate range; discard half using sorted order | [View](./BinarySearch.java) |
| 2 | Search Insert Position | Lower bound; save a qualifying candidate and continue left | [View](./SearchInsertPosition.java) |
| 3 | Find First and Last Position of Element in Sorted Array | Lower bound for first match; upper bound minus one for last | [View](./FindFirstAndLastPosition.java) |
| 4 | Search in Rotated Sorted Array | Identify the sorted half and check whether the target falls within it | [View](./SearchInRotatedSortedArray.java) |
| 5 | Search in Rotated Sorted Array II | Shrink equal endpoints when duplicates hide the sorted half | [View](./SearchInRotatedSortedArrayII.java) |
| 6 | Find Minimum in Rotated Sorted Array | Narrow the range around the rotation point, or track minimum candidates | [View](./FindMinimumInRotatedSortedArray.java) |

The reusable [lower-bound and upper-bound implementations](./BinarySearchBounds.java) are reference helpers, not additional LeetCode problems.

## Reasoning checklist

1. Is the input ordered in a way that lets a comparison rule out values?
2. What index range still contains possible answers?
3. Is the range inclusive or half-open? Make the loop condition match it.
4. After checking the midpoint, how do you exclude it from the next range?
5. What does an empty candidate range mean?

Use [`../LeetCode.md`](../LeetCode.md) for the central problem index and the [notes index](notes/README.md) for explanations.
