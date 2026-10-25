# Sets and sequence starts

## Find starts, not every possible path

A set makes membership checks fast on average. For a longest consecutive sequence, starting a full count at every value repeats work. Instead, only count forward from values that have no predecessor.

    valuesSet = set containing all input values
    longest = 0
    for each value x in valuesSet:
        if x - 1 is not in valuesSet:
            length = 1
            while x + length is in valuesSet:
                length = length + 1
            longest = max(longest, length)

Every consecutive run has exactly one start. Counting only there avoids recounting the same run. The expected time is O(n) because each value is checked a bounded number of times across the set of starts; space is O(n).

## Why counting only at starts is linear

Every maximal run of consecutive values has one smallest value. That value has no predecessor in the set, so it is the run's unique start. Any other value in the run has a predecessor and is skipped as an expansion point.

For input values {100, 4, 200, 1, 3, 2}, only 1 starts the run 1, 2, 3, 4. The scan checks that run once instead of recounting its suffix from 2, 3, and 4. Across all runs, each value is traversed only from its unique start; set construction and membership checks make the total expected work O(n).

The sequence is based on numeric adjacency, not input order. Sorting would work but costs O(n log n); the set-start observation is what permits expected linear time.
## Assumptions and pitfalls

- Deduplicate input through the set; duplicates do not extend a consecutive run.
- Do not sort if expected O(n) time is required.
- Be aware of integer overflow when checking predecessor or successor values at the numeric limits. The problem's constraints may rule this out; otherwise use safe boundary checks.

## Practice link

[Longest Consecutive Sequence](../LongestConsecutive.java)
