# 01 — Arrays & Hashing

**Status: 24 / 24 implemented**

This section is the first DSA progression and is intended to be re-solved periodically from scratch.

The goal is not to memorize the 24 solutions. The goal is to recognize the underlying constraints and derive the appropriate technique.

## At-a-Glance Revision

| # | Problem | Attempted / Derived Approach | Idea | Pitfall | Solution |
|---:|---|---|---|---|---|
| 1 | Two Sum | HashMap of previously seen values | Find `target - x` in O(1) average lookup | Don't use brute-force pairs | [View](./TwoSum.java) |
| 2 | Contains Duplicate | HashSet / HashMap | A set exposes repeated values immediately | `Set.add()` returns false for an existing value | [View](./ContainsDuplicate.java) |
| 3 | Valid Anagram | Frequency map / `int[26]` | Same character frequencies ⇒ anagrams | Sorting is unnecessary when alphabet is fixed | [View](./ValidAnagram.java) |
| 4 | Group Anagrams | Frequency signature | Equal frequency vectors belong to the same group | Avoid order-dependent keys | [View](./GroupAnagrams.java) |
| 5 | Top K Frequent Elements | Frequency + TreeMap / min-heap | Keep only the k most relevant frequencies | Heap size must stay at `k` | [View](./TopKFrequent.java) |
| 6 | Product Except Self | Left products + right products | Product on each side can be accumulated independently | Don't divide; zeros must work | [View](./ProductExceptSelf.java) |
| 7 | Longest Consecutive Sequence | HashSet + sequence starts | Only count when `num - 1` is absent | Don't sort if O(n) average is desired | [View](./LongestConsecutive.java) |
| 8 | Valid Palindrome | Two pointers | Compare valid characters from both ends | Skip non-alphanumeric characters correctly | [View](./ValidPalindrome.java) |
| 9 | Two Sum II | Two pointers on sorted input | Sum tells which boundary can move | This relies on sorted order | [View](./TwoSumII.java) |
| 10 | 3Sum | Sort + two pointers | Fix one value, then solve a 2Sum-style remainder | Duplicate handling is essential | [View](./ThreeSum.java) |
| 11 | Best Time to Buy and Sell Stock | Running minimum | Cheapest price so far is the only past state needed | Buy must occur before sell | [View](./BestTimeToBuyAndSellStock.java) |
| 12 | Longest Substring Without Repeating Characters | Variable window + index map | Move left past the previous duplicate | Left must never move backward | [View](./LongestSubstringWithoutRepeatingCharacters.java) |
| 13 | Minimum Size Subarray Sum | Variable sliding window | Positive values make sum monotonic under expansion/shrink | Doesn't generalize directly to negative values | [View](./MinimumSizeSubarraySum.java) |
| 14 | Maximum Average Subarray I | Fixed-size window | Maintain one rolling sum of exactly k elements | Don't recompute every window | [View](./MaximumAverageSubarrayI.java) |
| 15 | Permutation in String | Fixed window + frequency counts | Compare character-frequency state | Window must remain exactly the pattern length | [View](./PermutationInString.java) |
| 16 | Longest Repeating Character Replacement | Variable window + max frequency | Valid when `windowLength - maxFrequency <= k` | `maxFrequency` is the dominant frequency | [View](./LongestRepeatingCharacterReplacement.java) |
| 17 | Container With Most Water | Two pointers | Move the shorter boundary because width only decreases | Moving the taller side cannot improve the limiting height | [View](./ContainerWithMostWater.java) |
| 18 | Trapping Rain Water | Prefix/suffix maxima → two pointers | Water is limited by the lower side | The O(1)-space proof depends on boundary dominance | [View](./TrappingRainWater.java) |
| 19 | Subarray Sum Equals K | Prefix sum + frequency map | Look for `currentPrefix - k` among prior prefixes | Negative numbers break ordinary sliding window | [View](./SubarraySumEqualsK.java) |
| 20 | Maximum Product Subarray | Max/min ending states | A negative can turn min into max and vice versa | Preserve previous max/min before updating | [View](./MaximumProductSubarray.java) |
| 21 | Maximum Subarray | Kadane's algorithm | Best subarray ending here is either current value or previous best + current | Initialize from first element for all-negative arrays | [View](./MaximumSubarray.java) |
| 22 | Subarray Product Less Than K | Variable window + counting | Positive products let the window shrink safely; add `right-left+1` | `k <= 1` ⇒ answer 0 | [View](./SubarrayProductLessThanK.java) |
| 23 | Subarrays Divisible by K | Prefix remainder frequency | Same remainder ⇒ difference divisible by k | Normalize Java's negative `%` result | [View](./SubarraysDivByK.java) |
| 24 | Longest Increasing Subsequence | O(n²) DP | `dp[i]` = best increasing subsequence ending at i | Subsequence is not necessarily contiguous; global max may be before last index | [View](./LongestIncreasingSubsequence.java) |

## Pattern Recognition Cheatsheet

### HashMap / HashSet

Ask:

> Do I need fast lookup, frequency, uniqueness, or a complement?

Typical signals:

```text
"Have I seen this?"
"How many times have I seen this?"
"What value would complete this?"
```

### Two Pointers

Ask:

> Is there sorted/order information that lets me eliminate possibilities?

Typical pair-sum rule:

```text
sum < target → left++
sum > target → right--
```

### Sliding Window

Ask:

> Am I looking for a contiguous range and does expanding/shrinking have predictable effects?

For positive numbers:

```text
expand right
while invalid:
    shrink left
```

### Prefix Sum

Ask:

> Do I care about sums of many contiguous ranges?

Core relationship:

```text
rangeSum(L..R) = prefix[R] - prefix[L-1]
```

For exact target `k`:

```text
previousPrefix = currentPrefix - k
```

### Prefix Remainders

Ask:

> Do I care whether a range sum is divisible by k?

Core relationship:

```text
same remainder → difference divisible by k
```

### Dynamic Programming

Ask:

> Can the problem be expressed as smaller answers that can be reused?

For LIS:

```text
dp[i] = best increasing subsequence ending at i
```

### Stack

Ask:

> Does the most recent unresolved item need to be handled first?

Typical signal:

```text
last opened → first closed
```

## Re-solving Checklist

Before opening an existing implementation:

1. What are the constraints?
2. Is the data sorted?
3. Are values positive, negative, or unrestricted?
4. Is the target contiguous?
5. Do I need lookup or frequency?
6. Can I maintain a window?
7. Can I express the problem using prefix information?
8. What invariant makes pointer/state movement safe?
9. What is the simplest correct solution?
10. Can I reduce time or space after finding it?

## Problem Files

All 24 implementations live directly in this folder.

Use [`../LeetCode.md`](../LeetCode.md) for the canonical problem links.
