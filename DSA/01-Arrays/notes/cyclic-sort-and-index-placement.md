# Cyclic sort and index placement

## When index placement applies

This pattern is useful when values come from a known bounded range and each value has a natural destination index. For example, values from 1 through n can target index `value - 1`; values from 0 through n - 1 can target index `value`.

The input range is essential. Arbitrary values do not have a valid destination under this rule. The method is often used to find a missing or duplicated value in a constrained range, not as a general-purpose sorting algorithm.

## Place each value at its destination

At index `i`, if the current value belongs at a different in-range index, swap it into that destination. Re-examine index `i`, since the swapped-in value may also be misplaced.

For distinct values from 1 through n:

    i = 0
    while i < length(values):
        value = values[i]
        if value < 1 or value > length(values):
            i = i + 1
            continue
        destination = value - 1
        if value != values[destination]:
            swap(values[i], values[destination])
        else:
            i = i + 1

Afterward, each value that can be placed is at its matching index. A scan can identify a missing value when `values[i] != i + 1`.

Each successful swap places at least one value into its final position; the scan index only advances when the current value is already placed, out of range, or cannot be placed because its target contains a duplicate. There are O(n) placements and O(n) checks, so the approach is O(n) time and O(1) extra space.

## Duplicates and invalid values

With duplicates, stop swapping when the destination already contains the same value. Otherwise identical copies can swap forever. Values outside the expected range must not be used as indices; decide whether to skip, report, or handle them according to the problem.

The comparison `values[i] != values[destination]` also distinguishes a value already correctly placed from one blocked by a duplicate. For finding duplicates or missing values, the final scan interprets each mismatch according to the problem's exact guarantees.

## Reasoning checks and pitfalls

- Is the value range exactly known, and does it map to a valid index?
- Is the mapping `value -> value - 1` or `value -> value`?
- After swapping, should the current index be checked again? Yes; its new value is not yet known to be placed.
- Can duplicates cause a swap loop? Check that the destination does not already hold the same value.
- Are out-of-range values allowed? Guard before indexing.
- This pattern mutates the input. Is that permitted?

Do not confuse cyclic placement with cycle detection in linked lists; they use different ideas despite the shared word “cycle.”
