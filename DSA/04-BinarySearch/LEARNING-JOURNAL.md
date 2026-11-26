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

## Learning note — 2026-11-19

For [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/), the learner used `nums[low] <= nums[mid]` to identify a sorted left segment, recorded its first value, and continued right; otherwise they recorded `nums[mid]` and continued left. Code review found this candidate-tracking approach correct for the problem's non-empty, distinct-value input. We also discussed a shorter range-only approach based on comparing `nums[mid]` with `nums[high]`; both approaches are `O(log n)`. The learner reports that the original submission was accepted.

**Revisit:** Explain why the tracked minimum must survive after its index is removed from the active interval, and derive the range-only update when `nums[mid]` is greater than `nums[high]`.

## Learning note — 2026-11-20

For [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/), the learner aligned `mid` to an even index, compared that index with its neighbor, skipped an intact pair to the right, and kept `mid` as a candidate after a mismatch. Code review found the approach correct. The learner reports that LeetCode accepted the submission.

**Revisit:** Explain why pair starts are even before the singleton and odd after it, and how the mismatch tells the search which half still contains the singleton.

## Learning note — 2026-11-20

For [Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/), the learner adapted candidate tracking to duplicate values. When `nums[low]`, `nums[mid]`, and `nums[high]` are equal, the code compares both endpoints with the best minimum candidate before shrinking both ends; otherwise it narrows using the sorted-left-segment check. The learner reports that LeetCode accepted the submission.

**Revisit:** Explain why duplicate endpoints can hide which side contains the rotation, why both endpoints must be considered before shrinking, and why this ambiguity can make the worst-case runtime O(n).

## Learning note — 2026-11-21

For [Find Peak Element](https://leetcode.com/problems/find-peak-element/), the learner checked the endpoints first, then searched the interior by comparing a midpoint with its neighbors and following the rising or falling slope. Code review caught and corrected the right-endpoint return index (`high` rather than `low`). The learner reports that LeetCode accepted the solution.

**Revisit:** Explain why either slope direction guarantees a peak in the retained half, and why the endpoint checks make neighbor accesses safe in the interior search.

## Learning note — 2026-11-23

For [Sqrt(x)](https://leetcode.com/problems/sqrtx/), the learner used an inclusive binary search over possible roots and saved the largest candidate whose square is at most `x`. Code review caught that the multiplication must be widened before it happens, using `(long) mid * mid`; the learner reports that the corrected submission was accepted.

**Revisit:** Explain why valid roots form a prefix of the candidate range, why a valid midpoint should be saved before searching right, and why the cast must happen before multiplication.

## Learning note — 2026-11-24

For [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/), the learner used an inclusive binary search over eating speeds and a helper to count the hours required. Discussion corrected the lower bound to speed `1` and changed the per-pile time from floor division to integer ceiling division; the learner reports the revised submission was accepted.

**Revisit:** Explain why feasible speeds form a suffix, why the maximum pile is a feasible upper bound under `h >= piles.length`, and how remainder-based ceiling division counts a partially eaten pile.

## Learning note — 2026-11-24

For [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/), the learner binary-searched days and checked feasibility by counting bouquets within consecutive runs of bloomed flowers. Discussion corrected the `m * k` overflow risk by widening before multiplication and clarified that a feasible day must be saved before searching earlier. The learner reports that the submission was accepted.

**Revisit:** Explain why bouquets cannot cross an unbloomed flower, why the feasible days form a suffix, and why `(long) m * k` must widen before multiplying.

## Learning note — 2026-11-24

For [Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/), the learner binary-searched divisors and checked feasibility by summing rounded-up quotients. Discussion corrected the lower bound to `1` and the midpoint expression to use `high - low`; the learner reports the corrected submission was accepted.

**Revisit:** Explain why the divisor sum is monotone non-increasing, why each quotient must round up, and why a feasible divisor should be saved before searching left.

## Learning note — 2026-11-25

For [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/), the learner binary-searched the ship capacity and greedily packed packages in their original order. Discussion corrected the feasibility comparison to compare days used with `days` and derived direct bounds: the heaviest package is the minimum possible capacity and the total weight is a guaranteed feasible capacity. The learner reports that the submission was accepted. The saved reference also keeps an expanding-upper-bound variant for comparison.

**Revisit:** Explain why greedy packing minimizes days for a fixed capacity, why feasible capacities form a suffix, and why the sum of weights guarantees a feasible upper bound.

## Learning note — 2026-11-25

For [Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/), the learner binary-searched the first index where `arr[i] - (i + 1)` reaches `k`. Discussion corrected the missing-count formula. The learner's submitted return expression `high + 1 + k` is equivalent to `low + k`, because an inclusive search exits with `low == high + 1`. The learner shared a LeetCode submission; acceptance status was not stated.

**Revisit:** Derive the missing count from the number of positive integers through `arr[i]`, then explain why the two return expressions match at loop exit.

## Learning note — 2026-11-26

For [Aggressive Cows](https://www.geeksforgeeks.org/problems/aggressive-cows/1), the learner sorted the stalls, binary-searched a candidate minimum gap, and greedily placed each next cow at the earliest stall far enough from the previous cow. Discussion corrected the upper bound from an array-index distance to the physical stall span and the return value to the largest feasible gap. The learner reports that the submission was accepted.

**Revisit:** Explain why feasible distances form a prefix, why earliest-available placement can fit at least as many cows as any other placement for a fixed distance, and why the search upper bound is `last stall - first stall`.

## Problem implementation history

| Study date | Problem | Problem link | Repository solution | Result |
|---|---|---|---|---|
| 2026-11-17 | Binary Search | [problem](https://leetcode.com/problems/binary-search/) | [BinarySearch.java](BinarySearch.java) | Learner reports accepted |
| 2026-11-17 | Search Insert Position | [problem](https://leetcode.com/problems/search-insert-position/) | [SearchInsertPosition.java](SearchInsertPosition.java) | Learner reports accepted |
| 2026-11-17 | Find First and Last Position of Element in Sorted Array | [problem](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [FindFirstAndLastPosition.java](FindFirstAndLastPosition.java) | Learner reports accepted |
| 2026-11-17 | Search in Rotated Sorted Array | [problem](https://leetcode.com/problems/search-in-rotated-sorted-array/) | [SearchInRotatedSortedArray.java](SearchInRotatedSortedArray.java) | Learner reports accepted |
| 2026-11-18 | Search in Rotated Sorted Array II | [problem](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | [SearchInRotatedSortedArrayII.java](SearchInRotatedSortedArrayII.java) | Learner reports accepted |
| 2026-11-19 | Find Minimum in Rotated Sorted Array | [problem](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | [FindMinimumInRotatedSortedArray.java](FindMinimumInRotatedSortedArray.java) | Learner reports accepted |
| 2026-11-20 | Single Element in a Sorted Array | [problem](https://leetcode.com/problems/single-element-in-a-sorted-array/) | [SingleElementInSortedArray.java](SingleElementInSortedArray.java) | Learner reports accepted |
| 2026-11-20 | Find Minimum in Rotated Sorted Array II | [problem](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/) | [FindMinimumInRotatedSortedArrayII.java](FindMinimumInRotatedSortedArrayII.java) | Learner reports accepted |
| 2026-11-21 | Find Peak Element | [problem](https://leetcode.com/problems/find-peak-element/) | [FindPeakElement.java](FindPeakElement.java) | Learner reports accepted |
| 2026-11-23 | Sqrt(x) | [problem](https://leetcode.com/problems/sqrtx/) | [SqrtX.java](SqrtX.java) | Learner reports accepted |
| 2026-11-24 | Koko Eating Bananas | [problem](https://leetcode.com/problems/koko-eating-bananas/) | [KokoEatingBananas.java](KokoEatingBananas.java) | Learner reports accepted |
| 2026-11-24 | Minimum Number of Days to Make m Bouquets | [problem](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/) | [MinimumNumberOfDaysToMakeMBouquets.java](MinimumNumberOfDaysToMakeMBouquets.java) | Learner reports accepted |
| 2026-11-24 | Find the Smallest Divisor Given a Threshold | [problem](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/) | [SmallestDivisorGivenAThreshold.java](SmallestDivisorGivenAThreshold.java) | Learner reports accepted |
| 2026-11-25 | Capacity To Ship Packages Within D Days | [problem](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) | [CapacityToShipPackagesWithinDDays.java](CapacityToShipPackagesWithinDDays.java) | Learner reports accepted |
| 2026-11-25 | Kth Missing Positive Number | [problem](https://leetcode.com/problems/kth-missing-positive-number/) | [KthMissingPositiveNumber.java](KthMissingPositiveNumber.java) | Submitted; acceptance not stated |
| 2026-11-26 | Aggressive Cows | [GeeksforGeeks](https://www.geeksforgeeks.org/problems/aggressive-cows/1) | [AggressiveCows.java](AggressiveCows.java) | Learner reports accepted |
