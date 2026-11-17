# Binary Search notes

These notes start from the idea of an ordered search space, then build from exact search to lower and upper boundaries. The examples use a sorted array and an inclusive range of candidate indices.

## Suggested reading order

1. Read [Binary search fundamentals](binary-search-fundamentals.md) for the sorted-order property, candidate range, midpoint decisions, a worked trace, and common boundary mistakes.
2. Read [Lower Bound and Upper Bound](lower-and-upper-bounds.md) to learn how binary search finds the first index meeting a condition and how those boundaries handle duplicates.
3. Re-solve [LeetCode 704: Binary Search](../BinarySearch.java) and [LeetCode 35: Search Insert Position](../SearchInsertPosition.java) from their prompts. Explain why each comparison lets you discard half the remaining indices.
4. Use [BinarySearchBounds.java](../BinarySearchBounds.java) as a reference after attempting both boundary loops yourself.

## Re-solving checklist

1. State the exact meaning of `low` and `high`.
2. Trace one comparison and show which indices are eliminated.
3. Explain why the checked midpoint is excluded from the next range.
4. Check a one-element range, a target at either end, and an absent target.
5. State the time and auxiliary-space complexity.
