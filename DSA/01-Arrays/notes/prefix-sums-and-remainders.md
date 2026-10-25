# Prefix sums and remainders

## Prefix sums

Define prefix[t] as the sum of the first t values. This exclusive convention uses prefix[0] = 0. Then the sum from index left through right is:

    rangeSum(left, right) = prefix[right + 1] - prefix[left]

A prefix array supports range-sum queries in O(1) after O(n) preprocessing. A running prefix can also count subarrays with a target sum.

## Counting subarrays with sum k

For a current prefix sum p, an earlier prefix q forms a range of sum k exactly when q = p - k. Store counts of earlier prefix sums.

    count = map with count[0] = 1
    prefix = 0
    answer = 0
    for each value x:
        prefix = prefix + x
        answer = answer + count[prefix - k]
        count[prefix] = count[prefix] + 1

The initial zero prefix accounts for ranges that begin at index zero. Query the needed earlier prefix before adding the current prefix, so a range is non-empty unless the problem says otherwise. This method works with negative values; a standard variable sliding window does not.

Expected time and space are O(n).

## Prefix remainders

Two prefix sums with the same remainder modulo k differ by a multiple of k. To count divisible ranges, keep how often each remainder has appeared. A remainder of zero must be counted before scanning begins.

For languages where remainder can be negative, normalize it into the range from zero through k minus one:

    remainder = ((value mod k) + k) mod k

Assume k is positive, or normalize the divisor according to the problem definition. Handle zero and negative divisors explicitly if the problem permits them.

## Common pitfalls

- Mixing inclusive and exclusive prefix definitions.
- Forgetting the initial empty prefix.
- Using a sliding window for exact-sum counting when negative values are allowed.
- Forgetting negative-remainder normalization.
- Confusing a prefix value with the frequency of that value.

## Practice links

[Subarray Sum Equals K](../SubarraySumEqualsK.java) · [Subarrays Divisible by K](../SubarraysDivByK.java)
