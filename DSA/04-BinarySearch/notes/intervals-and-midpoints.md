# Binary Search Intervals and Midpoints

## 1. Why the range convention matters

`low` and `high` describe a search interval. The loop condition and the way the bounds move depend on whether both ends are candidates or the right end is excluded. Mixing rules from the two conventions is a common source of infinite loops and skipped indices.

The current LeetCode 704 and 35 solutions use an inclusive range. This note also shows the half-open form because it is common for boundary searches.

## 2. Inclusive range: `[low, high]`

Both `low` and `high` are candidate indices. For an array of length `n`, initialize `low = 0` and `high = n - 1`. Search while `low <= high` so the final one-element range is still checked.

For exact search, after checking `mid`, exclude it from the next range:

```java
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
```

When the loop stops, `low > high`; no candidate remains. See [Binary Search Fundamentals](binary-search-fundamentals.md) for the full reasoning.

For lower bound in this convention, use an answer variable because `nums.length` can be the boundary even though it is not an array index. If `nums[mid] >= target`, save `mid` and move left with `high = mid - 1`; otherwise move right.

## 3. Half-open range: `[low, high)`

The left endpoint is included and the right endpoint is excluded. For a lower-bound search, initialize `low = 0`, `high = nums.length`. The value `nums.length` is allowed as a result boundary, but never read as an array element.

```java
int low = 0;
int high = nums.length;

while (low < high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] < target) {
        low = mid + 1;
    } else {
        high = mid;
    }
}

return low;
```

Here the active indices are from `low` through `high - 1`. Since `low < high` before computing `mid`, the midpoint is less than `high`, and therefore is a valid array index. If `nums[mid]` is too small, exclude it with `low = mid + 1`. Otherwise keep the possible boundary at `mid` by setting `high = mid`.

The loop stops at `low == high`. That position is the lower bound, including the possible end boundary `nums.length`.

## 4. Compare the update rules

| Question | Inclusive `[low, high]` | Half-open `[low, high)` lower bound |
|---|---|---|
| Initial high | `n - 1` | `n` |
| Continue while | `low <= high` | `low < high` |
| Midpoint too small | `low = mid + 1` | `low = mid + 1` |
| Midpoint qualifies | Save it, then `high = mid - 1` | Keep it, then `high = mid` |
| Meaning when done | No candidate remains; use saved answer | `low == high` is the boundary |

Both can find the same lower bound. Pick one convention for a given implementation and keep its initialization, condition, and updates together.

## 5. Midpoint overflow

The expression `(low + high) / 2` can overflow before division if `low + high` exceeds the largest `int`. For nonnegative array indices with `low <= high`, use:

```java
int mid = low + (high - low) / 2;
```

`high - low` is a nonnegative distance within the `int` range for valid Java array indices. Another option is to widen the sum before adding:

```java
int mid = (int) (((long) low + high) / 2);
```

The cast is safe because the average of two valid `int` indices is also a valid index. For a large numeric answer range, declare the bounds and midpoint as `long`; also ensure the difference itself stays within the `long` range.

Do not use `low - ((high - low) / 2)` for an ascending interval: subtracting the distance moves left of `low`, not between `low` and `high`.

## 6. Lower middle and upper middle

`low + (high - low) / 2` chooses the lower middle when there are two middle candidates. That is usually right for shrinking an inclusive exact-search range or a half-open lower-bound interval.

Some loops need the upper middle to guarantee progress when keeping `mid` as a candidate on the left side. With nonnegative `int` bounds, one overflow-aware expression is:

```java
int distance = high - low;
int upperMid = low + distance / 2 + distance % 2;
```

This is the ceiling of half the distance. The simpler `low + (high - low + 1) / 2` can overflow in the `+ 1` when the difference is already `Integer.MAX_VALUE`.

Do not switch to an upper middle just because it exists. First determine which midpoint is compatible with the update rules and needed progress.

## 7. Common failure patterns

- Using `low < high` with an inclusive range can skip the last candidate.
- Using `low <= high` with a half-open range can inspect the excluded endpoint.
- Setting `low = mid` when `mid` is the lower middle can leave the same range and loop forever.
- Setting `high = mid - 1` in a half-open lower-bound loop can skip a valid boundary.
- Calculating the midpoint as `(low + high) / 2` can overflow.
- Moving a bound without saying whether `mid` remains a possible answer makes the invariant hard to maintain.

## 8. Review prompts

1. Which indices are included in `[low, high]`? Which are included in `[low, high)`?
2. Why do exact search and half-open lower bound use different updates for a qualifying midpoint?
3. What value can a lower bound return that is not a valid array index?
4. Which midpoint formula avoids overflowing `low + high`?
5. When might the upper middle be useful?
