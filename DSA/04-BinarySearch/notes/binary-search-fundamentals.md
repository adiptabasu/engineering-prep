# Binary Search Fundamentals

## 1. Start with the order in the input

Binary search is useful when the search space has an order that makes comparisons informative. In the basic array version, the array must be sorted in ascending order.

For example:

```text
index:  0  1  2   3   4   5   6
value: [2, 5, 8, 12, 16, 23, 38]
```

If we look at `12` and want to find `16`, sorted order tells us that every value before `12` is also too small. We can discard that entire part of the array. If the middle value were larger than the target, we could instead discard it and everything to its right.

This is the reason binary search can be faster than checking every element one by one: a comparison eliminates about half of the remaining candidates.

## 2. Keep track of possible indices

Use two indices to describe the remaining search range:

- `low`: the smallest index that could still contain the target.
- `high`: the largest index that could still contain the target.

This note uses an **inclusive range**: both `low` and `high` are possible candidate indices. Initially, every array index is a candidate, so `low = 0` and `high = nums.length - 1`.

For the example array, the initial candidate range is indices `0` through `6`.

## 3. Check the middle candidate

Calculate the middle index of the current range:

```java
int mid = low + (high - low) / 2;
```

When `low` and `high` are nonnegative array indices and `low <= high`, `high - low` is safe in an `int`. Adding half of that difference to `low` gives an index between the bounds without first forming the potentially overflowing sum `low + high`.

Another safe option is to do the addition as a `long`, then convert the midpoint back to an `int`:

```java
int mid = (int) (((long) low + high) / 2);
```

This works because the addition happens after `low` is widened to `long`. The cast is safe here because the average of two valid `int` indices is also a valid `int` index.

Keep the plus sign in the difference-based formula. The expression `low - ((high - low) / 2)` moves to an index at or to the left of `low`, rather than finding the midpoint between `low` and `high`.

Compare `nums[mid]` with the target:

- If they are equal, the target is found at `mid`.
- If `nums[mid] < target`, the target can only be to the right. Set `low = mid + 1`.
- If `nums[mid] > target`, the target can only be to the left. Set `high = mid - 1`.

We use `mid + 1` or `mid - 1` because we have already checked `mid`; it is no longer a candidate.

## 4. Worked example

Search for `16` in `[2, 5, 8, 12, 16, 23, 38]`.

| Step | Candidate indices | Middle index and value | Decision |
|---:|---|---|---|
| 1 | `0..6` | `3`, value `12` | `12 < 16`, so set `low = 4` |
| 2 | `4..6` | `5`, value `23` | `23 > 16`, so set `high = 4` |
| 3 | `4..4` | `4`, value `16` | Found at index `4` |

The search does not move through every element. It repeatedly narrows the range using the sorted order.

## 5. Know when to stop

With an inclusive range, continue while `low <= high`:

```java
while (low <= high) {
    // inspect the middle and narrow the candidate range
}
```

If `low` becomes greater than `high`, the range is empty. Every possible index has been ruled out, so the target is absent. A common convention is to return `-1` in that case.

For an array with one remaining candidate, `low == high`, so the loop still checks that final index. This is why the inclusive form uses `<=` rather than `<`.

## 6. Basic iterative template

```java
int low = 0;
int high = nums.length - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}

return -1;
```

## 7. Complexity

If there are `n` candidates, one comparison leaves at most about `n / 2`. After another comparison, at most about `n / 4` remain. The number of halvings needed to reduce the range to zero or one is proportional to `log₂(n)`, so the running time is **O(log n)**.

The iterative version keeps only a few index variables, so it uses **O(1)** auxiliary space.

## 8. Common mistakes

- **Using binary search on data without the required ordering.** The comparison must let us rule out a whole side of the candidate range.
- **Mixing range conventions.** This template treats both `low` and `high` as candidates. Keep `low <= high` and initialize `high` to `nums.length - 1`.
- **Keeping the checked middle index.** Setting `low = mid` or `high = mid` can repeat the same midpoint forever. Move past it with `mid + 1` or `mid - 1`.
- **Using the wrong midpoint expression.** For the ascending index range, use `low + (high - low) / 2` or widen the sum to `long`. Subtracting half the difference from `low` moves outside the range.
- **Returning a value where an index is required.** For a search-by-index problem, return `mid` when the target is found.
- **Forgetting the absent case.** Once `low > high`, no candidate remains; return the problem's not-found result.

## 9. Review prompts

1. Why does a sorted array let a comparison discard half of the candidates?
2. What exactly do `low` and `high` mean in the inclusive template?
3. Why do we exclude `mid` after checking it?
4. What does `low > high` tell us?
5. Why does the search take logarithmic time while using constant extra space?
