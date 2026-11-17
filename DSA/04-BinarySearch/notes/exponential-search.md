# Exponential Search

## 1. When binary search does not yet have a range

Ordinary binary search needs a finite lower and upper index. Sometimes a sorted source has no known length, or it is expensive to ask how long it is. We can first find a finite interval that brackets the target, then apply binary search inside that interval.

This is called **exponential search** because the probed index grows by a factor of two: `0, 1, 2, 4, 8, 16, ...`.

## 2. Find a bracket

For an ascending source:

1. Check index `0`. If it already contains a value at least the target, the target's lower-bound position is `0` or the exact-search answer is at index `0`.
2. Otherwise check index `1`, then `2`, then `4`, doubling until a probed value is at least the target, or the source reports that the index is past its end.
3. Binary search between the last known-too-small index and the first index at or above the target.

Example: suppose the target is at index `11`. Probes at `0`, `1`, `2`, `4`, `8`, and `16` show that index `8` is too small and index `16` is large enough. The target must be between them, so binary search indices `9..16` (or `8..16` if the exact-search implementation permits checking the known-too-small endpoint).

The bracket size is at most about twice the target position. Exponential probing and the binary search each take logarithmic time in that position.

## 3. Pseudocode

An API for an unknown-length source may return a special value when an index is out of range. The exact sentinel depends on that API; do not assume `-1` is a valid sentinel if array values can also be negative.

```text
if valueAt(0) is at least target:
    handle index 0

bound = 1
while index bound exists and valueAt(bound) < target:
    bound = bound * 2

binary-search between bound / 2 and bound
```

When doubling, guard against integer overflow and respect the source's valid index range. In a bounded `int` index API, use a wider type for the growing bound if needed.

## 4. Complexity

If the target is at position `p`, the exponential phase makes **O(log p)** probes. Binary searching the bracket also takes **O(log p)** comparisons. Total time is **O(log p)** and the iterative method uses **O(1)** extra space.

## 5. When this is useful

- A sorted list or stream has no known end.
- An API allows indexed reads but does not reveal the size.
- A target is expected to be near the beginning and probing the full range is undesirable.

For a normal Java array, `nums.length` is already known, so use ordinary binary search instead of adding an exponential-bracketing phase.

## 6. Common mistakes

- **Skipping index 0.** The target may be at the first position.
- **Using the wrong binary-search range.** The last probe below the target and first probe at or above it form the bracket.
- **Confusing an out-of-range marker with a real value.** Follow the source API's contract.
- **Overflow while doubling.** The probe index must not wrap around to a negative number.
- **Applying it to a known-size array without a reason.** It adds a phase without improving the usual asymptotic bound over a directly known range.

## 7. Review prompts

1. Why does doubling eventually bracket any target at a finite index?
2. Once a bracket is found, which algorithm searches inside it?
3. Why is the number of probes logarithmic in the target's position?
4. What extra problem does an unknown-length API introduce?
