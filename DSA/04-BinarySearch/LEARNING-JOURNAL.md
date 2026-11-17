# Binary Search learning journal

## Learning note — 2026-11-17

For [Binary Search](https://leetcode.com/problems/binary-search/), the learner independently implemented iterative binary search with an inclusive `[low, high]` candidate range, a difference-based midpoint, and `mid + 1` / `mid - 1` updates. In discussion, they explained why sorted order rules out the left half when the target is greater than the midpoint, traced the range through `[2, 5, 8, 12, 16, 23, 38]`, and identified that the loop should continue while `low <= high`. The learner reports that the submission was accepted on LeetCode.

**Revisit:** Explain the inclusive-range invariant and why the checked midpoint must be excluded after each comparison. Try a target that is absent and a target at each endpoint.

## Learning note — 2026-11-17

For [Search Insert Position](https://leetcode.com/problems/search-insert-position/), the learner shared an inclusive binary search that saves an index whenever `nums[mid] >= target`, then continues left to find the first qualifying position. The learner reports that the LeetCode submission was accepted. We discussed why the saved midpoint may not be the earliest candidate and how changing the condition to `nums[mid] > target` gives the upper-bound rule. The learner correctly identified that comparison change. The reusable lower-bound and upper-bound implementations are saved separately in [BinarySearchBounds.java](BinarySearchBounds.java) as reference helpers; they are not recorded as additional solved problems.

**Revisit:** Explain why lower bound uses `>=`, upper bound uses `>`, and why both return `nums.length` when no element qualifies. Given a sorted array with duplicates, find the number of target occurrences by subtracting the two boundaries.

## Problem implementation history

| Study date | Problem | LeetCode | Repository solution | Result |
|---|---|---|---|---|
| 2026-11-17 | Binary Search | [problem](https://leetcode.com/problems/binary-search/) | [BinarySearch.java](BinarySearch.java) | Learner reports accepted |
| 2026-11-17 | Search Insert Position | [problem](https://leetcode.com/problems/search-insert-position/) | [SearchInsertPosition.java](SearchInsertPosition.java) | Learner reports accepted |
