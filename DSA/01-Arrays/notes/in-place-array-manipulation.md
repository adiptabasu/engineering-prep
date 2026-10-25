# In-place array manipulation

## What in-place means

An algorithm is in-place when it changes the input array directly and uses only a small, constant amount of extra storage, aside from any required output. The exact definition can vary: some descriptions allow O(log n) auxiliary stack space, while stricter ones require O(1). State which convention applies when space complexity matters.

In-place does not mean no memory is used. A few indices, a temporary value, or a Boolean flag still use O(1) extra space. It also does not automatically mean faster; the benefit is avoiding a second array or reducing allocation.

## Read and write pointers

For compaction, use one pointer to read every original value and another to mark where the next retained value belongs. The write pointer never moves ahead of the read pointer, so writing cannot destroy an unread element.

    write = 0
    for read from 0 through length(values) - 1:
        if shouldKeep(values[read]):
            values[write] = values[read]
            write = write + 1
    newLength = write

Afterward, the meaningful result is the prefix from index 0 through `newLength - 1`. Values beyond that prefix may be stale and should be ignored unless the problem asks to clear them.

This takes O(n) time and O(1) extra space. It is the central idea behind removing selected values, deduplicating a sorted array, and moving zeroes while preserving the order of other elements.

## Direction preserves unread values

Choose a traversal direction that protects values that have not yet been read:

- When shifting a suffix right to make room, copy from right to left.
- When shifting a suffix left to close a gap, copy from left to right.
- When compacting retained values toward the front, scan left to right and write no farther right than the current read position.

For example, to insert `X` at index 1 in `[A, B, C]`, move `C` to index 3, then `B` to index 2, and finally write `X` at index 1. Copying from the front would overwrite `B` before it was moved.

## Reversal and swapping

To reverse an array or a range in place, swap its outermost unprocessed pair and move both boundaries inward.

    left = start
    right = end
    while left < right:
        swap(values[left], values[right])
        left = left + 1
        right = right - 1

Each element is moved at most once, so the time is O(n) for a range of n elements and the extra space is O(1). The loop condition handles both even and odd lengths.

## Reasoning checks and pitfalls

- Is the original order required for retained values? If so, a forward read/write pass preserves it.
- Is the result defined by a new logical length, or must the backing array also be cleared?
- Does each write stay behind all unread input?
- Are the boundaries inclusive? For an empty range, `start > end`, so reversal performs no swaps.
- If the caller needs the original array afterward, an in-place mutation is not acceptable even if it uses less space.

## Complexity

Compaction, shifting, and reversal generally take O(n) time. With a fixed number of indices and temporary values, they use O(1) extra space. Returning a separate array changes the space cost to O(n).
