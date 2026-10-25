# Increasing subsequence dynamic programming

## Subsequence is not subarray

A subsequence keeps the original order but may skip positions. It is not required to be contiguous. A subarray is contiguous.

## State: best sequence ending at each position

For the longest strictly increasing subsequence, define dp[i] as the length of the best increasing subsequence that ends exactly at index i.

    for i from 0 through n - 1:
        dp[i] = 1
        for j from 0 through i - 1:
            if values[j] < values[i]:
                dp[i] = max(dp[i], dp[j] + 1)
    answer = maximum value in dp

The strict comparison matches a strictly increasing sequence. For a non-decreasing sequence, the compatibility test changes to allow equality. The answer is the maximum over every endpoint, not necessarily dp at the final index.

This direct recurrence takes O(n squared) time and O(n) space. It is a useful starting point because the state and transition are explicit. An O(n log n) method also exists, but its tails array is not itself necessarily a longest subsequence; study it as a separate optimization.

## Questions to ask

- What does dp[i] mean exactly?
- Which earlier positions can precede i?
- Why does every dp[i] start at length one?
- Why can the final answer occur before the last input position?

## Practice link

[Longest Increasing Subsequence](../LongestIncreasingSubsequence.java)
