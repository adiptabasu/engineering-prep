# Difference arrays

## When to use them

A difference array converts many range additions into two boundary updates each. Use it when there are multiple updates of the form “add x to every position from L through R,” and the final values are needed after all updates. It is less useful when the answer must be queried after every update; a Fenwick tree or segment tree may then be appropriate.

## Boundary updates

Let `diff` have length n and start at zero. For an inclusive update `[L, R]` that adds `x`, record where the change begins and where it stops:

    diff[L] = diff[L] + x
    if R + 1 < n:
        diff[R + 1] = diff[R + 1] - x

The subtraction cancels the update just after its right endpoint. Taking a prefix sum reconstructs the combined values:

    running = 0
    for i from 0 through n - 1:
        running = running + diff[i]
        result[i] = running

Each update takes O(1); materializing the final array takes O(n). For q updates, the total time is O(n + q). A separate difference array and result use O(n) extra space; if the input itself may hold the differences and be reconstructed in place, extra space can be O(1), subject to the required output.

## Why it works

An update contributes `x` to every prefix position starting at L. The `+x` at L makes that contribution appear. The `-x` at R + 1 removes it from later prefixes. Contributions from overlapping updates add together, so one prefix scan combines all of them.

For example, adding 3 to indices 1 through 3 in an array of length 5 records `[0, 3, 0, 0, -3]`. Its prefix sums are `[0, 3, 3, 3, 0]`, exactly the requested range update.

## Half-open ranges

For a half-open range `[L, R)`, add at `L` and subtract at `R` when `R < n`. Choosing one convention and using it consistently prevents off-by-one errors.

## Reasoning checks and pitfalls

- Are endpoints inclusive or half-open?
- Is `R + 1` within the difference array? Guard the boundary, or allocate an extra sentinel slot of length n + 1.
- Are updates applied to the original values as well as the difference contributions? Initialize the difference representation to reflect the original array if needed.
- Could cumulative sums overflow the chosen numeric type?
- Are intermediate query answers required? A plain difference array only reconstructs after updates; it does not provide fast arbitrary range queries during updates.
