# Learning journal

## Learning note — 2026-10-25

The learner reports that monotonic stack questions initially felt overwhelming. They stepped back to simpler stack and queue concepts, then returned to stack questions. The current goal is to build the stack concepts steadily and independently answer several questions for a pattern before moving to the next pattern or logic.

**Next step:** Continue with the current stack pattern. Try questions from a fresh prompt before opening the solution, use simpler examples or hints to clarify the invariant when needed, and explain the approach and complexity. Move on only after independently solving and explaining several representative questions.

This entry is based on the learner's report in conversation. Git history provides a sequence of implementation commits; it does not establish unaided solving, LeetCode acceptance, or retained understanding.

## Learning note — 2026-10-26

For [Next Greater Element II](DSA/03-StackQueue/NextGreaterElementII.java), the learner proposed scanning the circular array twice with a stack. After discussion, they corrected the virtual scan bounds and changed the pop condition to remove equal values as well as smaller values, matching the strictly-greater requirement. The learner reports that the solution was accepted on LeetCode. Independent retention has not yet been checked.

## Learning note — 2026-10-27

For [132 Pattern](DSA/03-StackQueue/Find132Pattern.java), the learner first explored storing low/high pairs and then considered left minima with right-side maxima. Through hints and examples, they identified that suffix extrema lose the order and range information the pattern needs. The learner then asked for the right-to-left monotonic-stack invariant to be explained, implemented that approach, and reports that LeetCode accepted the submission. The learner's goal is to derive the logic independently from the problem, not only reproduce the implementation from memory; this derivation has not yet been demonstrated.

## LeetCode problem implementation history

The learner reports having solved the questions listed here. The repository contains a Java implementation for each. Dates and short hashes below come from the commit that added each solution file; they record Git activity and may not be the exact study date. A committed implementation is not, by itself, evidence of independent mastery.

### Arrays & Hashing — 24

| Solution commit date | Problem | LeetCode | Repository solution | Commit |
|---|---|---|---|---|
| 2026-10-01 | Two Sum | [problem](https://leetcode.com/problems/two-sum/) | [TwoSum.java](DSA/01-Arrays/TwoSum.java) | 7d326f6 |
| 2026-10-01 | Contains Duplicate | [problem](https://leetcode.com/problems/contains-duplicate/) | [ContainsDuplicate.java](DSA/01-Arrays/ContainsDuplicate.java) | 52156b7 |
| 2026-10-02 | Valid Anagram | [problem](https://leetcode.com/problems/valid-anagram/) | [ValidAnagram.java](DSA/01-Arrays/ValidAnagram.java) | ffcd47f |
| 2026-10-02 | Group Anagrams | [problem](https://leetcode.com/problems/group-anagrams/) | [GroupAnagrams.java](DSA/01-Arrays/GroupAnagrams.java) | 6465cfc |
| 2026-10-02 | Top K Frequent Elements | [problem](https://leetcode.com/problems/top-k-frequent-elements/) | [TopKFrequent.java](DSA/01-Arrays/TopKFrequent.java) | 0bdde05 |
| 2026-10-02 | Product of Array Except Self | [problem](https://leetcode.com/problems/product-of-array-except-self/) | [ProductExceptSelf.java](DSA/01-Arrays/ProductExceptSelf.java) | 70d937b |
| 2026-10-02 | Longest Consecutive Sequence | [problem](https://leetcode.com/problems/longest-consecutive-sequence/) | [LongestConsecutive.java](DSA/01-Arrays/LongestConsecutive.java) | ba58b42 |
| 2026-10-03 | Valid Palindrome | [problem](https://leetcode.com/problems/valid-palindrome/) | [ValidPalindrome.java](DSA/01-Arrays/ValidPalindrome.java) | 05729ed |
| 2026-10-03 | Two Sum II - Input Array Is Sorted | [problem](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | [TwoSumII.java](DSA/01-Arrays/TwoSumII.java) | f3f1885 |
| 2026-10-04 | 3Sum | [problem](https://leetcode.com/problems/3sum/) | [ThreeSum.java](DSA/01-Arrays/ThreeSum.java) | 811dd08 |
| 2026-10-04 | Best Time to Buy and Sell Stock | [problem](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | [BestTimeToBuyAndSellStock.java](DSA/01-Arrays/BestTimeToBuyAndSellStock.java) | d572f52 |
| 2026-10-04 | Longest Substring Without Repeating Characters | [problem](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | [LongestSubstringWithoutRepeatingCharacters.java](DSA/01-Arrays/LongestSubstringWithoutRepeatingCharacters.java) | 7233f89 |
| 2026-10-04 | Minimum Size Subarray Sum | [problem](https://leetcode.com/problems/minimum-size-subarray-sum/) | [MinimumSizeSubarraySum.java](DSA/01-Arrays/MinimumSizeSubarraySum.java) | 6c79e60 |
| 2026-10-04 | Maximum Average Subarray I | [problem](https://leetcode.com/problems/maximum-average-subarray-i/) | [MaximumAverageSubarrayI.java](DSA/01-Arrays/MaximumAverageSubarrayI.java) | 39d5a3d |
| 2026-10-04 | Permutation in String | [problem](https://leetcode.com/problems/permutation-in-string/) | [PermutationInString.java](DSA/01-Arrays/PermutationInString.java) | ba58899 |
| 2026-10-04 | Longest Repeating Character Replacement | [problem](https://leetcode.com/problems/longest-repeating-character-replacement/) | [LongestRepeatingCharacterReplacement.java](DSA/01-Arrays/LongestRepeatingCharacterReplacement.java) | 62cd02a |
| 2026-10-04 | Container With Most Water | [problem](https://leetcode.com/problems/container-with-most-water/) | [ContainerWithMostWater.java](DSA/01-Arrays/ContainerWithMostWater.java) | 359013e |
| 2026-10-05 | Trapping Rain Water | [problem](https://leetcode.com/problems/trapping-rain-water/) | [TrappingRainWater.java](DSA/01-Arrays/TrappingRainWater.java) | da0f5f8 |
| 2026-10-05 | Subarray Sum Equals K | [problem](https://leetcode.com/problems/subarray-sum-equals-k/) | [SubarraySumEqualsK.java](DSA/01-Arrays/SubarraySumEqualsK.java) | fdda08c |
| 2026-10-05 | Maximum Product Subarray | [problem](https://leetcode.com/problems/maximum-product-subarray/) | [MaximumProductSubarray.java](DSA/01-Arrays/MaximumProductSubarray.java) | c744550 |
| 2026-10-05 | Maximum Subarray | [problem](https://leetcode.com/problems/maximum-subarray/) | [MaximumSubarray.java](DSA/01-Arrays/MaximumSubarray.java) | d8d66db |
| 2026-10-05 | Subarray Product Less Than K | [problem](https://leetcode.com/problems/subarray-product-less-than-k/) | [SubarrayProductLessThanK.java](DSA/01-Arrays/SubarrayProductLessThanK.java) | 5a2c7bc |
| 2026-10-06 | Subarrays Divisible by K | [problem](https://leetcode.com/problems/subarray-sums-divisible-by-k/) | [SubarraysDivByK.java](DSA/01-Arrays/SubarraysDivByK.java) | 896423a |
| 2026-10-06 | Longest Increasing Subsequence | [problem](https://leetcode.com/problems/longest-increasing-subsequence/) | [LongestIncreasingSubsequence.java](DSA/01-Arrays/LongestIncreasingSubsequence.java) | c9aac12 |

### Linked Lists — 15

| Solution commit date | Problem | LeetCode | Repository solution | Commit |
|---|---|---|---|---|
| 2026-10-07 | Reverse Linked List | [problem](https://leetcode.com/problems/reverse-linked-list/) | [ReverseLinkedList.java](DSA/02-LinkedLists/ReverseLinkedList.java) | 3c7ddf8 |
| 2026-10-07 | Merge Two Sorted Lists | [problem](https://leetcode.com/problems/merge-two-sorted-lists/) | [MergeTwoSortedLists.java](DSA/02-LinkedLists/MergeTwoSortedLists.java) | c64e219 |
| 2026-10-07 | Linked List Cycle | [problem](https://leetcode.com/problems/linked-list-cycle/) | [LinkedListCycle.java](DSA/02-LinkedLists/LinkedListCycle.java) | 3ffa7e4 |
| 2026-10-08 | Remove Nth Node From End of List | [problem](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | [RemoveNthNodeFromEnd.java](DSA/02-LinkedLists/RemoveNthNodeFromEnd.java) | c75822c |
| 2026-10-08 | Middle of the Linked List | [problem](https://leetcode.com/problems/middle-of-the-linked-list/) | [MiddleOfTheLinkedList.java](DSA/02-LinkedLists/MiddleOfTheLinkedList.java) | 690be64 |
| 2026-10-08 | Palindrome Linked List | [problem](https://leetcode.com/problems/palindrome-linked-list/) | [PalindromeLinkedList.java](DSA/02-LinkedLists/PalindromeLinkedList.java) | ce1cb9d |
| 2026-10-08 | Reorder List | [problem](https://leetcode.com/problems/reorder-list/) | [ReorderList.java](DSA/02-LinkedLists/ReorderList.java) | 1b19e46 |
| 2026-10-09 | Copy List with Random Pointer | [problem](https://leetcode.com/problems/copy-list-with-random-pointer/) | [CopyListWithRandomPointer.java](DSA/02-LinkedLists/CopyListWithRandomPointer.java) | 885072c |
| 2026-10-09 | Add Two Numbers | [problem](https://leetcode.com/problems/add-two-numbers/) | [AddTwoNumbers.java](DSA/02-LinkedLists/AddTwoNumbers.java) | 64a4268 |
| 2026-10-09 | Intersection of Two Linked Lists | [problem](https://leetcode.com/problems/intersection-of-two-linked-lists/) | [IntersectionOfTwoLinkedLists.java](DSA/02-LinkedLists/IntersectionOfTwoLinkedLists.java) | f4073ed |
| 2026-10-10 | Reverse Linked List II | [problem](https://leetcode.com/problems/reverse-linked-list-ii/) | [ReverseLinkedListII.java](DSA/02-LinkedLists/ReverseLinkedListII.java) | 395c4ac |
| 2026-10-11 | Swap Nodes in Pairs | [problem](https://leetcode.com/problems/swap-nodes-in-pairs/) | [SwapNodesInPairs.java](DSA/02-LinkedLists/SwapNodesInPairs.java) | 9b708a0 |
| 2026-10-11 | Reverse Nodes in k-Group | [problem](https://leetcode.com/problems/reverse-nodes-in-k-group/) | [ReverseNodesInKGroup.java](DSA/02-LinkedLists/ReverseNodesInKGroup.java) | 0cb019c |
| 2026-10-11 | Rotate List | [problem](https://leetcode.com/problems/rotate-list/) | [RotateList.java](DSA/02-LinkedLists/RotateList.java) | 3376646 |
| 2026-10-11 | Partition List | [problem](https://leetcode.com/problems/partition-list/) | [PartitionList.java](DSA/02-LinkedLists/PartitionList.java) | 9b7d3c0 |

### Stack & Queue — 22

| Solution commit date | Problem | LeetCode | Repository solution | Commit |
|---|---|---|---|---|
| 2026-10-13 | Valid Parentheses | [problem](https://leetcode.com/problems/valid-parentheses/) | [ValidParentheses.java](DSA/03-StackQueue/ValidParentheses.java) | b18b275 |
| 2026-10-13 | Next Greater Element I | [problem](https://leetcode.com/problems/next-greater-element-i/) | [NextGreaterElementI.java](DSA/03-StackQueue/NextGreaterElementI.java) | d0f6482 |
| 2026-10-13 | Daily Temperatures | [problem](https://leetcode.com/problems/daily-temperatures/) | [DailyTemperatures.java](DSA/03-StackQueue/DailyTemperatures.java) | 3adfa0b |
| 2026-10-14 | Final Prices With a Special Discount in a Shop | [problem](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | [FinalPricesWithSpecialDiscount.java](DSA/03-StackQueue/FinalPricesWithSpecialDiscount.java) | 1c25f9b |
| 2026-10-14 | Online Stock Span | [problem](https://leetcode.com/problems/online-stock-span/) | [StockSpanner.java](DSA/03-StackQueue/StockSpanner.java) | 25d749d |
| 2026-10-14 | Largest Rectangle In Histogram | [problem](https://leetcode.com/problems/largest-rectangle-in-histogram/) | [LargestRectangleInHistogram.java](DSA/03-StackQueue/LargestRectangleInHistogram.java) | c9f8584 |
| 2026-10-14 | Trapping Rain Water | [problem](https://leetcode.com/problems/trapping-rain-water/) | [TrappingRainWaterStack.java](DSA/03-StackQueue/TrappingRainWaterStack.java) | b0f3093 |
| 2026-10-16 | Implement Queue using Stacks | [problem](https://leetcode.com/problems/implement-queue-using-stacks/) | [MyQueue.java](DSA/03-StackQueue/MyQueue.java) | ba6ae75 |
| 2026-10-17 | Number of Recent Calls | [problem](https://leetcode.com/problems/number-of-recent-calls/) | [RecentCounter.java](DSA/03-StackQueue/RecentCounter.java) | 5134206 |
| 2026-10-18 | Design Circular Queue | [problem](https://leetcode.com/problems/design-circular-queue/) | [MyCircularQueue.java](DSA/03-StackQueue/MyCircularQueue.java) | f2eebd1 |
| 2026-10-19 | Implement Stack using Queues | [problem](https://leetcode.com/problems/implement-stack-using-queues/) | [MyStack.java](DSA/03-StackQueue/MyStack.java) | f0032c1 |
| 2026-10-20 | Evaluate Reverse Polish Notation | [problem](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | [EvaluateReversePolishNotation.java](DSA/03-StackQueue/EvaluateReversePolishNotation.java) | 2d61e72 |
| 2026-10-20 | Simplify Path | [problem](https://leetcode.com/problems/simplify-path/) | [SimplifyPath.java](DSA/03-StackQueue/SimplifyPath.java) | 5280703 |
| 2026-10-20 | Asteroid Collision | [problem](https://leetcode.com/problems/asteroid-collision/) | [AsteroidCollision.java](DSA/03-StackQueue/AsteroidCollision.java) | 97f68a5 |
| 2026-10-21 | Removing Stars From a String | [problem](https://leetcode.com/problems/removing-stars-from-a-string/) | [RemovingStarsFromString.java](DSA/03-StackQueue/RemovingStarsFromString.java) | d9a1cb3 |
| 2026-10-21 | Make The String Great | [problem](https://leetcode.com/problems/make-the-string-great/) | [MakeTheStringGreat.java](DSA/03-StackQueue/MakeTheStringGreat.java) | 959fcb5 |
| 2026-10-22 | Backspace String Compare | [problem](https://leetcode.com/problems/backspace-string-compare/) | [BackspaceStringCompare.java](DSA/03-StackQueue/BackspaceStringCompare.java) | 7a8dcc8 |
| 2026-10-23 | Decode String | [problem](https://leetcode.com/problems/decode-string/) | [DecodeString.java](DSA/03-StackQueue/DecodeString.java) | 76dad77 |
| 2026-10-23 | Remove K Digits | [problem](https://leetcode.com/problems/remove-k-digits/) | [RemoveKDigits.java](DSA/03-StackQueue/RemoveKDigits.java) | c7ad92a |
| 2026-10-24 | Remove Duplicate Letters | [problem](https://leetcode.com/problems/remove-duplicate-letters/) | [RemoveDuplicateLetters.java](DSA/03-StackQueue/RemoveDuplicateLetters.java) | 620c9c6 |
| 2026-10-24 | Remove All Adjacent Duplicates in String II | [problem](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) | [RemoveAllAdjacentDuplicatesInStringII.java](DSA/03-StackQueue/RemoveAllAdjacentDuplicatesInStringII.java) | 1777816 |
| 2026-10-26 | Next Greater Element II | [problem](https://leetcode.com/problems/next-greater-element-ii/) | [NextGreaterElementII.java](DSA/03-StackQueue/NextGreaterElementII.java) | 317f7ba |

### Design — 1

| Solution commit date | Problem | LeetCode | Repository solution | Commit |
|---|---|---|---|---|
| 2026-10-12 | LRU Cache | [problem](https://leetcode.com/problems/lru-cache/) | [LRUCache.java](DSA/DSN-Design/LRUCache.java) | 423a910 |
