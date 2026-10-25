# Arrays & Hashing theory notes

These notes explain reusable problem-solving patterns in language-neutral pseudocode. Indices are zero-based. Unless stated otherwise, a range from left through right includes both endpoints.

Use a note to understand a pattern, then test the idea on the linked problems without opening their implementations first. The parent [Arrays & Hashing revision guide](../README.md) remains the quick review of all 24 practiced problems.

## Patterns

| Note | Useful when | Related problems |
|---|---|---|
| [Array fundamentals](array-fundamentals.md) | You need the basic model, indexing, traversal, and operation costs | All array patterns build on these basics |
| [In-place array manipulation](in-place-array-manipulation.md) | You need to transform or compact an array while limiting extra storage | Builds on the read/write pointer idea in [Two Pointers](two-pointers.md) |
| [2D arrays and matrices](two-dimensional-arrays-and-matrices.md) | Values are arranged by row and column, and traversal or boundaries matter | Foundational reference; no matrix problems are in this section yet |
| [Sorting fundamentals](sorting-fundamentals.md) | Reordering values may expose adjacency, order, or duplicate groups | [Two Sum II](../TwoSumII.java), [3Sum](../ThreeSum.java) |
| [Hashing and frequency](hashing-and-frequency.md) | You need fast lookup, counts, or a stable signature | [Two Sum](../TwoSum.java), [Contains Duplicate](../ContainsDuplicate.java), [Valid Anagram](../ValidAnagram.java), [Group Anagrams](../GroupAnagrams.java) |
| [Hash table mechanics](hash-table-mechanics.md) | You want to understand hashing, collisions, resizing, and average-case lookup | Supports the [hashing and frequency](hashing-and-frequency.md) pattern |
| [Two pointers](two-pointers.md) | Ordering or boundary reasoning lets you discard candidates | [Valid Palindrome](../ValidPalindrome.java), [Two Sum II](../TwoSumII.java), [3Sum](../ThreeSum.java), [Container With Most Water](../ContainerWithMostWater.java), [Trapping Rain Water](../TrappingRainWater.java) |
| [Sliding window](sliding-window.md) | The target is a contiguous range and window changes can be maintained | [Longest Substring Without Repeating Characters](../LongestSubstringWithoutRepeatingCharacters.java), [Minimum Size Subarray Sum](../MinimumSizeSubarraySum.java), [Maximum Average Subarray I](../MaximumAverageSubarrayI.java), [Permutation in String](../PermutationInString.java), [Longest Repeating Character Replacement](../LongestRepeatingCharacterReplacement.java), [Subarray Product Less Than K](../SubarrayProductLessThanK.java) |
| [Prefix sums and remainders](prefix-sums-and-remainders.md) | You need range sums or counts of subarrays meeting a sum or divisibility condition | [Subarray Sum Equals K](../SubarraySumEqualsK.java), [Subarrays Divisible by K](../SubarraysDivByK.java) |
| [Difference arrays](difference-arrays.md) | Many updates add a value to whole ranges, followed by one final materialization | Foundational reference; no range-update problem is in this section yet |
| [Prefix and suffix aggregates](prefix-and-suffix.md) | Each answer combines information from both sides of an index | [Product of Array Except Self](../ProductExceptSelf.java) |
| [Running state and subarray DP](running-state-and-subarray-dp.md) | A compact summary of earlier values is enough to extend an answer | [Best Time to Buy and Sell Stock](../BestTimeToBuyAndSellStock.java), [Maximum Subarray](../MaximumSubarray.java), [Maximum Product Subarray](../MaximumProductSubarray.java) |
| [Sets and sequence starts](sets-and-sequence-starts.md) | Membership tests can identify the start of a longer sequence | [Longest Consecutive Sequence](../LongestConsecutive.java) |
| [Cyclic sort and index placement](cyclic-sort-and-index-placement.md) | Values belong to a known index range and can be placed at their matching index | Foundational reference; no index-placement problem is in this section yet |
| [Top K and heaps](top-k-and-heaps.md) | You need only the best k elements from a larger collection | [Top K Frequent Elements](../TopKFrequent.java) |
| [Increasing subsequence DP](increasing-subsequence-dp.md) | A best answer can be built from compatible earlier positions | [Longest Increasing Subsequence](../LongestIncreasingSubsequence.java) |

## Choosing a pattern

Start with the shape of the question rather than a remembered solution:

- Need to find a matching value, complement, or count? Consider hashing.
- Need a contiguous range whose state can be updated at its boundaries? Consider a sliding window.
- Need repeated range sums or counts of ranges with a target sum? Consider prefix sums.
- Is the input sorted, or can a safe boundary rule eliminate candidates? Consider two pointers.
- Does the answer depend on the best state ending at the current position? Consider a running-state recurrence or dynamic programming.
- Does membership reveal where a sequence begins? Consider a set and sequence starts.
- Need only the best k candidates? Consider a bounded heap.

These are clues, not rules. Check the assumptions in the relevant note before applying a pattern.

## How to use the notes

For each problem, first identify the input properties and the result being requested. Pick a pattern only when its assumptions hold. State the invariant in plain language, trace a small example, and then implement. Afterward, explain why the invariant makes the algorithm correct and where its time and space go.

The pseudocode describes the algorithm rather than any particular programming language. Data structures such as maps, sets, and heaps are abstract.
