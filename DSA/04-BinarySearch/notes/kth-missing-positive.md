# Kth Missing Positive Number

In [LeetCode 1539](https://leetcode.com/problems/kth-missing-positive-number/), the array is strictly increasing and contains positive numbers. The goal is to find the `k`th positive integer that does not appear in the array.

## Count missing values at an index

At index `i`, the value `arr[i]` tells us that there are `arr[i]` positive integers from `1` through `arr[i]`. The array has `i + 1` values in that range, so the number missing by that point is:

```java
int missing = arr[i] - (i + 1);
```

For example, for `arr = [2, 3, 4, 7, 11]`, at index `3` the value is `7`. There are four array values through that index, so `7 - 4 = 3` positive integers are missing through `7`: `1`, `5`, and `6`.

As the index moves right, the missing count never decreases. Binary-search for the first index whose missing count is at least `k`:

- If `missing < k`, the kth missing number lies farther right, so set `low = mid + 1`.
- Otherwise, this index is at or after the answer boundary, so set `high = mid - 1`.

## Two equivalent answer expressions

When an inclusive `while (low <= high)` search ends, the bounds have crossed by one position: `low == high + 1`.

Therefore either expression returns the answer:

```java
return low + k;
```

or:

```java
return high + 1 + k;
```

They are equivalent because `high + 1 == low` after the loop. For `arr = [2, 3, 4, 7, 11]` and `k = 5`, the final boundary is `low = 4`, `high = 3`; both forms return `9`.

See the [saved implementation](../KthMissingPositiveNumber.java).
