# Arrays & Hashing theory notes

These notes explain reusable problem-solving patterns in language-neutral pseudocode. Indices are zero-based. Unless stated otherwise, a range from left through right includes both endpoints.

Use a note to understand a pattern, then test the idea on the linked problems without opening their implementations first. The parent [Arrays & Hashing revision guide](../README.md) remains the quick review of all 24 practiced problems.

## Patterns

| Note | Useful when | Related problems |
|---|---|---|
| [Hashing and frequency](hashing-and-frequency.md) | You need fast lookup, counts, or a stable signature | [Two Sum](../TwoSum.java), [Contains Duplicate](../ContainsDuplicate.java), [Valid Anagram](../ValidAnagram.java), [Group Anagrams](../GroupAnagrams.java) |
| [Two pointers](two-pointers.md) | Ordering or boundary reasoning lets you discard candidates | [Valid Palindrome](../ValidPalindrome.java), [Two Sum II](../TwoSumII.java), [3Sum](../ThreeSum.java), [Container With Most Water](../ContainerWithMostWater.java), [Trapping Rain Water](../TrappingRainWater.java) |
| [Sliding window](sliding-window.md) | The target is a contiguous range and window changes can be maintained | [Longest Substring Without Repeating Characters](../LongestSubstringWithoutRepeatingCharacters.java), [Minimum Size Subarray Sum](../MinimumSizeSubarraySum.java), [Maximum Average Subarray I](../MaximumAverageSubarrayI.java), [Permutation in String](../PermutationInString.java), [Longest Repeating Character Replacement](../LongestRepeatingCharacterReplacement.java), [Subarray Product Less Than K](../SubarrayProductLessThanK.java) |
| [Prefix sums and remainders](prefix-sums-and-remainders.md) | You need range sums or counts of subarrays meeting a sum or divisibility condition | [Subarray Sum Equals K](../SubarraySumEqualsK.java), [Subarrays Divisible by K](../SubarraysDivByK.java) |
| [Prefix and suffix aggregates](prefix-and-suffix.md) | Each answer combines information from both sides of an index | [Product of Array Except Self](../ProductExceptSelf.java) |
| [Running state and subarray DP](running-state-and-subarray-dp.md) | A compact summary of earlier values is enough to extend an answer | [Best Time to Buy and Sell Stock](../BestTimeToBuyAndSellStock.java), [Maximum Subarray](../MaximumSubarray.java), [Maximum Product Subarray](../MaximumProductSubarray.java) |
| [Sets and sequence starts](sets-and-sequence-starts.md) | Membership tests can identify the start of a longer sequence | [Longest Consecutive Sequence](../LongestConsecutive.java) |
| [Top K and heaps](top-k-and-heaps.md) | You need only the best k elements from a larger collection | [Top K Frequent Elements](../TopKFrequent.java) |
| [Increasing subsequence DP](increasing-subsequence-dp.md) | A best answer can be built from compatible earlier positions | [Longest Increasing Subsequence](../LongestIncreasingSubsequence.java) |

## How to use the notes

For each problem, first identify the input properties and the result being requested. Pick a pattern only when its assumptions hold. State the invariant in plain language, trace a small example, and then implement. Afterward, explain why the invariant makes the algorithm correct and where its time and space go.

The pseudocode describes the algorithm rather than any particular programming language. Data structures such as maps, sets, and heaps are abstract.
