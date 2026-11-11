# Monotonic stacks

A **monotonic stack** is an ordinary stack used to keep candidate values in sorted order. “Monotonic” here means the values go only one direction as you read from the bottom of the stack to the top: they are either increasing or decreasing.

This pattern helps answer questions such as “where is the next taller value?” or “what is the nearest smaller value on either side?”

In an array, an **index** is a position. With zero-based indexing, the first position is 0; `values[i]` means the value at position `i`. A stack can store values directly, but storing indices is useful when you need the position or a distance.

## Start with a simple search

For every value, a direct way to find the next greater value is to scan all later positions until a bigger value appears. With `n` values, this can take O(n²) time: in the worst case, many positions each scan most of the remaining array.

The stack improves this by remembering positions whose answers are not known yet. These are **unresolved candidates**. A new value may answer several candidates at once.

## Worked example: next greater value

Suppose the values are `[2, 1, 3]`. Store indices so the answer can refer to the correct position. Scan left to right; the stack holds unresolved indices, with the value at the top shown last.

| Current index and value | What happens | Unresolved stack (bottom → top) |
|---|---|---|
| `0:2` | No earlier candidates; push index 0 | `0:2` |
| `1:1` | 1 is not greater than 2; push index 1 | `0:2, 1:1` |
| `2:3` | 3 is greater than 1, then greater than 2; resolve and pop both | empty, then push `2:3` |

Index 1 and index 0 both get next-greater index 2. When the scan ends, any indices still on the stack have no next greater value.

The stack values decrease from bottom to top in this scan. A `while` loop may pop several indices at one position, but each index is pushed once and popped once, so the total work is O(n).

## Previous and next boundaries

A **boundary** is the nearest position on one side whose value meets a comparison rule. For example, to find the next smaller value, scan from right to left so the stack contains positions to the right of the current one. Pop candidates that are too large or equal if the question asks for a strictly smaller value. The remaining top is the nearest candidate that qualifies.

The exact comparison depends on whether the problem asks for strict or inclusive ordering. The shared boundary reference uses these conventions:

“Strictly smaller” means `<`; “smaller-or-equal” means `<=`. The same distinction applies to greater values.

| Boundary being found | Scan direction | Pop while top value is | If no boundary exists |
|---|---|---|---:|
| Previous smaller-or-equal | Left to right | `>` current | `-1` |
| Next strictly smaller | Right to left | `>=` current | `n` |
| Previous greater-or-equal | Left to right | `<` current | `-1` |
| Next strictly greater | Right to left | `<=` current | `n` |

Here `n` is the array length. `-1` and `n` are sentinel indices just outside the array; they simplify distance calculations.

## Why store indices?

Storing only values can be enough when the answer asks for a value. Store **indices** when you need to know where the answer is, measure a distance, calculate a width, or detect that an item has left a window. When comparing an index on the stack with the current value, compare `values[stackTop]` with the current value.

## Why is popping safe?

For a next-greater scan, if the current value is greater than the value at a waiting index, the current position is its first greater answer: all positions between them have already been checked and did not resolve it.

For a boundary search, a popped position cannot be the nearest qualifying boundary after a closer value makes it invalid. Before using a monotonic stack, state why a removed candidate will never be needed again. That explanation is the invariant that proves the algorithm.

## Circular arrays

For a circular next-greater question, imagine scanning two copies of the array so values after the end can serve as candidates. One common implementation scans `2 * n` virtual positions from right to left, maps them with `position = virtualIndex % n`, and writes answers only for the original-array positions.

## Practice links

[Daily Temperatures](../DailyTemperatures.java) · [Online Stock Span](../StockSpanner.java) · [Largest Rectangle in Histogram](../LargestRectangleInHistogram.java) · [Next Greater Element II](../NextGreaterElementII.java) · [Find 132 Pattern](../Find132Pattern.java) · [Most Competitive Subsequence](../MostCompetitiveSubsequence.java)

See the four worked boundary helpers in [MonotonicStackBoundaryReference.java](../MonotonicStackBoundaryReference.java).
