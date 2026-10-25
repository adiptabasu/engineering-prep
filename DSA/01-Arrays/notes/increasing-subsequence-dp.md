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

## Why the recurrence covers every valid subsequence

Consider an increasing subsequence that ends at index i. If it contains only values[i], its length is one. Otherwise, its previous value must be at some earlier index j where values[j] < values[i]. Among all such j, the longest subsequence ending at j gives the longest one that can be extended to i. Taking the maximum over compatible earlier positions checks every possible predecessor.

For values [10, 9, 2, 5, 3, 7], the best lengths ending at each position are [1, 1, 1, 2, 2, 3]. The answer is 3, even though the last input value is not itself the end of every candidate sequence. One corresponding subsequence is [2, 3, 7].

The dynamic-programming state is deliberately local: it answers “best subsequence ending here.” The final answer is the best state across all endpoints.
## Questions to ask

- What does dp[i] mean exactly?
- Which earlier positions can precede i?
- Why does every dp[i] start at length one?
- Why can the final answer occur before the last input position?

## Practice link

[Longest Increasing Subsequence](../LongestIncreasingSubsequence.java)
