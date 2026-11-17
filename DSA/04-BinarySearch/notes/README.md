# Binary Search notes

These notes start from the idea of an ordered search space and build toward the first binary-search implementation. The examples use a sorted array and an inclusive range of candidate indices.

## Suggested reading order

1. Read [Binary search fundamentals](binary-search-fundamentals.md) for the sorted-order property, candidate range, midpoint decisions, a worked trace, and common boundary mistakes.
2. Re-solve [LeetCode 704: Binary Search](../BinarySearch.java) from the prompt. Explain why each comparison lets you discard half the remaining indices.

## Re-solving checklist

1. State the exact meaning of `low` and `high`.
2. Trace one comparison and show which indices are eliminated.
3. Explain why the checked midpoint is excluded from the next range.
4. Check a one-element range, a target at either end, and an absent target.
5. State the time and auxiliary-space complexity.
