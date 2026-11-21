# Single Element in a Sorted Array

## 1. The pair structure

The array is sorted. Every value appears exactly twice except one value, which appears once. For example:

```text
[1, 1, 2, 3, 3, 4, 4, 8, 8]
```

Before the single value, equal pairs begin at even indices:

```text
indices:  0  1  2  3  4  5  6  7  8
values:  [1, 1, 2, 3, 3, 4, 4, 8, 8]
          pair  single  pair  pair  pair
```

The single value takes one index from the usual pair pattern. After it, the pairs shift: pair starts are now odd indices. That change in alignment is what binary search detects.

## 2. Align the midpoint with a pair start

Choose a midpoint and move it to the nearest even index on its left:

```java
if (mid % 2 == 1) {
    mid--;
}
```

Now `mid` is even, so under the normal pair pattern it is the first index of a pair. Compare `nums[mid]` with `nums[mid + 1]`:

- If they are equal, the pair is intact. The single value must be to the right, so set `low = mid + 2`.
- If they differ, the normal pair alignment has already broken by `mid`. The single value is at `mid` or to the left; save `mid` and search earlier even indices with `high = mid - 2`.

Keep the candidate search on even indices, where a pair is expected to start. With an inclusive loop, save a mismatching aligned midpoint as a candidate, then search earlier even indices. If one candidate index remains, it is the singleton.

## 3. Worked example

Search in `[1, 1, 2, 3, 3, 4, 4, 8, 8]`:

| Step | Candidate range | Aligned midpoint | Comparison | Next range |
|---:|---|---:|---|---|
| 1 | `0..8` | `4` | `nums[4] = 3`, `nums[5] = 4`; mismatch | `0..2` |
| 2 | `0..2` | `0` | `nums[0] = nums[1] = 1`; pair intact | `2..2` |
| 3 | `2..2` | — | One index remains | singleton is at index `2` |

The only remaining index is `2`, containing the single value `2`.

## 4. Inclusive-loop implementation

```java
public int singleNonDuplicate(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    int singleIndex = -1;

    while (low <= high) {
        if (low == high) {
            singleIndex = low;
            break;
        }

        int mid = low + (high - low) / 2;
        if (mid % 2 == 1) {
            mid--;
        }

        if (nums[mid] == nums[mid + 1]) {
            low = mid + 2;
        } else {
            // Keep mid as a possible answer, then continue left. Once saved,
            // it does not need to remain in the active interval.
            singleIndex = mid;
            high = mid - 2;
        }
    }

    return nums[singleIndex];
}
```

This is an inclusive-loop version of the search. The saved LeetCode submission is [SingleElementInSortedArray.java](../SingleElementInSortedArray.java).

## 5. How the inclusive loop preserves candidates

The interval begins and remains aligned to even pair-start indices. If an aligned pair matches, both indices are known to be before the singleton, so search resumes at `mid + 2`. If it does not match, the singleton is at `mid` or earlier; save `mid` and set `high = mid - 2` to search earlier pair starts.

When `low == high`, that remaining index is the singleton. This check happens before reading `mid + 1`, so the last candidate is safe even when it is the final array index. If the saved `singleIndex` is updated earlier, it remains a candidate while the search continues left.

## 6. Complexity

Each comparison discards about half the candidate indices, so time is **O(log n)**. The algorithm uses only a few indices, so auxiliary space is **O(1)**.

## 7. Common mistakes

- **Comparing an unaligned midpoint with its neighbor.** Align `mid` to an even index first; otherwise it may be the second item of a pair.
- **Keeping an intact pair.** If `nums[mid] == nums[mid + 1]`, remove both by setting `low = mid + 2`.
- **Discarding a possible singleton at `mid`.** Save it before searching earlier pair starts.
- **Using this on an unsorted array.** Sorting order is what makes pair alignment reveal which half is affected.
- **Returning an index instead of a value.** This problem asks for the unique element itself, so return the value at the saved or final singleton index.

## 8. Review prompts

1. Why do pairs start at even indices before the singleton?
2. How does the singleton change pair alignment to its right?
3. Why does an intact pair let us discard both indices?
4. Why must a mismatch save `mid` before searching earlier pair starts?
5. What part of the prompt makes binary search possible?

## Related problem

[LeetCode 540 — Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/)
