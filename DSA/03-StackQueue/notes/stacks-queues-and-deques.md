# Stacks, queues, and deques

Start with the order in which items are added and removed. This determines whether a stack, queue, or deque fits a problem.

## A few basic terms

An item is **added** when it enters a data structure and **removed** when it leaves. A **front** or **top** is an exposed position where an item can be inspected or removed. A **deque** (pronounced “deck”) is a double-ended queue: items can be added and removed at either end.

## Stack: last in, first out

Imagine a pile of books. You put a new book on top, and usually take the top book off first. The most recently added item leaves first. This is called **last in, first out (LIFO)**.

The main operations are:

- `push(x)`: put `x` on top.
- `pop()`: remove and return the top item.
- `peek()`: look at the top without removing it.

Example, reading operations from top to bottom:

```text
push A  → [A]
push B  → [A, B]       B is on top
push C  → [A, B, C]    C is on top
pop()   → returns C; [A, B]
peek()  → returns B; [A, B]   (nothing is removed)
```

Use a stack when the newest unresolved item should be handled first. Matching nested brackets is a simple example: the last bracket opened must be the first one closed.

## Queue: first in, first out

Imagine people waiting in a line. New people join at the back; the person who has waited longest leaves from the front. This is **first in, first out (FIFO)**.

The main operations are:

- `offer(x)`: add `x` at the back.
- `poll()`: remove and return the front item.
- `peek()`: look at the front without removing it.

Example:

```text
offer A  → front [A] back
offer B  → front [A, B] back
offer C  → front [A, B, C] back
poll()   → returns A; front [B, C] back
```

Use a queue when items should be handled in arrival order, such as recent requests or tasks waiting to be processed.

## Deque: both ends are available

A deque lets you add or remove an item at either the front or back. It can act like a stack, a queue, or a candidate list that removes items from both ends for different reasons.

In Java, `Deque<E>` describes the operations and `ArrayDeque<E>` is a common implementation. For stack behavior, use one end consistently. For queue behavior, add at the back and remove from the front. For a sliding-window maximum, new candidates enter at the back, weaker candidates leave from the back, and expired candidates leave from the front.

## How much work do operations take?

Let `n` mean the number of items. With an array-backed deque such as Java's `ArrayDeque`, adding or removing at an end is usually O(1): the work stays roughly constant as the collection grows. O(n) means work grows in proportion to the number of items, such as scanning an array once. In the algorithms here, each index is added once and removed at most once, so a loop that contains a `while` can still take O(n) total time.

Some designs are **amortized O(1)**: an occasional operation may move many items, but if we spread that work across a long sequence of operations, the average work per operation stays constant. Queue-using-two-stacks is one example.

## Building one structure from another

### Queue using two stacks

Use an input stack for new items and an output stack for removals. When the output stack is empty, move all input items to it. The reversal makes the oldest item the output stack's top. Do not transfer while the output stack still has items, or newer items could get ahead of older ones.

For `A`, then `B`:

```text
input stack:  [A, B]   (B is on top)
transfer:     output stack [B, A]   (A is on top)
remove:       returns A
```

Each item transfers at most once, so the operations are amortized O(1). One particular removal may trigger an O(n) transfer.

### Stack using a queue

One approach rotates the queue after each push so that the newest item moves to the front. Then pop and peek are simple. The trade-off is that push may move all existing items. A design question often asks which operation should be cheap.

## Circular queue

A fixed-size array can be reused by wrapping its indices back to the start. If the capacity is `C`, the next insertion position can be calculated with:

```text
tail = (head + size) % C
```

Here `head` is the front index and `size` is the number of stored items. After removing the front, advance it with `(head + 1) % C`.

For a capacity of 3, after filling slots 0, 1, and 2, removing the front moves `head` to 1. The next insertion position wraps to slot 0. Tracking `size` tells you whether the queue is empty (`size == 0`) or full (`size == C`).

## Choosing a structure

| Need | Structure | Example |
|---|---|---|
| Newest item first | Stack | Nested brackets, undo, cancellation |
| Oldest item first | Queue | Requests arriving over time |
| Work at either end | Deque | Sliding windows, custom ordering |
| Current maximum or minimum plus expiry | Monotonic deque | Window maximum or window with a range limit |

## Practice links

[Implement Queue using Stacks](../MyQueue.java) · [Implement Stack using Queues](../MyStack.java) · [Design Circular Queue](../MyCircularQueue.java) · [Number of Recent Calls](../RecentCounter.java)
