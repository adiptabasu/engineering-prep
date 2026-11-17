# Binary Search learning journal

## Learning note — 2026-11-17

For [Binary Search](https://leetcode.com/problems/binary-search/), the learner independently implemented iterative binary search with an inclusive `[low, high]` candidate range, a difference-based midpoint, and `mid + 1` / `mid - 1` updates. In discussion, they explained why sorted order rules out the left half when the target is greater than the midpoint, traced the range through `[2, 5, 8, 12, 16, 23, 38]`, and identified that the loop should continue while `low <= high`. The learner reports that the submission was accepted on LeetCode.

**Revisit:** Explain the inclusive-range invariant and why the checked midpoint must be excluded after each comparison. Try a target that is absent and a target at each endpoint.

## Problem implementation history

| Study date | Problem | LeetCode | Repository solution | Result |
|---|---|---|---|---|
| 2026-11-17 | Binary Search | [problem](https://leetcode.com/problems/binary-search/) | [BinarySearch.java](BinarySearch.java) | Learner reports accepted |
