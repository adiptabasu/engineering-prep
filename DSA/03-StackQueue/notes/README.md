# Stack & Queue notes

These notes start with the basic structures and build toward monotonic stacks and deques. They use plain-language definitions, small traced examples, and language-neutral reasoning; Java collection names connect the ideas to the solutions. Use the [Stack & Queue revision guide](../README.md) for the problem list and implementation links.

## Suggested reading order

1. Start with [stacks, queues, and deques](stacks-queues-and-deques.md) to learn what each structure does and how its ends work.
2. Read [stack simulation and parsing](stack-simulation-and-parsing.md) for simple examples of using a stack to remember unfinished work.
3. Learn the [monotonic stack](monotonic-stacks.md) by starting with the next-greater example.
4. Continue to [boundaries and subarray contributions](boundaries-and-subarray-contributions.md), which builds on monotonic stacks and introduces the distance formula.
5. Study [monotonic deques and sliding windows](monotonic-deques-and-sliding-windows.md) after you understand queues and deques.
6. Use [queue design and streams](queue-design-and-streams.md) for implementations, circular storage, and timestamps.

## Notes and related problems

| Note | Main ideas | Practice |
|---|---|---|
| [Stacks, queues, and deques](stacks-queues-and-deques.md) | LIFO/FIFO behavior, operations, choosing a structure, Java `Deque` | [Implement Queue using Stacks](../MyQueue.java), [Implement Stack using Queues](../MyStack.java), [Design Circular Queue](../MyCircularQueue.java) |
| [Stack simulation and parsing](stack-simulation-and-parsing.md) | Delimiter matching, cancellation, collisions, postfix evaluation, paths, nested encodings | [Valid Parentheses](../ValidParentheses.java), [Asteroid Collision](../AsteroidCollision.java), [Evaluate Reverse Polish Notation](../EvaluateReversePolishNotation.java), [Decode String](../DecodeString.java) |
| [Monotonic stacks](monotonic-stacks.md) | Next/previous greater or smaller values, unresolved indices, amortized linear scans | [Daily Temperatures](../DailyTemperatures.java), [Next Greater Element II](../NextGreaterElementII.java), [Monotonic stack boundary reference](../MonotonicStackBoundaryReference.java) |
| [Boundaries and subarray contributions](boundaries-and-subarray-contributions.md) | Nearest blockers, duplicate tie rules, histogram areas, per-index min/max contributions, prefix sums | [Sum of Subarray Minimums](../SumOfSubarrayMinimums.java), [Sum of Subarray Ranges](../SumOfSubarrayRanges.java), [Maximum Subarray Min-Product](../MaximumSubarrayMinProduct.java) |
| [Monotonic deques and sliding windows](monotonic-deques-and-sliding-windows.md) | Candidate extrema, fixed and variable windows, expiry, shrinking | [Sliding Window Maximum](../SlidingWindowMaximum.java), [Longest Continuous Subarray](../LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.java) |
| [Queue design and streams](queue-design-and-streams.md) | Two-stack queue, one-queue stack, circular buffers, timestamp windows, amortized transfer | [MyQueue](../MyQueue.java), [MyStack](../MyStack.java), [Recent Counter](../RecentCounter.java) |

## Re-solving checklist

1. State what the stack or deque stores: values, indices, or groups.
2. Name the invariant and what the front or top means.
3. Explain why an item can be removed permanently.
4. For a window, define its valid index range and expiry condition.
5. For duplicate boundaries, decide which side keeps equal values.
6. Check empty input, one item, duplicates, and boundary values.
7. Derive the complexity by counting how often each item enters and leaves.
8. Explain the reasoning before consulting the implementation.
