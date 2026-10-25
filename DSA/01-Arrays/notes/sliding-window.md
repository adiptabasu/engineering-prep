# Sliding window

## When this pattern helps

A sliding window represents a contiguous range. It is useful when the range can grow or shrink while its state is updated incrementally, without recomputing the whole range each time.

A key question is whether the validity condition changes predictably as the boundaries move. Ordinary sum-based shrinking works for positive values because extending right increases the sum and shrinking left decreases it. Negative values break that monotonic relationship.

## Fixed-size window

When every candidate range has the same length k, add the entering item and remove the leaving item.

    state = summarize the first k items
    answer = evaluate(state)
    for right from k through length(values) - 1:
        remove values[right - k] from state
        add values[right] to state
        answer = update answer using state

After initialization, each shift takes constant update work, giving O(n) time. Frequency-based windows may need more work to compare state, depending on the alphabet and representation.

## Variable-size window

For a longest or shortest valid range, extend the right boundary and shrink the left boundary while needed.

    left = 0
    for right from 0 through length(values) - 1:
        add values[right] to state
        while the window violates the required condition:
            remove values[left] from state
            left = left + 1
        update answer using the current window

This is O(n) when each boundary only moves forward and state updates are constant time. The exact order of updating the answer depends on whether the task asks for the longest valid range, shortest valid range, or a count of ranges.

## Frequency windows

For an anagram or permutation check, maintain symbol counts for a window of the required length. The window must have exactly the pattern length; otherwise equal counts could describe the wrong range size. Choose a representation that supports efficient updates and comparisons.

For a replacement budget, a window can be valid when its length minus the count of its most frequent symbol is at most the allowed replacements. If using a non-decreasing maximum-frequency shortcut, understand that it may be a stale upper bound; it supports finding the maximum length but does not always mean every intermediate window is exactly valid.

## Pitfalls and practice

- Sliding windows require contiguous ranges; subsequences are different.
- Do not use the positive-number sum argument when negative values are allowed.
- For counting subarrays, justify why every range ending at the right boundary contributes the amount you add.
- Handle boundary conditions such as an empty window or a threshold that makes every non-empty window invalid.

[Longest Substring Without Repeating Characters](../LongestSubstringWithoutRepeatingCharacters.java) · [Minimum Size Subarray Sum](../MinimumSizeSubarraySum.java) · [Maximum Average Subarray I](../MaximumAverageSubarrayI.java) · [Permutation in String](../PermutationInString.java) · [Longest Repeating Character Replacement](../LongestRepeatingCharacterReplacement.java) · [Subarray Product Less Than K](../SubarrayProductLessThanK.java)
