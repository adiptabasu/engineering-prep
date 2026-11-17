# Searching a Matrix

## 1. Inspect the exact ordering rule

“Sorted matrix” can mean different things. Before applying binary search, check whether the values are globally sorted in row-major order or whether only each row and column is sorted. Those guarantees lead to different algorithms.

### Row-major sorted

Every row is sorted, and the first value in a row is greater than the last value in the previous row. Example:

```text
1   3   5   7
10 11  16  20
23 30  34  60
```

Reading left to right, top to bottom gives one sorted sequence. We can binary search that virtual one-dimensional array.

### Rows and columns sorted independently

Each row increases left to right, and each column increases top to bottom, but a later row's first value need not be greater than the previous row's last. Example:

```text
1   4   7  11
2   5   8  12
3   6   9  16
```

Flattening this matrix does not produce a sorted array, so ordinary binary search over flattened indices is invalid.

## 2. Row-major matrix as one sorted array

For `rows` by `cols`, a virtual index maps to a matrix cell:

```java
int row = index / cols;
int col = index % cols;
```

For example, with 4 columns, virtual index `6` maps to row `1`, column `2` (`matrix[1][2]`).

```java
public boolean searchMatrix(int[][] matrix, int target) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    long low = 0;
    long high = (long) rows * cols - 1;

    while (low <= high) {
        long mid = low + (high - low) / 2;
        int row = (int) (mid / cols);
        int col = (int) (mid % cols);
        int value = matrix[row][col];

        if (value == target) {
            return true;
        } else if (value < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return false;
}
```

The `long` virtual index makes the `rows * cols` multiplication safe from `int` overflow. This code assumes the matrix has at least one row and each row has at least one element; handle empty dimensions first if the problem allows them.

If there are `m` rows and `n` columns, the time complexity is **O(log(mn))**, and extra space is **O(1)**.

## 3. Matrix with rows and columns sorted

When the entire matrix is not row-major sorted, one option is to binary search each row. This takes **O(m log n)** time for `m` rows and `n` columns.

If both rows and columns increase, a staircase search can often do better. Start at the top-right cell:

- If the value is too large, everything below it in that column is at least as large, so move left.
- If the value is too small, everything left of it in that row is at most as small, so move down.
- If it matches, return true.

```java
public boolean searchRowsAndColumnsSorted(int[][] matrix, int target) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int row = 0;
    int col = cols - 1;

    while (row < rows && col >= 0) {
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] > target) {
            col--;
        } else {
            row++;
        }
    }

    return false;
}
```

Each move removes one whole row or column from consideration, so the time complexity is **O(m + n)** and extra space is **O(1)**.

## 4. Choosing between the methods

| Matrix guarantee | Useful method | Time |
|---|---|---:|
| Rows sorted, and each row begins after the previous row ends | Flattened binary search | `O(log(mn))` |
| Each row sorted, no ordering promise between rows | Binary search each row | `O(m log n)` |
| Every row and every column sorted | Staircase search | `O(m + n)` |

Do not infer global row-major order just because rows and columns are individually sorted.

## 5. Common mistakes

- **Flattening without the global ordering guarantee.** The virtual sequence must be sorted.
- **Swapping row and column calculations.** Use division by `cols` for the row and remainder by `cols` for the column.
- **Off-by-one virtual range.** The last virtual index is `rows * cols - 1`.
- **Integer overflow in cell count.** Widen before multiplying: `(long) rows * cols`.
- **Starting staircase search in the wrong corner.** The top-right or bottom-left corner provides one row and one column to eliminate per move.
- **Ignoring empty matrices.** Check dimensions before reading `matrix[0]` when empty inputs are allowed.

## 6. Related problems

- [LeetCode 74 — Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) uses the global row-major ordering.
- [LeetCode 240 — Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) uses independently sorted rows and columns.
