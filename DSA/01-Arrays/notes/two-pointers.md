# Two pointers

## When this pattern helps

Two pointers are useful when the problem has order or structure that lets the algorithm discard candidates safely. A common form starts at both ends of a sorted sequence. Another form moves two indices through a sequence while maintaining a relationship between them.

## Opposite-end pointers on sorted data

For a sorted array and a target sum, the sum at the two ends tells which side can no longer be part of a solution.

    left = 0
    right = length(values) - 1
    while left < right:
        current = values[left] + values[right]
        if current equals target:
            return the pair
        if current < target:
            left = left + 1
        else:
            right = right - 1

If the current sum is too small, moving the right pointer left would only make it smaller. The left value must increase. The symmetric argument applies when the sum is too large. This reasoning depends on sorted order.

For 3Sum, sort, fix one position, and use the two-pointer search on the remaining suffix. Skip repeated fixed values and repeated matches when unique triplets are required.

## Comparing from both ends

For palindrome-style checks, move inward while skipping characters the problem says to ignore. Compare the next relevant pair; stop at the first mismatch. Be precise about whether the middle element needs comparison.

## Boundary optimization

In Container With Most Water, the shorter boundary limits the area. Moving the taller boundary inward reduces width without increasing the limiting height, so it cannot improve the current area. Move the shorter boundary and continue.

Trapping Rain Water has a different invariant: water at a position is limited by the smaller of the highest boundary seen from each side. A two-pointer solution processes the side whose known maximum is lower, because the opposite side already provides a boundary at least that high.

## Complexity and checks

Two pointers often reduce a scan from O(n²) to O(n), after any sorting cost. Check that pointer movement discards only candidates that cannot form a better or valid answer. If the input is not sorted, do not use the sorted pair-sum rule without first establishing another valid ordering property.

## Practice links

[Valid Palindrome](../ValidPalindrome.java) · [Two Sum II](../TwoSumII.java) · [3Sum](../ThreeSum.java) · [Container With Most Water](../ContainerWithMostWater.java) · [Trapping Rain Water](../TrappingRainWater.java)
