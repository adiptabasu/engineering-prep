# 2D arrays and matrices

## Model and indexing

A rectangular 2D array has rows and columns. With zero-based indices, `matrix[row][column]` identifies a cell. For a rectangular matrix with `rows` rows and `columns` columns, valid indices satisfy:

    0 <= row < rows
    0 <= column < columns

Do not assume the matrix is square. A jagged collection may have a different number of columns in each row, so a column bound must come from the particular row.

## Traversal

Visiting each cell once takes O(rows * columns) time.

    for row from 0 through rows - 1:
        for column from 0 through columns - 1:
            process(matrix[row][column])

The outer loop chooses a row; the inner loop completes that row before advancing. In a row-major representation, this order also tends to access neighboring memory locations together, though the logical indexing model does not require a particular storage layout.

To traverse columns first, reverse the loop order. This is valid for rectangular matrices only when each visited row has that column. For jagged rows, use each row's own length or choose a traversal that does not assume equal widths.

## Neighbor movement and boundaries

For a cell `(r, c)`, its four orthogonal neighbors are `(r - 1, c)`, `(r + 1, c)`, `(r, c - 1)`, and `(r, c + 1)`. Check bounds before reading a neighbor.

    for each (rowDelta, columnDelta) in directions:
        nextRow = row + rowDelta
        nextColumn = column + columnDelta
        if 0 <= nextRow < rows and 0 <= nextColumn < columns:
            process(matrix[nextRow][nextColumn])

Diagonal movement adds the four combinations of row and column changes. The chosen direction set is part of the problem's rules; do not assume diagonals count as adjacent.

## Common traversal shapes

- **Rows or columns:** Useful for row totals, column totals, and checking each line.
- **Main diagonals:** In a square matrix, the main diagonal has `row == column`; the opposite diagonal has `row + column == size - 1`.
- **Boundary layers:** Track top, bottom, left, and right boundaries. Before traversing the bottom row or left column, check that the corresponding boundary still exists; a single row or column can otherwise be visited twice.
- **Four-direction exploration:** Maintain a visited structure or mutate cells only when the problem permits it, so a cell is not processed repeatedly.

## Space and complexity

Reading or writing every cell is O(rows * columns) time. A few loop indices use O(1) extra space. A separate visited matrix uses O(rows * columns); a queue or stack for a traversal can also grow to that size.

## Reasoning checks and pitfalls

- Is the shape rectangular, square, or jagged?
- Are dimensions zero, or can the matrix have an empty row?
- Does the problem define four-way or eight-way neighbors?
- Are row and column swapped in a boundary check?
- For spiral or layer traversals, can the remaining region collapse to one row or one column?
- Does the task permit modifying the input matrix?
