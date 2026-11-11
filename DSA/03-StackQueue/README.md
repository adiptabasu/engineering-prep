# 03 — Stack & Queue

**Status: Complete for the current problem set — 30 problems recorded**

This section practices LIFO and FIFO behavior, queue/deque design, stack-based parsing and simulation, monotonic stacks, and monotonic deques for sliding windows. Re-solve from the prompt before opening an implementation; explain what each structure stores and why items can be removed.

Language-neutral explanations are in the [Stack & Queue notes](notes/README.md). The list below links the 30 unique problems; Sum of Subarray Minimums has both grouped-minimum and boundary-based implementations.

## Start here

If stacks and queues are new, begin with the [notes index](notes/README.md): it introduces the basic structures, traces small examples, then builds toward monotonic stacks and sliding windows. After that, use the problem list below to choose a practice question. The implementation links are references to consult after attempting a problem.

## Problem List

| # | Problem | Main pattern to recall | Solution |
|---:|---|---|---|
| 1 | Valid Parentheses | Match the most recent unmatched opener | [View](./ValidParentheses.java) |
| 2 | Next Greater Element I | Resolve waiting indices with a monotonic stack | [View](./NextGreaterElementI.java) |
| 3 | Daily Temperatures | Next greater value and distance | [View](./DailyTemperatures.java) |
| 4 | Final Prices With a Special Discount in a Shop | Next smaller-or-equal value | [View](./FinalPricesWithSpecialDiscount.java) |
| 5 | Online Stock Span | Previous greater boundary with compressed spans | [View](./StockSpanner.java) |
| 6 | Largest Rectangle in Histogram | Nearest lower boundaries define width | [View](./LargestRectangleInHistogram.java) |
| 7 | Trapping Rain Water | Monotonic stack identifies bounded basins | [View](./TrappingRainWaterStack.java) |
| 8 | Implement Queue using Stacks | Two stacks; transfer only when output is empty | [View](./MyQueue.java) |
| 9 | Number of Recent Calls | FIFO timestamps in a moving time range | [View](./RecentCounter.java) |
| 10 | Design Circular Queue | Array indices wrap with modular arithmetic | [View](./MyCircularQueue.java) |
| 11 | Implement Stack using Queues | Rotate a queue to expose the newest item | [View](./MyStack.java) |
| 12 | Evaluate Reverse Polish Notation | Pop right operand, then left operand | [View](./EvaluateReversePolishNotation.java) |
| 13 | Simplify Path | Stack of directory components | [View](./SimplifyPath.java) |
| 14 | Asteroid Collision | Repeatedly resolve opposing top-of-stack collisions | [View](./AsteroidCollision.java) |
| 15 | Removing Stars From a String | Stack-like cancellation of the previous character | [View](./RemovingStarsFromString.java) |
| 16 | Make The String Great | Cancel adjacent case-paired characters | [View](./MakeTheStringGreat.java) |
| 17 | Backspace String Compare | Build and compare the edited strings | [View](./BackspaceStringCompare.java) |
| 18 | Decode String | Save nested repeat counts and string prefixes | [View](./DecodeString.java) |
| 19 | Remove K Digits | Greedy monotonic stack with a deletion budget | [View](./RemoveKDigits.java) |
| 20 | Remove Duplicate Letters | Lexicographically smallest unique subsequence | [View](./RemoveDuplicateLetters.java) |
| 21 | Remove All Adjacent Duplicates in String II | Track character groups and counts | [View](./RemoveAllAdjacentDuplicatesInStringII.java) |
| 22 | Next Greater Element II | Circular next-greater scan | [View](./NextGreaterElementII.java) |
| 23 | 132 Pattern | Right-to-left stack maintains candidate middle values | [View](./Find132Pattern.java) |
| 24 | Find the Most Competitive Subsequence | Greedy monotonic subsequence under deletions | [View](./MostCompetitiveSubsequence.java) |
| 25 | Maximum Width Ramp | Dominated starting indices form a candidate stack | [View](./MaximumWidthRamp.java) |
| 26 | Sum of Subarray Minimums | Group subarrays by ending minimum; alternate boundary method | [Grouped](./SumOfSubarrayMinimums.java) · [Boundaries](./SumOfSubarrayMinimumsByBoundaries.java) |
| 27 | Sum of Subarray Ranges | Maximum contributions minus minimum contributions | [View](./SumOfSubarrayRanges.java) |
| 28 | Maximum Subarray Min-Product | Minimum boundaries plus prefix-sum span | [View](./MaximumSubarrayMinProduct.java) |
| 29 | Sliding Window Maximum | Decreasing deque of candidate indices | [View](./SlidingWindowMaximum.java) |
| 30 | Longest Continuous Subarray With Absolute Diff ≤ Limit | Two monotonic deques and a variable window | [View](./LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) |

The four standalone previous/next boundary helpers are in [MonotonicStackBoundaryReference.java](./MonotonicStackBoundaryReference.java).

## Reasoning checklist

Before coding, answer:

1. What does each stack, queue, or deque entry represent?
2. Which end is used for insertion, inspection, and removal?
3. What invariant does the structure maintain?
4. Why can a popped candidate never be needed again?
5. For a window, when does an index expire and when should `left` move?
6. For equal boundary values, which side keeps ties?
7. What is the amortized cost, and how often can each item enter and leave?

## Revisiting the section

The problem set is recorded; continued practice should focus on deriving the invariant from a fresh prompt and explaining it without relying on a saved implementation. In particular, revisit duplicate tie ownership in contribution counting and distinguish fixed-size window expiry from shrinking a variable-size window.

Use [`../LeetCode.md`](../LeetCode.md) for canonical problem links and the [notes index](notes/README.md) for topic explanations.
