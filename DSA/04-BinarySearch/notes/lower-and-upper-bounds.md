# Lower Bound and Upper Bound

## 1. Why search for a boundary?

An exact search can stop as soon as it finds a matching value. Sometimes we need a particular position in the sorted array instead:

- the first value that is at least a target;
- the first value that is greater than a target;
- the position where a value should be inserted;
- the range containing all copies of a value.

These positions are called **lower bound** and **upper bound**. Each is found with binary search by choosing which midpoint values qualify as a boundary candidate.

## 2. Lower bound

The **lower bound** of `target` is the first index whose value is greater than or equal to the target:

```text
first index i such that nums[i] >= target
```

If no value qualifies, the lower bound is `nums.length`, the position just after the last element.

For `[1, 3, 3, 5, 8]`:

| Target | Lower bound | Why |
|---:|---:|---|
| `3` | `1` | Index 1 is the first value at least 3. |
| `4` | `3` | Value 5 at index 3 is the first value at least 4. |
| `9` | `5` | No value is at least 9, so return the array length. |

When `nums[mid] >= target`, `mid` could be the first qualifying index. Save it, then continue left to see if an earlier index qualifies. When `nums[mid] < target`, `mid` and every earlier index are too small, so continue right.

```java
int answer = nums.length;
int low = 0;
int high = nums.length - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] >= target) {
        answer = mid;
        high = mid - 1;
    } else {
        low = mid + 1;
    }
}

return answer;
```

## 3. Upper bound

The **upper bound** of `target` is the first index whose value is strictly greater than the target:

```text
first index i such that nums[i] > target
```

If no value is greater than the target, the upper bound is `nums.length`.

For `[1, 3, 3, 5, 8]` and target `3`, the upper bound is index `3`, where the value is `5`. Both copies of `3` are before this boundary.

The algorithm is almost identical to lower bound. Change the qualification condition from `nums[mid] >= target` to `nums[mid] > target`. When `nums[mid] <= target`, that midpoint is not past the target, so continue right.

```java
int answer = nums.length;
int low = 0;
int high = nums.length - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] > target) {
        answer = mid;
        high = mid - 1;
    } else {
        low = mid + 1;
    }
}

return answer;
```

The reusable implementations are in [`../BinarySearchBounds.java`](../BinarySearchBounds.java).

## 4. Connection to Search Insert Position

LeetCode 35 asks for the current index of `target`, or the place where it belongs if absent. That insertion position is the lower bound: the first index whose value is at least the target.

For example, in `[1, 3, 5, 6]`:

- target `5` has lower bound `2`, its existing index;
- target `2` has lower bound `1`, between `1` and `3`;
- target `7` has lower bound `4`, which equals the array length.

Thus, Search Insert Position can be solved with the lower-bound loop. The saved candidate is important: when a midpoint qualifies, a smaller index may also qualify, so save the midpoint and keep searching left.

See [Search Insert Position](../SearchInsertPosition.java).

## 5. Counting duplicates with both boundaries

For a sorted array, all copies of a value form one contiguous block. The lower bound points to the first copy; the upper bound points just after the last copy.

For `[1, 3, 3, 5, 8]` and target `3`:

```text
lowerBound(3) = 1
upperBound(3) = 3
count = 3 - 1 = 2
```

If the target is absent, both bounds give its insertion position and their difference is zero. The same `upperBound(target) - lowerBound(target)` calculation therefore counts occurrences whether or not the target exists.

## 6. What each boundary loop maintains

Both reference methods use an inclusive range `[low, high]` and initialize `answer` to `nums.length`.

- `answer` is the best qualifying boundary found so far. The initial array length represents “no qualifying element seen yet.”
- If `mid` qualifies, save it and search left. The left side may contain an earlier qualifying index.
- If `mid` does not qualify, search right. Sorted order shows that values farther left cannot qualify either.
- When `low > high`, no unexamined candidate remains. The saved answer is the first qualifying index, or the array length if none qualified.

Both take **O(log n)** time and **O(1)** auxiliary space.

## 7. Common mistakes

- **Stopping at any equality.** That finds a match but may not find the first copy. A lower-bound search saves a qualifying midpoint and keeps searching left.
- **Using `>=` for upper bound.** Upper bound is strictly greater, so its condition is `nums[mid] > target`.
- **Forgetting the end boundary.** The correct answer can be `nums.length`, after the final element.
- **Not saving a qualifying midpoint.** If you move left without saving it, you can lose the best candidate found so far.
- **Using this reasoning on an unsorted array.** The left/right elimination only works because values are sorted.

## 8. Review prompts

1. In one sentence, how do lower bound and upper bound differ?
2. Why does lower bound keep searching left after `nums[mid] >= target`?
3. What does a boundary equal to `nums.length` mean?
4. How do lower and upper bounds count repeated values?
5. Which boundary does Search Insert Position return, and why?
