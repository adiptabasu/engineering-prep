# Binary Search Pattern Reference

This guide is a map of the main ways binary search appears in problem solving. It starts with the ideas already used in this section, then previews patterns to study in later problems. A preview is a reference, not a claim that the pattern has been practiced yet.

## 1. The common idea: a one-way boundary

Binary search needs a property that changes in one direction across the search space. For a sorted array and a target, values to the left of the target are too small and values to the right are too large. That lets a comparison eliminate half the candidates.

For other problems, the property might be whether a capacity is sufficient, whether a time limit allows completion, or whether a position is on the rising or falling side of a peak. Look for a boundary between two regions:

```text
false false false | true true true
                  ^ first true
```

or the reverse arrangement:

```text
true true true | false false false
               ^ last true is just before this point
```

The exact predicate changes by problem, but the important requirement is that once the predicate changes, it does not switch back and forth.

## 2. Exact search in a sorted array

Use exact binary search when the task asks whether a particular value is present, or asks for its index. Keep an inclusive range `[low, high]`. Compare the midpoint, return if equal, and otherwise discard the side that cannot contain the target.

The full explanation, overflow-safe midpoint, and iterative and recursive versions are in [Binary Search Fundamentals](binary-search-fundamentals.md). The saved practice problem is [LeetCode 704](../BinarySearch.java).

## 3. Lower bound and upper bound

Use a boundary search when the task asks for the first position meeting a comparison:

- **Lower bound:** first index with `nums[i] >= target`.
- **Upper bound:** first index with `nums[i] > target`.

Both boundaries can equal `nums.length`, meaning the qualifying value would lie after the array. With duplicates, the lower bound is the first copy and the upper bound is just after the final copy. Subtracting them counts copies of the target.

The worked examples, loops, and explanations are in [Lower Bound and Upper Bound](lower-and-upper-bounds.md), with Java helpers in [BinarySearchBounds.java](../BinarySearchBounds.java). Search Insert Position is a lower-bound problem: [LeetCode 35](../SearchInsertPosition.java).

### First and last occurrence

For a sorted array that may contain duplicates:

```text
first occurrence = lowerBound(target)
last occurrence  = upperBound(target) - 1
```

First check that `first occurrence < nums.length` and `nums[first occurrence] == target`. Otherwise the target is absent, so the answer is `[-1, -1]`. The count of occurrences is `upperBound(target) - lowerBound(target)`.

This gives a natural next exercise: [LeetCode 34 — Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/).

## 4. Search in a rotated sorted array — preview

A rotated sorted array was sorted and then shifted around a pivot. For example, `[4, 5, 6, 7, 0, 1, 2]`. With distinct values, at least one side of the midpoint is still sorted. Identify that side, check whether the target lies inside its value range, and keep that side only when it can contain the target.

```java
int low = 0;
int high = nums.length - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] == target) {
        return mid;
    }

    if (nums[low] <= nums[mid]) { // left half is sorted
        if (nums[low] <= target && target < nums[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    } else { // right half is sorted
        if (nums[mid] < target && target <= nums[high]) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
}

return -1;
```

This version assumes distinct values. Duplicates can make both ends and the midpoint equal, hiding which side is sorted; some variants need to shrink an endpoint, and worst-case time can become `O(n)`.

## 5. Binary search on an answer — preview

Sometimes the answer is not an array index. The problem asks for the smallest or largest numeric value that makes a condition possible. If feasibility changes monotonically as the candidate value changes, binary search can find the boundary.

Example shape: “What is the minimum capacity that can complete the work?” If a capacity works, every larger capacity works too. Feasibility has the form `false ... false, true ... true`; search for the first `true`.

```java
long low = smallestPossibleAnswer;
long high = largestPossibleAnswer;
long answer = high;

while (low <= high) {
    long mid = low + (high - low) / 2;

    if (feasible(mid)) {
        answer = mid;       // candidate works; try a smaller one
        high = mid - 1;
    } else {
        low = mid + 1;      // candidate fails; try a larger one
    }
}

return answer;
```

This version assumes the upper bound is known to be feasible. If the problem allows no feasible answer, use a suitable “not found” sentinel and check for it, or handle infeasibility before returning. For the **largest** value that is still feasible, use the opposite monotonic direction: when `mid` works, save it and search right; when it fails, search left. Define the possible answer bounds and prove the direction of the feasibility predicate before coding. Answer values may require `long`, even when array indices use `int`.

## 6. Binary search with a matrix — preview

First check exactly how the matrix is sorted. Two common conditions lead to different strategies.

### Entire matrix is sorted in row-major order

If each row is sorted and the first value of each row is greater than the last value of the previous row, the matrix acts like one sorted array. For `rows` by `cols`, map a virtual index `mid` back to a cell:

```java
int row = mid / cols;
int col = mid % cols;
int value = matrix[row][col];
```

Binary search virtual indices from `0` through `rows * cols - 1`.

### Each row and column is sorted, but row-major order is not guaranteed

The flattened binary-search assumption may fail. One option is to binary search each row in `O(rows * log(cols))`. Another is to start at the top-right corner: if the current value is too large, move left; if too small, move down. That staircase search takes `O(rows + cols)`.

## 7. Find a peak or mountain boundary — preview

Some arrays are not globally sorted, but their direction around a peak provides a one-way decision. If `nums[mid] < nums[mid + 1]`, the slope is rising, so a peak lies to the right. Otherwise a peak lies at `mid` or to the left.

```java
int low = 0;
int high = nums.length - 1;

while (low < high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] < nums[mid + 1]) {
        low = mid + 1;
    } else {
        high = mid;
    }
}

return low; // low == high at a peak index
```

Notice the different interval behavior: `high = mid` keeps `mid` as a possible peak, and the loop uses `low < high`. Do not mix this template's updates with the inclusive exact-search template. The problem's constraints matter; plateaus or multiple peaks may require different reasoning.

## 8. Search when the upper bound is unknown — preview

If values are sorted but the search range has no known end, first check index `0`. If that is too small, grow a bound exponentially by checking indices `1, 2, 4, 8, ...` until reaching or passing the target's possible position, then binary search inside the last interval. The range grows by a factor of two, so locating an interval takes logarithmic probes; binary search then locates the value within it. This pattern is useful for an unbounded or hidden-length sorted source, not an ordinary Java array whose length is known.

## 9. Midpoint, interval, and stopping rules

Choose one interval convention and keep its initialization, condition, and updates consistent:

| Convention | Typical initialization | Loop condition | When moving right |
|---|---|---|---|
| Inclusive candidates `[low, high]` | `low = 0`, `high = n - 1` | `low <= high` | `low = mid + 1` |
| Half-open candidates `[low, high)` | `low = 0`, `high = n` | `low < high` | If `mid` fails, use `low = mid + 1`; if it qualifies as a boundary, keep it with `high = mid` |

For example, lower bound can use a half-open range because `n` is a valid answer boundary even though it is not an array index:

```java
int low = 0;
int high = nums.length; // excluded endpoint; may be the returned boundary

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

Here `low` is the first position not yet proven too small, and `high` is excluded from the active interval. When the interval becomes empty (`low == high`), both point to the lower-bound insertion position. Do not mix these updates with the inclusive `answer` template.

For nonnegative Java array indices, `low + (high - low) / 2` avoids overflow from `low + high`. Another option is `(int) (((long) low + high) / 2)`. For large numeric answer ranges, use `long` bounds and a `long` midpoint. The midpoint formula must match the interval and preserve progress.

In Java, integer division rounds toward zero. With nonnegative bounds, `(high - low) / 2` gives the lower middle. If the problem needs an upper middle while `low < high`, use `low + (high - low) / 2 + (high - low) % 2`. This avoids the possible overflow in `high - low + 1` when using `low + (high - low + 1) / 2` with very large `int` bounds.

## 10. Complexity and implementation choices

- **Time:** Usually `O(log n)` when each decision cuts the candidate range by a constant factor.
- **Iterative extra space:** `O(1)`.
- **Recursive extra space:** `O(log n)` call stack for a halving search.
- **Binary search on answer:** `O(log R * C)`, where `R` is the size of the answer range and `C` is the cost of checking feasibility once.
- **Staircase matrix search:** `O(rows + cols)` because each move eliminates a row or column.

Use recursion when it clarifies a small divide-and-conquer structure; an iterative loop is often simpler for index boundaries and avoids call-stack use.

## 11. Quick pattern recognition

| Problem clue | Possible pattern | First question to ask |
|---|---|---|
| Sorted array; find a value | Exact search | Which side can be ruled out? |
| First value meeting `>=` or `>` | Lower / upper bound | Is equality included in the boundary? |
| Duplicates; first and last positions | Two boundaries | Can the range be `[lower, upper)`? |
| Sorted then rotated | Search rotated array | Which half is sorted? |
| Minimum capacity, time, speed, or maximum minimum distance | Binary search on answer | Does feasibility change only once? |
| Matrix with an ordering guarantee | Flattened or staircase search | Is the whole matrix row-major sorted, or only rows and columns? |
| Rising then falling values | Peak / mountain search | What does the local slope tell us? |
| Sorted source with unknown end | Exponential then binary search | How can we find a finite bracket first? |

## 12. Study order and references

1. Learn the inclusive exact-search invariant in [Binary Search Fundamentals](binary-search-fundamentals.md).
2. Learn first-qualifying boundaries in [Lower Bound and Upper Bound](lower-and-upper-bounds.md).
3. Practice lower and upper bounds together with [Find First and Last Position](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/).
4. Treat the rotated-array, answer-space, matrix, peak, and unknown-bound sections above as previews. Study each in depth when a problem calls for it; do not memorize a template without proving the property that makes the search one-way.
