# Binary Search Pattern Map

Use this page to pick one focused note. Each pattern has a separate beginner-oriented explanation with its own reasoning, template, pitfalls, and review prompts. **Preview** notes are useful references for unfamiliar problems; study them when the corresponding pattern comes up.

| Pattern | Use it when the problem asks for… | Note |
|---|---|---|
| Exact search | The index of a target in sorted data | [Fundamentals](binary-search-fundamentals.md) |
| Lower/upper bound | The first position meeting `>=` or `>` | [Bounds](lower-and-upper-bounds.md) |
| Interval conventions | Help with loop bounds, midpoint choice, or overflow | [Intervals and Midpoints](intervals-and-midpoints.md) |
| Recursive search | The same exact search written with recursive calls | [Recursion](recursive-binary-search.md) |
| Rotated sorted array | Search an array shifted around a pivot, with or without duplicates | [Rotated Array Search](rotated-sorted-array-search.md) |
| Minimum in a rotated array | Find the smallest value or rotation point | [Rotated Array Minimum](minimum-in-rotated-array.md) |
| Minimum with duplicates | Find a minimum when equal endpoints can hide the rotation side | [Rotated Array Minimum](minimum-in-rotated-array.md) and [LeetCode 154 implementation](../FindMinimumInRotatedSortedArrayII.java) |
| Rotation count — Reference | Count right rotations when the original values are distinct | [Rotation Count](rotation-count.md) |
| Single element in sorted array | Find the unpaired value from the shift in pair positions | [Single Element](single-element-in-sorted-array.md) |
| Binary search on answer — Preview | The smallest/largest numeric value that satisfies a check | [Answer Search](binary-search-on-answer.md) |
| Peak finding — Preview | Find a local maximum using a rising/falling property | [Peak Finding](peak-finding.md) |
| Matrix search — Preview | Search a matrix with a specific ordering guarantee | [Matrix Search](matrix-search.md) |
| Exponential search — Preview | Search a sorted source with no known end | [Exponential Search](exponential-search.md) |

The current practice sequence covered exact search, boundaries, rotated-array search and minimum (including duplicates), and singleton search by pair alignment. Rotation count is available as a related reference. Java lower- and upper-bound helpers are in [BinarySearchBounds.java](../BinarySearchBounds.java).
