# 04 — Binary Search

**Status: In progress — 13 problems recorded**

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
| 7 | Single Element in a Sorted Array | Use the pair-index alignment change around the singleton | [View](./SingleElementInSortedArray.java) |
| 8 | Find Minimum in Rotated Sorted Array II | Track endpoint candidates when duplicates hide the rotation | [View](./FindMinimumInRotatedSortedArrayII.java) |
| 9 | Find Peak Element | Check endpoints, then follow the rising or falling slope | [View](./FindPeakElement.java) |
| 10 | Sqrt(x) | Search for the largest integer whose square is at most `x` | [View](./SqrtX.java) |
| 11 | Koko Eating Bananas | Find the first feasible speed using a monotone hours check | [View](./KokoEatingBananas.java) |
| 12 | Minimum Number of Days to Make m Bouquets | Find the first feasible day while counting adjacent bloomed runs | [View](./MinimumNumberOfDaysToMakeMBouquets.java) |
| 13 | Find the Smallest Divisor Given a Threshold | Find the first divisor whose rounded-up sum is within threshold | [View](./SmallestDivisorGivenAThreshold.java) |

The reusable [lower-bound and upper-bound implementations](./BinarySearchBounds.java) are reference helpers, not additional LeetCode problems.

For the related rotation-count reference implementation, see [RotationCountInRotatedSortedArray.java](./RotationCountInRotatedSortedArray.java) and the [rotation-count note](./notes/rotation-count.md). It assumes distinct values and is not a separate LeetCode problem entry.

## Reasoning checklist

1. Is the input ordered in a way that lets a comparison rule out values?
2. What index range still contains possible answers?
3. Is the range inclusive or half-open? Make the loop condition match it.
4. After checking the midpoint, how do you exclude it from the next range?
5. What does an empty candidate range mean?

Use [`../LeetCode.md`](../LeetCode.md) for the central problem index and the [notes index](notes/README.md) for explanations.
