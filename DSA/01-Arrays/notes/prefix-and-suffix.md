# Prefix and suffix aggregates

## The pattern

Some answers at index i need the aggregate of all values before i and all values after i. Compute those two sides independently, then combine them. This avoids recomputing a product or other aggregate for every position.

An identity value lets the same method handle the boundaries. For multiplication, the identity is 1; for addition, it is 0.

    result = output array of length n
    leftAggregate = identity
    for i from 0 through n - 1:
        result[i] = leftAggregate
        leftAggregate = combine(leftAggregate, values[i])

    rightAggregate = identity
    for i from n - 1 down through 0:
        result[i] = combine(result[i], rightAggregate)
        rightAggregate = combine(values[i], rightAggregate)

For Product of Array Except Self, combine is multiplication. The left pass stores the product strictly before i; the right pass multiplies in the product strictly after i. No division is needed, so zero values are handled naturally.

## Why the two passes produce the answer

Before the left pass reaches index i, leftAggregate contains exactly the aggregate of values before i. Store that value in result[i], then include values[i] for the next position. The right pass does the mirror image: before it reaches i, rightAggregate contains exactly the aggregate after i. Combining the two gives every value except values[i].

For products [1, 2, 3], the left pass stores [1, 1, 2]. The right pass multiplies in [6, 3, 1], producing [6, 3, 2]. A zero causes no special case: products on either side naturally become zero, and no division by zero is needed.

The invariant is about which indices each running aggregate covers. Keeping that boundary explicit prevents accidentally including the current value in either side.
## Complexity

The two passes take O(n) time. The output uses O(n) space; aside from that output, the algorithm uses O(1) extra state.

## When the order matters

The example assumes the aggregate can be combined in the desired order. Addition and multiplication are commutative, but not every operation is. For non-commutative operations, preserve the original sequence order when combining left and right aggregates.

## Practice link

[Product of Array Except Self](../ProductExceptSelf.java)
