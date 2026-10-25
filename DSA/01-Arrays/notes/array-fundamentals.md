# Array fundamentals

## What an array is

An array is an ordered collection whose elements are addressed by position. The length tells how many positions contain elements. Many implementations store elements in contiguous memory; a dynamic array may reserve extra capacity and occasionally move elements when it grows.

Arrays provide direct indexed access because the location of an element can be computed from its index. This makes arrays useful when a problem repeatedly reads or updates positions. It does not make searching for an unknown value constant time.

## Basic operations

| Operation | Typical cost |
|---|---:|
| Read or update values[index] | O(1) |
| Scan all n elements | O(n) |
| Search an unsorted array | O(n) |
| Search a sorted array by binary search | O(log n) |
| Insert or remove at an arbitrary index | O(n) due to shifting |
| Append to a dynamic array | Amortized O(1); an individual resize can cost O(n) |

The costs assume the usual array or dynamic-array model. A fixed-capacity array cannot append beyond its capacity without a different storage strategy.

## Traversing an array

A forward traversal examines each index once.

    for i from 0 through length(values) - 1:
        process values[i]

The loop has O(n) time. It uses O(1) extra space if process does not store a result proportional to the input.

For a range from left through right inclusive, the number of elements is right - left + 1. Be consistent about inclusive endpoints; many off-by-one errors come from mixing them with half-open ranges.

## Inserting and removing by index

To insert at index i in a dynamic array with spare capacity, shift the suffix one place right, from the end toward i. Shifting forward would overwrite values that have not moved yet.

    for position from size down to i + 1:
        values[position] = values[position - 1]
    values[i] = newValue
    size = size + 1

To remove at i, shift the later items left.

    for position from i through size - 2:
        values[position] = values[position + 1]
    size = size - 1

Both operations take O(n) time in the worst case because many elements may move. Inserting at the end of a dynamic array is usually amortized O(1).

## Why insertion shifts from the end

Suppose the logical contents are [A, B, C] and X is inserted at index 1. Move C from index 2 to index 3, then B from index 1 to index 2, then write X at index 1. Moving from the end backward preserves every value before it is copied. The result is [A, X, B, C].

Removal works in the opposite direction: after removing index 1, move each later element one position toward the front. Either operation may move a number of elements proportional to n, which is why indexed insertion and removal are O(n).

## Fixed arrays and dynamic arrays

A fixed array has a set capacity. A dynamic array grows when it runs out of room, commonly by allocating a larger block and copying its elements. If capacity grows geometrically, a sequence of appends has amortized O(1) cost per append: occasional O(n) copies are spread over many cheap appends.

Size and capacity are different. Size is the number of stored elements; capacity is the amount of storage currently reserved. Only indices from zero through size - 1 contain logical elements.

## Reasoning checks and pitfalls

- Is the input sorted? Sorting or binary-search reasoning depends on that property.
- Is the requested range contiguous, or can elements be skipped?
- Are indices valid for the current size, not merely for capacity?
- Does an in-place shift run in the direction that preserves unread values?
- Check empty arrays, one-element arrays, first and last positions, and indices at the size boundary.
- Do not assume inserting in the middle is O(1) just because accessing an index is O(1).

## Related patterns

Array fundamentals support the patterns in this folder: [hashing and frequency](hashing-and-frequency.md), [two pointers](two-pointers.md), [sliding window](sliding-window.md), and [prefix sums](prefix-sums-and-remainders.md).
