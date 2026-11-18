# Binary Search learning journal

## Learning note — 2026-11-17

For [Binary Search](https://leetcode.com/problems/binary-search/), the learner independently implemented iterative binary search with an inclusive `[low, high]` candidate range, a difference-based midpoint, and `mid + 1` / `mid - 1` updates. In discussion, they explained why sorted order rules out the left half when the target is greater than the midpoint, traced the range through `[2, 5, 8, 12, 16, 23, 38]`, and identified that the loop should continue while `low <= high`. The learner reports that the submission was accepted on LeetCode.

**Revisit:** Explain the inclusive-range invariant and why the checked midpoint must be excluded after each comparison. Try a target that is absent and a target at each endpoint.

## Learning note — 2026-11-17

For [Search Insert Position](https://leetcode.com/problems/search-insert-position/), the learner shared an inclusive binary search that saves an index whenever `nums[mid] >= target`, then continues left to find the first qualifying position. The learner reports that the LeetCode submission was accepted. We discussed why the saved midpoint may not be the earliest candidate and how changing the condition to `nums[mid] > target` gives the upper-bound rule. The learner correctly identified that comparison change. The reusable lower-bound and upper-bound implementations are saved separately in [BinarySearchBounds.java](BinarySearchBounds.java) as reference helpers; they are not recorded as additional solved problems.

**Revisit:** Explain why lower bound uses `>=`, upper bound uses `>`, and why both return `nums.length` when no element qualifies. Given a sorted array with duplicates, find the number of target occurrences by subtracting the two boundaries.

## Learning note — 2026-11-17

For [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/), the learner combined lower bound and upper bound: check that the lower-bound index contains the target, then return that index and `upperBound - 1`. Code review found the boundary logic and the short-circuit absent-target check correct. The learner reports that the LeetCode submission was accepted.

**Revisit:** Explain why the upper bound is one position past the final match, why subtracting one gives the last index, and why the lower-bound result must be checked before indexing the array.

## Learning note — 2026-11-17

For [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/), the learner implemented an inclusive binary search that identifies the sorted half at each midpoint and checks whether the target lies within that half's value range. Code review found the approach correct under LeetCode 33's distinct-value constraint. The learner reports that the submission was accepted.

**Revisit:** Explain why at least one half is sorted when values are distinct, how its endpoint values determine whether the target can be there, and why the duplicate variant can require a different approach.

## Learning note — 2026-11-18

For [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/), the learner extended the rotated-array search to allow duplicates. Their code checks the midpoint first, shrinks both endpoints when `nums[low] == nums[mid] == nums[high]`, then uses the sorted-half range test when possible. Code review confirmed the search logic; returning `true` immediately on a match would simplify the `found` flag and avoid an extra loop iteration. The learner reports that the LeetCode submission was accepted.

**Revisit:** Explain why equal endpoints can be removed after checking `mid`, why duplicates may force one-step shrinking and `O(n)` worst-case time, and why the distinct-value version can guarantee `O(log n)`.

## Problem implementation history

| Study date | Problem | LeetCode | Repository solution | Result |
|---|---|---|---|---|
| 2026-11-17 | Binary Search | [problem](https://leetcode.com/problems/binary-search/) | [BinarySearch.java](BinarySearch.java) | Learner reports accepted |
| 2026-11-17 | Search Insert Position | [problem](https://leetcode.com/problems/search-insert-position/) | [SearchInsertPosition.java](SearchInsertPosition.java) | Learner reports accepted |
| 2026-11-17 | Find First and Last Position of Element in Sorted Array | [problem](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [FindFirstAndLastPosition.java](FindFirstAndLastPosition.java) | Learner reports accepted |
| 2026-11-17 | Search in Rotated Sorted Array | [problem](https://leetcode.com/problems/search-in-rotated-sorted-array/) | [SearchInRotatedSortedArray.java](SearchInRotatedSortedArray.java) | Learner reports accepted |
| 2026-11-18 | Search in Rotated Sorted Array II | [problem](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | [SearchInRotatedSortedArrayII.java](SearchInRotatedSortedArrayII.java) | Learner reports accepted |
