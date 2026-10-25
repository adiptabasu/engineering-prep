# Sorting fundamentals

## What sorting provides

Sorting reorders values according to a chosen comparison rule. A sorted order makes relationships between neighbors visible and can turn repeated search or pair decisions into a scan. It also changes the positions of elements, so use it only when the original order is irrelevant or can be restored.

Sorting is a transformation that may enable another pattern, not a complete solution by itself. For example, after sorting, two pointers can move inward because increasing the left value raises a sum and decreasing the right value lowers it. That movement is not safe on unsorted data.

## Comparison-based sorting

General-purpose comparison sorts have an O(n log n) lower bound in the comparison model for arbitrary input. Common efficient sorts meet O(n log n) time, though their extra-space use, stability, and worst-case guarantees differ by algorithm and implementation.

For interview analysis, state the properties you rely on instead of assuming every library sort behaves identically:

- **Time:** Is the bound worst-case or expected? Is the data nearly sorted?
- **Extra space:** Is sorting in place, or does it allocate proportional storage?
- **Stability:** Do equal-key items keep their original relative order?
- **Comparator:** Does it define a consistent ordering for all relevant values?

Stability matters when records have multiple keys. Sorting by a secondary key and then stably by a primary key preserves the secondary order among equal primary keys. Stability does not matter if only the final values or groups matter.

## Using sorted order

After sorting, scan adjacent values to detect duplicates or group equal keys. For pair sums in a sorted array, compare the endpoint sum to the target:

    left = 0
    right = length(values) - 1
    while left < right:
        total = values[left] + values[right]
        if total == target:
            return pair
        if total < target:
            left = left + 1
        else:
            right = right - 1

If the total is too small, keeping the left value and choosing any value no larger than the current right value cannot increase the sum enough; discard the left value. The too-large case is symmetric. This proof depends on ascending sorted order.

Sorting followed by a linear scan costs O(n log n) time overall and O(n) time for the scan. The scan's extra space may be O(1), but total space must include the sorting algorithm's auxiliary space and any output.

## Comparator and numeric pitfalls

- A comparator must be transitive and consistent. A contradictory comparator can invalidate the sort's result.
- Avoid subtraction as a comparison shortcut when values can be near numeric limits; subtraction may overflow. Compare by ordering directly.
- Sorting a copy costs O(n) extra space; sorting the input mutates it.
- Sorting may destroy meaningful positions or stable ordering if the chosen method is unstable.
- Duplicate handling is a separate part of the algorithm. Sorting groups duplicates but does not decide whether to keep one, skip them, or count them.

## Complexity summary

For a comparison sort, expect O(n log n) time in common efficient implementations, but check the guarantee for the algorithm or library in use. A following scan is O(n). The sort's space use varies; do not infer O(1) auxiliary space from an in-place-looking interface.
