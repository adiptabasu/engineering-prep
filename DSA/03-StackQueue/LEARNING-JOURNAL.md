# Stack & Queue learning journal

## Learning note — 2026-10-25

The learner reports that monotonic stack questions initially felt overwhelming. They stepped back to simpler stack and queue concepts, then returned to stack questions. The current goal is to build the stack concepts steadily and independently answer several questions for a pattern before moving to the next pattern or logic.

**Next step:** Continue with the current stack pattern. Try questions from a fresh prompt before opening the solution, use simpler examples or hints to clarify the invariant when needed, and explain the approach and complexity. Move on only after independently solving and explaining several representative questions.

This entry is based on the learner's report in conversation. Git history provides a sequence of implementation commits; it does not establish unaided solving, LeetCode acceptance, or retained understanding.

## Learning note — 2026-10-26

For [Next Greater Element II](NextGreaterElementII.java), the learner proposed scanning the circular array twice with a stack. After discussion, they corrected the virtual scan bounds and changed the pop condition to remove equal values as well as smaller values, matching the strictly-greater requirement. The learner reports that the solution was accepted on LeetCode. Independent retention has not yet been checked.

## Learning note — 2026-10-27

For [132 Pattern](Find132Pattern.java), the learner first explored storing low/high pairs and then considered left minima with right-side maxima. Through hints and examples, they identified that suffix extrema lose the order and range information the pattern needs. The learner then asked for the right-to-left monotonic-stack invariant to be explained, implemented that approach, and reports that LeetCode accepted the submission. The learner's goal is to derive the logic independently from the problem, not only reproduce the implementation from memory; this derivation has not yet been demonstrated.

## Learning note — 2026-10-27

For [Find the Most Competitive Subsequence](MostCompetitiveSubsequence.java), the learner understood that the result must use small values while preserving order and reasoned about which early positions leave enough values to complete the subsequence. Hints did not make the full logic clear, so the learner used a YouTube explanation, implemented the monotonic-stack greedy approach, and debugged the code. Review caught a double decrement in the final removal loop; the learner corrected it and reports that LeetCode accepted the solution. The learner reports knowing how to use a monotonic stack once the approach is known, while deriving when and how to use it does not yet come naturally.

**Revisit:** Practice recognizing when a monotonic stack fits and deriving its invariant from the problem constraints. For this problem, explain why a larger stack value can be removed only while the deletion budget remains, and why leftover removals come from the end.

## Learning note — 2026-10-27

For [Maximum Width Ramp](MaximumWidthRamp.java), the learner first described the O(n²) pair-checking approach. With guidance, they recognized that an earlier index with a lower or equal value dominates a later starting index, then built a stack of prefix-minimum candidates and scanned possible right endpoints from right to left. They needed help correcting the candidate-stack construction and changing the endpoint check from a single `if` to a `while`. The learner reports that the submitted solution was accepted and says they needed help developing the optimized code.

**Revisit:** Derive the dominance rule for candidate starts and why scanning endpoints from right to left lets each matched start be discarded.

## Problem implementation history

The learner reports having solved the questions listed here. The repository contains a Java implementation for each. Dates and short hashes below come from the commit that added each solution file; they record Git activity and may not be the exact study date. A committed implementation is not, by itself, evidence of independent mastery.

### Stack & Queue — 25

| Solution commit date | Problem | LeetCode | Repository solution | Commit |
|---|---|---|---|---|
| 2026-10-13 | Valid Parentheses | [problem](https://leetcode.com/problems/valid-parentheses/) | [ValidParentheses.java](ValidParentheses.java) | b18b275 |
| 2026-10-13 | Next Greater Element I | [problem](https://leetcode.com/problems/next-greater-element-i/) | [NextGreaterElementI.java](NextGreaterElementI.java) | d0f6482 |
| 2026-10-13 | Daily Temperatures | [problem](https://leetcode.com/problems/daily-temperatures/) | [DailyTemperatures.java](DailyTemperatures.java) | 3adfa0b |
| 2026-10-14 | Final Prices With a Special Discount in a Shop | [problem](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) | [FinalPricesWithSpecialDiscount.java](FinalPricesWithSpecialDiscount.java) | 1c25f9b |
| 2026-10-14 | Online Stock Span | [problem](https://leetcode.com/problems/online-stock-span/) | [StockSpanner.java](StockSpanner.java) | 25d749d |
| 2026-10-14 | Largest Rectangle In Histogram | [problem](https://leetcode.com/problems/largest-rectangle-in-histogram/) | [LargestRectangleInHistogram.java](LargestRectangleInHistogram.java) | c9f8584 |
| 2026-10-14 | Trapping Rain Water | [problem](https://leetcode.com/problems/trapping-rain-water/) | [TrappingRainWaterStack.java](TrappingRainWaterStack.java) | b0f3093 |
| 2026-10-16 | Implement Queue using Stacks | [problem](https://leetcode.com/problems/implement-queue-using-stacks/) | [MyQueue.java](MyQueue.java) | ba6ae75 |
| 2026-10-17 | Number of Recent Calls | [problem](https://leetcode.com/problems/number-of-recent-calls/) | [RecentCounter.java](RecentCounter.java) | 5134206 |
| 2026-10-18 | Design Circular Queue | [problem](https://leetcode.com/problems/design-circular-queue/) | [MyCircularQueue.java](MyCircularQueue.java) | f2eebd1 |
| 2026-10-19 | Implement Stack using Queues | [problem](https://leetcode.com/problems/implement-stack-using-queues/) | [MyStack.java](MyStack.java) | f0032c1 |
| 2026-10-20 | Evaluate Reverse Polish Notation | [problem](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | [EvaluateReversePolishNotation.java](EvaluateReversePolishNotation.java) | 2d61e72 |
| 2026-10-20 | Simplify Path | [problem](https://leetcode.com/problems/simplify-path/) | [SimplifyPath.java](SimplifyPath.java) | 5280703 |
| 2026-10-20 | Asteroid Collision | [problem](https://leetcode.com/problems/asteroid-collision/) | [AsteroidCollision.java](AsteroidCollision.java) | 97f68a5 |
| 2026-10-21 | Removing Stars From a String | [problem](https://leetcode.com/problems/removing-stars-from-a-string/) | [RemovingStarsFromString.java](RemovingStarsFromString.java) | d9a1cb3 |
| 2026-10-21 | Make The String Great | [problem](https://leetcode.com/problems/make-the-string-great/) | [MakeTheStringGreat.java](MakeTheStringGreat.java) | 959fcb5 |
| 2026-10-22 | Backspace String Compare | [problem](https://leetcode.com/problems/backspace-string-compare/) | [BackspaceStringCompare.java](BackspaceStringCompare.java) | 7a8dcc8 |
| 2026-10-23 | Decode String | [problem](https://leetcode.com/problems/decode-string/) | [DecodeString.java](DecodeString.java) | 76dad77 |
| 2026-10-23 | Remove K Digits | [problem](https://leetcode.com/problems/remove-k-digits/) | [RemoveKDigits.java](RemoveKDigits.java) | c7ad92a |
| 2026-10-24 | Remove Duplicate Letters | [problem](https://leetcode.com/problems/remove-duplicate-letters/) | [RemoveDuplicateLetters.java](RemoveDuplicateLetters.java) | 620c9c6 |
| 2026-10-24 | Remove All Adjacent Duplicates in String II | [problem](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) | [RemoveAllAdjacentDuplicatesInStringII.java](RemoveAllAdjacentDuplicatesInStringII.java) | 1777816 |
| 2026-10-26 | Next Greater Element II | [problem](https://leetcode.com/problems/next-greater-element-ii/) | [NextGreaterElementII.java](NextGreaterElementII.java) | 317f7ba |
| 2026-10-27 | 132 Pattern | [problem](https://leetcode.com/problems/132-pattern/) | [Find132Pattern.java](Find132Pattern.java) | 8e398e3 |
| 2026-10-27 | Find the Most Competitive Subsequence | [problem](https://leetcode.com/problems/find-the-most-competitive-subsequence/) | [MostCompetitiveSubsequence.java](MostCompetitiveSubsequence.java) | ef1a974 |
| 2026-10-27 | Maximum Width Ramp | [problem](https://leetcode.com/problems/maximum-width-ramp/) | [MaximumWidthRamp.java](MaximumWidthRamp.java) | 3ad79e2 |
