# Binary Search notes

Each note focuses on one Binary Search concept and builds from the fundamentals. The [pattern map](binary-search-patterns.md) is a short directory; open the specific concept note when you need more detail.

## Suggested reading order

1. Start with [Binary Search Fundamentals](binary-search-fundamentals.md): why sorted order lets us discard candidates, the inclusive range, and exact search.
2. Read [Lower Bound and Upper Bound](lower-and-upper-bounds.md), then attempt [LeetCode 35: Search Insert Position](../SearchInsertPosition.java) from its prompt.
3. Read [Intervals and Midpoints](intervals-and-midpoints.md) when you need to compare inclusive and half-open ranges or choose a safe midpoint.
4. Use [Recursive Binary Search](recursive-binary-search.md) to see the same search expressed with recursive calls.
5. Apply both boundaries in [LeetCode 34: Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/).
6. Read the [rotated-array search note](rotated-sorted-array-search.md), then attempt [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/) from a fresh prompt.
7. Treat the [pattern map](binary-search-patterns.md) as a directory to the preview notes for answer search, peaks, matrices, and exponential search.

## Concept notes

| Note | Concept |
|---|---|
| [Binary Search Fundamentals](binary-search-fundamentals.md) | Exact search in a sorted array |
| [Lower Bound and Upper Bound](lower-and-upper-bounds.md) | First qualifying positions and duplicate ranges |
| [Intervals and Midpoints](intervals-and-midpoints.md) | Inclusive/half-open ranges and overflow-safe midpoint choices |
| [Recursive Binary Search](recursive-binary-search.md) | Base case, recursive progress, and stack space |
| [Rotated Sorted Array Search](rotated-sorted-array-search.md) | Recognize the sorted half |
| [Binary Search on an Answer](binary-search-on-answer.md) | Find a minimum or maximum feasible value |
| [Peak Finding](peak-finding.md) | Follow a rising or falling slope |
| [Matrix Search](matrix-search.md) | Choose a method based on matrix ordering |
| [Exponential Search](exponential-search.md) | Bracket a target when the upper bound is unknown |

## Re-solving checklist

1. State the exact meaning of `low` and `high`.
2. Trace one comparison and show which indices are eliminated.
3. Explain why the checked midpoint is excluded from the next range.
4. Check a one-element range, a target at either end, and an absent target.
5. State the time and auxiliary-space complexity.
