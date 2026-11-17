# 04 — Binary Search

**Status: In progress — 1 problem recorded**

This section starts with binary search over a sorted array. The central idea is to use order to rule out half of the remaining candidate indices after each comparison.

If binary search is new to you, begin with the [beginner-friendly notes](notes/README.md), then try the problem from its prompt before opening the implementation.

## Problem List

| # | Problem | Main pattern to recall | Solution |
|---:|---|---|---|
| 1 | Binary Search | Inclusive candidate range; discard half using sorted order | [View](./BinarySearch.java) |

## Reasoning checklist

1. Is the input ordered in a way that lets a comparison rule out values?
2. What index range still contains possible answers?
3. Is the range inclusive or half-open? Make the loop condition match it.
4. After checking the midpoint, how do you exclude it from the next range?
5. What does an empty candidate range mean?

Use [`../LeetCode.md`](../LeetCode.md) for the central problem index and the [notes index](notes/README.md) for explanations.
