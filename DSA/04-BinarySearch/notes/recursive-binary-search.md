# Recursive Binary Search

## 1. What recursion means here

Recursion is when a method solves a problem by calling itself on a smaller version of that problem. Binary search naturally has this shape: inspect the middle of a range, then continue searching only the left or right part.

Each call receives an inclusive candidate range `[low, high]`. The target can only be in that range. A call either finds the target, passes a smaller range to another call, or detects that the range is empty.

## 2. The recursive implementation

```java
public int searchRecursive(int[] nums, int target) {
    return searchRange(nums, target, 0, nums.length - 1);
}

private int searchRange(int[] nums, int target, int low, int high) {
    if (low > high) {
        return -1;
    }

    int mid = low + (high - low) / 2;

    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        return searchRange(nums, target, mid + 1, high);
    } else {
        return searchRange(nums, target, low, mid - 1);
    }
}
```

The public method starts the search over the whole array. The private helper carries the changing range. Keeping the helper private makes its extra `low` and `high` parameters an implementation detail.

## 3. The base case prevents invalid access

The base case is:

```java
if (low > high) {
    return -1;
}
```

It means the candidate range is empty: every possible index has already been ruled out. It must be checked before reading `nums[mid]`; otherwise an absent target could eventually lead to an invalid index.

For an array of length zero, the initial range is `low = 0`, `high = -1`. The first call immediately reaches this base case and returns `-1`.

## 4. Each recursive call must make progress

After checking `mid`, that index is known not to contain the target. The next call must exclude it:

- If `nums[mid] < target`, call with `mid + 1` as the new low index.
- If `nums[mid] > target`, call with `mid - 1` as the new high index.

Passing `mid` again as the new boundary can repeat the same range and recurse forever. The range gets smaller on every call because the array is sorted and the midpoint is excluded.

## 5. A short trace

Search for `16` in `[2, 5, 8, 12, 16, 23, 38]`:

| Call | Range | Midpoint | Next action |
|---:|---|---|---|
| 1 | `0..6` | index `3`, value `12` | Search `4..6` |
| 2 | `4..6` | index `5`, value `23` | Search `4..4` |
| 3 | `4..4` | index `4`, value `16` | Return index `4` |

The recursive calls visit the same midpoint sequence as the iterative loop. Recursion changes how the smaller range is represented; it does not change the binary-search reasoning.

## 6. Complexity and when to use it

Each call cuts the candidate range roughly in half, so the time complexity is **O(log n)**. The active calls wait on the call stack until a result returns. There are at most about `log₂(n)` calls, so auxiliary space is **O(log n)**.

The iterative version uses **O(1)** auxiliary space and is often simpler for boundary searches. Recursion can make a divide-and-conquer explanation feel direct, but for binary search it is a style choice rather than an asymptotic time improvement.

## 7. Common mistakes

- **No base case:** absent targets can recurse until a stack overflow or invalid access.
- **Base case after indexing:** an empty range must be handled before calculating or using a midpoint.
- **Not shrinking the range:** the next call must use `mid + 1` or `mid - 1` for exact search.
- **Losing the returned result:** each recursive branch must `return` the result of the next call.
- **Confusing recursion with a different search:** the sorted-order property and midpoint decisions are the same as in the iterative algorithm.

## 8. Review prompts

1. What does `low > high` mean?
2. Why must the base case run before reading `nums[mid]`?
3. How does each recursive call guarantee progress?
4. Why is recursive space `O(log n)` while iterative space is `O(1)`?
